package me.sirlich.AsmodeusRpg.inventoryUtils;

import org.bukkit.entity.Player;
import org.bukkit.Material;

public class ArmourUtils {
    public static String wearingSet(Player p){
        if(p.getInventory().getHelmet() != null &&
                p.getInventory().getChestplate() != null &&
                p.getInventory().getLeggings() != null &&
                p.getInventory().getBoots() != null &&
                p.getInventory().getHelmet().getType().equals(Material.GOLD_HELMET) &&
                p.getInventory().getChestplate().getType().equals(Material.GOLD_CHESTPLATE) &&
                p.getInventory().getLeggings().getType().equals(Material.GOLD_LEGGINGS) &&
                p.getInventory().getBoots().getType().equals(Material.GOLD_BOOTS)){
            return "warlock";
        }
        if(p.getInventory().getHelmet() != null &&
                p.getInventory().getChestplate() != null &&
                p.getInventory().getLeggings() != null &&
                p.getInventory().getBoots() != null &&
                p.getInventory().getHelmet().getType().equals(Material.IRON_HELMET) &&
                p.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE) &&
                p.getInventory().getLeggings().getType().equals(Material.IRON_LEGGINGS) &&
                p.getInventory().getBoots().getType().equals(Material.IRON_BOOTS)){
            return "samurai";
        }
        if(p.getInventory().getHelmet() != null &&
                p.getInventory().getChestplate() != null &&
                p.getInventory().getLeggings() != null &&
                p.getInventory().getBoots() != null &&
                p.getInventory().getHelmet().getType().equals(Material.CHAINMAIL_HELMET) &&
                p.getInventory().getChestplate().getType().equals(Material.CHAINMAIL_CHESTPLATE) &&
                p.getInventory().getLeggings().getType().equals(Material.CHAINMAIL_LEGGINGS) &&
                p.getInventory().getBoots().getType().equals(Material.CHAINMAIL_BOOTS)){
            return "marksman";
        }
        if(p.getInventory().getHelmet() != null &&
                p.getInventory().getChestplate() != null &&
                p.getInventory().getLeggings() != null &&
                p.getInventory().getBoots() != null &&
                p.getInventory().getHelmet().getType().equals(Material.DIAMOND_HELMET) &&
                p.getInventory().getChestplate().getType().equals(Material.DIAMOND_CHESTPLATE) &&
                p.getInventory().getLeggings().getType().equals(Material.DIAMOND_LEGGINGS) &&
                p.getInventory().getBoots().getType().equals(Material.DIAMOND_BOOTS)){
            return "exemplar";
        }
        return "none";
    }
}
