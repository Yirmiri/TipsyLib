package net.azurune.runiclib.core.platform;

import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.services.RLClientHelper;
import net.azurune.runiclib.core.platform.services.RLPlatformHelper;
import net.azurune.runiclib.core.platform.services.RLRegistryHelper;

import java.util.ServiceLoader;

/**
 * Services will be refactored to RLServices for versions of this mod on Minecraft version 1.22+
 * This change is to help differentiate between this class and other classes with similar names
 */
public class Services {
    public static final RLPlatformHelper PLATFORM = load(RLPlatformHelper.class);
    public static final RLRegistryHelper REGISTRY = load(RLRegistryHelper.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        RunicLib.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }

    public static RLClientHelper loadClient() {
        if (!PLATFORM.isClient()) {
            throw new IllegalStateException("Client helper requested on server!");
        }
        return load(RLClientHelper.class);
    }
}