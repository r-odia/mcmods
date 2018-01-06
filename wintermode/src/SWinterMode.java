package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class SWinterMode extends Season {

	public SWinterMode(String name) { super(name); }

	@Override
	public void tick(Minecraft minecraft) {
		final WorldInfo info = minecraft.theWorld.worldInfo;
		info.setRaining(true);
		info.setRainTime(1000);
	}

	@Override
	public boolean canSnow(final BiomeGenBase biome) {
		return true;
	}

}
