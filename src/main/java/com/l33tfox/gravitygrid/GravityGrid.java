package com.l33tfox.gravitygrid;

import com.l33tfox.gravitygrid.util.ChunkDataAttachmentUtil;
import com.l33tfox.gravitygrid.util.PlayerTickingUtil;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GravityGrid implements ModInitializer {
	public static final String MOD_ID = "gravitygrid";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModAttachmentTypes.init();

		ServerChunkEvents.CHUNK_GENERATE.register(ChunkDataAttachmentUtil::attachGravityDirectionData);
		ServerTickEvents.END_WORLD_TICK.register(PlayerTickingUtil::playersWorldTick);
	}

}