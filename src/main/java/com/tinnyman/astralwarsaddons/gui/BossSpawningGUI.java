package com.tinnyman.astralwarsaddons.gui;

import com.tinnyman.astralwarsaddons.AstralAddonsSettings;
import com.tinnyman.astralwarsaddons.TinnyLib;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BossSpawningGUI extends Gui {
    public BossSpawningGUI(Player player) {
        super(player, "boss-spawning-gui", "§cAstral Wars §8Boss Spawning", 5);
    }


    @Override
    public void onOpen(InventoryOpenEvent event) {
        AstralAddonsSettings settings = AstralAddonsSettings.getInstance(); // Get the settings instance
        List<String> bosses = settings.getBosses(); // Get the list of bosses from the settings instance

        // Add each bosses button to the GUI from the list of bosses in the config
        for (String bossKey : bosses) {
            String bossPath = "bosses." + bossKey + ".";
            String displayName = settings.getString(bossPath + "display_name");
            String mythicId = settings.getString(bossPath + "mythic_id");
            String skullTextureString = settings.getString(bossPath + "skull_texture");

            // Check if skull_texture is present before creating URL and adding icon
            if (skullTextureString != null && !skullTextureString.isEmpty()) {
                try {
                    URL skullTexture = new URL(skullTextureString);

                    addItem(new Icon(TinnyLib.createSkull(skullTexture))
                            .setName(displayName)
                            .setLore("§7Left-Click to spawn " + displayName + " §7at your location.",
                                    "§7Right-Click to get a " + displayName + " §7spawn egg.")
                            .onClick(e -> {
                                if (e.isLeftClick()) {
                                    String spawnCommand = "mm mob spawn " + mythicId + " 1 world," + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ();
                                    Bukkit.dispatchCommand(player.getServer().getConsoleSender(), spawnCommand);
                                    // Special case for OOG
                                    if (bossKey.equals("OOG")) {
                                        spawnCommand = "mm mob spawn " + "OOG_mace 1 world," + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ();
                                        Bukkit.dispatchCommand(player.getServer().getConsoleSender(), spawnCommand);
                                    }
                                } else if (e.isRightClick()) {
                                    String eggCommand = "mm egg give " + player.getName() + " " + mythicId;
                                    Bukkit.dispatchCommand(player.getServer().getConsoleSender(), eggCommand);
                                    // Special case for OOG
                                    if (bossKey.equals("oog")) {
                                        eggCommand = "mm egg give " + player.getName() + " " + "OOG_mace";
                                        Bukkit.dispatchCommand(player.getServer().getConsoleSender(), eggCommand);
                                    }
                                }
                            }));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Bukkit.getLogger().warning("Skull texture for boss " + bossKey + " is not a valid URL!");
                }
            } else {
                Bukkit.getLogger().warning("Skull texture for boss " + bossKey + " is null or empty!");
            }
        }

        // Back button
        addItem(40, new Icon(Material.BARRIER).setName("§c§lBack").setLore("§7Click to go back to the Boss Management menu.").onClick(e -> {
            new BossManagementGUI(player).open();
        }));
    }
}
