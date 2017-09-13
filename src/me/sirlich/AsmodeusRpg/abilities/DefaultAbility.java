package me.sirlich.AsmodeusRpg.abilities;

import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.entity.Player;

public class DefaultAbility extends Ability
{
    public DefaultAbility(Player p) {
        super(p);
        player = super.getPlayer();
        name = "Default Ability";
    }
    @Override
    public void run(){
        ChatUtils.chatWarning(player, "This ability is currently locked.");
    }

    @Override
    public int getRechargeRate(int level)
    {
        return 100;
    }
}
