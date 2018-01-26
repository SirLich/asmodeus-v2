package me.sirlich.AsmodeusRpg.mobs.entityHandling;

import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RpgSummon extends AsmodeusCommand
{
    public RpgSummon()
    {
        super("RpgSummon");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args){
        if(args != null && Integer.parseInt(args[1]) >= 0 && Integer.parseInt(args[1]) <= 10){
            if(sender instanceof  Player){
                Player player = (Player) sender;
                if(args[0].equalsIgnoreCase("lich")){
                    net.minecraft.server.v1_12_R1.Entity entity = MobCreator.makeMob(RpgEntityType.RPG_LICH, Integer.parseInt(args[1]));
                    MobCreator.spawn(entity, player.getLocation());
                } else if (args[0].equalsIgnoreCase("critter")){
                    net.minecraft.server.v1_12_R1.Entity entity = MobCreator.makeMob(RpgEntityType.RPG_CRITTER, Integer.parseInt(args[1]));
                    MobCreator.spawn(entity, player.getLocation());
                } else{
                    System.out.println("Please only create real mobs");
                }
            } else{
                System.out.println("Please only call this command in-game");
            }

        } else {
            String msg = "Something is not formatted correctly";
            if(sender instanceof Player){
                sender.sendMessage("msg");
            }
            System.out.println("msg");
        }
        return true;
    }
}