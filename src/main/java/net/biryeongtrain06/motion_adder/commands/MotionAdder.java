package net.biryeongtrain06.motion_adder.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;

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
    public static int push (Entity entity, String type, float power, float yVelocity) {
        try {
            // TODO : Forward Dash = entity.getRotationVector()
            Vec3d vec3d = getVec3d(entity, getDashValue(type), power, yVelocity);

            entity.setVelocity(entity.getVelocity().add(vec3d));
            entity.velocityModified = true;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int getDashValue(String value) {

        if (value.equals("forward")) {
            return 0;
        }
        if (value.equals("backward")) {
            return 1;
        }
        if (value.equals("left")) {
            return 2;
        }
        if (value.equals("right")) {
            return 3;
        }

        return -1;
    }

    private static Vec3d getVec3d (Entity entity, int value, float power, float yVelocity) throws CommandSyntaxException {

        Vec3d testVec3d = entity.getRotationVector().multiply(power, 0.15, power).normalize();

        if (value == -1) throw new CommandSyntaxException(new SimpleCommandExceptionType(Text.literal("can't find this type")), Text.literal("test"));
        if (value == 0) return new Vec3d(testVec3d.x, yVelocity, testVec3d.z);
        if (value == 1) return new Vec3d(-testVec3d.x, yVelocity, -testVec3d.z);
        if (value == 2) return new Vec3d(testVec3d.z, yVelocity, -testVec3d.x);
        if (value == 3) return new Vec3d(-testVec3d.z, yVelocity, testVec3d.x);
        return new Vec3d(0 , power, 0);
    }
}
