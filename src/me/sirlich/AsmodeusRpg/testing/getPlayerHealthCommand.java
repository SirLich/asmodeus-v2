package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getPlayerHealthCommand extends AsmodeusCommand
{

    public getPlayerHealthCommand()
    {
        super("test_player_health");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args)
    {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ChatUtils.chatInfo(player, "" + PlayerList.getRpgPlayer(player).getHealth());
        }
        return true;
    }
}
