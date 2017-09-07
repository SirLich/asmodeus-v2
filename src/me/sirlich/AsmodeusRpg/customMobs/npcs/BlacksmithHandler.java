package me.sirlich.AsmodeusRpg.customMobs.npcs;

import de.tr7zw.itemnbtapi.NBTItem;
import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;
import java.util.Arrays;

public class BlacksmithHandler implements Listener {


    /*
    Handles the Villlager interaction
     */
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getScoreboardTags().contains("blacksmith")) {
            openBlacksmith(event.getPlayer());
            event.setCancelled(true);
        }
    }

    /*
    Handles opening the GUI
     */
    public void openBlacksmith(Player p) {
        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatColor.GRAY + "Equip your gear:");
        if(p.getInventory().getHelmet() != null){inv.setItem(0, p.getInventory().getHelmet());}
        if(p.getInventory().getChestplate() != null){inv.setItem(1, p.getInventory().getChestplate());}
        if(p.getInventory().getLeggings() != null){inv.setItem(3, p.getInventory().getLeggings());}
        if(p.getInventory().getBoots() != null){inv.setItem(4, p.getInventory().getBoots());}
        p.openInventory(inv);
    }


    /*
    On close, all items stored in the GUI are equipped. This includes air. Bogus items are returned to player inventory
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Equip your gear:")) {
            Inventory inv = event.getInventory();
            Player p = (Player) event.getPlayer();

            if(inv.getItem(0) != null){
                if(isHelmet(inv.getItem(0))){
                    p.getInventory().setHelmet(inv.getItem(0));
                } else{
                    p.getInventory().addItem(inv.getItem(0));
                    p.sendMessage("Warning: That is not a helmet. Your item has been returned.");
                }
            } else{
                p.getInventory().setHelmet(new ItemStack(Material.AIR));
            }

            if(inv.getItem(1)!=null){
                if(isChestplate(inv.getItem(1))){
                    p.getInventory().setChestplate(inv.getItem(1));
                } else{
                    p.getInventory().addItem(inv.getItem(1));
                    p.sendMessage("Warning: That is not a chestplate. Your item has been returned.");
                }
            }else{
                p.getInventory().setChestplate(new ItemStack(Material.AIR));
            }

            if(inv.getItem(2)!=null){
                if(!isAir(inv.getItem(2))){
                    p.getInventory().addItem(inv.getItem(2));
                    p.sendMessage("Why did you put stuff in that slot!?? Items returned...");
                }
            }

            if(inv.getItem(3)!=null){
                if(isLeggings(inv.getItem(3))){
                    p.getInventory().setLeggings(inv.getItem(3));
                } else{
                    p.getInventory().addItem(inv.getItem(3));
                    p.sendMessage("Warning: Those are not legging. Your item has been returned.");
                }
            }else{
                p.getInventory().setLeggings(new ItemStack(Material.AIR));
            }

            if(inv.getItem(4)!=null){
                if(isBoots(inv.getItem(4))){
                    p.getInventory().setBoots(inv.getItem(4));
                } else{
                    p.getInventory().addItem(inv.getItem(4));
                    p.sendMessage("Warning: Those are not boots. Your item has been returned.");
                }
            }else{
                p.getInventory().setBoots(new ItemStack(Material.AIR));
            }

        }
    }

    /*
    Sort of crappy code that TRIES to stop bad items from going into the GUI.
    If a bogus item gets inside: The player can always just take it out again.
    If the bogus item is left inside, it will be returned when he closes the inv.

    BONUS:
    Custom Shift Clicking! If you shift click and the GUI on-top is our Blacksmith GUI,
    the piece of armour will be placed in the correct slot.
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null) {
            event.getWhoClicked().sendMessage(event.getView().getTopInventory().getName());
            if(ChatColor.stripColor(event.getView().getTopInventory().getName()).equalsIgnoreCase("Equip your gear:")){
                if(event.isShiftClick()){
                    //vars
                    ItemStack item = new ItemStack(Material.AIR);
                    int slot = event.getSlot();
                    Player p = (Player) event.getWhoClicked();
                    Inventory inv = event.getView().getTopInventory();

                    if(event.getCurrentItem() != null){
                        item = event.getCurrentItem();
                    }

                    if(inv.getItem(0)==null && isHelmet(item)){
                        p.getInventory().setItem(slot, new ItemStack(Material.AIR));
                        inv.setItem(0,item);
                    } else{
                        event.setCancelled(true);
                    }

                    if(inv.getItem(1)==null && isChestplate(item)){
                        p.getInventory().setItem(slot, new ItemStack(Material.AIR));
                        inv.setItem(1,item);
                    } else{
                        event.setCancelled(true);
                    }

                    if(inv.getItem(3)==null && isLeggings(item)){
                        p.getInventory().setItem(slot, new ItemStack(Material.AIR));
                        inv.setItem(3,item);
                    } else{
                        event.setCancelled(true);
                    }

                    if(inv.getItem(4)==null && isBoots(item)){
                        p.getInventory().setItem(slot, new ItemStack(Material.AIR));
                        inv.setItem(4,item);
                    } else{
                        event.setCancelled(true);
                    }

                }
            }
            if (ChatColor.stripColor(event.getClickedInventory().getName()).equalsIgnoreCase("Equip your gear:")) {

                //vars
                ItemStack heldItem = new ItemStack(Material.AIR);
                int slot = event.getSlot();
                Player p = (Player) event.getWhoClicked();

                if(p.getItemOnCursor() != null){
                    heldItem = event.getWhoClicked().getItemOnCursor();
                }

                //Helm slot
                if(slot == 0){
                    if(!isHelmet(heldItem)){
                        if(isAir(heldItem)){
                            event.setCancelled(false);
                        } else{
                            event.setCancelled(true);
                        }
                    }
                }

                //Chest slot
                if(slot == 1){
                    if(!isChestplate(heldItem)){
                        if(isAir(heldItem)){
                            event.setCancelled(false);
                        } else{
                            event.setCancelled(true);
                        }
                    }
                }

                //center
                if(slot == 2){
                    if(!isAir(heldItem)){
                        event.setCancelled(true);
                    }
                }
                //Pant slot
                if(slot == 3){
                    if(!isLeggings(heldItem)){
                        if(isAir(heldItem)){
                            event.setCancelled(false);
                        } else{
                            event.setCancelled(true);
                        }
                    }
                }
                //Boots slot
                if(slot == 4){
                    if(!isBoots(heldItem)){
                        if(isAir(heldItem)){
                            event.setCancelled(false);
                        } else{
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    /*
    Removes cursor (Put in Utils method somewhere?)
     */
    private void removeCursor(Player p) {
        int i = Bukkit.getScheduler().scheduleSyncDelayedTask(AsmodeusRpg.getPlugin(AsmodeusRpg.class), new Runnable() {
            @Override
            public void run() {

                p.setItemOnCursor(null);

            }
        }, 1L);
    }

    /*
    Checks if item is armor or not. I love return(foo) methods.
     */
    private boolean isArmor(ItemStack item){
        return(isBoots(item)||isLeggings(item)||isChestplate(item)||isHelmet(item));
    }

    /*
    Checks if ItemStack is boots
     */
    private boolean isBoots(ItemStack item){
        return(item.getType().equals(Material.GOLD_BOOTS)||
                item.getType().equals(Material.IRON_BOOTS)||
                item.getType().equals(Material.DIAMOND_BOOTS)||
                item.getType().equals(Material.CHAINMAIL_BOOTS)||
                item.getType().equals(Material.LEATHER_BOOTS));
    }

    /*
    Checks if ItemStack is pants
     */
    private boolean isLeggings(ItemStack item){
        return(item.getType().equals(Material.GOLD_LEGGINGS)||
                item.getType().equals(Material.IRON_LEGGINGS)||
                item.getType().equals(Material.DIAMOND_LEGGINGS)||
                item.getType().equals(Material.CHAINMAIL_LEGGINGS)||
                item.getType().equals(Material.LEATHER_LEGGINGS));
    }

    /*
    Checks if ItemStack is chestplate
     */
    private boolean isChestplate(ItemStack item){
        return(item.getType().equals(Material.GOLD_CHESTPLATE)||
                item.getType().equals(Material.IRON_CHESTPLATE)||
                item.getType().equals(Material.DIAMOND_CHESTPLATE)||
                item.getType().equals(Material.CHAINMAIL_CHESTPLATE)||
                item.getType().equals(Material.LEATHER_CHESTPLATE));
    }

    /*
    Checks if ItemStack is helmet
     */
    private boolean isHelmet(ItemStack item){
        return(item.getType().equals(Material.GOLD_HELMET)||
                item.getType().equals(Material.IRON_HELMET)||
                item.getType().equals(Material.DIAMOND_HELMET)||
                item.getType().equals(Material.CHAINMAIL_HELMET)||
                item.getType().equals(Material.LEATHER_HELMET));
    }

    /*
    Checks if ItemStack is air
     */
    private boolean isAir(ItemStack item){
        return(item.getType().equals(Material.AIR));
    }

}
