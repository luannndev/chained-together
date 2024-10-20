package dev.luan.chained.listeners;

import com.google.inject.Inject;
import dev.luan.chained.common.annotations.RegisteredListener;
import dev.luan.chained.services.ChainService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import static org.bukkit.entity.EntityType.SLIME;

@RegisteredListener
public class DeathListener implements Listener {

    @Inject
    private ChainService chainService;

    @EventHandler

    public void handleDeath(EntityDeathEvent event) {
        if (event.getEntityType() != SLIME) {
            return;
        }

        boolean isChain = this.chainService.getActiveChains().stream()
                .anyMatch(chain -> chain.getSlime().equals(event.getEntity()));

        event.setCancelled(isChain);
    }
}
