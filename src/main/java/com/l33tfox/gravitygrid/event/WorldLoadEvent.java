package com.l33tfox.gravitygrid.event;

import com.l33tfox.gravitygrid.ModAttachmentTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.WorldChunk;

public class WorldLoadEvent {
    public static int chunkLength = 16;

    public static void setFairSpawnpoint(MinecraftServer server, ServerWorld world) {
        int spawnChunkX = world.getSpawnPos().getX() / chunkLength;
        int spawnChunkZ = world.getSpawnPos().getZ() / chunkLength;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                WorldChunk nearbySpawnChunk = world.getChunk(spawnChunkX + i, spawnChunkZ + j);

                if (nearbySpawnChunk.getAttachedOrCreate(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE) != Direction.DOWN.getId()) {
                    nearbySpawnChunk.setAttached(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE, Direction.DOWN.getId());
                }
            }
        }
    }
}
