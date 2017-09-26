package me.sirlich.AsmodeusRpg.items;

import de.tr7zw.itemnbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemHandler {

    public static HashMap<String, RPGWeapon> weapons = new HashMap<>();

    public static RPGWeapon getWeaponFromString(String s) {
        if (weapons.containsKey(s)) {
            return weapons.get(s);
        }
        return null;
    }

    public static RPGWeapon getWeaponFromItem(ItemStack i) {
        NBTItem nbt = new NBTItem(i);
        if (weapons.containsKey(nbt.getString("Identifier"))) {
            return weapons.get(nbt.getString("Identifier"));
        }
        return null;
    }

}
