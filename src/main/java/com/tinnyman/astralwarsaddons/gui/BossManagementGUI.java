package com.tinnyman.astralwarsaddons.gui;

import com.tinnyman.astralwarsaddons.TinnyLib;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

import java.net.MalformedURLException;
import java.net.URL;

public class BossManagementGUI extends Gui {
    public BossManagementGUI(Player player) {
        super(player, "boss-management-gui", "§cAstral Wars §7Boss Management", 4);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        ItemStack skull = null;

        try {
            skull = TinnyLib.createSkull(new URL("https://textures.minecraft.net/texture/8975e51bfe48f876e7748935d692234dbff5df7398d7698adce346788f3ce7d8"));
            addItem(11, new Icon(skull).setName("§c§lSpawn Bosses").setLore("§7Click to open the boss spawning menu.").onClick(e -> {
                new BossSpawningGUI(player).open();
            }));

            skull = TinnyLib.createSkull(new URL("https://textures.minecraft.net/texture/fb3329b3c82ddfcd19255c42712eca7bc24472c03f78668faff7d8431997d693"));
            addItem(13, new Icon(skull).setName("§4§lKill Active Bosses").setLore("§7Click to kill all active bosses!").onClick(e -> {
                ConsoleCommandSender console = player.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "mm mobs killall");
            }));

            skull = TinnyLib.createSkull(new URL("https://textures.minecraft.net/texture/fcb8f06885d1daafd26cd95b3482cb525d881a67e0d247161b908d93d56d114f"));
            addItem(15, new Icon(skull).setName("§e§lBoss Auto-Spawning").setLore("§7Click to open the boss auto-spawning menu.").onClick(e -> {
                //new BossAutoSpawningGUI(player).open();
            }));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        addItem(31, new Icon(Material.BARRIER).setName("§c§lBack").setLore("§7Click to go back to the admin menu.").onClick(e -> {
            new AdminGUI(player).open();
        }));
    }
}