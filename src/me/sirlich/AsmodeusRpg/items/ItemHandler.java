package me.sirlich.AsmodeusRpg.items;

import de.tr7zw.itemnbtapi.NBTItem;
import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.items.attackevents.Heal;
import me.sirlich.AsmodeusRpg.items.attackevents.Hit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ItemHandler
{

    public static HashMap<String, RPGWeapon> weapons = new HashMap<>();

    public static HashMap<String, MiscItem> misc = new HashMap<>();

    public static HashMap<String, UseItem> use = new HashMap<>();

    public static RPGWeapon getWeaponFromString(String s)
    {
        if (weapons.containsKey(s)) {
            return weapons.get(s);
        }
        return null;
    }

    public static RPGWeapon getWeaponFromItem(ItemStack i)
    {
        NBTItem nbt = new NBTItem(i);
        if (weapons.containsKey(nbt.getString("Identifier"))) {
            return weapons.get(nbt.getString("Identifier"));
        }
        return null;
    }

    public static MiscItem getMiscFromString(String s)
    {
        if (misc.containsKey(s)) {
            return misc.get(s);
        }
        return null;
    }

    public static MiscItem getMiscFromItem(ItemStack i)
    {
        NBTItem nbt = new NBTItem(i);
        if (misc.containsKey(nbt.getString("Identifier"))) {
            return misc.get(nbt.getString("Identifier"));
        }
        return null;
    }

    public static UseItem getUseFromString(String s)
    {
        if (use.containsKey(s)) {
            return use.get(s);
        }
        return null;
    }

    public static UseItem getUseFromItem(ItemStack i)
    {
        NBTItem nbt = new NBTItem(i);
        if (use.containsKey(nbt.getString("Identifier"))) {
            return use.get(nbt.getString("Identifier"));
        }
        return null;
    }

    public static void loadItems() {

        File weps = new File(AsmodeusRpg.getInstance().getDataFolder() + "/items/weapons");
        for (File f : weps.listFiles()) {
            loadWeapon(f);
        }


    }

    public static void loadWeapon(File f) {

        Scanner scan = null;

        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String next;

        RPGWeapon wep = new RPGWeapon(f.getName().replace(".yml", ""));

        wep.name(scan.nextLine().trim());

        next = scan.nextLine().trim();
        System.out.println(next);

        int level;

        if (next.startsWith("Level")) {
            System.out.println("inside level hi mom");
            level = Integer.parseInt(next.substring(6));
        } else {
            System.out.println("very sad");
            level = 0;
        }

        wep.level(level);

        wep.rarity(scan.nextLine().trim());

        wep.texture(Texture.fromString(scan.nextLine().trim()));

        String[] args = scan.nextLine().trim().split(" ");

        wep.primaryEvent(AttackEventHandler.getFromString(args));

        args = scan.nextLine().trim().split(" ");

        wep.secondaryEvent(AttackEventHandler.getFromString(args));

        String[] description = scan.nextLine().trim().split("~");

        wep.description(description);

        wep.finish();

    }

}
