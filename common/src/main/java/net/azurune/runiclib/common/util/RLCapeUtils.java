package net.azurune.runiclib.common.util;

import java.util.*;

public class RLCapeUtils {
    public static final String[] CAPE_OPTIONS = {
            "None", "Custom", "RunicLib", "Dungeon's Delight", "Excessive Building", "Delicate Dyes", "Urban Decor", "Yapping Tooltips"
    };

    public static final Map<String, Set<UUID>> CAPE_WHITELIST = new HashMap<>();

    public static final Map<UUID, Integer> PLAYER_CAPES = new HashMap<>();

    static {
        CAPE_WHITELIST.put("RunicLib", Set.of(
                UUID.fromString("1cedf927-5c8f-4650-95e9-808fc8f94d00") //Yirmiri
        ));

        CAPE_WHITELIST.put("Dungeon's Delight", Set.of(
                UUID.fromString("1cedf927-5c8f-4650-95e9-808fc8f94d00") //Yirmiri
        ));

        CAPE_WHITELIST.put("Excessive Building", Set.of(
                UUID.fromString("1cedf927-5c8f-4650-95e9-808fc8f94d00") //Yirmiri
        ));

        CAPE_WHITELIST.put("Delicate Dyes", Set.of(
                UUID.fromString("1cedf927-5c8f-4650-95e9-808fc8f94d00") //Yirmiri
        ));

        CAPE_WHITELIST.put("Urban Decor", Set.of(
                UUID.fromString("1cedf927-5c8f-4650-95e9-808fc8f94d00") //Yirmiri
        ));

        CAPE_WHITELIST.put("Yapping Tooltips", Set.of(
                UUID.fromString("1cedf927-5c8f-4650-95e9-808fc8f94d00") //Yirmiri
        ));

        CAPE_WHITELIST.put("Custom", Set.of(
                UUID.fromString("1cedf927-5c8f-4650-95e9-808fc8f94d00"), //Yirmiri
                UUID.fromString("bd35c402-fa9c-4d00-afe6-b4ed9ebe90c4"), //amirasana
                UUID.fromString("2913d971-a58d-4566-8706-b4fb5eacb954"), //redeyevain
                UUID.fromString("d1dac9fe-3ef0-4ea8-997b-b7cdd6a92131"), //tellio_ari
                UUID.fromString("9778ff53-d83d-4233-8fa6-8aab7b89c4c0"), //arbeeet
                UUID.fromString("c12df14d-24ed-4247-84e8-e10c111237df"), //Dedemianmia
                UUID.fromString("416fc916-69cc-4b3c-8c5e-a39a5acb6981"), //Kolos69
                UUID.fromString("3fd1d511-62d6-4e18-a28d-3e3d4fd93620"), //KekeCreations
                UUID.fromString("452ec9e4-a4f8-4edf-bd3c-ab3d7b751359"), //BackupCup
                UUID.fromString("c1e0e811-8b55-4ff2-be32-443596a12ade"), //QueenSilverBlue
                UUID.fromString("2ab2e589-b328-441d-bebb-1f129e330ec2"), //aCryingCloud
                UUID.fromString("f73f8d0e-5c82-48d2-bad0-b7f1796aa2fc"), //tortulss
                UUID.fromString("eff789b6-ed9d-4787-8640-ab37e7daf81f"), //Slicraw
                UUID.fromString("bc56b2c8-9ef8-4532-b045-00f44804bca4"), //Hecco__
                UUID.fromString("27a729ac-0a2a-42fc-8e65-a37fcba6a6c7"), //ZeusIGN
                UUID.fromString("4bc0a7a9-497a-4aa1-a5af-cee312f94b01"), //CreekWanderer
                UUID.fromString("7a6a8c68-8b73-47f6-b08f-0dde5f1848dd"), //SmillyScarfs
                UUID.fromString("8429992e-eba7-4dc9-a0b9-f941a55a5fb4") //Pearlision3st
        ));
    }
}
