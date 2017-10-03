package me.sirlich.AsmodeusRpg.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/*public class RPGItem {

    public static HashMap<String, RPGItem> items = new HashMap<>();
    public static HashMap<String, RPGWeapon> weapons = new HashMap<>();

    private ItemStack item;

    public RPGItem(Texture t, String name, String identifier) {
        item.setType(t.getItem().getType());
        item.setDurability(t.getItem().getDurability());
        ItemMeta m = item.getItemMeta();
        m.setDisplayName(name);
        item.setItemMeta(m);
        NBTItem nbt = new NBTItem(item);
        nbt.setString("Identifier", identifier);
        item = nbt.getItem();
        items.put(identifier, this);
    }

    public void giveItem(Player p) {
        p.getInventory().addItem(item);
    }

    public static RPGItem getItem(ItemStack i) {
        NBTItem item = new NBTItem(i);
        if (item.hasKey("Identifier")) {
            if (items.containsKey(item.getString("Identifier"))) {
                return items.get(item.getString("Identifier"));
            }
        }
        return null;
    }

    public static RPGWeapon getWeapon(ItemStack i) {
        NBTItem item = new NBTItem(i);
        if (item.hasKey("Identifier")) {
            if (weapons.containsKey(item.getString("Identifier"))) {
                return weapons.get(item.getString("Identifier"));
            }
        }
        return null;
    }

    public static RPGItem getItem(String s) {
        if (items.containsKey(s)) {
            return items.get(s);
        }
        return null;
    }

    public static RPGWeapon getWeapon(String s) {
        if (weapons.containsKey(s)) {
            return weapons.get(s);
        }
        return null;
    }

}*/

public abstract class RPGItem
{

    public String identifier = null;
    public String name = null;
    public Material material = null;
    public short durability = 0;
    public String[] description = null;

    public abstract ItemStack generate();
}
