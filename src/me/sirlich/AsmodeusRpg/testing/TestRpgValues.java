package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgEntityList;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestRpgValues extends AsmodeusCommand
{

    public TestRpgValues()
    {
        super("print");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args)
    {
        if(args.length >= 1){
            String printout = "I can't print that sorry";
            if(args[0].equals("entities") || args[1].equals("entitylist")){
                printout = RpgEntityList.rpgEntityHashMap.values().toString();
            } else if(args[1].equals("players") || args[1].equals("playerlist")){
                System.out.println(PlayerList.playerHashMap);
            }
            if(sender instanceof Player){
                sender.sendMessage(printout);
            }
            System.out.println(printout);
        } else{
            System.out.println("entitylist, playerlist");
        }
        return true;
    }
}
