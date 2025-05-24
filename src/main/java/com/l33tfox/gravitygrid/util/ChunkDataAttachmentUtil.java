package com.l33tfox.gravitygrid.util;

import com.l33tfox.gravitygrid.ModAttachmentTypes;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.WorldChunk;

import java.util.Random;

public class ChunkDataAttachmentUtil {
    public static void attachGravityDirectionData(ServerWorld serverWorld, WorldChunk chunk) {
        //int gravDir = chunk.getAttachedOrCreate(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE);
        chunk.setAttached(ModAttachmentTypes.GRAVITY_DIRECTION_TYPE, generateRandomDirectionId());
    }

    public static int generateRandomDirectionId() {
        Random rand = new Random();
        return rand.nextInt(Direction.EAST.getId() + 1);
    }
}
