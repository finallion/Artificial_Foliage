package com.finallion.artificialfoliage.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.sound.BlockSoundGroup;

public class ARFOJungleLeavesBlock extends LeavesBlock {

    public ARFOJungleLeavesBlock() {
        super(FabricBlockSettings.copyOf(Blocks.JUNGLE_LEAVES).breakByTool(FabricToolTags.HOES).breakByTool(FabricToolTags.SHEARS).sounds(BlockSoundGroup.GRASS));
    }

}
