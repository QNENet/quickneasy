package com.qnenet.system;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qnenet.addons.QVaadinAddonInfo;
import com.qnenet.quickneasyutils.QAddon;
import com.qnenet.utils.QNEPaths;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteData;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.jar.JarFile;

@Service
public class QSystem {

    private Path addonsPath;
    private final Logger log = LoggerFactory.getLogger(QSystem.class);

    // Rest of the code...
    private final Map<String, Object> plugins = new HashMap<>();
    private final Map<String, URLClassLoader> pluginClassLoaders = new HashMap<>();
    private final Map<String, QAddon> addonInstances = new HashMap<>();

    private final AtomicBoolean running = new AtomicBoolean(false);
    private Thread watchThread;
    // private RouteConfiguration routeConfig;
    private Map<String, QVaadinAddonInfo> vaadinAddons;
    private boolean isNew = false;

    @PostConstruct
    public void startUp() {
        // routeConfig = RouteConfiguration.forApplicationScope();

        addonsPath = QNEPaths.getAddonsPath();
        if (Files.notExists(addonsPath)) {
            try {
                isNew = true;
                Files.createDirectories(addonsPath);
                log.info("Addons directory created: {}", addonsPath);
            } catch (IOException e) {
                log.error("Error creating addons directory", e);
                // e.printStackTrace();
            }
        }

        running.set(true);
        watchThread = Thread.ofVirtual().start(this::watchDirectory);
        // Load existing addons
        File[] addonFiles = addonsPath.toFile().listFiles((dir, name) -> name.endsWith(".jar"));
        if (addonFiles != null) {
            for (File addonFile : addonFiles) {
                log.info("Loading existing addon: {}", addonFile.getName());
                loadJar(addonFile);
            }
        }

    }

    @PreDestroy
    public void stopWatching() {
        log.info("Stopping JarWatcherService...");
        running.set(false);
        if (watchThread != null) {
            watchThread.interrupt();
        }
        pluginClassLoaders.values().forEach(loader -> {
            try {
                loader.close();
            } catch (IOException e) {
                log.error("Error closing plugin class loader", e);
            }
        });
        log.info("JarWatcherService stopped.");

        running.set(false);
        if (watchThread != null) {
            watchThread.interrupt();
        }
        pluginClassLoaders.values().forEach(loader -> {
            try {
                loader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void watchDirectory() {
        log.info("Watching directory: {}", addonsPath);

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            addonsPath.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    // StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);

            while (running.get()) {
                WatchKey key;
                try {
                    key = watchService.take();
                } catch (InterruptedException e) {
                    if (!running.get()) {
                        break;
                    }
                    continue;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path newPath = addonsPath.resolve((Path) event.context());

                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        if (newPath.toString().endsWith(".jar")) {
                            log.info("New JAR file created: {}", newPath);
                            loadJar(newPath.toFile());
                        }
                    } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        if (newPath.toString().endsWith(".jar")) {
                            log.info("JAR file deleted: {}", newPath);
                            removeJar(newPath.toFile());
                        }
                    }
                }
                key.reset();
            }
        } catch (IOException e) {
            log.error("Error watching directory", e);
        }
    }

    private void loadJar(File jarFile) {
        log.info("Loading JAR file: {}", jarFile.getName());

        try (JarFile jar = new JarFile(jarFile)) {
            URL[] urls = { jarFile.toURI().toURL() };
            URLClassLoader loader = new URLClassLoader(urls, this.getClass().getClassLoader());

            jar.stream()
                    .filter(entry -> entry.getName().endsWith(".class"))
                    .forEach(entry -> {
                        try {
                            String className = entry.getName().replace("/", ".").replace(".class", "");
                            Class<?> clazz = loader.loadClass(className);

                            if (Component.class.isAssignableFrom(clazz)) {
                                QVaadinAddonInfo vaadinAddonInfo = new QVaadinAddonInfo();
                                vaadinAddonInfo.className = className;
                                vaadinAddonInfo.classLoader = loader;

                                if (clazz.isAnnotationPresent(Route.class)) {
                                    Route routeAnnotation = clazz.getAnnotation(Route.class);
                                    String routeValue = routeAnnotation.value();
                                    vaadinAddonInfo.route = routeValue;
                                    vaadinAddons.put(routeValue, vaadinAddonInfo);

                                    log.info("Loaded Vaadin component: {}", routeValue);
                                } else {
                                    log.error("Class is not a Vaadin Component: {}", clazz.getName());
                                }

                            } else if (QAddon.class.isAssignableFrom(clazz)) {
                                QAddon addonInstance = (QAddon) clazz.getDeclaredConstructor().newInstance();
                                plugins.put(addonInstance.getName(), addonInstance);
                                pluginClassLoaders.put(addonInstance.getName(), loader);
                                addonInstances.put(addonInstance.getName(), addonInstance);
                                addonInstance.startup();
                                log.info("Loaded Addon: {}", addonInstance.getName());
                            }
                        } catch (Exception e) {
                            log.error("Error loading class from JAR file", e);
                        }
                    });
        } catch (IOException e) {
            log.error("Error loading JAR file", e);
        }
    }

    // private void reloadJar(File jarFile) {
    //     removeJar(jarFile);
    //     loadJar(jarFile);
    // }

    private void removeJar(File jarFile) {
        log.info("Removing JAR file: {}", jarFile.getName());

        String jarFileName = jarFile.getName();
        String pluginKey = null;

        for (Map.Entry<String, Object> entry : plugins.entrySet()) {
            if (jarFileName.contains(entry.getKey())) {
                pluginKey = entry.getKey();
                break;
            }
        }

        if (pluginKey != null) {

            plugins.remove(pluginKey);
            QAddon addonInstance = addonInstances.get(pluginKey);
            addonInstance.shutdown();
            URLClassLoader loader = pluginClassLoaders.remove(pluginKey);
            try {
                if (loader != null) {
                    loader.close();
                }
            } catch (IOException e) {
                log.error("Error closing plugin class loader", e);
            }
            log.info("Removed plugin: {}", pluginKey);
        }
    }

    public Map<String, Object> getPlugins() {
        return plugins;
    }

    public List<RouteData> getAllRoutes() {
        return RouteConfiguration.forApplicationScope().getAvailableRoutes();
    }

    public void downloadFromUrl(URL url, Path path) throws IOException {
        try (InputStream in = url.openStream()) {
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean b) {
        isNew = b;
    }

}