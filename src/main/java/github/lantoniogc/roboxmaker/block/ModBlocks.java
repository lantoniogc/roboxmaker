package github.lantoniogc.roboxmaker.block;

import github.lantoniogc.roboxmaker.Roboxmaker;
import github.lantoniogc.roboxmaker.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Roboxmaker.MOD_ID);

    public static final RegistryObject<Block> ROBOT_PART_BLOCK =
            registerBlock("robot_part_block",
                    () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                            .strength(6F)
                            .requiresCorrectToolForDrops()));

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);

        return toReturn;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static void buildContents(CreativeModeTabEvent.BuildContents event) {
        event.accept(ROBOT_PART_BLOCK);
    }
}
