package dev.luan.chained.listeners;

import com.google.inject.Inject;
import dev.luan.chained.common.annotations.RegisteredListener;
import dev.luan.chained.services.ChainService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

@RegisteredListener
public class MovementListener implements Listener {

    @Inject
    private ChainService chainService;

    @EventHandler
    public void handleMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Vector velocity = player.getVelocity();

        if (velocity.length() > 0.1 && !player.isClimbing()) {
            return;
        }

        if (!event.hasChangedPosition()) {
            return;
        }

        this.chainService.getLastMoved().put(event.getPlayer().getUniqueId(), System.currentTimeMillis());
    }
}
