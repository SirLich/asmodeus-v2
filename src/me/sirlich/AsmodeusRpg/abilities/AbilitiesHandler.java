package me.sirlich.AsmodeusRpg.abilities;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class AbilitiesHandler implements Listener
{
    @EventHandler
    public void switchHandsEvent(PlayerSwapHandItemsEvent event){
        event.setCancelled(true);
        if(event.getPlayer() != null){
            Player player = (Player) event.getPlayer();
            RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);
            Ability ability = rpgPlayer.getSwapAbility();
            if(rpgPlayer.isCanUseSwapAbility()){
                ability.run();
                rpgPlayer.setCanUseSwapAbility(false);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        rpgPlayer.setCanUseSwapAbility(true);
                        ChatUtils.abilitiesChat(player,ability.getRechargeMessage());
                    }

                }.runTaskLater(AsmodeusRpg.getInstance(), rpgPlayer.getSwapAbility().getRechargeRate(rpgPlayer.getSwapAbilityLevel()));
            } else{
                ChatUtils.chatWarning(player,"That ability has not recharged yet.");
            }
        } else{
            System.out.println("Stop fucking around with packets.");
        }
    }
}
