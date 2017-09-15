package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.abilities.EscapeAbility;
import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener{
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event){
        System.out.println("PLayer is attempting to join...");
        Player player = event.getPlayer();
        if(PlayerList.isPlayerOnline(player.getUniqueId())){
            System.out.println("New player joining... adding to RpgPlayer");
            //THIS STUFF WILL EVENTUALY BE READ IN FROM THE DATABASE
            PlayerList.addPlayer(player);
            RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);
            rpgPlayer.setCanUseSwapAbility(true);
            rpgPlayer.setSwapAbility(new EscapeAbility(player));
            rpgPlayer.setSwapAbilityLevel(1);
            //Other shit that needs to get set.
            player.setExp(1.0f);
            player.setGameMode(GameMode.SURVIVAL);
            player.setAllowFlight(true);
            player.setNoDamageTicks(0);
        } else{
            System.out.println("Old player " + player.getName() + " joined. Not added to PlayerList");
        }
    }
}
