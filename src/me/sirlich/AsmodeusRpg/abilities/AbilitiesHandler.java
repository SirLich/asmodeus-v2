package me.sirlich.AsmodeusRpg.abilities;

import de.tr7zw.itemnbtapi.NBTItem;
import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class AbilitiesHandler implements Listener
{
    @EventHandler
    public void switchHandsEvent(PlayerSwapHandItemsEvent event)
    {
        event.setCancelled(true);
        if (event.getPlayer() != null) {
            Player player = event.getPlayer();
            if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);
                Ability ability = rpgPlayer.getCarnageAbility();
                if (rpgPlayer.isCanUseCarnageAbility()) {
                    player.sendMessage(ChatColor.WHITE + "You used the " + ChatColor.AQUA + ability.getName() + ChatColor.WHITE + " ability.");
                    ability.run();
                    rpgPlayer.setCanUseCarnageAbility(false);
                    new BukkitRunnable()
                    {
                        @Override
                        public void run()
                        {
                            rpgPlayer.setCanUseCarnageAbility(true);
                            player.sendMessage("You can now use " + ChatColor.AQUA + ability.getName() + ChatColor.WHITE + " ability");
                        }

                    }.runTaskLater(AsmodeusRpg.getInstance(), rpgPlayer.getCarnageAbility().getRechargeRate());
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1, 1);
                    ChatUtils.chatWarning(player, ability.getName() + " ability has not recharged yet.");
                }
            } else {
                System.out.println("Stop fucking around with packets.");
            }
        }
    }

    @EventHandler
    public void dropItemEvent(PlayerDropItemEvent event)
    {
        if (event.getPlayer() instanceof Player) {
            Player player = event.getPlayer();
            if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);
                if (event.getItemDrop() != null) {
                    Item item = event.getItemDrop();
                    ItemStack itemStack = item.getItemStack();
                    NBTItem nbtItem = new NBTItem(itemStack);
                    if (nbtItem.hasKey("wep")) {
                        event.setCancelled(true);
                        Ability ability = rpgPlayer.getCarnageAbility();
                        if (rpgPlayer.isCanUseMythicalAbility()) {
                            ability.run();
                            rpgPlayer.setCanUseMythicalAbility(false);
                            new BukkitRunnable()
                            {
                                @Override
                                public void run()
                                {
                                    rpgPlayer.setCanUseMythicalAbility(true);
                                }

                            }.runTaskLater(AsmodeusRpg.getInstance(), rpgPlayer.getCarnageAbility().getRechargeRate());
                        } else {
                            player.playSound(player.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1, 1);
                            ChatUtils.chatWarning(player, "That ability has not recharged yet.");
                        }
                    }
                }
            }
        } else {
            System.out.println("Stop fucking around with packets");
        }
    }

    @EventHandler
    public void toggleFlyEvent(PlayerToggleFlightEvent event)
    {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);
                event.setCancelled(true);
                Ability ability = rpgPlayer.getMobilityAbility();
                if (rpgPlayer.isCanUseMobilityAbility()) {
                    ability.run();
                    rpgPlayer.setCanUseMobilityAbility(false);
                    new BukkitRunnable()
                    {
                        @Override
                        public void run()
                        {
                            player.playSound(player.getLocation(), Sound.ITEM_BOTTLE_FILL, 1, 1);
                            rpgPlayer.setCanUseMobilityAbility(true);
                        }

                    }.runTaskLater(AsmodeusRpg.getInstance(), rpgPlayer.getMobilityAbility().getRechargeRate());
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1, 1);
                    ChatUtils.chatWarning(player, "That ability has not recharged yet.");
                }
            }
        }
    }
}
