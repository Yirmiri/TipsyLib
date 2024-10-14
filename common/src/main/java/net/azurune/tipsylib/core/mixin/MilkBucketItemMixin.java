package net.azurune.tipsylib.core.mixin;

import net.azurune.tipsylib.core.registry.TLEffects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemMixin {

    @Inject(method = "finishUsingItem", at = @At("HEAD"), cancellable = true)
    private void tipsylib_finishUsingItem(ItemStack stack, Level level, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (user instanceof ServerPlayer serverPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.awardStat(Stats.ITEM_USED.get(MilkBucketItem.class.cast(this)));
        }

        if (user instanceof Player && !((Player)user).getAbilities().instabuild) {
            stack.shrink(1);
        }

        if (!level.isClientSide() && !user.hasEffect(TLEffects.IMPURE)) {
            user.removeAllEffects();
        }

        cir.setReturnValue(stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack);
    }
}
