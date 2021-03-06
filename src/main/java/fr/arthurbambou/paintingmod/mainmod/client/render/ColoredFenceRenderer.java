package fr.arthurbambou.paintingmod.mainmod.client.render;

import fr.arthurbambou.paintingmod.mainmod.api.ColoredBlock;
import fr.arthurbambou.paintingmod.mainmod.api.ColoredObject;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelItemPropertyOverrideList;
import net.minecraft.client.texture.Sprite;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ExtendedBlockView;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.Random;
import java.util.function.Supplier;

public class ColoredFenceRenderer extends AbstractColoredRenderer {

    public ColoredFenceRenderer(ColoredBlock coloredblock) {
        super(coloredblock);
    }

    @Override
    public void emitBlockQuads(ExtendedBlockView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        context.meshConsumer().accept(mesh(false, state.get(FenceBlock.EAST), state.get(FenceBlock.WEST), state.get(FenceBlock.SOUTH), state.get(FenceBlock.NORTH)));
    }

    private Mesh mesh(boolean inventory,boolean east, boolean west, boolean south, boolean north) {
        Renderer renderer = RendererAccess.INSTANCE.getRenderer();
        MeshBuilder builder = renderer.meshBuilder();
        QuadEmitter emitter = builder.getEmitter();

        RenderMaterial mat = renderer.materialFinder().disableDiffuse(0, true).find();
        int color = coloredBlock.getColor().getColor().getRGB();

        Sprite sprite = MinecraftClient.getInstance().getSpriteAtlas().getSprite(coloredBlock.getColoredObject().getTextureMap().get(ColoredObject.TextureFace.ALL));
        if (inventory) {
            inventoryModel(emitter, mat, color, sprite);
        } else {
            postModel(emitter, mat, color, sprite);
            if (south) southModel(emitter, mat, color, sprite);
//            if (north) northModel(emitter, mat, color, sprite);
//            if (east) eastModel(emitter, mat, color, sprite);
//            if (west) westModel(emitter, mat, color, sprite);
        }

        return builder.build();
    }

    private void inventoryModel(QuadEmitter emitter, RenderMaterial mat, int color, Sprite sprite) {

        // Left Post
        emitter.square(Direction.DOWN, 0.375F, 0, 0.625F, 0.25F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.UP, 0.375F, 0, 0.625F, 0.25F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.NORTH, 0.375F, 0, 0.625F, 1.0F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.SOUTH, 0.375F, 0, 0.625F, 1.0F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.WEST, 0F, 0, 0.25F, 1.0F, 0.375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.EAST, 0F, 0, 0.25F, 1.0F, 0.375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();

        // Right Post
        emitter.square(Direction.DOWN, 0.375F, 0.75F, 0.625F, 1, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.UP, 0.375F, 0.75F, 0.625F, 1, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.NORTH, 0.375F, 0, 0.625F, 1.0F, 0.75F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.SOUTH, 0.375F, 0, 0.625F, 1.0F, 0.75F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.WEST, 0.75F, 0, 1, 1.0F, 0.375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.EAST, 0.75F, 0, 1, 1.0F, 0.375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();

        // Top bar
        emitter.square(Direction.DOWN, 0.4375F, 0, 0.5625F, 1, 1-0.0625F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.UP, 0.4375F, 0, 0.5625F, 1, 0.1875F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.NORTH, 0.4375F, 0.0625F, 0.5625F, 0.1875F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.SOUTH, 0.4375F, 0.0625F, 0.5625F, 0.1875F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.WEST, 0, 1-0.0625F, 1, 1-0.1875F, 0.4375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.EAST, 0, 1-0.0625F, 1, 1-0.1875F, 0.4375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();

        // Top bar
        emitter.square(Direction.DOWN, 0.4375F, 0, 0.5625F, 1, 1-0.5625F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.UP, 0.4375F, 0, 0.5625F, 1, 0.6875F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.NORTH, 0.4375F, 0.5625F, 0.5625F, 0.6875F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.SOUTH, 0.4375F, 0.5625F, 0.5625F, 0.6875F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.WEST, 0, 1-0.5625F, 1, 1-0.6875F, 0.4375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.EAST, 0, 1-0.5625F, 1, 1-0.6875F, 0.4375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
    }

    private void postModel(QuadEmitter emitter, RenderMaterial mat, int color, Sprite sprite) {
        emitter.square(Direction.SOUTH, 0.375F, 0, 0.625F, 1.0F, 0.375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.EAST, 0.375F, 0, 0.625F, 1.0F, 0.375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.WEST, 0.375F, 0, 0.625F, 1.0F, 0.375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.NORTH, 0.375F, 0, 0.625F, 1.0F, 0.375F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.DOWN, 0.375F, 0.375F, 0.625F, 0.625F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.UP, 0.375F, 0.375F, 0.625F, 0.625F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
    }

    private void southModel(QuadEmitter emitter, RenderMaterial mat, int color, Sprite sprite) {
//        emitter.square(Direction.SOUTH, 0.3125F, 0, 0.6875F, 0.875F, 0.0F)
//                .material(mat)
//                .spriteColor(0, color, color, color, color)
//                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.EAST, 0F, 0.0625F, 0.5625F, 0.25F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.WEST, 0, 0.0625F, 0.5625F, 0.25F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.NORTH, 0.4375F, 0.0625F, 0.5625F, 0.25F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.DOWN, 0.4375F, 0, 0.5625F, 0.5625F, 0.0625F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.UP, 0.4375F, 0, 0.5625F, 0.5625F, 0.0625F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
    }

    private void northModel(QuadEmitter emitter, RenderMaterial mat, int color, Sprite sprite) {
        emitter.square(Direction.SOUTH, 0.3125F, 0, 0.6875F, 0.875F, 0.3125F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.EAST, 1F-0.6875F, 0, 1F, 0.875F, 0.3125F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.WEST, 0F, 0, 0.6875F, 0.875F, 0.3125F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.NORTH, 0.3125F, 0, 0.6875F, 0.875F, 0F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.DOWN, 0.3125F, 1F, 0.6875F, 0.6875F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.UP, 0.3125F, 0.3125F, 0.6875F, 1F, 1-0.875F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
    }

    private void eastModel(QuadEmitter emitter, RenderMaterial mat, int color, Sprite sprite) {
        emitter.square(Direction.EAST, 0.3125F, 0, 0.6875F, 0.875F, 0.0F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.SOUTH, 0F, 0, 0.6875F, 0.875F, 0.3125F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.NORTH, 1F-0.6875F, 0, 1F, 0.875F, 0.3125F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.WEST, 0.3125F, 0, 0.6875F, 0.875F, 0.3125F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.DOWN, 0.3125F, 0.3125F, 1F, 0.6875F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.UP, 0.3125F, 0.3125F, 1F, 0.6875F, 1-0.875F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
    }

    private void westModel(QuadEmitter emitter, RenderMaterial mat, int color, Sprite sprite) {
        emitter.square(Direction.EAST, 0.3125F, 0, 0.6875F, 0.875F, 0.3125F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.SOUTH, 0.3125F, 0, 1F, 0.875F, 0.3125F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.NORTH, 0.0F, 0, 0.6875F, 0.875F, 0.3125F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.WEST, 0.3125F, 0, 0.6875F, 0.875F, 0F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.DOWN, 0.0F, 0.3125F, 0.6875F, 0.6875F, 0)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
        emitter.square(Direction.UP, 0.0F, 0.3125F, 0.6875F, 0.6875F, 1-0.875F)
                .material(mat)
                .spriteColor(0, color, color, color, color)
                .spriteBake(0, sprite, MutableQuadView.BAKE_LOCK_UV).emit();
    }

    @Override
    public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
        context.meshConsumer().accept(mesh(true,false, false, false, false));
    }

    protected class ItemProxy extends ModelItemPropertyOverrideList {
        public ItemProxy() {
            super(null, null, null, Collections.emptyList());
        }

        @Override
        public BakedModel apply(BakedModel bakedModel_1, ItemStack itemStack_1, World world_1, LivingEntity livingEntity_1) {
            return ColoredFenceRenderer.this;
        }
    }

    @Override
    public ModelItemPropertyOverrideList getItemPropertyOverrides() {
        return new ItemProxy();
    }
}
