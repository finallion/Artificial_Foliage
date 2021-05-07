package com.finallion.artificialfoliage.fluid;

import com.finallion.artificialfoliage.registry.ModBlocks;
import com.finallion.artificialfoliage.registry.ModFluids;
import com.finallion.artificialfoliage.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class LukewarmOceanWater extends ARFOWater {

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_LUKEWARM_OCEAN_WATER;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_LUKEWARM_OCEAN_WATER;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.LUKEWARM_OCEAN_WATER_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return (BlockState) ModBlocks.LUKEWARM_OCEAN_WATER.getDefaultState().with(FluidBlock.LEVEL, method_15741(state));
    }


    public static class Flowing extends LukewarmOceanWater {
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        public int getLevel(FluidState state) {
            return (Integer)state.get(LEVEL);
        }

        public boolean isStill(FluidState state) {
            return false;
        }
    }

    public static class Still extends LukewarmOceanWater {
        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
