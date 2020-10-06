package net.kyrptonaught.birpi;

import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.resource.ResourcePackSource;

import java.util.function.Consumer;

public class BIRPIResourcePackProvider implements ResourcePackProvider {
    String modID;
    String id;
    String fileName;
    String description;
    Class modInstance;
    boolean alwaysEnabled;

    public BIRPIResourcePackProvider(BIRPIdata birpIdata) {
        this(birpIdata.modID, birpIdata.id, birpIdata.fileName, birpIdata.description, birpIdata.modInstance, birpIdata.alwaysEnabled);
    }

    public BIRPIResourcePackProvider(String modID, String id, String fileName, String description, Class modInstance, boolean alwaysenabled) {
        this.modID = modID;
        this.id = id;
        this.fileName = fileName;
        this.description = description;
        this.modInstance = modInstance;
        this.alwaysEnabled = alwaysenabled;
    }

    @Override
    public void register(Consumer<ResourcePackProfile> consumer, ResourcePackProfile.Factory factory) {
        InputStreamResourcePack pack = new InputStreamResourcePack(modID, "/resourcepacks/" + fileName, modInstance) {
            public String getName() {
                return description;
            }
        };
        ResourcePackProfile resourcePackProfile2 = ResourcePackProfile.of(id, alwaysEnabled, () -> pack, factory, ResourcePackProfile.InsertionPosition.TOP, ResourcePackSource.PACK_SOURCE_BUILTIN);
        if (resourcePackProfile2 != null) {
            consumer.accept(resourcePackProfile2);
        }
    }
}
