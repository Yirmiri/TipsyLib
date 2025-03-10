package net.azurune.tipsylib.platform;

import net.azurune.tipsylib.platform.services.TLIPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public class FabricTLPlatformHelper implements TLIPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
