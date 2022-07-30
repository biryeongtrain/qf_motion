package net.fabricmc.example.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import java.util.Collection;

public class MotionAdder {

    public static int AddMotion (Entity entity, float xValue, float yValue, float zValue) {

        try {
            entity.addVelocity(xValue, yValue, zValue);
            entity.velocityModified = true;

            Text.literal("Motion Used!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static int pushCommand(CommandContext<ServerCommandSource> objectCommandContext) {

        try {

            ServerPlayerEntity player = objectCommandContext.getSource().getPlayer();
            final float importantValue = 0.017453292f;
            double x;
            double z;

            x = (double) MathHelper.sin((float) player.getRotationVector().getX() * importantValue);
            z = (double) -(MathHelper.cos((float) player.getRotationVector().getZ() * importantValue));
            player.sendMessage(Text.literal("Rotation Vec : " + player.getRotationVector().getX()));
            player.sendMessage(Text.literal("Rotation Vec : " + player.getRotationVector().getZ()));
            player.sendMessage((Text.literal("X = " + x)));
            player.sendMessage((Text.literal("Z = " + z)));

            //player.setVelocity(x, 1.5, z);
            player.setVelocity(player.getRotationVector().getX(), 1, player.getRotationVector().getZ());
            player.velocityModified = true;

        }catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
