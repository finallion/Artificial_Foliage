package com.finallion.artificialfoliage.block;

import com.finallion.artificialfoliage.registry.ModBlocks;
import com.finallion.artificialfoliage.utils.GrassFeatures;
import com.finallion.artificialfoliage.utils.BlockMapping;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;


public class ARFOGrassBlockSlab extends ARFOSlabBlock {

    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;
    private static final Map<Block, List<Block>> grassFeatures = new HashMap<>();

    public ARFOGrassBlockSlab() {
        super(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).breakByTool(FabricToolTags.SHOVELS).sounds(BlockSoundGroup.GRASS));
    }


    public void growArtificialGrass(World world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(pos);
        BlockState failState = Blocks.AIR.getDefaultState();
        initMap();
        boolean large = false;
        List<Block> features = new ArrayList<>();

        for (Block b1 : BlockMapping.slabAndBlocks.keySet()) {
            if (BlockMapping.slabAndBlocks.get(b1).is(blockState.getBlock())) features = grassFeatures.get(b1);
        }


        label48:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockPos2 = blockPos;

            for (int j = 0; j < i / 16; ++j) {
                blockPos2 = blockPos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!(world.getBlockState(blockPos2.down()).getBlock() instanceof ARFOGrassBlock) || world.getBlockState(blockPos2).isFullCube(world, blockPos2)) {
                    continue label48;
                }
            }
            BlockState blockState2 = world.getBlockState(blockPos2);
            if (blockState2.getBlock() instanceof ARFOGrassBlock && random.nextInt(10) == 0) {
                ((ARFOGrassBlock)blockState.getBlock()).growArtificialGrass(world, random, blockPos2, blockState2);
            }
            if (blockState2.getBlock() instanceof ARFOGrassBlockSlab && random.nextInt(10) == 0) {
                if (blockState2.get(TYPE) != SlabType.BOTTOM) ((ARFOGrassBlockSlab)blockState.getBlock()).growArtificialGrass(world, random, blockPos2, blockState2);
            }


            if (blockState2.isAir()) {
                BlockState blockState4 = failState;


                int rand = random.nextInt(80);
                if (rand < 10) {
                    blockState4 = features.get(0).getDefaultState();
                } else if (rand < 30) {
                    blockState4 = features.get(1).getDefaultState();
                } else if (rand < 35) {
                    if (world.getBlockState(blockPos2.up()).isAir()) {
                        blockState4 = features.get(2).getDefaultState().with(HALF, DoubleBlockHalf.LOWER);
                        large = true;
                    }
                } else if (rand < 40) {
                    if (world.getBlockState(blockPos2.up()).isAir()) {
                        blockState4 = features.get(3).getDefaultState().with(HALF, DoubleBlockHalf.LOWER);
                        large = true;
                    }
                } else if (rand < 45) {
                    blockState4 = features.get(random.nextInt(features.size() / 2) + 4).getDefaultState();
                }

                if (blockState4.canPlaceAt(world, blockPos2) && world.getBlockState(blockPos2).isAir()) {
                    world.setBlockState(blockPos2, blockState4, 3);
                    if (large) {
                        world.setBlockState(blockPos2.up(), blockState4.getBlock().getDefaultState().with(HALF, DoubleBlockHalf.UPPER), 3);
                    }
                    large = false;
                }
            }
        }
    }

    private void initMap() {
        grassFeatures.put(ModBlocks.JUNGLE_GRASS_BLOCK, GrassFeatures.jungle);
        grassFeatures.put(ModBlocks.JUNGLE_EDGE_GRASS_BLOCK, GrassFeatures.jungle_edge);
        grassFeatures.put(ModBlocks.BADLANDS_GRASS_BLOCK, GrassFeatures.badlands);
        grassFeatures.put(ModBlocks.SAVANNA_GRASS_BLOCK, GrassFeatures.savanna);
        grassFeatures.put(ModBlocks.FOREST_GRASS_BLOCK, GrassFeatures.forest);
        grassFeatures.put(ModBlocks.BIRCH_FOREST_GRASS_BLOCK, GrassFeatures.birch_forest);
        grassFeatures.put(ModBlocks.DARK_FOREST_GRASS_BLOCK, GrassFeatures.dark_forest);
        grassFeatures.put(ModBlocks.PLAINS_GRASS_BLOCK, GrassFeatures.plains);
        grassFeatures.put(ModBlocks.SWAMP_GRASS_BLOCK, GrassFeatures.swamp);
        grassFeatures.put(ModBlocks.LUSH_SWAMP_GRASS_BLOCK, GrassFeatures.lush_swamp);
        grassFeatures.put(ModBlocks.MOUNTAINS_GRASS_BLOCK, GrassFeatures.mountains);
        grassFeatures.put(ModBlocks.MUSHROOM_FIELDS_GRASS_BLOCK, GrassFeatures.mushrooms_fields);
        grassFeatures.put(ModBlocks.TAIGA_GRASS_BLOCK, GrassFeatures.taiga);
        grassFeatures.put(ModBlocks.MEGA_TAIGA_GRASS_BLOCK, GrassFeatures.mega_taiga);
        grassFeatures.put(ModBlocks.SNOWY_GRASS_BLOCK, GrassFeatures.snowy_biomes);
        grassFeatures.put(ModBlocks.SNOWY_BEACH_GRASS_BLOCK, GrassFeatures.snowy_beach);
        grassFeatures.put(ModBlocks.WATERS_GRASS_BLOCK, GrassFeatures.waters);
    }
}
