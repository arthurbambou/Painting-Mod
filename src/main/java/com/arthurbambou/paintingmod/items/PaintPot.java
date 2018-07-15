package com.arthurbambou.paintingmod.items;

import com.arthurbambou.paintingmod.Main;
import com.arthurbambou.paintingmod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;

public class PaintPot extends ItemBase
{

	public PaintPot(String name) {
		super(name);
		
		setMaxDamage(5);
		setMaxStackSize(1);
		setNoRepair();
	}

}