package com.tinnyman.astralwarsaddons.gui;

import com.tinnyman.astralwarsaddons.TinnyLib;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

import java.net.MalformedURLException;
import java.net.URL;

public class AdminGUI extends Gui {
    public AdminGUI(Player player) {
        super(player, "admin-gui", "§cAstral Wars §7Admin Menu", 4);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        ItemStack skull = null;

        try {
            skull = TinnyLib.createSkull(new URL("https://textures.minecraft.net/texture/869887018b2ab31b744375801bb0e75575d710628ee362e76918c4fda3e4b507"));
            addItem(11, new Icon(skull).setName("§c§lBoss Management").setLore("§7Click to open the boss management menu.").onClick(e -> {
                new BossManagementGUI(player).open();
            }));

            skull = TinnyLib.createSkull(new URL("https://textures.minecraft.net/texture/5502d50eab63f292f42ae9ef7e5d039fb1c133ff4699d5a30b2e0e8e748a2787"));
            addItem(13, new Icon(skull).setName("§e§lRank Management").setLore("§7Click to open the rank management menu.").onClick(e -> {
                //new RankManagementGUI(player).open();
            }));

            skull = TinnyLib.createSkull(new URL("https://textures.minecraft.net/texture/dd27a921c67d8e22ddf19959ffd9b5555978a5c1ef6601e3a08105dd1a5c4dd8"));
            addItem(15, new Icon(skull).setName("§b§lPlayer Management").setLore("§7Click to open the player management menu.").onClick(e -> {
                //new PlayerManagementGUI(player).open();
            }));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        addItem(31, new Icon(Material.BARRIER).setName("§c§lClose").setLore("§7Click to exit the admin menu.").onClick(e -> {
            player.closeInventory();
        }));
    }
}
