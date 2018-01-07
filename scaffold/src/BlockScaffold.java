package net.minecraft.src;

public class BlockScaffold extends Block {
    
    public BlockScaffold(int id, String name, String tex) {
        super(id, Material.wood); 
        setBlockName(name); 
        ModLoader.RegisterBlock(this); 
        blockIndexInTexture = ModLoader.addOverride("/terrain.png", tex);
        ModLoader.AddName(this, name);
        setHardness(0.2f);
    }

    @Override
    public int getBlockTextureFromSide(int i) {
        if (i < 2) return Block.planks.blockIndexInTexture;
        return blockIndexInTexture;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

}
