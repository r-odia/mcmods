package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class mod_OldCobble extends BaseMod {

    private static void overrideTerrain(String tex, int idx) {
        try {
            Field field = ModLoader.class.getDeclaredField("overrides");
            field.setAccessible(true);
            Map overrides = (Map) field.get(null);

            Map obj = (Map) overrides.get(0);
            if (obj == null) {
                obj = new HashMap();
                overrides.put(0, obj);
            }
            
            obj.put(tex, idx);

        } catch (Exception e) {
            System.out.println("Error swapping texture!");
            e.printStackTrace();
        }
    }

    public mod_OldCobble() {
        overrideTerrain("/oldcobble/cobblestone.png", 16);
    }

    public String Version() { return "1.7.3"; }
}
