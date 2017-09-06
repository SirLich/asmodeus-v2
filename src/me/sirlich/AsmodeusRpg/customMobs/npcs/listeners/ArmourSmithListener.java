package me.sirlich.AsmodeusRpg.customMobs.npcs.listeners;

import de.tr7zw.itemnbtapi.NBTItem;
import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ArmourSmithListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getScoreboardTags().contains("armour_smith")) {

            openArmourSmith(event.getPlayer());

            event.setCancelled(true);
        }
    }

    public void openArmourSmith(Player p) {
        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatColor.translateAlternateColorCodes('&',
                "&6&lArmour Smith"));
        if (p.getInventory().getHelmet() == null) {
            ItemStack helm = new ItemStack(Material.STONE_SPADE, 1, (short) 1);
            ItemMeta meta = helm.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                    "&7Helmet"));
            meta.setUnbreakable(true);
            helm.setItemMeta(meta);
            NBTItem nbti = new NBTItem(helm);
            nbti.setByte("asmodeus.blank_helmet", (byte) 1);
            inv.setItem(0, nbti.getItem());
        } else {
            inv.setItem(0, p.getInventory().getHelmet());
        }

        if (p.getInventory().getChestplate() == null) {
            ItemStack chestplate = new ItemStack(Material.STONE_SPADE, 1, (short) 2);
            ItemMeta meta = chestplate.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                    "&7Chestplate"));
            meta.setUnbreakable(true);
            chestplate.setItemMeta(meta);
            NBTItem nbti = new NBTItem(chestplate);
            nbti.setByte("asmodeus.blank_chestplate", (byte) 1);
            inv.setItem(1, nbti.getItem());
        } else {
            inv.setItem(1, p.getInventory().getChestplate());
        }

        ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta borderMeta = border.getItemMeta();
        borderMeta.setDisplayName(ChatColor.GRAY + "");
        border.setItemMeta(borderMeta);
        inv.setItem(2, border);

        if (p.getInventory().getLeggings() == null) {
            ItemStack leggings = new ItemStack(Material.STONE_SPADE, 1, (short) 3);
            ItemMeta meta = leggings.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                    "&7Leggings"));
            meta.setUnbreakable(true);
            leggings.setItemMeta(meta);
            NBTItem nbti = new NBTItem(leggings);
            nbti.setByte("asmodeus.blank_leggings", (byte) 1);
            inv.setItem(3, nbti.getItem());
        } else {
            inv.setItem(3, p.getInventory().getLeggings());
        }

        if (p.getInventory().getBoots() == null) {
            ItemStack boots = new ItemStack(Material.STONE_SPADE, 1, (short) 4);
            ItemMeta meta = boots.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                    "&7Boots"));
            meta.setUnbreakable(true);
            boots.setItemMeta(meta);
            NBTItem nbti = new NBTItem(boots);
            nbti.setByte("asmodeus.blank_boots", (byte) 1);
            inv.setItem(4, nbti.getItem());
        } else {
            inv.setItem(4, p.getInventory().getBoots());
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory().getName() != null) {
            if (ChatColor.stripColor(event.getClickedInventory().getName()).equalsIgnoreCase("Armour Smith")) {

                Material[] helms = new Material[]{Material.DIAMOND_HELMET, Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, Material.GOLD_HELMET};
                Material[] chests = new Material[]{Material.DIAMOND_CHESTPLATE, Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLD_CHESTPLATE};
                Material[] legs = new Material[]{Material.DIAMOND_LEGGINGS, Material.LEATHER_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS, Material.GOLD_LEGGINGS};
                Material[] boots = new Material[]{Material.DIAMOND_BOOTS, Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.GOLD_BOOTS};
                ArrayList<Material> armour = new ArrayList<>();
                armour.addAll(Arrays.asList(helms));
                armour.addAll(Arrays.asList(chests));
                armour.addAll(Arrays.asList(legs));
                armour.addAll(Arrays.asList(boots));

                if (event.getCursor().getType().equals(Material.AIR)) {
                    for (Material m : armour) {
                        if (event.getCurrentItem().getType().equals(m)) {
                            int i = Bukkit.getScheduler().scheduleSyncDelayedTask(AsmodeusRpg.getPlugin(AsmodeusRpg.class), new Runnable() {
                                @Override
                                public void run() {
                                    if (event.getSlot() == 0) {
                                        ItemStack helm = new ItemStack(Material.STONE_SPADE, 1, (short) 1);
                                        ItemMeta meta = helm.getItemMeta();
                                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                                                "&7Helmet"));
                                        meta.setUnbreakable(true);
                                        helm.setItemMeta(meta);
                                        NBTItem nbti = new NBTItem(helm);
                                        nbti.setByte("asmodeus.blank_helmet", (byte) 1);
                                        event.getInventory().setItem(1, nbti.getItem());
                                        event.getWhoClicked().getInventory().setHelmet(null);
                                    } else if (event.getSlot() == 1) {
                                        ItemStack chestplate = new ItemStack(Material.STONE_SPADE, 1, (short) 2);
                                        ItemMeta meta = chestplate.getItemMeta();
                                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                                                "&7Chestplate"));
                                        meta.setUnbreakable(true);
                                        chestplate.setItemMeta(meta);
                                        NBTItem nbti = new NBTItem(chestplate);
                                        nbti.setByte("asmodeus.blank_chestplate", (byte) 1);
                                        event.getInventory().setItem(1, nbti.getItem());
                                        event.getWhoClicked().getInventory().setChestplate(null);
                                    } else if (event.getSlot() == 3) {
                                        ItemStack leggings = new ItemStack(Material.STONE_SPADE, 1, (short) 3);
                                        ItemMeta meta = leggings.getItemMeta();
                                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                                                "&7Leggings"));
                                        meta.setUnbreakable(true);
                                        leggings.setItemMeta(meta);
                                        NBTItem nbti = new NBTItem(leggings);
                                        nbti.setByte("asmodeus.blank_leggings", (byte) 1);
                                        event.getInventory().setItem(3, nbti.getItem());
                                        event.getWhoClicked().getInventory().setLeggings(null);
                                    } else if (event.getSlot() == 4) {
                                        ItemStack boots = new ItemStack(Material.STONE_SPADE, 1, (short) 4);
                                        ItemMeta meta = boots.getItemMeta();
                                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                                                "&7Boots"));
                                        meta.setUnbreakable(true);
                                        boots.setItemMeta(meta);
                                        NBTItem nbti = new NBTItem(boots);
                                        nbti.setByte("asmodeus.blank_boots", (byte) 1);
                                        event.getInventory().setItem(4, nbti.getItem());
                                        event.getWhoClicked().getInventory().setBoots(null);
                                    }
                                }
                            }, 1L);
                            return;
                        }
                    }
                    event.setCancelled(true);
                } else {
                    if (event.getSlot() == 0) {
                        for (Material m : helms) {
                            if (event.getCursor().getType().equals(m)) {
                                NBTItem nbti = new NBTItem(event.getCurrentItem());
                                if (nbti.getByte("asmodeus.blank_helmet") == 1) {
                                    event.setCancelled(true);
                                    event.getClickedInventory().setItem(event.getSlot(), event.getCursor());
                                    removeCursor((Player) event.getWhoClicked());
                                }
                                event.getWhoClicked().getInventory().setHelmet(event.getCursor());
                                return;
                            }
                        }
                        event.setCancelled(true);
                    } else if (event.getSlot() == 1) {
                        for (Material m : chests) {
                            if (event.getCursor().getType().equals(m)) {
                                NBTItem nbti = new NBTItem(event.getCurrentItem());
                                if (nbti.getByte("asmodeus.blank_chestplate") == 1) {
                                    event.setCancelled(true);
                                    event.getClickedInventory().setItem(event.getSlot(), event.getCursor());
                                    removeCursor((Player) event.getWhoClicked());
                                }
                                event.getWhoClicked().getInventory().setChestplate(event.getCursor());
                                return;
                            }
                        }
                        event.setCancelled(true);
                    } else if (event.getSlot() == 3) {
                        for (Material m : legs) {
                            if (event.getCursor().getType().equals(m)) {
                                NBTItem nbti = new NBTItem(event.getCurrentItem());
                                if (nbti.getByte("asmodeus.blank_leggings") == 1) {
                                    event.setCancelled(true);
                                    event.getClickedInventory().setItem(event.getSlot(), event.getCursor());
                                    removeCursor((Player) event.getWhoClicked());
                                }
                                event.getWhoClicked().getInventory().setLeggings(event.getCursor());
                                return;
                            }
                        }
                        event.setCancelled(true);
                    } else if (event.getSlot() == 4) {
                        for (Material m : boots) {
                            if (event.getCursor().getType().equals(m)) {
                                NBTItem nbti = new NBTItem(event.getCurrentItem());
                                if (nbti.getByte("asmodeus.blank_boots") == 1) {
                                    event.setCancelled(true);
                                    event.getClickedInventory().setItem(event.getSlot(), event.getCursor());
                                    removeCursor((Player) event.getWhoClicked());
                                }
                                event.getWhoClicked().getInventory().setBoots(event.getCursor());
                                return;
                            }
                        }
                        event.setCancelled(true);
                    } else {
                        event.setCancelled(true);
                    }

                }
            }
        }
    }

    public void removeCursor(Player p) {
        int i = Bukkit.getScheduler().scheduleSyncDelayedTask(AsmodeusRpg.getPlugin(AsmodeusRpg.class), new Runnable() {
            @Override
            public void run() {

                p.setItemOnCursor(null);

            }
        }, 1L);
    }

}
