package me.sirlich.AsmodeusRpg.abilities;

import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.entity.Player;

public class DefaultAbility extends Ability
{
    private Player player;
    public DefaultAbility(Player p) {
        super(p);
        this.player = super.getPlayer();
    }
    @Override
    public void run(){
        ChatUtils.chatWarning(player, "This ability is currently locked.");
    }

    @Override
    public int getRechargeRate()
    {
        return 100;
    }
}
