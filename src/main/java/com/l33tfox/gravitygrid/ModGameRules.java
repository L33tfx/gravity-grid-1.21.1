package com.l33tfox.gravitygrid;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ModGameRules {
    public static final GameRules.Key<GameRules.BooleanRule> GRAVITY_GRID_ENABLED =
            GameRuleRegistry.register("gravityGridEnabled", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));

    public static void init() {
        
    }
}
