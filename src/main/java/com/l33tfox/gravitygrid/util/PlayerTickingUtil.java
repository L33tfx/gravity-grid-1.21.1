package com.l33tfox.gravitygrid.util;

import com.l33tfox.gravitygrid.GravityGrid;
import com.l33tfox.gravitygrid.ModAttachmentTypes;
import gravity_changer.api.GravityChangerAPI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.WorldChunk;

public class PlayerTickingUtil {
    public static void playersWorldTick(ServerWorld world) {
        for (ServerPlayerEntity player : world.getPlayers()) {
            WorldChunk chunk = world.getChunk(player.getChunkPos().x, player.getChunkPos().z);
            GravityGrid.LOGGER.info("player: " + player);
            GravityGrid.LOGGER.info("chunk: " + chunk);
            if (chunk != null && GravityChangerAPI.getBaseGravityDirection(player).getId() != chunk.getAttachedOrCreate(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE)) {
                GravityGrid.LOGGER.info("A");
                GravityChangerAPI.setBaseGravityDirection(player, Direction.byId(chunk.getAttachedOrCreate(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE)));
            }
        }
    }
}
