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
import java.util.Collections;
import java.util.List;

public final class RubyMMobsQAddon extends CustomObjective implements Listener {
    Quests qp = (Quests) Bukkit.getServer().getPluginManager().getPlugin("Quests");
    private final List<String> mobnames;

    public RubyMMobsQAddon() {
        this.setName("MobArena Kill Hard");
        this.setAuthor("RubyCraft");
        this.setShowCount(true);
        this.setCountPrompt("Quanti vouchers deve aprire:");
        this.setDisplay("vouchers aperti: %count%");
        mobnames = Arrays.asList(
                "ZOMBIESNOWMAN",
                "ZOMBIESNOWMAN_SUPPLY",
                "SKELETON_ICE",
                "WOLF_ICE",
                "ZOMBIESNOWMAN2",
                "ICE_BOSS_MINI",
                "IRONGOLEM_ICE",
                "ICE_BOSS",
                "BABY_ZOMBIESNOWMAN",
                "ZOMBIESNOWMAN3",
                "ZOMBIESNOWMAN2_SUPPLY"
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
