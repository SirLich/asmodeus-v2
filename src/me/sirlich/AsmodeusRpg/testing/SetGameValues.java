package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgEntityList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetGameValues extends AsmodeusCommand
{
    public SetGameValues()
    {
        super("set");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args)
    {
        if(sender instanceof Player){
            Player player = (Player) sender;
            RpgPlayer rpgPlayer = PlayerList.getRpgPlayer(player);
            if(args.length > 1){
                String cmd = args[0];
                if(cmd.equalsIgnoreCase("health")){
                    rpgPlayer.setHealth(Double.parseDouble(args[1]));
                } else if(cmd.equalsIgnoreCase("maxHealth")){
                    rpgPlayer.setMaxHealth(Double.parseDouble(args[1]));
                } else if(cmd.equalsIgnoreCase("healthRegen")){
                    rpgPlayer.setHealthRegenPerTick(Double.parseDouble(args[1]));
                } else if(cmd.equalsIgnoreCase("regenTickrate")){
                    AsmodeusRpg.getInstance().setRegenTickrate(Integer.parseInt(args[1]));
                }
            } else{
                System.out.println("List of setting coming soon!");
            }
        } else{
            System.out.println("Please only use this command from in-game");
        }
        return true;
    }
}
