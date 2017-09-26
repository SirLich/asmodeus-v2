package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.core.PlayerList;
import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.items.ItemHandler;
import me.sirlich.AsmodeusRpg.items.RPGItem;
import me.sirlich.AsmodeusRpg.items.RPGWeapon;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetItem extends AsmodeusCommand {

    public GetItem() {
        super("item");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(sender instanceof Player){
            if (args.length == 1) {
                RPGWeapon i = ItemHandler.getWeaponFromString(args[0]);
                if (i != null) {
                    ((Player) sender).setItemInHand(i.generate());
                } else {
                    sender.sendMessage("Invalid item.");
                }
            } else {
                sender.sendMessage("Please specify your item.");
            }
        } else{
            System.out.println("Please only use this command in-game!");
            return false;
        }
        return true;
    }
}
