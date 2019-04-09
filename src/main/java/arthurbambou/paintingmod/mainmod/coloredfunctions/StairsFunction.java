package arthurbambou.paintingmod.mainmod.coloredfunctions;

import arthurbambou.paintingmod.mainmod.api.ColoredFunction;
import arthurbambou.paintingmod.mainmod.api.ColoredObject;
import arthurbambou.paintingmod.mainmod.coloredblocks.ColoredBlock;
import arthurbambou.paintingmod.mainmod.coloredblocks.ColoredStairs;
import arthurbambou.paintingmod.mainmod.registery.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StairsFunction extends ColoredFunction {
    public StairsFunction(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void paint(ItemUsageContext var1, ColoredObject coloredObject) {
        BlockPos pos = var1.getBlockPos();
        World worldIn = var1.getWorld();
        PlayerEntity player = var1.getPlayer();
        BlockState blockState = worldIn.getBlockState(pos);
        if (coloredObject instanceof ColoredStairs
                && worldIn.getBlockState(pos).getBlock() == coloredObject.replace) {
            for (int a = 0; a < coloredObject.getArrayList().size(); a++) {
                if (player.getStackInHand(Hand.MAIN).getItem() == ModItems.PAINTBRUSHES.get(a + 1)) {
                    worldIn.setBlockState(pos, coloredObject.getArrayList().get(a).getStateFactory().getDefaultState()
                            .with(StairsBlock.WATERLOGGED, blockState.get(StairsBlock.WATERLOGGED))
                            .with(StairsBlock.HALF, blockState.get(StairsBlock.HALF))
                            .with(StairsBlock.FACING, blockState.get(StairsBlock.FACING))
                            .with(StairsBlock.SHAPE, blockState.get(StairsBlock.SHAPE)));
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
        if (coloredObject instanceof ColoredStairs) {
            for (int b = 0; b < coloredObject.getArrayList().size(); b++) {
                if (worldIn.getBlockState(pos).getBlock() == coloredObject.getArrayList().get(b)) {
                    worldIn.setBlockState(pos, coloredObject.replace.getStateFactory().getDefaultState()
                            .with(StairsBlock.WATERLOGGED, blockState.get(StairsBlock.WATERLOGGED))
                            .with(StairsBlock.HALF, blockState.get(StairsBlock.HALF))
                            .with(StairsBlock.FACING, blockState.get(StairsBlock.FACING))
                            .with(StairsBlock.SHAPE, blockState.get(StairsBlock.SHAPE)));
                    heatgun(player.getStackInHand(Hand.MAIN));
                }
            }
        }
    }

    @Override
    public boolean isFromType(ColoredObject coloredObject) {
        if (coloredObject instanceof ColoredStairs) return true;
        return false;
    }

    @Override
    public Class getColoredObjectType() {
        return ColoredStairs.class;
    }
}
