package me.sirlich.AsmodeusRpg.abilities;

import de.tr7zw.itemnbtapi.NBTItem;
import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.utilities.DebugUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AbilitiesEditor implements Listener
{
    //Button predicates
    private final int BACK_BUTTON_PREDICATE = 8;

    //Ability type predicates
    private final int MOBILITY_PREDICATE = 1;
    private final int CARNAGE_PREDICATE = 2;
    private final int MYTHICAL_PREDICATE = 3;

    //Mobility type predicates
    private final short HYPERSPEED_PREDICATE = 4;
    private final short LEAP_PREDICATE = 5;
    private final short HOLY_ORDER_PREDICATE = 6;
    private final short LIGHTNING_STRIKE_PREDICATE = 7;

    //Inventory names
    private final String ABILITY_TYPE_INVENTORY_NAME = "Select a Type";
    private final String MOBILITY_INVENTORY_NAME = "Mobility";
    private final String CARNAGE_INVENTORY_NAME = "Carnage";
    private final String MYTHICAL_INVENTORY_NAME = "Mythical";


    /*
    This event is used to handle players clicks on an enchant table.
    This event will handle that click and open the Ability Type GUI.
     */
    @EventHandler
    public void clickEnchantTable(PlayerInteractEvent event)
    {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if(event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE)
            {
                event.setCancelled(true);
                openAbilityTypeInventory(event.getPlayer());
            }
        }
    }

    /*
    This event handles all clicks that occurs inside the AbilityType GUI.
     */
    @EventHandler
    public void abilityTypeInventoryHandler (InventoryClickEvent event){
        if(event.getWhoClicked() != null){
            Player player = (Player) event.getWhoClicked();
            if(event.getClickedInventory()!=null && event.getCurrentItem() != null){
                if(event.getClickedInventory().getName().contains(ABILITY_TYPE_INVENTORY_NAME)){
                    int dmg = event.getCurrentItem().getDurability();
                    if(dmg == MOBILITY_PREDICATE){
                        openMobilityInventory(player);
                    } else if(dmg == CARNAGE_PREDICATE){
                        openCarnageInventory(player);
                    } else if(dmg == MYTHICAL_PREDICATE){
                        //more
                    }
                }
            }
        } else{
            System.out.println("Please only use this event from in-game!");
        }
    }

    /*
    This event handles all clicks that occurs inside the Mobility GUI.
     */
    @EventHandler
    public void mobilityAbilityInventoryHandler(InventoryClickEvent event){
        if(event.getWhoClicked() != null){
            Player player = (Player) event.getWhoClicked();
            if(event.getClickedInventory()!=null && event.getCurrentItem() != null){
                if(event.getClickedInventory().getName().contains(MOBILITY_INVENTORY_NAME)){
                    event.setCancelled(true);
                    int dmg = event.getCurrentItem().getDurability();

                    if(dmg == BACK_BUTTON_PREDICATE){
                        openAbilityTypeInventory(player);
                    } else if(dmg == HYPERSPEED_PREDICATE){
                        setMobilityAbility(player,new HyperspeedAbility(player));
                    } else if(dmg == LEAP_PREDICATE){
                        setMobilityAbility(player,new EscapeAbility(player));
                    }
                }
            }
        } else{
            System.out.println("Please only use this event from in-game!");
        }
    }

    /*
    This event handles all clicks inside the Carnage type inventory
     */
    @EventHandler
    public void carnageAbilityInventoryHandler(InventoryClickEvent event){
        if(event.getWhoClicked() != null){
            Player player = (Player) event.getWhoClicked();
            if(event.getClickedInventory()!=null && event.getCurrentItem() != null){
                if(event.getClickedInventory().getName().contains(CARNAGE_INVENTORY_NAME)){
                    event.setCancelled(true);
                    int dmg = event.getCurrentItem().getDurability();
                    if(dmg == BACK_BUTTON_PREDICATE){
                        openAbilityTypeInventory(player);
                    } else if(dmg == HOLY_ORDER_PREDICATE){
                        setCarnageAbility(player,new HolyOrderAbility(player));
                    } else if(dmg == LIGHTNING_STRIKE_PREDICATE){
                        setCarnageAbility(player,new LightningAbility(player));
                    }
                }
            }
        } else{
            System.out.println("Please only use this event from in-game!");
        }
    }


    /*
    This method is used to set the players Mobility Ability.
     */
    private void setMobilityAbility(Player player, Ability ability){
        DebugUtilities.debug("Set " + player.getName() + "'s mobility ability to " + ability.getName());
        RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);
        rpgPlayer.setMobilityAbility(ability);
        rpgPlayer.setCanUseMobilityAbility(true);
        rpgPlayer.setMobilityAbilityLevel(1);
    }

    /*
    This method is used to set the players swap Carnage Ability/
     */
    private void setCarnageAbility(Player player, Ability ability){
        DebugUtilities.debug("Set " + player.getName() + "'s carnage ability to " + ability.getName());
        RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);
        rpgPlayer.setCarnageAbility(ability);
        rpgPlayer.setCanUseCarnageAbility(true);
        rpgPlayer.setCarnageAbilityLevel(1);
    }


    /*
    Open the AbilityType selector Inventory
     */
    private void openAbilityTypeInventory(Player player){
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.GRAY + ABILITY_TYPE_INVENTORY_NAME);
        inventory.setItem(11,getMobilityItem());
        inventory.setItem(13,getCarnageItem());
        inventory.setItem(15,getMythicalItem());
        player.openInventory(inventory);
    }
    private void openMobilityInventory(Player player){
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.GRAY + MOBILITY_INVENTORY_NAME);
        inventory.setItem(0,getBackButtonItem());
        inventory.setItem(1, getAbilityItem("Leap",LEAP_PREDICATE,"Leap high into the air!",10));
        inventory.setItem(2, getAbilityItem("Hyperspeed",HYPERSPEED_PREDICATE,"Run forest, run!",15));
        player.openInventory(inventory);
    }

    private void openCarnageInventory(Player player){
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.GRAY + CARNAGE_INVENTORY_NAME);
        inventory.setItem(0,getBackButtonItem());
        inventory.setItem(1, getAbilityItem("Holy Order",HOLY_ORDER_PREDICATE,"Blow your enemies",15));
        inventory.setItem(2, getAbilityItem("Lightning Strike",LIGHTNING_STRIKE_PREDICATE,"Strike!",15));
        player.openInventory(inventory);
    }

    //Simple method to make the GUI code itself cleaner
    private ItemStack getMobilityItem(){
        ItemStack itemStack = new ItemStack(Material.STONE_SPADE);
        itemStack.setDurability((short) MOBILITY_PREDICATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName(ChatColor.AQUA + "Mobility");
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setShort("Unbreakable",(short)1);
        itemStack = nbtItem.getItem();
        return itemStack;
    }

    //Simple method to make the GUI code itself cleaner
    private ItemStack getCarnageItem(){
        ItemStack itemStack = new ItemStack(Material.STONE_SPADE);
        itemStack.setDurability((short) CARNAGE_PREDICATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName(ChatColor.RED + "Carnage");
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setShort("Unbreakable",(short)1);
        itemStack = nbtItem.getItem();
        return itemStack;
    }

    //Simple method to make the GUI code itself cleaner
    private ItemStack getMythicalItem(){
        ItemStack itemStack = new ItemStack(Material.STONE_SPADE);
        itemStack.setDurability((short) MYTHICAL_PREDICATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Mythical");
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setShort("Unbreakable",(short)1);
        itemStack = nbtItem.getItem();
        return itemStack;
    }

    //Simple method to make the GUI code itself cleaner
    private ItemStack getAbilityItem(String name, int damagePredicate, String effect, double rechargeRate){
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
        itemStack = nbtItem.getItem();
        return itemStack;
    }

    private ItemStack getBackButtonItem(){
        ItemStack itemStack = new ItemStack(Material.STONE_SPADE);
        itemStack.setDurability((short) BACK_BUTTON_PREDICATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.setDisplayName(ChatColor.GREEN + "Back");
        itemStack.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setShort("Unbreakable",(short)1);
        itemStack = nbtItem.getItem();
        return itemStack;
    }
}
