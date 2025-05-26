package com.l33tfox.gravitygrid.event;

import com.l33tfox.gravitygrid.ModAttachmentTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.WorldChunk;

import java.util.Random;

public class ChunkGenerateEvent {
    // Called when chunks are generated using event in ModServerEvents class. Adds int data attachments to each chunk
    // using Fabric Data Attachment API which auto-syncs
    public static void attachGravityDirectionData(ServerWorld serverWorld, WorldChunk chunk) {
        chunk.setAttached(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE, generateRandomDirectionId());
    }

    public static int generateRandomDirectionId() {
        Random rand = new Random();
        return rand.nextInt(Direction.EAST.getId() + 1);
    }
}
