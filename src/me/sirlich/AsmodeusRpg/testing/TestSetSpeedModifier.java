package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestSetSpeedModifier implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] argv)
    {
        if (sender instanceof Player) {
            RpgPlayer rpgPlayer = PlayerList.getRpgPlayer((Player) sender);
            rpgPlayer.setSpeedModifier(Float.parseFloat(argv[0]));
        } else {
            System.out.println("Please only use this command in-game!");
            return false;
        }
        return true;
    }
}
