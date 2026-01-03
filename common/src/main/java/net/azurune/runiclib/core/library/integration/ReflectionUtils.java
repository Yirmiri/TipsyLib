package net.azurune.runiclib.core.library.integration;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class ReflectionUtils {
    /**
     * This instantiates a class from a specified path and automatically creates a fallback if it cannot be found
     * @param classPath the path of the class to instantiate
     * @param args the constructor arguments
     */
    @SuppressWarnings("unchecked")
    public static <T> T createInstance(String classPath, Object... args) {
        try {
            Class<?>[] argTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }
            return (T) Class.forName(classPath).getConstructor(argTypes).newInstance(args);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static <T extends Block> T reflectBlock(String classPath, Supplier<T> supplier, Object... args) {
        T block = createInstance(classPath, args);
        return block != null ? block : supplier.get();
    }

    public static <T extends Item> T reflectItem(String classPath, Supplier<T> supplier, Object... args) {
        T item = createInstance(classPath, args);
        return item != null ? item : supplier.get();
    }

    public static <T extends MobEffect> T reflectMobEffect(String classPath, Supplier<T> supplier, Object... args) {
        T mobEffect = createInstance(classPath, args);
        return mobEffect != null ? mobEffect : supplier.get();
    }
}
