package com.tinnyman.astralwarsaddons;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;

import java.net.URL;
import java.util.UUID;

public class TinnyLib {
    /**
     * Creates a skull with a custom texture.
     * @param url The url of the texture.
     * @return The skull with the added texture.
     */
    public static ItemStack createSkull(URL url) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta(); // Get the skull meta.
        PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID()); // Create a new game profile with a random UUID.
        PlayerTextures textures = profile.getTextures(); // Get the textures of the profile.
        textures.setSkin(url); // Set the skin of the textures.
        profile.setTextures(textures); // Set the textures of the profile.
        skullMeta.setPlayerProfile(profile); // Set the skull meta to the skull.
        skull.setItemMeta(skullMeta); // Set the skull meta to the skull.

        return skull; // Return the skull.
    }
}
