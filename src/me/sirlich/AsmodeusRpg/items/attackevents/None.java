package me.sirlich.AsmodeusRpg.items.attackevents;

import me.sirlich.AsmodeusRpg.items.AttackEvent;
import org.bukkit.entity.Player;

public class None extends AttackEvent {

    @Override
    public void execute(Player p) {

    }

    @Override
    public None finish() {
        return this;
    }
}
