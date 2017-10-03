package me.sirlich.AsmodeusRpg.abilities;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HyperspeedAbility extends Ability
{
    public HyperspeedAbility(Player p)
    {
        super(p);
        setName("Hyperspeed");
        setRechargeRate(300);
        setDuration(40);
    }

    @Override
    public void run()
    {
        RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(getPlayer());
        rpgPlayer.editSpeedModifier(0.5f);
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                rpgPlayer.editSpeedModifier(-0.5f);
                getPlayer().sendMessage(ChatColor.AQUA + getName() + ChatColor.WHITE + " has expired.");
            }

        }.runTaskLater(AsmodeusRpg.getInstance(), getDuration());
    }
}
