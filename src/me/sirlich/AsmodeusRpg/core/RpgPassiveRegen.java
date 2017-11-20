package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.utilities.DebugUtilities;
import org.bukkit.GameMode;
import org.bukkit.Particle;
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
                for (RpgPlayer rpgPlayer : PlayerList.getRpgPlayers()) {
                    if(!rpgPlayer.getPlayer().isDead() && !(rpgPlayer.getPlayer().getGameMode() == GameMode.CREATIVE)){
                        rpgPlayer.rawHeal(rpgPlayer.getHealthRegenPerTick() * AsmodeusRpg.getInstance().getRegenTickrate());
                        if (rpgPlayer.getHealth() != rpgPlayer.getMaxHealth()) {
                            rpgPlayer.getPlayer().getWorld().spawnParticle(Particle.HEART, rpgPlayer.getPlayer().getLocation().add(0, 1, 0), 3, 0.2, 0.2, 0.2);
                        }
                    }
                }
            }
        }, 0L, AsmodeusRpg.getInstance().getRegenTickrate());
    }
}
