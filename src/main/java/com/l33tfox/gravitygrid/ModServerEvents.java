package com.l33tfox.gravitygrid;

import com.l33tfox.gravitygrid.event.ChunkGenerateEvent;
import com.l33tfox.gravitygrid.event.ServerWorldTickEvent;
import com.l33tfox.gravitygrid.event.WorldLoadEvent;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;

public class ModServerEvents {

    public static void init() {
        ServerChunkEvents.CHUNK_GENERATE.register(ChunkGenerateEvent::attachGravityDirectionData);
        ServerTickEvents.END_WORLD_TICK.register(ServerWorldTickEvent::playersWorldTick);
        ServerWorldEvents.LOAD.register(WorldLoadEvent::setFairSpawnpoint);
    }
}
