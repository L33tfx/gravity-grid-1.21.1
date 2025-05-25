package com.l33tfox.gravitygrid.event;

import com.l33tfox.gravitygrid.ModAttachmentTypes;
import gravity_changer.api.GravityChangerAPI;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.WorldChunk;

public class ServerWorldTickEvent {
    public static void playersWorldTick(ServerWorld world) {
        for (ServerPlayerEntity player : world.getPlayers()) {
            if (player.isSpectator()) {
                return;
            }

            WorldChunk chunk = world.getChunk(player.getChunkPos().x, player.getChunkPos().z);
            if (chunk != null && GravityChangerAPI.getBaseGravityDirection(player).getId() != chunk.getAttachedOrCreate(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE)) {
                GravityChangerAPI.setBaseGravityDirection(player, Direction.byId(chunk.getAttachedOrCreate(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE)));
            }
        }
    }
}
