package me.sirlich.AsmodeusRpg.abilities;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HyperspeedAbility extends Ability
{
    private Player player;
    public HyperspeedAbility(Player p) {
        super(p);
        this.player = super.getPlayer();
    }
    @Override
    public void run(){
        RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);
        rpgPlayer.editSpeedModifier(0.5f);
        new BukkitRunnable() {
            @Override
            public void run() {
                rpgPlayer.editSpeedModifier(-0.5f);
                ChatUtils.abilitiesChat(player,"Hyperspeed has expired.");
            }

        }.runTaskLater(AsmodeusRpg.getInstance(), rpgPlayer.getSwapAbility().getRechargeRate(rpgPlayer.getSwapAbilityLevel()));
    }

    @Override
    public int getRechargeRate(int level)
    {
        return 50;
    }
}
