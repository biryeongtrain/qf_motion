package net.biryeongtrain06.motion_adder;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.biryeongtrain06.motion_adder.commands.MotionAdder;
import net.biryeongtrain06.motion_adder.commands.vec3dSidebar;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
					literal("addmotion").requires(cs -> cs.hasPermissionLevel(2))
							.then(CommandManager.argument("entity", EntityArgumentType.entity())
									.then(CommandManager.argument("x", FloatArgumentType.floatArg())
											.then(CommandManager.argument("y", FloatArgumentType.floatArg())
													.then(CommandManager.argument("z", FloatArgumentType.floatArg())
															.executes(ctx -> MotionAdder.AddMotion(EntityArgumentType.getEntity(ctx, "entity"), FloatArgumentType.getFloat(ctx, "x"), FloatArgumentType.getFloat(ctx, "y"), FloatArgumentType.getFloat(ctx, "z")))
													)
											)
									)
							)
			);
			dispatcher.register(
					literal("Open_Gui").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
							.executes(vec3dSidebar::openGUI)
			);
			dispatcher.register(
					literal("Close_Gui").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
							.executes(vec3dSidebar::closeGUI)
			);

			dispatcher.register(
					literal("dash").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
							.then(CommandManager.argument("entity", EntityArgumentType.entity())
								.then(CommandManager.argument("type", StringArgumentType.string())
										.then(CommandManager.argument("power", FloatArgumentType.floatArg())
												.then(CommandManager.argument("yheight", FloatArgumentType.floatArg())
											.executes(ctx -> MotionAdder.push(EntityArgumentType.getEntity(ctx, "entity"), StringArgumentType.getString(ctx, "type"), FloatArgumentType.getFloat(ctx, "power"), FloatArgumentType.getFloat(ctx, "yheight")))))))
			);
		})));
		LOGGER.info("qf Motion Adder is Completed Loaded!");
	}
}
