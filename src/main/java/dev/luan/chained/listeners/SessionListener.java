package dev.luan.chained.listeners;

import com.google.inject.Inject;
import dev.luan.chained.common.annotations.RegisteredListener;
import dev.luan.chained.services.ChainService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static dev.luan.chained.common.Colors.LIGHT_GRAY;
import static dev.luan.chained.common.Colors.YELLOW;
import static java.util.Optional.ofNullable;
import static net.kyori.adventure.text.Component.text;

@RegisteredListener
public class SessionListener implements Listener {

    @Inject
    private ChainService chainService;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Scoreboard scoreboard = player.getScoreboard();
        Team team = ofNullable(scoreboard.getTeam("NoCollision")).orElseGet(() -> scoreboard.registerNewTeam("NoCollision"));

        team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        team.addPlayer(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.chainService.getActiveChains().stream()
                .filter(chain -> chain.getPlayer().equals(player) || chain.getTarget().equals(player))
                .findFirst()
                .ifPresent(chain -> {
                    Player target = chain.getPlayer() == player ? chain.getTarget() : player;
                    this.chainService.disbandChain(chain);

                    target.sendMessage(text("Da", LIGHT_GRAY).appendSpace()
                            .append(text(player.getName(), YELLOW)).appendSpace()
                            .append(text("den Server verlassen hat, wurde die Kette aufgelöst.", LIGHT_GRAY)));
                });
    }
}