package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestPlayerList implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] argv)
    {
        System.out.println(PlayerList.playerHashMap.toString());
        return true;
    }
}