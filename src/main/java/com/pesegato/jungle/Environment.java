package com.pesegato.jungle;

import com.sun.jna.platform.win32.KnownFolders;
import com.sun.jna.platform.win32.Shell32Util;
import org.lwjgl.opengl.GL11;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class Environment {

    static Logger log = LoggerFactory.getLogger(Environment.class);

    public static String CPU_NAME;
    public static String CPU_FAMILY;
    public static String CPU_STEPPING;
    public static String CPU_IDENTIFIER;
    public static String CPU_ID;
    public static String CPU_ARCHITECTURE;
    public static String CPU_MODEL;
    public static String CPU_VENDOR;
    public static long CPU_VENDORFREQ;

    public static int OS_BITNESS;
    public static String OS_FAMILY;
    public static String OS_MANUFACTURER;
    public static String OS_VERSION;
    public static String OS_BUILDNUMBER;
    public static String OS_CODENAME;

    public static String RAM_AVAILABLE;
    public static String RAM_TOTAL;
    public static String VRAM_TOTAL;

    public static String RENDERER;

    private static String gameFolderName = null;
    private static String settingsPath = null;

    public static void init(Logger log) {
        log.info("checking system...");
        RENDERER = GL11.glGetString(GL11.GL_RENDERER);
        oshi.SystemInfo si = new oshi.SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        CentralProcessor cpu = hal.getProcessor();
        CPU_NAME = cpu.getProcessorIdentifier().getName();
        CPU_FAMILY = cpu.getProcessorIdentifier().getFamily();
        CPU_STEPPING = cpu.getProcessorIdentifier().getStepping();
        CPU_IDENTIFIER = cpu.getProcessorIdentifier().getIdentifier();
        CPU_ID = cpu.getProcessorIdentifier().getProcessorID();
        CPU_ARCHITECTURE = cpu.getProcessorIdentifier().getMicroarchitecture();
        CPU_MODEL = cpu.getProcessorIdentifier().getModel();
        CPU_VENDOR = cpu.getProcessorIdentifier().getVendor();
        CPU_VENDORFREQ = cpu.getProcessorIdentifier().getVendorFreq();
        OS_BITNESS = os.getBitness();
        OS_FAMILY = os.getFamily();
        OS_MANUFACTURER = os.getManufacturer();
        OS_VERSION = os.getVersionInfo().getVersion();
        OS_BUILDNUMBER = os.getVersionInfo().getBuildNumber();
        OS_CODENAME = os.getVersionInfo().getCodeName();
        //System.out.println(hal.getMemory().getPhysicalMemory() + " physical memory");
        RAM_TOTAL = hal.getMemory().getTotal() / 1073741824f + " Gb total memory";

        log.info("CPU name: " + CPU_NAME);
        log.info("CPU family: " + CPU_FAMILY);
        log.info("CPU stepping: " + CPU_STEPPING);
        log.info("CPU identifier: " + CPU_IDENTIFIER);
        log.info("CPU id: " + CPU_ID);
        log.info("CPU architecture: " + CPU_ARCHITECTURE);
        log.info("CPU model: " + CPU_MODEL);
        log.info("CPU vendor: " + CPU_VENDOR);
        log.info("CPU vendor freq: " + CPU_VENDORFREQ);
        log.info("OS bitness: " + OS_BITNESS);
        log.info("OS family: " + OS_FAMILY);
        log.info("OS manufacturer: " + OS_MANUFACTURER);
        log.info("OS version: " + OS_VERSION);
        log.info("OS build number: " + OS_BUILDNUMBER);
        log.info("OS code name: " + OS_CODENAME);

        log.info(RAM_TOTAL);

        log.info(hal.getMemory().getAvailable() / 1073741824f + " Gb available memory");
        int i = 1;
        for (GraphicsCard gc : hal.getGraphicsCards()) {
            log.info("Graphics card #" + i + ":");
            log.info(" Name: " + gc.getName());
            log.info(" Device id: " + gc.getDeviceId());
            log.info(" Version: " + gc.getVersionInfo());
            log.info(" Vendor: " + gc.getVendor());
            VRAM_TOTAL = (gc.getVRam() / 1073741824f + " Gb VRAM");
            log.info(" " + VRAM_TOTAL);
            i++;
        }
    }

    public static String getOSFriendlyName() {
        //return (System.getProperty("os.name") + " ver." + System.getProperty("os.version"));
        return "System: " + OS_FAMILY + " " + OS_VERSION + " " + OS_BUILDNUMBER;
    }

    public static String getJavaFriendlyName() {
        return "JVM: Java " + System.getProperty("java.version") + " " + System.getProperty("os.arch");
    }

    public static String getRendererFriendlyName() {
        return "Renderer: " + RENDERER;
    }

    public static void setGameFolderName(String newName) {
        if (gameFolderName != null) {
            log.error("Game name already set, cannot be changed and exiting now.");
            System.exit(0);
        }
        gameFolderName = getSystemGameFolder() + newName + "/";;
        settingsPath = gameFolderName + "settings.json";
    }

    static String getSystemGameFolder() {
        String path = System.getProperty("user.home") + "/Saved Games/";
        if (System.getProperty("os.name").startsWith("Windows")) {
            path = Shell32Util.getKnownFolderPath(KnownFolders.FOLDERID_SavedGames) + "/";
        }
        return path;
    }

    public static String getGameFolder() {
        return gameFolderName;
    }

    public static String getSettingsFile() {
        return settingsPath;
    }
}
