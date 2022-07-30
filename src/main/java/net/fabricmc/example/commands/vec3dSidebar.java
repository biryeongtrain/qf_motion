package net.fabricmc.example.commands;


import com.mojang.brigadier.context.CommandContext;
import eu.pb4.sidebars.api.Sidebar;
import eu.pb4.sidebars.api.lines.SidebarLine;
import eu.pb4.sidebars.interfaces.SidebarHolder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class vec3dSidebar {

    public static int openGUI (CommandContext<ServerCommandSource> objectCommandContext) {

        try {
            ServerPlayerEntity player = objectCommandContext.getSource().getPlayer();
            Sidebar sidebar = new Sidebar(Sidebar.Priority.HIGH);

            sidebar.setTitle(Text.literal("Vec3d Debug"));
            sidebar.setUpdateRate(1);
            sidebar.addLines(SidebarLine.create(0, (p) -> Text.literal("Player UUID : " + p.getUuid())));
            sidebar.addLines(SidebarLine.create(0, (p) -> Text.literal("Player X : " + p.getX())));
            sidebar.addLines(SidebarLine.create(0, (p) -> Text.literal("Player Y : " + p.getY())));
            sidebar.addLines(SidebarLine.create(0, (p) -> Text.literal("Player Z : " + p.getZ())));
            sidebar.addLines(SidebarLine.create(0, (p) -> Text.literal("Player Vec3D : " + p.getVelocity())));
            sidebar.addLines(SidebarLine.create(0, (p) -> Text.literal("Player RotatationVector : " + p.getRotationVector())));

            sidebar.addPlayer(player);
            sidebar.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int closeGUI (CommandContext<ServerCommandSource> objectCommandContext){
        try {

            ServerPlayerEntity player = objectCommandContext.getSource().getPlayer();

            ((SidebarHolder) player.networkHandler).removeSidebar(((SidebarHolder) player.networkHandler).getCurrentSidebar());
        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}
