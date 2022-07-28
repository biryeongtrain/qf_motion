package net.fabricmc.example.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;

public class MotionAdder {

    public static int AddMotion (Collection<? extends ServerPlayerEntity> players, float xValue, float yValue, float zValue) {

        try {
            for (ServerPlayerEntity player : players) {
                player.addVelocity(xValue, yValue, zValue);
                player.velocityModified = true;
            }
            Text.literal("Motion Used!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
