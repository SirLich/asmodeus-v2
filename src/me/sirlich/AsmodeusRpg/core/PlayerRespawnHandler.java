package me.sirlich.AsmodeusRpg.core;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnHandler implements Listener
{
    private static Location RESPAWN_LOCATION = new Location(Bukkit.getWorld("world"),236,67,294);
    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent event){
        event.getPlayer().teleport(RESPAWN_LOCATION);
        RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(event.getPlayer());
        rpgPlayer.fullHeal();
    }
}
