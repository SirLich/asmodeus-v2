package me.sirlich.AsmodeusRpg.abilities;

import de.tr7zw.itemnbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AbilitiesEditor implements Listener
{
    @EventHandler
    public void playerInteractEvent (PlayerInteractEvent event)
    {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if(event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE)
            {
                event.setCancelled(true);
                Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.GRAY + "Edit your abilities.");
                inventory.setItem(1,getMenuItem("Escape Ability",10,"Leap high into the air!",0.03));
                event.getPlayer().openInventory(inventory);
            }
        }
    }

    public ItemStack getMenuItem(String name, int damagePredicate, String effect, double rechargeRate){
        ItemStack itemStack = new ItemStack(Material.STONE_SPADE);
        itemStack.setDurability((short)damagePredicate);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName(ChatColor.AQUA + name);
        ArrayList<String> loreLines = new ArrayList<>();
        loreLines.add(ChatColor.WHITE + effect);
        loreLines.add(ChatColor.WHITE + "Recharge rate: " + ChatColor.GRAY + rechargeRate + ChatColor.WHITE + " seconds.");
        itemMeta.setLore(loreLines);
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setShort("Unbreakable",(short)1);
        return itemStack;
    }
}
