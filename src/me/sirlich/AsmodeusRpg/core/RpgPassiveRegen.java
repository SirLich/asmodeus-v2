package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import org.bukkit.scheduler.BukkitScheduler;

public class RpgPassiveRegen
{
    public static void startTicker()
    {
        int TICK_REFRESH_RATE = 100;
        BukkitScheduler scheduler = AsmodeusRpg.getInstance().getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(AsmodeusRpg.getInstance(), new Runnable()
        {
            @Override
            public void run()
            {
                for (RpgPlayer rpgPlayer : PlayerList.getRpgPlayers()) {
                    System.out.println("Healed players");
                    rpgPlayer.rawHeal(rpgPlayer.getHealthRegenPerSecond());
                }
            }
        }, 0L, TICK_REFRESH_RATE);
    }
}
