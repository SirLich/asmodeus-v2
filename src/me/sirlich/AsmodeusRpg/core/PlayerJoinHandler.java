package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.abilities.DefaultAbility;
import me.sirlich.AsmodeusRpg.abilities.HyperspeedAbility;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener
{
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        if (!RpgPlayerList.isPlayerOnline(player)) {
            //All of this shit will be read in from the DB eventually.
            RpgPlayerList.addPlayer(player);
            RpgPlayer rpgPlayer = RpgPlayerList.getRpgPlayer(player);

            rpgPlayer.setCanUseCarnageAbility(true);
            rpgPlayer.setCarnageAbility(new HyperspeedAbility(player));

            rpgPlayer.setMythicalAbility(new DefaultAbility(player));
            rpgPlayer.setCanUseMythicalAbility(true);

            rpgPlayer.setMobilityAbility(new DefaultAbility(player));
            rpgPlayer.setCanUseMobilityAbility(true);

            //Other shit that needs to get set.
            player.setGameMode(GameMode.SURVIVAL);
            player.setAllowFlight(true);
            player.setFlySpeed(0);
            player.setFoodLevel(20);

            //set Rpgplayer shit.
            rpgPlayer.setSpeedModifier(0.2f); //NOTE: This should eventualy loop over armour etc to figure out speed. 0,2 is just base.
            rpgPlayer.setMaxHealth(50);
            rpgPlayer.setHealth(50);
            rpgPlayer.setHealthRegenPerTick(0.01);
            rpgPlayer.setKnockbackResistance(0.5);
        } else {
            System.out.println("Something went wrong! Please see the player list.");
            RpgPlayerList.removePlayer(player);
            RpgPlayerList.addPlayer(player);
        }
    }
}
