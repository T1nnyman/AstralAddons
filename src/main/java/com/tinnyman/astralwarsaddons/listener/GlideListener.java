package com.tinnyman.astralwarsaddons.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.*;

public class GlideListener implements Listener {
    private final List<UUID> glidingPlayers = new ArrayList<>();

    @EventHandler
    public void onToggleGlideEvent(EntityToggleGlideEvent event) {
        if (glidingPlayers.contains(event.getEntity().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // If there is a solid block directly under the player, set the player to not gliding
        if (glidingPlayers.contains(player.getUniqueId()) && player.getLocation().subtract(0, 1, 0).getBlock().getType().isSolid()) {
            player.setGliding(false);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Check if the list of gliding players contains the player
        if (glidingPlayers.contains(event.getPlayer().getUniqueId())) {
            // Check if the player Right-Clicked with an empty hand
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                // Check if the player is midair
                if (event.getPlayer().getLocation().getBlock().getType().isAir()) {
                    event.getPlayer().setGliding(true); // Set the player to gliding
                    // Give the player a boost in the direction they are looking
                    event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(2));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        // Check if the player is in the list of gliding players and is sneaking
        if (glidingPlayers.contains(event.getPlayer().getUniqueId()) && event.isSneaking()) {
            event.getPlayer().setGliding(false); // Set the player to not gliding
        }
    }

    public void toggleGlide(Player player) {
        if (glidingPlayers.contains(player.getUniqueId())) {
            player.sendMessage("Glide disabled.");
            glidingPlayers.remove(player.getUniqueId());
        } else {
            player.sendMessage("Glide enabled.");
            glidingPlayers.add(player.getUniqueId());
        }
    }
}
