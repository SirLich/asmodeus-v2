package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestSpeedModifierCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] argv)
    {
        System.out.println("Send command: setSpeed");
        if(sender instanceof Player){
            System.out.println("Sender was player");
            RpgPlayer rpgPlayer = PlayerList.getRpgPlayer((Player) sender);
            System.out.println("Got new RPG player");
            rpgPlayer.IncreaseSpeedModifier(0.2f);
            System.out.println("Success setting speed!");
        } else{
            System.out.println("Please only use this command in-game!");
            return false;
        }
        return true;
    }
}
