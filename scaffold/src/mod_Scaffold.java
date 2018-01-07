package net.minecraft.src;

public class mod_Scaffold extends BaseMod {

    public static BlockScaffold scaffoldWood;

    public mod_Scaffold() {
        scaffoldWood = new BlockScaffold(215, "Wooden Scaffold", "/scaffold/wood.png");
        ModLoader.AddRecipe(new ItemStack(scaffoldWood, 16), new Object[] {
                            "WWW",
                            " S ",
                            "S S",
                            Character.valueOf('W'), Block.planks,
                            Character.valueOf('S'), Item.stick});
    }

    public String Version() { return "1.7.3"; }

}
