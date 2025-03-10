package net.azurune.tipsylib.platform;

import net.azurune.tipsylib.TipsyLib;
import net.azurune.tipsylib.platform.services.TLIPlatformHelper;
import net.azurune.tipsylib.platform.services.TLRegistryHelper;

import java.util.ServiceLoader;

public class Services {
    public static final TLIPlatformHelper PLATFORM = load(TLIPlatformHelper.class);
    public static final TLRegistryHelper REGISTRY = load(TLRegistryHelper.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        TipsyLib.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}