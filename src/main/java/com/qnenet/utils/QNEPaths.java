package com.qnenet.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.qnenet.constants.QSysConstants;

public class QNEPaths {

    public static String getCDNStoreURLPrefix() {
        return "https://qnepz.b-cdn.net/";
    }

    // public static Object getInstallerPropsFilePath;

    public static Path getUserHomePath() {
        return Paths.get(System.getProperty("user.home"));
    }

    public static Path getQnePath() {
        return Paths.get(getUserHomePath().toString(), QSysConstants.APP_NAME);
    }

    public static Path getInstallPath() {
        return Paths.get(getQnePath().toString(), "install");
    }

    public static Path getInstallerPropsFilePath() {
        return Paths.get(getInstallPath().toString(), "installer.props");
    }

    public static Path getRepositoryPath() {
        return Paths.get(getQnePath().toString(), "repository");
    }

    public static Path getKeystoreFilePath() {
        return Paths.get(getRepositoryPath().toString(), "keystore.p12");
    }

    public static Path getSystemPath() {
        return Paths.get(getQnePath().toString(), "system");
    }

    public static Path getSiteUsersPath() {
        return Paths.get(getSystemPath().toString(), "siteUsers");
    }

    public static Path getKnownPath() {
        return Paths.get(getSystemPath().toString(), "known");
    }

    public static Path getAddonsPath() {
        return Paths.get(getSystemPath().toString(), "addons");
    }

    public static Path getEndPointsPath() {
        return Paths.get(getSystemPath().toString(), "endPoints");
    }

    public static Path getTmpPath() {
        return Paths.get(getSystemPath().toString(), "tmp");
    }

    public static Path getSystemPropsFilePath() {
        return Paths.get(getSystemPath().toString(), "system.props");
    }

    public static Path getSecurityBytesFilePath() {
        return Paths.get(getSystemPath().toString(), "security.bytes");
    }

    public static Path getGeoIPMapFilePath() {
        return Paths.get(getSystemPath().toString(), "geoIPInfo.map");
    }

    public static boolean checkDirectory(Path path) {
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
