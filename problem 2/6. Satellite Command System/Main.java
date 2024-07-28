import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

// utils/LoggerUtil.java
class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logError(String message, Throwable e) {
        logger.severe(message + " - " + e.getMessage());
    }
}

// models/Satellite.java
class Satellite {
    private String orientation;
    private String solarPanelStatus;
    private int dataCollected;

    public Satellite() {
        this.orientation = "North";
        this.solarPanelStatus = "Inactive";
        this.dataCollected = 0;
        LoggerUtil.logInfo("Satellite initialized. Orientation: North, Solar Panels: Inactive, Data Collected: 0");
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
        LoggerUtil.logInfo("Satellite orientation set to " + orientation);
    }

    public String getSolarPanelStatus() {
        return solarPanelStatus;
    }

    public void setSolarPanelStatus(String solarPanelStatus) {
        this.solarPanelStatus = solarPanelStatus;
        LoggerUtil.logInfo("Solar panels " + solarPanelStatus);
    }

    public int getDataCollected() {
        return dataCollected;
    }

    public void collectData() {
        if (solarPanelStatus.equals("Active")) {
            dataCollected += 10;
            LoggerUtil.logInfo("Data collected. Total data: " + dataCollected);
        } else {
            LoggerUtil.logInfo("Cannot collect data. Solar panels are inactive.");
        }
    }

    public void displayStatus() {
        System.out.println("Orientation: " + orientation);
        System.out.println("Solar Panels: " + solarPanelStatus);
        System.out.println("Data Collected: " + dataCollected);
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Satellite satellite = new Satellite();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            command = scanner.nextLine().trim();

            if (command.startsWith("rotate")) {
                String[] parts = command.split(" ");
                if (parts.length == 2) {
                    satellite.setOrientation(parts[1]);
                } else {
                    LoggerUtil.logError("Invalid rotate command format", null);
                }
            } else if (command.equalsIgnoreCase("activatePanels")) {
                satellite.setSolarPanelStatus("Active");
            } else if (command.equalsIgnoreCase("deactivatePanels")) {
                satellite.setSolarPanelStatus("Inactive");
            } else if (command.equalsIgnoreCase("collectData")) {
                satellite.collectData();
            } else if (command.equalsIgnoreCase("status")) {
                satellite.displayStatus();
            } else {
                LoggerUtil.logError("Unknown command", null);
            }
        }
    }
}
