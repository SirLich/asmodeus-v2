package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameItem extends AsmodeusCommand
{
    public RenameItem()
    {
        super("rn");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args){
        if(args.length > 0){
            ItemStack itemStack = new ItemStack(Material.STICK);
            ItemMeta isMeta= itemStack.getItemMeta();
            isMeta.setDisplayName(args[0]);
            itemStack.setItemMeta(isMeta);
            Player player = (Player) sender;
            player.getInventory().addItem(itemStack);
        }
        return false;
    }
}
