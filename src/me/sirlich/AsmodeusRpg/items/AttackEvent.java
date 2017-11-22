package me.sirlich.AsmodeusRpg.items;

import me.sirlich.AsmodeusRpg.utilities.Range;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AttackEvent {

    public ArrayList<String> description = new ArrayList<>();

    public abstract void execute(Player p);

    public abstract AttackEvent finish();

    public ArrayList<String> getDescription() {
        return this.description;
    }

    public AttackEvent setDescription(String... lore) {
        Arrays.stream(lore).forEach(s -> this.description.add(ChatColor.translateAlternateColorCodes('&', s)));
        return this;
    }
}
