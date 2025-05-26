package com.l33tfox.gravitygrid.mixin;

import com.l33tfox.gravitygrid.config.GravityGridConfig;
import gravity_changer.api.GravityChangerAPI;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LayeredDrawer;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow
    @Final
    private LayeredDrawer layeredDrawer;

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    @Final
    private DebugHud debugHud;

    @Shadow @Final private ChatHud chatHud;
    @Unique
    private static final Text GRAVITY_DIRECTION_DISPLAY = Text.translatable("menu.gravity_direction_display");

    @Unique
    private static GravityGridConfig config;

    @Inject(at=@At("TAIL"), method = "<init>")
    private void addGravityHudLayer(MinecraftClient client, CallbackInfo ci) {
        layeredDrawer.addLayer(this::renderGravityDirectionIndicator);
    }

    // Runs every tick on client to render the gravity text HUD display on screen
    @Unique
    private void renderGravityDirectionIndicator(DrawContext context, RenderTickCounter tickCounter) {
        config = AutoConfig.getConfigHolder(GravityGridConfig.class).getConfig();
        if (client.options.hudHidden
                || client.player == null
                || client.player.isSpectator()
                || !config.displayGravityDirection
                || debugHud.shouldShowDebugHud()
                || chatHud.isChatFocused()) {
            return; // do nothing - dont render gravity direction indicator hud element
        }

        Text direction = Text.of(GravityChangerAPI.getBaseGravityDirection(client.player).getName().toUpperCase());
        Text fullText = Texts.join(List.of(GRAVITY_DIRECTION_DISPLAY, direction), Text.of(" "));

        // Calculate absolute positions using config position percentages
        int xPos = Math.round(context.getScaledWindowWidth() * config.textXPos / 100.0F);
        int yPos = Math.round(context.getScaledWindowHeight() * config.textYPos / 100.0F);

        int textWidth = client.textRenderer.getWidth(fullText);

        if (config.displayBackground) {
            // this is how context.drawTextWithBackground creates a background
            context.fill(xPos - 2, yPos - 2, xPos + textWidth + 2, yPos + 9 + 2, 0x80000000);
        }
        context.drawTextWithShadow(client.textRenderer, fullText, xPos, yPos, config.textColor);
    }
}
