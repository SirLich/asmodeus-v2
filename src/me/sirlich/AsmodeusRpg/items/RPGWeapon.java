package me.sirlich.AsmodeusRpg.items;

import de.tr7zw.itemnbtapi.NBTItem;
import me.sirlich.AsmodeusRpg.utilities.Range;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class RPGWeapon extends RPGItem
{
    /*public RPGWeapon(Texture t, String name, String identifier, Rarity r, boolean hasPrimaryAttack, int damage1Min, int damage1Max, double damage1Range,
                     double stamina1, boolean hasSecondaryAttack, int damage2Min, int damage2Max, double damage2Range, double stamina2,
                     Material damage2Item, SpecialType damage2Type, Particle damage2Particle) {

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");

        if (hasPrimaryAttack) {
            primaryDamage = new Range<Integer>(damage1Min, damage1Max, null);
            primaryStamina = stamina1;
            if (damage1Range < 1) {
                primaryRange = 3.0;
            } else {
                primaryRange = damage1Range;
            }
            lore.add(ChatColor.translateAlternateColorCodes('&',"&fPrimary Attack"));
            lore.add(ChatColor.translateAlternateColorCodes('&',"&6&o- Damage: &e&o" + damage1Min + "-" + damage1Max));
            if (primaryRange.toString().endsWith(".0")) {
                lore.add(ChatColor.translateAlternateColorCodes('&',"&6&o- Range: &e&o" + primaryRange.intValue() + " Blocks"));
            } else {
                lore.add(ChatColor.translateAlternateColorCodes('&',"&6&o- Range: &e&o" + primaryRange + " Blocks"));
            }
            if (primaryStamina.toString().endsWith(".0")) {
                lore.add(ChatColor.translateAlternateColorCodes('&',"&6&o- Stamina Cost: &e&o" + primaryStamina.intValue()));
            } else {
                lore.add(ChatColor.translateAlternateColorCodes('&',"&6&o- Stamina Cost: &e&o" + primaryStamina));
            }
            lore.add("");
            lore.add(r.color + "&o" + r.name + " &r" + r.color + "Rarity &8- " + r.color + "Level 10");
        }
        if (hasSecondaryAttack) {
            specialDamage = new Range<Integer>(damage2Min, damage2Max, null);
            this.specialType = damage2Type;
            this.specialItem = damage2Item;
            this.specialParticle = damage2Particle;
            specialStamina = stamina2;
            secondaryRange = damage2Range;
        }
        ItemMeta m = item.getItemMeta();
        m.setLore(lore);
        m.setDisplayName(ChatColor.translateAlternateColorCodes('&', r.color + name));
        item.setItemMeta(m);
        RPGItem.weapons.put(identifier, this);


    }*/

    private Rarity r;

    private Range<Integer> primaryDamage;
    private Range<Integer> specialDamage;

    private SpecialType specialType;
    private Material specialItem;
    private Particle specialParticle;

    private Double primaryRange;
    private Double secondaryRange;

    private Double primaryStamina;
    private Double specialStamina;

    public RPGWeapon(String identifier)
    {
        this.identifier = identifier;
    }

    public Range<Integer> getPrimaryDamage()
    {
        return primaryDamage;
    }

    public Double getPrimaryRange()
    {
        return primaryRange;
    }

    public RPGWeapon texture(Texture t)
    {
        this.material = t.getItem().getType();
        this.durability = t.getItem().getDurability();
        return this;
    }

    public RPGWeapon rarity(Rarity r)
    {
        this.r = r;
        return this;
    }

    public RPGWeapon rarity(String s)
    {
        return this.rarity(Rarity.fromString(s));
    }

    public RPGWeapon rarity(int i)
    {
        return this.rarity(Rarity.fromIdentifier(i));
    }

    public RPGWeapon primaryAttack(int min, int max, double range, double stamina)
    {
        this.primaryDamage = new Range<Integer>(min, max, null);
        this.primaryRange = range;
        this.primaryStamina = stamina;
        return this;
    }

    public RPGWeapon primaryAttack(int damage, double range, double stamina)
    {
        return this.primaryAttack(damage, damage, range, stamina);
    }

    public RPGWeapon primaryAttack(int damage, double stamina)
    {
        return this.primaryAttack(damage, damage, 3, stamina);
    }

    public RPGWeapon primaryAttack(int min, int max, double stamina)
    {
        return this.primaryAttack(min, max, 3, stamina);
    }

    public RPGWeapon specialAttack(int min, int max, double range, double stamina, Material item)
    {
        this.specialDamage = new Range<>(min, max, null);
        this.specialType = SpecialType.ITEM;
        this.specialItem = item;
        this.secondaryRange = range;
        this.specialStamina = stamina;
        return this;
    }

    public RPGWeapon specialAttack(int min, int max, double range, double stamina, Particle particle)
    {
        this.specialDamage = new Range<>(min, max, null);
        this.specialType = SpecialType.PARTICLE;
        this.specialParticle = particle;
        this.secondaryRange = range;
        this.specialStamina = stamina;
        return this;
    }

    public RPGWeapon specialAttack(int min, int max, double range, double stamina)
    {
        this.specialDamage = new Range<>(min, max, null);
        this.specialType = SpecialType.ARROW;
        this.secondaryRange = range;
        this.specialStamina = stamina;
        return this;
    }

    public RPGWeapon name(String name)
    {
        this.name = ChatColor.translateAlternateColorCodes('&', name);
        return this;
    }

    public RPGWeapon description(String... description)
    {
        this.description = description.clone();
        return this;
    }

    public void finish()
    {
        ItemHandler.weapons.put(this.identifier, this);
    }

    @Override
    public ItemStack generate()
    {
        //Texture
        ItemStack i = new ItemStack(this.material);
        i.setDurability(this.durability);
        //Name
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(this.r.getColor() + this.name);
        //Lore
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&fPrimary Attack"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6&o- Damage: &e&o" + primaryDamage.getMinimum() + "-" + primaryDamage.getMaximum()));
        if (primaryRange.toString().endsWith(".0")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', "&6&o- Range: &e&o" + primaryRange.intValue() + " Blocks"));
        } else {
            lore.add(ChatColor.translateAlternateColorCodes('&', "&6&o- Range: &e&o" + primaryRange + " Blocks"));
        }
        if (primaryStamina.toString().endsWith(".0")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', "&6&o- Stamina Cost: &e&o" + primaryStamina.intValue()));
        } else {
            lore.add(ChatColor.translateAlternateColorCodes('&', "&6&o- Stamina Cost: &e&o" + primaryStamina));
        }
        if (description.length > 0) {
            lore.add("");
            for (String aDescription : description) {
                lore.add(ChatColor.translateAlternateColorCodes('&', aDescription));
            }
        }
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', r.color + "&o" + r.name + " &r" + r.color + "Rarity &8- " + r.color + "Level 10"));
        m.setLore(lore);
        i.setItemMeta(m);
        //Tag
        NBTItem nbt = new NBTItem(i);
        nbt.setString("Identifier", this.identifier);
        return nbt.getItem();
    }

    public enum SpecialType
    {
        ARROW, ITEM, PARTICLE
    }

    public enum Rarity
    {

        COMMON("&7", 0, "Common"),
        RARE("&b", 1, "Rare"),
        EPIC("&5", 2, "Epic");

        private String color;
        private int identifier;
        private String name;

        Rarity(String color, int identifier, String name)
        {
            this.color = ChatColor.translateAlternateColorCodes('&', color);
            this.identifier = identifier;
            this.name = name;
        }

        public static Rarity fromIdentifier(int i)
        {
            for (Rarity r : Rarity.values()) {
                if (r.getIdentifier() == i) {
                    return r;
                }
            }
            return Rarity.COMMON;
        }

        public static Rarity fromString(String s)
        {
            for (Rarity r : Rarity.values()) {
                if (r.getName().equalsIgnoreCase(s)) {
                    return r;
                }
            }
            return Rarity.COMMON;
        }

        public String getColor()
        {
            return this.color;
        }

        public int getIdentifier()
        {
            return this.identifier;
        }

        public String getName()
        {
            return this.name;
        }

    }
}