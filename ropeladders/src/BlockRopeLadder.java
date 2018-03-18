package net.minecraft.src;

import java.util.Random;

public class BlockRopeLadder extends BlockLadder {

    public BlockRopeLadder(int id, String name, String tex) {
        super(id, 0);
        setBlockName(name);
        ModLoader.RegisterBlock(this);
        ModLoader.AddName(this, name);
        blockIndexInTexture = ModLoader.addOverride("/terrain.png", tex);
        setHardness(0.4f);
        Item.itemsList[blockID].setIconIndex(blockIndexInTexture);
    }

    @Override
    public int getRenderType() {
        return mod_RopeLadders.ropeLadderRenderID;
    }

    private int getLadderBottom(World world, int x, int y, int z, boolean place) {
        for (; y >= 0; --y) {
            final int id = world.getBlockId(x, y, z);
            if (id != blockID) {
                if (place && id != 0) {
                    y = 0;
                } else if (!place) {
                    ++y;
                }
                break;
            }
        }
        return y;
    }
	
    @Override
    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer player) {
        final int y = getLadderBottom(world, i, j, k, !player.isSneaking()); 

        if (player.isSneaking()) {
            if (y == j) return true;

            world.setBlockAndMetadataWithNotify(i, y, k, 0, 0);
            
            ItemStack item = new ItemStack(mod_RopeLadders.ropeLadder);
        	if (!player.inventory.addItemStackToInventory(item)) {
                Random rand = world.rand;
                float f = 0.7F;
                double d =  (double) (rand.nextFloat() * f + 1.0F - f) * 0.5D + i;
                double d1 = (double) (rand.nextFloat() * f + 1.0F - f) * 0.5D + j + 0.5;
                double d2 = (double) (rand.nextFloat() * f + 1.0F - f) * 0.5D + k;
                EntityItem entityitem = new EntityItem(world, d, d1, d2, item);
                entityitem.delayBeforeCanPickup = 10;
                world.entityJoinedWorld(entityitem);
            }

            return true;
            
        } else {
            ItemStack item = player.inventory.getCurrentItem();
            if (item == null) { 
                return false;
            } else if (item.getItem().shiftedIndex != blockID) {
                return false;
            }

            if (y == 0) {
                return false;
            } else {
                final int meta = world.getBlockMetadata(i, j, k);
                world.setBlockAndMetadataWithNotify(i, y, k, blockID, meta);
                --item.stackSize;
            }
        }

        return false;
    }

    private boolean isSupported(World world, int i, int j, int k, int l) {
        if (j > 127 || world.getBlockId(i, j, k) != blockID) return false;

        int meta = world.getBlockMetadata(i, j, k);
        if (meta == 2 && world.isBlockNormalCube(i, j, k + 1)) {
            return true;
        } else if (meta == 3 && world.isBlockNormalCube(i, j, k - 1)) {
            return true;
        } else if (meta == 4 && world.isBlockNormalCube(i + 1, j, k)) {
            return true;
        } else if (meta == 5 && world.isBlockNormalCube(i - 1, j, k)) {
            return true;
        }

        return isSupported(world, i, j + 1, k, l);
    }

    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
        if (!isSupported(world, i, j, k, l)) {
            dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k));
            world.setBlockWithNotify(i, j, k, 0);
        }
    }
}
