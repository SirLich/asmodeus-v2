package me.sirlich.AsmodeusRpg;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener{
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event){
        PlayerList.addPlayer(event.getPlayer());
        //StaminaHandler.addPlayer((Player) event.getPlayer());
        event.getPlayer().setExp(1.0f);
        event.getPlayer().setGameMode(GameMode.SURVIVAL);
        event.getPlayer().setAllowFlight(true);
        event.getPlayer().setNoDamageTicks(0);
    }
}
