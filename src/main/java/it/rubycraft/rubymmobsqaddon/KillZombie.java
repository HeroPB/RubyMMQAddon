package it.rubycraft.rubymmobsqaddon;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import me.blackvein.quests.CustomObjective;
import me.blackvein.quests.Quest;
import me.blackvein.quests.Quests;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;

public class KillZombie extends CustomObjective implements Listener {
    Quests qp = (Quests) Bukkit.getServer().getPluginManager().getPlugin("Quests");
    private final List<String> mobnames;

    public KillZombie() {
        this.setName("Kill Zombie");
        this.setAuthor("RubyCraft");
        this.setShowCount(true);
        this.setCountPrompt("Quanti vouchers deve aprire:");
        this.setDisplay("Zombie uccisi: %count%");
        mobnames = Arrays.asList(
                "ZOMBIEWOOD",
                "ZOMBIESNOWMAN3",
                "ZOMBIESNOWMAN2",
                "ZOMBIESNOWMAN",
                "ZOMBIEWOOD2",
                "ZOMBIEWOOD3",
                "ZOMBIENETHER3",
                "ZOMBIENETHER2",
                "ZOMBIENETHER"
        );
    }

    @EventHandler
    public void onNormalMobarenaMobSchiatEvent(MythicMobDeathEvent e) {
        if(!(e.getKiller() instanceof Player)) return;
        Player killer = (Player) e.getKiller();
        for(String mobname : mobnames) {
            if (e.getMobType().getInternalName().equals(mobname)) {
                for (Quest quest : qp.getQuester(killer.getUniqueId()).getCurrentQuests().keySet()) {
                    incrementObjective(killer.getPlayer(), this, 1, quest);
                }
            }
        }
    }
}