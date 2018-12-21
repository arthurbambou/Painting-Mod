package arthurbambou.paintingmod.api;

import arthurbambou.paintingmod.blocks.ColoredBlockBlock;
import arthurbambou.paintingmod.registery.ModBlocks;
import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;

public class ColoredBlock {
    private String name;
    private Material material;
    private BlockSoundGroup blockSoundGroup;
    private float hardness;
    private float resistance;
    private Tag<Item> harvestTool;
    private int harvestLevel;

    public Block black;
    public Block red;
    public Block green;
    public Block brown;
    public Block blue;
    public Block purple;
    public Block cyan;
    public Block lightgray;
    public Block gray;
    public Block pink;
    public Block lime;
    public Block yellow;
    public Block lightblue;
    public Block magenta;
    public Block orange;
    public Block white;
    public Block replace;
    private Block.Settings settings;

    public ColoredBlock(String name, Block replace) {
        this.name = name;
        this.replace = replace;
        ModBlocks.COLORED_BLOCKS.add(this);
    }

    public void createBlocks() {
        getSettings();
        this.black = new ColoredBlockBlock("black_" + this.name, this.settings);
        this.red = new ColoredBlockBlock("red_" + this.name, this.settings);
        this.green = new ColoredBlockBlock("green_" + this.name, this.settings);
        this.brown = new ColoredBlockBlock("brown_" + this.name, this.settings);
        this.blue = new ColoredBlockBlock("blue_" + this.name, this.settings);
        this.purple = new ColoredBlockBlock("purple_" + this.name, this.settings);
        this.cyan = new ColoredBlockBlock("cyan_" + this.name, this.settings);
        this.lightgray = new ColoredBlockBlock("light_gray_" + this.name, this.settings);
        this.gray = new ColoredBlockBlock("gray_" + this.name, this.settings);
        this.pink = new ColoredBlockBlock("pink_" + this.name, this.settings);
        this.lime = new ColoredBlockBlock("lime_" + this.name, this.settings);
        this.yellow = new ColoredBlockBlock("yellow_" + this.name, this.settings);
        this.lightblue = new ColoredBlockBlock("light_blue_" + this.name, this.settings);
        this.magenta = new ColoredBlockBlock("magenta_" + this.name, this.settings);
        this.orange = new ColoredBlockBlock("orange_" + this.name, this.settings);
        this.white = new ColoredBlockBlock("white_" + this.name, this.settings);
        ModBlocks.COLORED_BLOCK_LIST.add(this);
    }

    private void getSettings() {
        this.settings = FabricBlockSettings.of(this.material).strength(this.hardness, this.resistance).sounds(this.blockSoundGroup).build();;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public ColoredBlock setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public BlockSoundGroup getBlockSoundGroup() {
        return blockSoundGroup;
    }

    public ColoredBlock setBlockSoundGroup(BlockSoundGroup blockSoundGroup) {
        this.blockSoundGroup = blockSoundGroup;
        return this;
    }

    public float getHardness() {
        return hardness;
    }

    public ColoredBlock setHardness(float hardness) {
        this.hardness = hardness;
        return this;
    }

    public float getResistance() {
        return resistance;
    }

    public ColoredBlock setResistance(float resistance) {
        this.resistance = resistance;
        return this;
    }

//    public Tag<Item> getHarvestTool() {
//        return harvestTool;
//    }
//
//    public int getHarvestLevel() {
//        return harvestLevel;
//    }
//
//    public void setHarvestProperty(Tag<Item> harvestTool, int harvestLevel) {
//        this.harvestTool = harvestTool;
//        this.harvestLevel = harvestLevel;
//    }
}