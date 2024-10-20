package dev.luan.chained.common.models;

import dev.luan.chained.common.enums.Difficulty;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.scheduler.BukkitTask;

@Data
@Builder
public class Chain {

    private final Player player;
    private final Player target;
    private final Difficulty difficulty;
    private Slime slime;
    private BukkitTask task;

    public void attachLeash() {
        Location offset = this.target.getLocation().add(0, 0.5, 0);
        this.slime = this.target.getWorld().spawn(offset, Slime.class, slime -> {
           slime.setSize(0);
           slime.setAI(false);
           slime.setSilent(false);
           slime.setGravity(false);
           slime.setInvisible(true);
           slime.setNoPhysics(true);
           slime.setCollidable(false);
           slime.setInvulnerable(true);
           slime.setLeashHolder(this.player);
        });
    }

    public void disband() {
        this.slime.remove();
        this.task.cancel();
    }
}
