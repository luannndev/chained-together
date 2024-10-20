package dev.luan.chained.listeners;

import com.google.inject.Inject;
import dev.luan.chained.common.annotations.RegisteredListener;
import dev.luan.chained.services.ChainService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import static org.bukkit.event.player.PlayerKickEvent.Cause.FLYING_PLAYER;

@RegisteredListener
public class KickListener implements Listener {

    @Inject
    private ChainService chainService;

    @EventHandler
    public void handleKick(PlayerKickEvent event) {
        if (event.getCause() != FLYING_PLAYER) {
            return;
        }

        Player player = event.getPlayer();
        boolean isChained = this.chainService.getActiveChains().stream()
                .anyMatch(chain -> chain.getPlayer().equals(player) || chain.getTarget().equals(player));

        event.setCancelled(isChained);
    }
}
