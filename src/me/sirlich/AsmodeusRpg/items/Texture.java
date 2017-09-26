package me.sirlich.AsmodeusRpg.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Texture {

    WOOD_SWORD(Material.WOOD_SWORD, 0);

    private Material m;
    private short durability;

    Texture(Material m, int durability) {
        this.m = m;
        this.durability = (short) durability;
    }

    public ItemStack getItem() {
        return new ItemStack(m, 1, durability);
    }

}
