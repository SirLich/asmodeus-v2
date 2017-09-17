package me.sirlich.AsmodeusRpg.abilities;

import de.tr7zw.itemnbtapi.NBTItem;
import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class AbilitiesHandler implements Listener
{
    @EventHandler
    public void switchHandsEvent(PlayerSwapHandItemsEvent event){
        event.setCancelled(true);
        if(event.getPlayer() != null){
            Player player = event.getPlayer();
            RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);
            Ability ability = rpgPlayer.getSwapAbility();
            if(rpgPlayer.isCanUseSwapAbility()){
                player.sendMessage(ChatColor.WHITE + "You used the " + ChatColor.AQUA + ability.getName() + ChatColor.WHITE + " ability.");
                ability.run();
                rpgPlayer.setCanUseSwapAbility(false);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        rpgPlayer.setCanUseSwapAbility(true);
                        player.sendMessage("You can now use " + ChatColor.AQUA + ability.getName() + ChatColor.WHITE + " ability");
                    }

                }.runTaskLater(AsmodeusRpg.getInstance(), rpgPlayer.getSwapAbility().getRechargeRate());
            } else{
                ChatUtils.chatWarning(player, ability.getName() + " ability has not recharged yet.");
            }
        } else{
            System.out.println("Stop fucking around with packets.");
        }
    }

    @EventHandler
    public void dropItemEvent(PlayerDropItemEvent event){
        if(event.getPlayer() instanceof Player){
            Player player = event.getPlayer();
            RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);
            if(event.getItemDrop() != null){
                Item item = event.getItemDrop();
                ItemStack itemStack = item.getItemStack();
                NBTItem nbtItem = new NBTItem(itemStack);
                if(nbtItem.hasKey("wep")){
                    event.setCancelled(true);
                    Ability ability = rpgPlayer.getSwapAbility();
                    if(rpgPlayer.isCanUseDropAbility()){
                        ability.run();
                        rpgPlayer.setCanUseDropAbility(false);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                rpgPlayer.setCanUseDropAbility(true);
                            }

                        }.runTaskLater(AsmodeusRpg.getInstance(), rpgPlayer.getSwapAbility().getRechargeRate());
                    } else{
                        ChatUtils.chatWarning(player,"That ability has not recharged yet.");
                    }
                }
            }
        } else{
            System.out.println("Stop fucking around with packets");
        }
    }
}
