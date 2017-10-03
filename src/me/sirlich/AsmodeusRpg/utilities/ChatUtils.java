package me.sirlich.AsmodeusRpg.utilities;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils
{
    public static void chatInfo(Player p, String m)
    {
        p.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "!" + ChatColor.DARK_GREEN + "] " + ChatColor.WHITE + m);
    }

    public static void chatWarning(Player p, String m)
    {
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "!" + ChatColor.GOLD + "] " + ChatColor.WHITE + m);
    }

    public static void chatError(Player p, String m)
    {
        p.sendMessage(ChatColor.RED + m);
    }

    public static void civilianChat(Player p, String m)
    {
        p.sendMessage(ChatColor.AQUA + "<>" + ChatColor.WHITE + m);
    }

    public static void abilitiesChat(Player p, String m)
    {
        p.sendMessage(ChatColor.AQUA + m);
    }
}
