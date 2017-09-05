package me.sirlich.AsmodeusRpg.customMobs.npcs.listeners;

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
            ItemStack helm = new ItemStack(Material.BARRIER);
            ItemMeta meta = helm.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                    "&7Helmet"));
            helm.setItemMeta(meta);
            inv.setItem(0, helm);
        } else {
            inv.setItem(0, p.getInventory().getHelmet());
        }

        if (p.getInventory().getChestplate() == null) {
            ItemStack chest = new ItemStack(Material.BARRIER);
            ItemMeta meta = chest.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                    "&7Chestplate"));
            chest.setItemMeta(meta);
            inv.setItem(1, chest);
        } else {
            inv.setItem(1, p.getInventory().getChestplate());
        }

        ItemStack border = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta borderMeta = border.getItemMeta();
        borderMeta.setDisplayName(ChatColor.GRAY + "");
        border.setItemMeta(borderMeta);
        inv.setItem(2, border);

        if (p.getInventory().getLeggings() == null) {
            ItemStack legs = new ItemStack(Material.BARRIER);
            ItemMeta meta = legs.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                    "&7Leggings"));
            legs.setItemMeta(meta);
            inv.setItem(3, legs);
        } else {
            inv.setItem(3, p.getInventory().getLeggings());
        }

        if (p.getInventory().getBoots() == null) {
            ItemStack boots = new ItemStack(Material.BARRIER);
            ItemMeta meta = boots.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                    "&7Boots"));
            boots.setItemMeta(meta);
            inv.setItem(4, boots);
        } else {
            inv.setItem(4, p.getInventory().getBoots());
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
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

            if (event.getCursor() == null) {
                /*for (int i1 = 0, armourLength = armour.length; i1 < armourLength; i1++) {
                    Material[] m = armour[i1];
                    System.out.println("looping1");
                    for (Material n : m) {
                        System.out.println("looping2");
                        if (event.getCurrentItem().getType().equals(n)) {
                            int i = Bukkit.getScheduler().scheduleSyncDelayedTask(AsmodeusRpg.getPlugin(AsmodeusRpg.class), new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (event.getSlot() == 0) {
                                        ItemStack helm = new ItemStack(Material.BARRIER);
                                        ItemMeta meta = helm.getItemMeta();
                                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                                                "&7Helmet"));
                                        helm.setItemMeta(meta);
                                        event.getInventory().setItem(0, helm);
                                    } else if (event.getSlot() == 1) {
                                        ItemStack chest = new ItemStack(Material.BARRIER);
                                        ItemMeta meta = chest.getItemMeta();
                                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                                                "&7Chestplate"));
                                        chest.setItemMeta(meta);
                                        event.getInventory().setItem(1, chest);
                                    } else if (event.getSlot() == 3) {
                                        ItemStack legs = new ItemStack(Material.BARRIER);
                                        ItemMeta meta = legs.getItemMeta();
                                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                                                "&7Leggings"));
                                        legs.setItemMeta(meta);
                                        event.getInventory().setItem(3, legs);
                                    } else if (event.getSlot() == 4) {
                                        ItemStack boots = new ItemStack(Material.BARRIER);
                                        ItemMeta meta = boots.getItemMeta();
                                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                                                "&7Boots"));
                                        boots.setItemMeta(meta);
                                        event.getInventory().setItem(4, boots);
                                    }
                                }
                            }, 1L);
                            System.out.println("success");
                            return;
                        }
                        System.out.println("failure");
                    }
                }*/
                event.setCancelled(true);
            } else {
                System.out.println(event.getSlot());
                if (event.getSlot() == 0) {
                    for (Material m : armour) {
                        if (event.getCursor().getType().equals(m)) {
                            if (event.getCurrentItem().getType().equals(Material.BARRIER)) {
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
                    for (Material m : armour) {
                        if (event.getCursor().getType().equals(m)) {
                            if (event.getCurrentItem().getType().equals(Material.BARRIER)) {
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
                    for (Material m : armour) {
                        if (event.getCursor().getType().equals(m)) {
                            if (event.getCurrentItem().getType().equals(Material.BARRIER)) {
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
                    for (Material m : armour) {
                        if (event.getCursor().getType().equals(m)) {
                            if (event.getCurrentItem().getType().equals(Material.BARRIER)) {
                                event.setCancelled(true);
                                event.getClickedInventory().setItem(event.getSlot(), event.getCursor());
                                removeCursor((Player) event.getWhoClicked());
                            }
                        }
                        event.getWhoClicked().getInventory().setBoots(event.getCursor());
                        return;
                    }
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
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
