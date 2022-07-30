package net.biryeongtrain06.motion_adder.commands;

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
            entity.setVelocity(entity.getVelocity().add(xValue, yValue, zValue));
            entity.velocityModified = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public static int push (Entity entity, String type, float power) {
        try {
            double x = 0;
            double z = 0;
            // TODO : 이거 아마도 X 양수는 앞, 음수는 뒤 Z 양수는 우측 음수는 좌측인듯?
            entity.sendMessage(Text.literal("type = " + type));

            if (type.equals("forward")) {
                entity.sendMessage(Text.literal("forward"));
                x = MathHelper.cos((float) entity.getRotationVector().getX());
                z = MathHelper.sin((float) entity.getRotationVector().getZ());
            } else if (type.equals("backward")) {
                entity.sendMessage(Text.literal("backward"));
                x = -(MathHelper.cos((float) entity.getRotationVector().getX()));
                z = -(MathHelper.sin((float) entity.getRotationVector().getZ()));
            } else if (type.equals("left")) {
                entity.sendMessage(Text.literal("left"));
                x = (MathHelper.cos((float) entity.getRotationVector().getX()));
                z = -(MathHelper.sin((float) entity.getRotationVector().getZ()));
            } else if (type.equals("right")) {
                entity.sendMessage(Text.literal("right"));
                x = -(MathHelper.cos((float) entity.getRotationVector().getX()));
                z = (MathHelper.sin((float) entity.getRotationVector().getZ()));
            }
            
            entity.sendMessage(Text.literal("x =" + x + "z = " + z));
            entity.setVelocity(entity.getVelocity().add(x, power, z));
            entity.velocityModified = true;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
