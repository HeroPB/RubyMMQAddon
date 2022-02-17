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

public class CreeperKill extends CustomObjective implements Listener {
    Quests qp = (Quests) Bukkit.getServer().getPluginManager().getPlugin("Quests");
    private final List<String> mobnames;

    public CreeperKill() {
        this.setName("MobArena Kill Creeper");
        this.setAuthor("RubyCraft");
        this.setShowCount(true);
        this.setCountPrompt("Quanti vouchers deve aprire:");
        this.setDisplay("Creeper uccisi: %count%");
        mobnames = Arrays.asList(
                "CREEPER_NETHER",
                "CREEPER_WOOD",
                "CREEPER_ICE"
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
