package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class Season {

	public static final Season normal = new Season("Normal");
	public static final Season winterMode = new SWinterMode("Winter Mode");

	private String name;

	public Season(String name) {
		this.name = name;
	}

	public void tick(Minecraft minecraft) {}

	public boolean canSnow(final BiomeGenBase biome) {
		return biome.enableSnow;
	}

	public String name() { return name; }

}
