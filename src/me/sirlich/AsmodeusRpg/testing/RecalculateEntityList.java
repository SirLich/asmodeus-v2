package me.sirlich.AsmodeusRpg.testing;

import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityList;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import org.bukkit.command.CommandSender;

public class RecalculateEntityList extends AsmodeusCommand
{
    public RecalculateEntityList(){
        super("recalc");
    }
    @Override
    public boolean execute(CommandSender sender, String s, String[] args){
        RpgEntityList.recalculate();
        return true;
    }
}
