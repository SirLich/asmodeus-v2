package me.sirlich.AsmodeusRpg;

import me.sirlich.AsmodeusRpg.abilities.DefaultAbility;
import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener{
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event){

        //THIS STUFF WILL EVENTUALY BE READ IN FROM THE DATABASE
        PlayerList.addPlayer(event.getPlayer());
        RpgPlayer rpgPlayer = PlayerList.getPlayer(event.getPlayer());
        rpgPlayer.setCanUseSwitchHandAbility(true);
        rpgPlayer.setSwitchHandAbility(new DefaultAbility(event.getPlayer()));
        rpgPlayer.setSwitchHandAbilityLevel(1);

        //Other shit that needs to get set.
        event.getPlayer().setExp(1.0f);
        event.getPlayer().setGameMode(GameMode.SURVIVAL);
        event.getPlayer().setAllowFlight(true);
        event.getPlayer().setNoDamageTicks(0);
    }
}
