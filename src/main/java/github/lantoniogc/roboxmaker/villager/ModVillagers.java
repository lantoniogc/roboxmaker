package github.lantoniogc.roboxmaker.villager;

import com.google.common.collect.ImmutableSet;
import github.lantoniogc.roboxmaker.Roboxmaker;
import github.lantoniogc.roboxmaker.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, Roboxmaker.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, Roboxmaker.MOD_ID);

    public static final RegistryObject<PoiType> ROBOT_PART_BLOCK_POI = POI_TYPES.register("robot_part_block_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.ROBOT_PART_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> ROBOT_MASTER = VILLAGER_PROFESSIONS.register("robot_master",
            () -> new VillagerProfession("robot_master", x -> x.get() == ROBOT_PART_BLOCK_POI.get(),
                    x -> x.get() == ROBOT_PART_BLOCK_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));

    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, ROBOT_PART_BLOCK_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}