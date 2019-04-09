package arthurbambou.paintingmod.mainmod.coloredfunctions;

import arthurbambou.paintingmod.mainmod.api.ColoredFunction;
import arthurbambou.paintingmod.mainmod.api.ColoredObject;
import arthurbambou.paintingmod.mainmod.coloredblocks.ColoredFenceGate;
import arthurbambou.paintingmod.mainmod.coloredblocks.ColoredWall;
import arthurbambou.paintingmod.mainmod.registery.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FenceGateFunction extends ColoredFunction {
    public FenceGateFunction(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void paint(ItemUsageContext var1, ColoredObject coloredObject) {
        BlockPos pos = var1.getBlockPos();
        World worldIn = var1.getWorld();
        PlayerEntity player = var1.getPlayer();
        BlockState blockState = worldIn.getBlockState(pos);
        if (coloredObject instanceof ColoredFenceGate
                && worldIn.getBlockState(pos).getBlock() == coloredObject.replace) {
            for (int a = 0; a < coloredObject.getArrayList().size(); a++) {
                if (player.getStackInHand(Hand.MAIN).getItem() == ModItems.PAINTBRUSHES.get(a + 1)) {
                    worldIn.setBlockState(pos, coloredObject.getArrayList().get(a).getStateFactory().getDefaultState()
                            .with(HorizontalFacingBlock.field_11177, blockState.get(HorizontalFacingBlock.field_11177))
                            .with(FenceGateBlock.OPEN, blockState.get(FenceGateBlock.OPEN))
                            .with(FenceGateBlock.POWERED, blockState.get(FenceGateBlock.POWERED))
                            .with(FenceGateBlock.IN_WALL, blockState.get(FenceGateBlock.IN_WALL)));
                    usedpaintbrush(player);
                }
            }
        }
    }

    @Override
    public void unPaint(ItemUsageContext var1, ColoredObject coloredObject) {
        BlockPos pos = var1.getBlockPos();
        World worldIn = var1.getWorld();
        PlayerEntity player = var1.getPlayer();
        BlockState blockState = worldIn.getBlockState(pos);
        if (coloredObject instanceof ColoredFenceGate) {
            for (int b = 0; b < coloredObject.getArrayList().size(); b++) {
                if (worldIn.getBlockState(pos).getBlock() == coloredObject.getArrayList().get(b)) {
                    worldIn.setBlockState(pos, coloredObject.replace.getStateFactory().getDefaultState()
                            .with(HorizontalFacingBlock.field_11177, blockState.get(HorizontalFacingBlock.field_11177))
                            .with(FenceGateBlock.OPEN, blockState.get(FenceGateBlock.OPEN))
                            .with(FenceGateBlock.POWERED, blockState.get(FenceGateBlock.POWERED))
                            .with(FenceGateBlock.IN_WALL, blockState.get(FenceGateBlock.IN_WALL)));
                    heatgun(player.getStackInHand(Hand.MAIN));
                }
            }
        }
    }

    @Override
    public boolean isFromType(ColoredObject coloredObject) {
        if (coloredObject instanceof ColoredFenceGate) return true;
        return false;
    }

    @Override
    public Class getColoredObjectType() {
        return ColoredFenceGate.class;
    }
}
