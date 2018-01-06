package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class mod_WinterMode extends BaseMod {

	public static Season season = Season.winterMode;

	public mod_WinterMode() {
		ModLoader.SetInGameHook(this, true, true);
	}

	public String Version() { return "0.1"; }

	@Override
	public boolean OnTickInGame(Minecraft minecraft) {
		season.tick(minecraft);	
		return true;
	}

}
