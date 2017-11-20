package me.sirlich.AsmodeusRpg.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Texture
{

    PRIMARY(Material.WOOD_SWORD, 0),
    SECONDARY(Material.STICK, 0),
    DUAL(Material.BLAZE_ROD, 0),
    TEXTURE(Material.STONE, 0),
    POTION(Material.POTION, 8197);

    private Material m;
    private short durability;

    Texture(Material m, int durability)
    {
        this.m = m;
        this.durability = (short) durability;
    }

    public ItemStack getItem()
    {
        return new ItemStack(m, 1, durability);
    }

    public static Texture fromString(String s) {
        String st = s.toUpperCase();
        for (Texture t : Texture.values()) {
            if (t.toString().toUpperCase().equalsIgnoreCase(st)) {
                return t;
            }
        }
        return null;
    }

}
