package com.tinnyman.astralwarsaddons.command;

import com.tinnyman.astralwarsaddons.gui.AdminGUI;
import com.tinnyman.astralwarsaddons.gui.BossManagementGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class AstralAddonsCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
        // If the sender is not a player, send them a message and return true
        if (!(sender instanceof Player)) {
            sender.sendMessage("&8[&eAstralAddons&8] &cThis command can only be run by a player.");
            return true;
        }

        // If there is more than 1 argument, return false
        if (args.length > 1)
            return false;

        // Cast the sender to a player
        Player player = (Player) sender;

        // If the first argument is "admin" then open the admin menu
        if (args.length > 0 && args[0].equalsIgnoreCase("admin")) {
            player.sendMessage("Opening admin menu...");

            // Open the admin menu
            new AdminGUI(player).open();
        }

        return false;
    }

    @Override
    public java.util.List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // If the sender is not a player, return null
        if (!(sender instanceof Player))
            return null;

        // Set the tab completion options for the first argument
        if (args.length == 1) {
            java.util.List<String> options = new java.util.ArrayList<>();
            options.add("admin");
            return options;
        } else {
            return null;
        }
    }
}
