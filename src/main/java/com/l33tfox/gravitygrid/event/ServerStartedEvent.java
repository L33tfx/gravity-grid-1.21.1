package com.l33tfox.gravitygrid.event;

import com.l33tfox.gravitygrid.ModAttachmentTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.WorldChunk;

public class ServerStartedEvent {
    public static int chunkLength = 16;

    // Called immediately after server starts using event in ModServerEvents. Loops over 3x3 grid of chunks around
    // spawnpoint for each world and makes sure they all have normal downwards gravity to prevent spawn killing
    public static void setFairSpawnpoint(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    WorldChunk nearbySpawnChunk = world.getWorldChunk(world.getSpawnPos().add(chunkLength * i, 0, chunkLength * j));

                    if (nearbySpawnChunk.getAttachedOrCreate(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE) != Direction.DOWN.getId()) {
                        nearbySpawnChunk.setAttached(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE, Direction.DOWN.getId());
                    }
                }
            }
        }
    }
}
