package me.sirlich.AsmodeusRpg.items;

import de.tr7zw.itemnbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class UseItem extends RPGItem {

    public int uses;
    public int currentUses;
    public int level;
    public int cooldown;
    public int currentCooldown;
    public String type;
    public String color;

    public UseItem(String identifier)
    {
        this.identifier = "use_" + identifier;
    }

    public UseItem description(String... description)
    {
        this.description = description.clone();
        return this;
    }

    public UseItem texture(Texture t)
    {
        this.material = t.getItem().getType();
        this.durability = t.getItem().getDurability();
        return this;
    }

    public UseItem name(String name)
    {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        return this;
    }

    public UseItem useEvent(AttackEvent e, int uses, int cooldown) {
        this.secondaryEvent = e;
        this.uses = uses;
        this.currentUses = uses;
        this.cooldown = cooldown;
        this.currentCooldown = 0;
        return this;
    }

    public UseItem level(int level) {
        this.level = level;
        return this;
    }

    public UseItem color(String color) {
        this.color = color;
        return this;
    }

    public UseItem type(String type) {
        this.type = type;
        return this;
    }

    public void finish()
    {
        ItemHandler.use.put(this.identifier, this);
    }

    @Override
    public ItemStack generate() {
        //Texture
        ItemStack i = new ItemStack(this.material);
        i.setDurability(this.durability);
        //Name
        ItemMeta m = i.getItemMeta();
        if (this.uses > 0) {
            m.setDisplayName(this.name + " (" + this.uses + "/" + this.uses + ")");
        } else {
            m.setDisplayName(this.name);
        }
        //Lore
        ArrayList<String> lore = new ArrayList<>();
        if (description.length > 0) {
            lore.add("");
            for (String aDescription : description) {
                lore.add(ChatColor.translateAlternateColorCodes('&', aDescription));
            }
        }
        lore.add("");

        if (level != 0) {
            lore.add(ChatColor.translateAlternateColorCodes('&', color + type + " &8- " + color + "Level " + level));
        } else {
            lore.add(ChatColor.translateAlternateColorCodes('&', color + type));
        }
        m.setLore(lore);
        i.setItemMeta(m);
        //Tag
        NBTItem nbt = new NBTItem(i);
        nbt.setString("Identifier", this.identifier);
        return nbt.getItem();
    }
}
