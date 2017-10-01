package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import org.bukkit.scheduler.BukkitScheduler;

public class RpgPlayerTicker
{
    private int TICK_REFRESH_RATE = 20; //default 20
    public void startTicker(){
        BukkitScheduler scheduler = AsmodeusRpg.getInstance().getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(AsmodeusRpg.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(RpgPlayer rpgPlayer : PlayerList.getRpgPlayers()){
                    rpgPlayer.editHealth(rpgPlayer.getHealthRegenPerSecond());
                }
            }
        }, 0L, TICK_REFRESH_RATE);
    }
}
