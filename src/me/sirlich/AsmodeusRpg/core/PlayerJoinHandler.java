package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.abilities.DefaultAbility;
import me.sirlich.AsmodeusRpg.abilities.EscapeAbility;
import me.sirlich.AsmodeusRpg.abilities.HyperspeedAbility;
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
        Player player = event.getPlayer();
        if(!PlayerList.isPlayerOnline(player)){
            System.out.println("New player joining... adding to RpgPlayer");
            //THIS STUFF WILL EVENTUALY BE READ IN FROM THE DATABASE
            PlayerList.addPlayer(player);
            RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);

            rpgPlayer.setCanUseSwapAbility(true);
            rpgPlayer.setSwapAbility(new HyperspeedAbility(player));

            rpgPlayer.setDropAbility(new DefaultAbility(player));
            rpgPlayer.setCanUseDropAbility(true);

            rpgPlayer.setFlyAbility(new DefaultAbility(player));
            rpgPlayer.setCanUseFlyAbility(true);

            //Other shit that needs to get set.
            player.setGameMode(GameMode.SURVIVAL);
            player.setAllowFlight(true);
        } else{
            System.out.println("Something went wrong! Please see the player list.");
            PlayerList.removePlayer(player);
            PlayerList.addPlayer(player);
        }
    }
}
