package com.qnenet;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@Push
@SpringBootApplication
@Theme(value = "quickneasy", variant = Lumo.DARK)
@PWA(name = "QuickNEasy", shortName = "QNE", offlinePath = "offline.html", offlineResources = {
        "images/offline.png" })
public class QuickNEasyApp implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(QuickNEasyApp.class, args);
    }

}
