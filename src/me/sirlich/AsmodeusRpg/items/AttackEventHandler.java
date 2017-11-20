package me.sirlich.AsmodeusRpg.items;

import me.sirlich.AsmodeusRpg.items.attackevents.Heal;
import me.sirlich.AsmodeusRpg.items.attackevents.Hit;
import me.sirlich.AsmodeusRpg.items.attackevents.None;

import java.util.ArrayList;
import java.util.HashMap;

public class AttackEventHandler {

    public static HashMap<String, AttackEvent> events = new HashMap<>();

    public static AttackEvent getFromString(String... args) {
        String name = args[0];
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            strings.add(args[i]);
        }
        AttackEvent ev;
        if (!events.containsKey(name)) {
            ev = new None();
        } else {
            ev = events.get(name);
        }

        if (ev instanceof Hit) {
            return new Hit()
                    .setDamage(Integer.parseInt(strings.get(0)), Integer.parseInt(strings.get(1)))
                    .setRange(Double.parseDouble(strings.get(2)))
                    .setStamina(Double.parseDouble(strings.get(3)))
                    .setKnockback(Double.parseDouble(strings.get(4)))
                    .setKnockup(Double.parseDouble(strings.get(5)))
                    .finish();
        } else if (ev instanceof Heal) {
            return new Heal()
                    .setRecovery(Integer.parseInt(strings.get(0)))
                    .setCooldown(Integer.parseInt(strings.get(1)))
                    .setStamina(Double.parseDouble(strings.get(2)))
                    .finish();
        } else {
            return new None();
        }
    }

    public static void loadEvents() {
        events.put("melee-hit", new Hit());
        events.put("heal", new Heal());
    }


}
