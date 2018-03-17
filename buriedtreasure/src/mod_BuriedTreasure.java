package net.minecraft.src;

import java.util.Random;

public class mod_BuriedTreasure extends BaseMod {

    public static final int CHEST_CHANCE = 20;
    public static final boolean DEBUG = false;

    @Override
    public void GenerateSurface(World world, Random random, int i, int j) {
        if (random.nextInt(CHEST_CHANCE) != 0) return; 

        final int x = i + random.nextInt(16);
        final int z = j + random.nextInt(16); 
        int y = 127;
        boolean underwater = false;
        for (; y > 0; --y) {
            final int id = world.getBlockId(x, y, z);
            if (id == Block.sand.blockID) {
                if (random.nextInt(2) == 0) continue;
                --y;

                world.setBlockWithNotify(x, y, z, Block.chest.blockID);
                TileEntityChest chest = (TileEntityChest) world.getBlockTileEntity(x, y, z);
                for (int k = 0; k < 3; ++k) {
                    ItemStack loot = pickLoot(random, underwater);
                    final int slot = random.nextInt(chest.getSizeInventory());
                    chest.setInventorySlotContents(slot, loot);
                }

                if (DEBUG) System.out.printf("Generated chest at %d %d %d\n", x ,y ,z);
                return;
            } else if (id == Block.waterStill.blockID || id == Block.waterMoving.blockID) {
                underwater = true;
            }
        }
    }

    /* TODO: proper loot table API */
    private ItemStack pickLoot(Random random, boolean underwater) {
        final int i = random.nextInt(10);    
        switch (i) {
            case 0: return new ItemStack(Item.saddle);
            case 1: return new ItemStack(Item.ingotIron, random.nextInt(6) + 2);
            case 2: return new ItemStack(Item.ingotGold, random.nextInt(3) + 2);
            case 3: return new ItemStack(Item.book);
            case 4: return new ItemStack(Item.dyePowder, 1, 3);
            case 5: return new ItemStack(Item.diamond);
            case 6: return new ItemStack(Item.gunpowder, random.nextInt(4) + 2);
            case 7: return new ItemStack(Block.tnt, random.nextInt(3) + 1);
            case 8: return underwater ? new ItemStack(Block.blockGold) : null;
            case 9: return underwater ? new ItemStack(Item.lightStoneDust, random.nextInt(12) + 4) : null;
            default: return null;
        }
    }

    public String Version() { return "1.7.3"; }

}
