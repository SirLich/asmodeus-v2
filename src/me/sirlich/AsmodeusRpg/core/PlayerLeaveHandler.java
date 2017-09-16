package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.abilities.DefaultAbility;
import me.sirlich.AsmodeusRpg.abilities.EscapeAbility;
import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveHandler implements Listener{
    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(PlayerList.isPlayerOnline(player)){
            System.out.println("Player leaving. Removing from database.");
            PlayerList.removePlayer(player);
        } else{
            System.out.println("WARNING: Something went wrong in PlayerList");
        }
    }
}
