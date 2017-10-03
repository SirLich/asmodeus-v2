package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestIncreaseSpeedModifier implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] argv)
    {
        if (sender instanceof Player) {
            RpgPlayer rpgPlayer = PlayerList.getRpgPlayer((Player) sender);
            rpgPlayer.editSpeedModifier(Float.parseFloat(argv[0]));
        } else {
            System.out.println("Please only use this command in-game!");
            return false;
        }
        return true;
    }
}
