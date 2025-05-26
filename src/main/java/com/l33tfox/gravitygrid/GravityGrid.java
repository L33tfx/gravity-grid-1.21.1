package com.l33tfox.gravitygrid;

import com.l33tfox.gravitygrid.config.GravityGridConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GravityGrid implements ModInitializer {
	public static final String MOD_ID = "gravitygrid";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModAttachmentTypes.init();
		ModServerEvents.init();
		ModGameRules.init();
		AutoConfig.register(GravityGridConfig.class, GsonConfigSerializer::new);
	}

}