package net.azurune.runiclib.core.library.runiconfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Runiconfig {
    private static final Map<String, Config<?>> CONFIGS = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path configDirectory = Path.of("config");

    public static <T> void registerConfig(String modid, Class<T> configClass, Supplier<T> defaultSupplier) {
        if (CONFIGS.containsKey(modid)) {
            throw new IllegalArgumentException("Config already registered for mod: " + modid);
        }

        try {
            Files.createDirectories(configDirectory);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to create config directory", exception);
        }

        Path configFile = configDirectory.resolve(modid + ".json");
        T config = loadConfigFile(configFile, configClass, defaultSupplier.get());
        CONFIGS.put(modid, new Config<>(configFile, config));
        saveConfig(modid);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getConfig(String modid) {
        if (!CONFIGS.containsKey(modid)) {
            throw new IllegalArgumentException("No config registered for mod: " + modid);
        }
        return (T) CONFIGS.get(modid).config;
    }

    public static void saveConfig(String modid) {
        if (!CONFIGS.containsKey(modid)) {
            throw new IllegalArgumentException("No config registered for mod: " + modid);
        }

        Config<?> config = CONFIGS.get(modid);
        try (Writer writer = Files.newBufferedWriter(config.path)) {
            GSON.toJson(config.config, writer);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to save config for mod: " + modid, exception);
        }
    }

    private static <T> T loadConfigFile(Path path, Class<T> clazz, T defaultConfig) {
        if (!Files.exists(path)) {
            saveConfigFile(path, defaultConfig);
            return defaultConfig;
        }

        try (Reader reader = Files.newBufferedReader(path)) {
            T loaded = GSON.fromJson(reader, clazz);
            return loaded != null ? loaded : defaultConfig;
        } catch (IOException exception) {
            throw new RuntimeException("Failed to load config file: " + path, exception);
        }
    }

    private static <T> void saveConfigFile(Path path, T config) {
        try {
            Files.createDirectories(configDirectory);
            try (Writer writer = Files.newBufferedWriter(path)) {
                GSON.toJson(config, writer);
            }
        } catch (IOException exception) {
            throw new RuntimeException("Failed to save config file: " + path, exception);
        }
    }

    private record Config<T>(Path path, T config) {
    }
}
