package me.sirlich.AsmodeusRpg.items;

import de.tr7zw.itemnbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MiscItem extends RPGItem {

    public String type;

    public MiscItem(String identifier)
    {
        this.identifier = "misc_" + identifier;
    }

    public MiscItem description(String... description)
    {
        this.description = description.clone();
        return this;
    }

    public MiscItem texture(Texture t)
    {
        this.material = t.getItem().getType();
        this.durability = t.getItem().getDurability();
        return this;
    }

    public MiscItem name(String name)
    {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        return this;
    }

    public MiscItem type(String type) {
        this.type = type;
        return this;
    }

    public void finish()
    {
        ItemHandler.misc.put(this.identifier, this);
    }

    @Override
    public ItemStack generate() {
        //Texture
        ItemStack i = new ItemStack(this.material);
        i.setDurability(this.durability);
        //Name
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(this.name);
        //Lore
        ArrayList<String> lore = new ArrayList<>();
        if (description.length > 0) {
            lore.add("");
            for (String aDescription : description) {
                lore.add(ChatColor.translateAlternateColorCodes('&', aDescription));
            }
        }
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7" + type + " Item"));
        m.setLore(lore);
        i.setItemMeta(m);
        //Tag
        NBTItem nbt = new NBTItem(i);
        nbt.setString("Identifier", this.identifier);
        return nbt.getItem();
    }
}
