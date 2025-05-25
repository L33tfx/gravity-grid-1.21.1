package com.l33tfox.gravitygrid.mixin;

import com.l33tfox.gravitygrid.ModAttachmentTypes;
import gravity_changer.api.GravityChangerAPI;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LayeredDrawer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow
    @Final
    private LayeredDrawer layeredDrawer;

    @Shadow
    @Final
    private MinecraftClient client;

    @Unique
    private static final Text GRAVITY_DIRECTION_DISPLAY = Text.translatable("menu.gravity_direction_display");

    @Inject(at=@At("TAIL"), method = "<init>")
    private void addGravityHudLayer(MinecraftClient client, CallbackInfo ci) {
        layeredDrawer.addLayer(this::renderGravityDirectionIndicator);
    }

    @Unique
    private void renderGravityDirectionIndicator(DrawContext context, RenderTickCounter tickCounter) {
        if (client.options.hudHidden || client.player == null) {
            return;
        }

        Text direction = Text.of(GravityChangerAPI.getBaseGravityDirection(client.player).getName().toUpperCase());
        List<Text> fullText = List.of(GRAVITY_DIRECTION_DISPLAY, direction);

        context.drawTextWithShadow(client.textRenderer, Texts.join(fullText, Text.of(" ")), 0, 0, 0xFFFFFF);
    }
}
