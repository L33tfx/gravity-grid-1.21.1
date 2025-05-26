package com.l33tfox.gravitygrid;

// based off Linguardium's data attachment example https://gist.github.com/Linguardium/cebcd41c6bbcd74eaa1f8b40ec2bbec8

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class ModAttachmentTypes {
    // Register a type of attached data. This data can be attached to anything, this is only a type
    public static final AttachmentType<Integer> GRAVITY_DIRECTION_TYPE = AttachmentRegistry.create(
                    Identifier.of(GravityGrid.MOD_ID,"gravity_direction_attachment"),
                    builder ->builder // we are using a builder chain here to configure the attachment data type
                            .initializer(Direction.DOWN::getId) // a default value to provide if you dont supply one
                            .persistent(Codec.INT) // how to save and load the data when the object it is attached to is saved or loaded
            .syncWith(
                    PacketCodecs.INTEGER,  // how to turn the data into a packet to send to players
                    AttachmentSyncPredicate.all() // who to send the data to
            )
 );

    public static void init() {
        // This empty method can be called from the mod initializer to ensure our component type is registered at mod initialization time
        // ModAttachmentTypes.init();
    }
}
