package net.azurune.tipsylib.platform;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.azurune.tipsylib.TipsyLibConstants;
import net.azurune.tipsylib.core.platform.services.TipsyLibRegistryHelper;

import java.util.function.Supplier;

public class ForgeTipsyLibRegistryHelper implements TipsyLibRegistryHelper {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TipsyLibConstants.MOD_ID);

    @Override
    public <T extends MobEffect> Supplier<T> registerEffect(String id, Supplier<T> supplier) {
        return MOB_EFFECTS.register(id, supplier);
    }
}
