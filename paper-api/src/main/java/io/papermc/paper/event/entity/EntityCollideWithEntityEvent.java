package io.papermc.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

/**
 * Fired when two entities collide with each other.
 * If cancelled, the entities won't get pushed away from each other.
 */
public class EntityCollideWithEntityEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private boolean cancelled;
    private final Set<Entity> entities;
    private double pushX;
    private double pushY;
    private double pushZ;

    @ApiStatus.Internal
    public EntityCollideWithEntityEvent(@NotNull Entity entity1, @NotNull Entity entity2, double pushX, double pushY, double pushZ) {
        this.entities = Set.of(entity1, entity2);
        this.pushX = pushX;
        this.pushY = pushY;
        this.pushZ = pushZ;
    }

    /**
     * Returns the Entities involved in this event
     *
     * @return Entities that are involved in this event
     */
    public @NotNull Set<Entity> getEntities() {
        return this.entities;
    }

    /**
     * Returns push in the X direction
     *
     * @return push in the X direction
     */
    public double getPushX() {
        return this.pushX;
    }

    /**
     * Returns push in the Y direction
     *
     * @return push in the Y direction
     */
    public double getPushY() {
        return this.pushY;
    }

    /**
     * Returns push in the Z direction
     *
     * @return push in the Z direction
     */
    public double getPushZ() {
        return this.pushZ;
    }

    /**
     * Sets the push in the X direction
     *
     * @param pushX push in the X direction
     */
    public void setPushX(double pushX) {
        this.pushX = pushX;
    }

    /**
     * Sets the push in the Y direction
     *
     * @param pushY push in the Y direction
     */
    public void setPushY(double pushY) {
        this.pushY = pushY;
    }

    /**
     * Sets the push in the Z direction
     *
     * @param pushZ push in the Z direction
     */
    public void setPushZ(double pushZ) {
        this.pushZ = pushZ;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }
}
