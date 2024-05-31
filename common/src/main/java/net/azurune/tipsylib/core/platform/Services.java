package net.azurune.tipsylib.core.platform;

import net.azurune.tipsylib.TipsyLibConstants;
import net.azurune.tipsylib.core.platform.services.IPlatformHelper;
import net.azurune.tipsylib.core.platform.services.TipsyLibRegistryHelper;

import java.util.ServiceLoader;

public class Services {
    public static final TipsyLibRegistryHelper REGISTRY = load(TipsyLibRegistryHelper.class);
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        TipsyLibConstants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}