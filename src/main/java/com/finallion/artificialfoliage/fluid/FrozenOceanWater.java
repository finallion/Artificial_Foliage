package com.finallion.artificialfoliage.fluid;

import com.finallion.artificialfoliage.registry.ARFOBlocks;
import com.finallion.artificialfoliage.registry.ARFOFluids;
import com.finallion.artificialfoliage.registry.ARFOItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class FrozenOceanWater extends ARFOWater {

    @Override
    public Fluid getFlowing() {
        return ARFOFluids.FLOWING_FROZEN_OCEAN_WATER;
    }

    @Override
    public Fluid getStill() {
        return ARFOFluids.STILL_FROZEN_OCEAN_WATER;
    }

    @Override
    public Item getBucketItem() {
        return ARFOItems.FROZEN_OCEAN_WATER_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return (BlockState) ARFOBlocks.FROZEN_OCEAN_WATER.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }


    public static class Flowing extends FrozenOceanWater {
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

    public static class Still extends FrozenOceanWater {
        public int getLevel(FluidState state) {
            return 8;
        }

        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
