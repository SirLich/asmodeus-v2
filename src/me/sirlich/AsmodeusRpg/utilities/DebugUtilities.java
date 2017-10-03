package me.sirlich.AsmodeusRpg.utilities;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DebugUtilities implements CommandExecutor
{
    private static boolean debugMode = false;

    public static void debug(String s)
    {
        if (debugMode) {
            System.out.println(s);
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        System.out.println("Set debug mode to: " + !debugMode);
        debugMode = !debugMode;
        return false;
    }
}
