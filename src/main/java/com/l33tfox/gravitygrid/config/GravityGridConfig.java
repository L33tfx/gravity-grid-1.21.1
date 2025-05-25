package com.l33tfox.gravitygrid.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "gravitygrid")
public class GravityGridConfig implements ConfigData {
    public boolean displayGravityDirection = true;

    @ConfigEntry.ColorPicker
    public int textColor = 0xFFFFFF;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int textXPos = 1;

    @ConfigEntry.Gui.Tooltip
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int textYPos = 95;

    public boolean displayBackground = true;
}
