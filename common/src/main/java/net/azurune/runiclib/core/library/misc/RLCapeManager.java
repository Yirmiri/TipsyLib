package net.azurune.runiclib.core.library.misc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.azurune.runiclib.RunicLib;
import net.azurune.runiclib.core.platform.RLServices;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class RLCapeManager {
    private static final Map<UUID, List<ResourceLocation>> CAPES = new HashMap<>();
    private static final Map<UUID, Integer> SELECTED = new HashMap<>();
    private static final Map<ResourceLocation, String> NAMES = new HashMap<>();
    private static final Path FILE = RLServices.PLATFORM.gameDir().resolve("runiclib_cape.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static {
        //THE AZURUNE GANG
        add(UUID.fromString("1cedf927-5c8f-4650-95e9-808fc8f94d00"), //Yirmiri
                cape("azurune"),
                cape("evil_eye"),
                cape("pumpkin_queen"),
                cape("sad_cloud"),
                cape("wrathful"),
                cape("bug"),
                cape("axolotl"),
                cape("hyper"),
                cape("robor"),
                cape("rat"),
                cape("putrid"),
                cape("snom"),
                cape("lightning"),
                cape("rebellious"),
                cape("unicolor"),
                cape("jinxed"),
                cape("beetroot"),
                cape("hex"),
                cape("accursed"),
                cape("fudge_sundae"),
                cape("shiny_pearl")
        );
        add(UUID.fromString("bd35c402-fa9c-4d00-afe6-b4ed9ebe90c4"), //amirasana
                cape("azurune")
        );
        add(UUID.fromString("2913d971-a58d-4566-8706-b4fb5eacb954"), //redeyevain
                cape("evil_eye")
        );
        add(UUID.fromString("c1e0e811-8b55-4ff2-be32-443596a12ade"), //QueenSilverBlue
                cape("pumpkin_queen")
        );
        add(UUID.fromString("2ab2e589-b328-441d-bebb-1f129e330ec2"), //aCryingCloud
                cape("sad_cloud")
        );
        add(UUID.fromString("f73f8d0e-5c82-48d2-bad0-b7f1796aa2fc"), //tortulss
                cape("wrathful")
        );
        add(UUID.fromString("eff789b6-ed9d-4787-8640-ab37e7daf81f"), //Slicraw
                cape("bug")
        );
        add(UUID.fromString("bc56b2c8-9ef8-4532-b045-00f44804bca4"), //Hecco
                cape("axolotl")
        );
        add(UUID.fromString("774e37fc-1ca4-4156-827e-661afa24cb56"), //Artyrian_
                cape("hyper")
        );
        add(UUID.fromString("d1dac9fe-3ef0-4ea8-997b-b7cdd6a92131"), //arid_lizzy
                cape("robor")
        );
        add(UUID.fromString("4d5b5fa4-684c-4c3a-8154-a8ad8f13fcd4"), //SirPancakess
                cape("rat")
        );
        add(UUID.fromString("7ca4cbfd-bb7e-419c-a97c-26a54031d28d"), //Betwixer
                cape("putrid")
        );
        add(UUID.fromString("abb421d6-af98-4f57-a746-d082cb5cda37"), //DRiSFiSH
                cape("snom")
        );
        add(UUID.fromString("27a729ac-0a2a-42fc-8e65-a37fcba6a6c7"), //ZeusIGN
                cape("lightning")
        );
        add(UUID.fromString("4bc0a7a9-497a-4aa1-a5af-cee312f94b01"), //CreekWanderer
                cape("rebellious")
        );
        add(UUID.fromString("32290fa8-77ed-4794-9cba-25c09e7f4e1d"), //Diemond_Player
                cape("unicolor")
        );
        add(UUID.fromString("3fd1d511-62d6-4e18-a28d-3e3d4fd93620"), //KekeCreations
                cape("jinxed")
        );
        add(UUID.fromString("9778ff53-d83d-4233-8fa6-8aab7b89c4c0"), //Stellari_
                cape("beetroot"))
        ;
        add(UUID.fromString("452ec9e4-a4f8-4edf-bd3c-ab3d7b751359"), //BackupCup
                cape("hex")
        );
        add(UUID.fromString("416fc916-69cc-4b3c-8c5e-a39a5acb6981"), //Kolos69
                cape("accursed")
        );
        add(UUID.fromString("a0437891-2b2a-4d0e-9792-463cad28dd38"), //Smillyblade1
                cape("fudge_sundae")
        );
        add(UUID.fromString("8429992e-eba7-4dc9-a0b9-f941a55a5fb4"), //Pearlision
                cape("shiny_pearl")
        );

        //CONTRIBUTORS

        load();
    }

    private static ResourceLocation cape(String id) {
        ResourceLocation texture = RunicLib.modid("textures/capes/" + id + ".png");
        NAMES.put(texture, "runiclib.cape." + id);
        return texture;
    }

    private static void add(UUID uuid, ResourceLocation... capes) {
        CAPES.put(uuid, List.of(capes));
    }

    public static List<ResourceLocation> getCapes(UUID uuid) {
        return CAPES.getOrDefault(uuid, List.of());
    }

    public static ResourceLocation getCape(UUID uuid) {
        List<ResourceLocation> capes = getCapes(uuid);
        if (capes.isEmpty()) return null;

        int selected = SELECTED.getOrDefault(uuid, -1);
        if (selected < 0) return null;
        if (selected >= capes.size()) selected = 0;

        return capes.get(selected);
    }

    public static int getSelected(UUID uuid) {
        return SELECTED.getOrDefault(uuid, -1);
    }

    public static void setSelected(UUID uuid, int selected) {
        SELECTED.put(uuid, selected);
        save();
    }

    public static int next(UUID uuid) {
        List<ResourceLocation> capes = getCapes(uuid);
        if (capes.isEmpty()) return -1;

        int selected = getSelected(uuid);
        selected++;

        if (selected >= capes.size()) selected = -1;

        setSelected(uuid, selected);
        return selected;
    }

    public static Component getSelectedName(UUID uuid) {
        ResourceLocation texture = getCape(uuid);
        if (texture == null) return Component.translatable("runiclib.cape.vanilla");

        return Component.translatable(NAMES.get(texture));
    }

    private static void load() {
        if (!Files.exists(FILE)) return;

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) return;

        try (Reader reader = Files.newBufferedReader(FILE)) {
            Integer selected = GSON.fromJson(reader, Integer.class);

            if (selected == null) return;

            SELECTED.clear();
            SELECTED.put(minecraft.player.getUUID(), selected);
        } catch (IOException ignored) {
        }
    }

    private static void save() {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) return;

        try (Writer writer = Files.newBufferedWriter(FILE)) {
            GSON.toJson(SELECTED.getOrDefault(minecraft.player.getUUID(), -1), writer);
        } catch (IOException ignored) {
        }
    }
}