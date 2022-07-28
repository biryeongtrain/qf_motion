package net.fabricmc.example;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.commands.MotionAdder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

import static net.minecraft.server.command.CommandManager.literal;

public class Qf_motion_test implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("qf_motion_test");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		CommandRegistrationCallback.EVENT.register((((dispatcher, registryAccess, environment) -> {
			dispatcher.register(
					literal("test").requires(cs -> cs.hasPermissionLevel(2))
							.then(CommandManager.argument("entity", EntityArgumentType.entity())
									.then(CommandManager.argument("x", FloatArgumentType.floatArg())
											.then(CommandManager.argument("y", FloatArgumentType.floatArg())
													.then(CommandManager.argument("z", FloatArgumentType.floatArg())
															.executes(ctx -> {
																return MotionAdder::AddMotion(EntityArgumentType.getEntity(ctx, "entity"), FloatArgumentType.getFloat(ctx, "x"))
																	})
													)
											)
									)
							)
			);
		})));
		LOGGER.info("Hello Fabric world!");
	}
}
