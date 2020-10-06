package net.kyrptonaught.birpi.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.fabricmc.loader.api.FabricLoader;
import net.kyrptonaught.birpi.BIRPIResourcePackProvider;
import net.kyrptonaught.birpi.BIRPIdata;
import net.kyrptonaught.birpi.RegisterBIRPI;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProfile;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(ResourcePackManager.class)
public class ResourcePackManagerMixin {

    @Shadow
    @Final
    private ResourcePackProfile.Factory profileFactory;

    @Inject(method = "providePackProfiles", at = @At(value = "RETURN"), cancellable = true)
    public void ResourcePackManager(CallbackInfoReturnable<Map<String, ResourcePackProfile>> cir) {
        Map<String, ResourcePackProfile> packs = Maps.newTreeMap();
        packs.putAll(cir.getReturnValue());
        FabricLoader.getInstance().getEntrypoints("registerbirpis", RegisterBIRPI.class).forEach(getBIRPIs -> {
            List<BIRPIdata> birpis = getBIRPIs.getBIRPIs();
            for (BIRPIdata birpidata : birpis) {
                new BIRPIResourcePackProvider(birpidata).register((resourcePackProfile) -> packs.put(resourcePackProfile.getName(), resourcePackProfile), this.profileFactory);
            }
        });
        cir.setReturnValue(ImmutableMap.copyOf(packs));
    }
}
