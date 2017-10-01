package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class testDamageCommand extends AsmodeusCommand
{

    public testDamageCommand()
    {
        super("damage");
    }
    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player){
            System.out.println("dmg: 1");
            Player player = (Player) sender;
            System.out.println("dmg: 2");
            PlayerList.getRpgPlayer(player).rawDamage(Double.parseDouble(args[0]));
            System.out.println("dmg: 3");
        }
        return true;
    }
}
