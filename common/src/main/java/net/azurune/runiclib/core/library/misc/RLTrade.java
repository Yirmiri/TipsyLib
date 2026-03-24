package net.azurune.runiclib.core.library.misc;

import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Stores information for a Villager trade.
 * <p>
 * <a href="https://github.com/TheArtyrian/frontiers-fabric/blob/1.21.1-ML/common/src/main/java/net/vertisoft/vectorlib/agnostic/util/VectorTrade.java">
 *      Original code by Artyrian for Frontiers/VectorLib. Used with explict permission (check the commit history xd).
 * </a>
 *
 */
public class RLTrade
{
    private static final List<RLTrade> TRADES = new ArrayList<>();

    public static final float LOW_MULT = 0.05F;
    public static final float HIGH_MULT = 0.2F;

    private final VillagerTrades.ItemListing offer;
    @Nullable private final VillagerProfession profession;
    private final int level;
    private final boolean wanderingMode;
    private final boolean wanderingRare;

    private RLTrade(VillagerProfession prof, int level, VillagerTrades.ItemListing offer, boolean isWanderer, boolean isRareWandering) {
        this.profession = prof;
        this.level = level;
        this.offer = offer;

        this.wanderingMode = isWanderer;
        this.wanderingRare = isRareWandering;
    }

    public static List<RLTrade> getTrades() { return TRADES; }
    public static void professionTrade(VillagerProfession prof, int level, VillagerTrades.ItemListing offer) { TRADES.add(new RLTrade(prof, level, offer, false, false));}
    public static void wanderingTrade(boolean isRare, VillagerTrades.ItemListing offer) { TRADES.add(new RLTrade(null, 0, offer, true, isRare)); }

    public int getLvl() { return level; }
    public VillagerTrades.ItemListing getTrade() { return offer; }
    public boolean isWandering() { return this.profession == null && this.wanderingMode; }
    public boolean isRareWandering() { return this.wanderingMode && this.wanderingRare; }
    public VillagerProfession getJob() {
        try {
            if (this.profession == null && !this.wanderingMode) throw new Throwable("Villager profession can't be null in a non-wandering villager trade");
            return profession;
        } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.forThrowable(throwable, "Registering villager trade in RunicLib");
            CrashReportCategory crashReportCategory = crashReport.addCategory("RLTrade");
            crashReportCategory.setDetail("Trade", this.offer);
            crashReportCategory.setDetail("Level", this.level);
            throw new ReportedException(crashReport);
        }
    }

    /** Creates a MerchantOffer that provides a desired Explorer Map. */
    public static MerchantOffer explorerMap(
            Entity merchant, RandomSource random, int cost, TagKey<Structure> structure, String name, Holder<MapDecorationType> icon, int maxUses, int villagerXp
    ) {
        if (!(merchant.level() instanceof ServerLevel serverlevel)) return null;

        BlockPos blockpos = serverlevel.findNearestMapStructure(structure, merchant.blockPosition(), 100, true);
        if (blockpos == null) return null;

        ItemStack itemstack = MapItem.create(serverlevel, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
        MapItem.renderBiomePreviewMap(serverlevel, itemstack);
        MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", icon);
        itemstack.set(DataComponents.ITEM_NAME, Component.translatable(name));
        return new MerchantOffer(
                new ItemCost(Items.EMERALD, cost), Optional.of(new ItemCost(Items.COMPASS)), itemstack, maxUses, villagerXp, 0.2F
        );
    }
}
