package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.core.RpgEntityList;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class PrintGameValues extends AsmodeusCommand
{

    public PrintGameValues()
    {
        super("print");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args)
    {
        if(args.length >= 1){
            String printout = "I can't print that sorry";

            if(args[0].equals("entities") || args[0].equals("entitylist")){
                printout = RpgEntityList.rpgEntityHashMap.values().toString();
            }
            if(sender instanceof Player){
                sender.sendMessage(printout);
            }
            System.out.println(printout);
        } else{
            String printout = "entities";
            if(sender instanceof Player){
                sender.sendMessage(printout);
            }
            System.out.println(printout);
        }
        return true;
    }
}
