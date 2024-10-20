package dev.luan.chained.listeners;

import com.google.inject.Inject;
import dev.luan.chained.common.annotations.RegisteredListener;
import dev.luan.chained.services.ChainService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityUnleashEvent;

@RegisteredListener
public class LeashListener implements Listener {

    @Inject
    private ChainService chainService;

    @EventHandler
    public void handleLeash(EntityUnleashEvent event) {
        boolean isChain = this.chainService.getActiveChains().stream()
                .anyMatch(chain -> chain.getSlime().equals(event.getEntity()));

        event.setDropLeash(!isChain);
        event.setCancelled(isChain);
    }
}
