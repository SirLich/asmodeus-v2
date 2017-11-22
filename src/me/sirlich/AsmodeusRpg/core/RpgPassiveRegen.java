package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitScheduler;

public class RpgPassiveRegen
{
    public static void startTicker()
    {
        BukkitScheduler scheduler = AsmodeusRpg.getInstance().getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(AsmodeusRpg.getInstance(), new Runnable()
        {
            @Override
            public void run()
            {
                for (RpgPlayer rpgPlayer : RpgPlayerList.getRpgPlayers()) {
                    if(!rpgPlayer.getPlayer().isDead() && !(rpgPlayer.getPlayer().getGameMode() == GameMode.CREATIVE)){
                        rpgPlayer.rawHeal(rpgPlayer.getHealthRegenPerTick() * AsmodeusRpg.getInstance().getRegenTickrate());
                    }
                }
            }
        }, 0L, AsmodeusRpg.getInstance().getRegenTickrate());
    }
}
