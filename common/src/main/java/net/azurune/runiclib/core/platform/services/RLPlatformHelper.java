package net.azurune.runiclib.core.platform.services;

import java.nio.file.Path;

public interface RLPlatformHelper {

    /**
     * Gets the name of the current platformHelper
     * @return The name of the current platformHelper.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the current environment is client-side.
     * @return True if running on the client, false if on the server.
     */
    boolean isClient();

    /**
     * Check if the current environment is running data generation.
     * @return True if running data generation, false otherwise.
     */
    boolean isDatagen();

    /**
     * Check if the game is currently in a development environment.
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the config directory for the current loader.
     */
    Path configDir();

    /**
     * Gets the name of the environment type as a string.
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }
}