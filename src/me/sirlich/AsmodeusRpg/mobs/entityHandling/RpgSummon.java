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
        if(args != null){
            if(sender instanceof  Player){
                Player player = (Player) sender;
                if(args[0].equalsIgnoreCase("lich")){
                    net.minecraft.server.v1_12_R1.Entity entity = MobCreator.makeMob(RpgEntityType.RPG_LICH, Integer.parseInt(args[1]));
                    MobCreator.spawn(entity, player.getLocation());
                }
                if(args[0].equalsIgnoreCase("cow")){
                    net.minecraft.server.v1_12_R1.Entity entity = MobCreator.makeCow(20,3,"Bobby!",14);
                    MobCreator.spawn(entity, player.getLocation());
                }
            } else{
                System.out.println("Please only call this command in-game");
            }

        } else {
            String msg = "Spawn in a mob: cow, polar_bear";
            if(sender instanceof Player){
                sender.sendMessage("msg");
            }
            System.out.println("msg");
        }
        return true;
    }
}