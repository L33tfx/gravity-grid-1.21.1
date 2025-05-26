package com.l33tfox.gravitygrid.event;

import com.l33tfox.gravitygrid.ModAttachmentTypes;
import com.l33tfox.gravitygrid.ModGameRules;
import gravity_changer.api.GravityChangerAPI;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.WorldChunk;

public class ServerWorldTickEvent {
    // if all players' gravity has already been reset to Direction.DOWN after gamerule changed from true -> false
    public static boolean gravityWasReset = false;

    // Called every tick in a world on the server-side using event in ModServerEvents. Sets correct gravity directions
    // for all players.
    public static void playersWorldTick(ServerWorld world) {
        // if gravity grid gamerule is set to false
        if (!world.getGameRules().getBoolean(ModGameRules.GRAVITY_GRID_ENABLED)) {
            if (!gravityWasReset) {
                resetGravityForAllPlayers(world);
                gravityWasReset = true;
            }
            return;
        }

        gravityWasReset = false;
        setGravityDirectionForAllPlayers(world);
    }

    private static void resetGravityForAllPlayers(ServerWorld world) {
        for (ServerPlayerEntity player : world.getPlayers()) {
            if (!player.isSpectator()) {
                GravityChangerAPI.setBaseGravityDirection(player, Direction.DOWN);
            }
        }
    }

    private static void setGravityDirectionForAllPlayers(ServerWorld world) {
        for (ServerPlayerEntity player : world.getPlayers()) {
            if (!player.isSpectator()) { // don't affect spectators' gravity
                WorldChunk chunk = world.getChunk(player.getChunkPos().x, player.getChunkPos().z);

                // if chunk exists and player's gravity has not been set to chunk's gravity yet
                if (chunk != null && GravityChangerAPI.getBaseGravityDirection(player).getId() != chunk.getAttachedOrCreate(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE)) {
                    GravityChangerAPI.setBaseGravityDirection(player, Direction.byId(chunk.getAttachedOrCreate(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE)));
                }
            }
        }
    }
}
