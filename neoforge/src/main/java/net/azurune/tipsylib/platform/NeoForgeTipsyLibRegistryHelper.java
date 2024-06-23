package net.azurune.tipsylib.platform;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.azurune.tipsylib.TipsyLibConstants;
import net.azurune.tipsylib.core.platform.services.TipsyLibRegistryHelper;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeTipsyLibRegistryHelper implements TipsyLibRegistryHelper {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, TipsyLibConstants.MOD_ID);

    @Override
    public Holder<MobEffect> registerEffect(String id, MobEffect mobEffect) {
        return MOB_EFFECTS.register(id, () -> mobEffect);
    }
}
