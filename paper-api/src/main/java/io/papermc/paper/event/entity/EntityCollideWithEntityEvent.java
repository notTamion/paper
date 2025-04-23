package io.papermc.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Fired when two entities collide with each other.
 * If cancelled, both entities won't get pushed away from each other.
 * <br />
 * For specific entity collision results, use {@link #getResult()} and {@link #setResult(Result)}.
 * <br />
 * This event will not fire if collisions are disabled in the paper config.
 */
public class EntityCollideWithEntityEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity collided;
    private Result result;

    private double colliderPushX;
    private double colliderPushY;
    private double colliderPushZ;

    private double collidedPushX;
    private double collidedPushY;
    private double collidedPushZ;


    @ApiStatus.Internal
    public EntityCollideWithEntityEvent(@NotNull Entity collider, @NotNull Entity collided, double colliderPushX, double colliderPushY, double colliderPushZ, double collidedPushX, double collidedPushY, double collidedPushZ) {
        super(collider);
        this.collided = collided;
        this.result = Result.ALLOW;

        this.colliderPushX = colliderPushX;
        this.colliderPushY = colliderPushY;
        this.colliderPushZ = colliderPushZ;

        this.collidedPushX = collidedPushX;
        this.collidedPushY = collidedPushY;
        this.collidedPushZ = collidedPushZ;
    }


    /**
     * Gets the entity colliding with another entity.
     *
     * @return the entity colliding with another entity
     */
    public Entity getCollider() {
        return this.getEntity();
    }

    /**
     * Gets the entity that was collided with.
     *
     * @return the entity that was collided with
     */
    public Entity getCollided() {
        return this.collided;
    }

    /**
     * Returns the collider's push in the X direction
     *
     * @return the collider's push in the X direction
     */
    public double getColliderPushX() {
        return colliderPushX;
    }

    /**
     * Returns the collider's push in the Y direction
     *
     * @return the collider's push in the Y direction
     */
    public double getColliderPushY() {
        return colliderPushY;
    }

    /**
     * Returns the collider's push in the Z direction
     *
     * @return the collider's push in the Z direction
     */
    public double getColliderPushZ() {
        return colliderPushZ;
    }

    /**
     * Sets the collider's push in the X direction
     *
     * @param colliderPushX push in the X direction
     */
    public void setColliderPushX(double colliderPushX) {
        this.colliderPushX = colliderPushX;
    }

    /**
     * Sets the collider's push in the Y direction
     *
     * @param colliderPushY push in the Y direction
     */
    public void setColliderPushY(double colliderPushY) {
        this.colliderPushY = colliderPushY;
    }

    /**
     * Sets the collider's push in the Z direction
     *
     * @param colliderPushZ push in the Z direction
     */
    public void setColliderPushZ(double colliderPushZ) {
        this.colliderPushZ = colliderPushZ;
    }

    /**
     * Returns the collided entities' push in the X direction
     *
     * @return the collided entities' push in the X direction
     */
    public double getCollidedPushX() {
        return this.collidedPushX;
    }

    /**
     * Returns the collided entities' push in the Y direction
     *
     * @return the collided entities' push in the Y direction
     */
    public double getCollidedPushY() {
        return this.collidedPushY;
    }

    /**
     * Returns the collided entities' push in the Z direction
     *
     * @return the collided entities' push in the Z direction
     */
    public double getCollidedPushZ() {
        return this.collidedPushZ;
    }

    /**
     * Sets the collided entities' push in the X direction
     *
     * @param collidedPushX push in the X direction
     */
    public void setCollidedPushX(double collidedPushX) {
        this.collidedPushX = collidedPushX;
    }

    /**
     * Sets the collided entities' push in the Y direction
     *
     * @param collidedPushY push in the Y direction
     */
    public void setCollidedPushY(double collidedPushY) {
        this.collidedPushY = collidedPushY;
    }

    /**
     * Sets the collided entities' push in the Z direction
     *
     * @param collidedPushZ push in the Z direction
     */
    public void setCollidedPushZ(double collidedPushZ) {
        this.collidedPushZ = collidedPushZ;
    }

    /**
     * Returns the result of this event. By default, the result will be {@link Result#ALLOW}.
     *
     * @return the result of this event
     */
    public Result getResult() {
        return this.result;
    }

    /**
     * Sets the result of this event. This will change the behavior of the collision.
     *
     * @param result the result to set
     */
    public void setResult(Result result) {
        this.result = result;
    }


    /**
     * Returns true only if both entities will NOT be pushed away from each other.
     * For specific entity collision results, use {@link #getResult()}.
     *
     * @return true if the event is cancelled
     */
    @Override
    public boolean isCancelled() {
        return this.result == Result.DENY;
    }

    /**
     * Sets the cancellation state of this event.
     * For specific entity collision results, use {@link #setResult(Result)}.
     *
     * @param cancel true if you wish to cancel this event
     */
    @Override
    public void setCancelled(final boolean cancel) {
        this.result = cancel ? Result.DENY : Result.ALLOW;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public enum Result {
        /**
         * Allows both collisions and both entities will be pushed.
         */
        ALLOW,
        /**
         * Denies both collisions and neither entity will be pushed.
         */
        DENY,
        /**
         * Denies the collision for only the collider.
         */
        DENY_COLLIDER_COLLISION,
        /**
         * Denies the collision for only the entity that was collided with.
         */
        DENY_COLLIDED_COLLISION
    }
}
