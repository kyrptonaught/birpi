package net.kyrptonaught.birpi;

public class BIRPIdata {
    String modID;
    String id;
    String fileName;
    String description;
    Class modInstance;
    boolean alwaysEnabled = false;

    public BIRPIdata(String modID, String id, String fileName, String description, Class modInstance) {
        this.modID = modID;
        this.id = id;
        this.fileName = fileName;
        this.description = description;
        this.modInstance = modInstance;
    }

    public BIRPIdata(String modID, String id, String fileName, String description, Class modInstance, boolean alwaysEnabled) {
        this(modID, id, fileName, description, modInstance);
        this.alwaysEnabled = alwaysEnabled;
    }
}
