package com.tinnyman.astralwarsaddons;

import com.tinnyman.astralwarsaddons.command.AstralAddonsCommand;
import com.tinnyman.astralwarsaddons.listener.GlideListener;
import mc.obliviate.inventory.InventoryAPI;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class AstralAddons extends JavaPlugin {
    private final GlideListener glideListener = new GlideListener();

    @Override
    public void onLoad() {
        // Plugin startup logic
        getLogger().info("Astral Addons is loading...");
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Astral Addons has been enabled!");
        new InventoryAPI(this).init(); // Initialize InventoryAPI

        getCommand("astraladdons").setExecutor(new AstralAddonsCommand(glideListener));

        getServer().getPluginManager().registerEvents(glideListener, this);

        // Load the config
        AstralAddonsSettings.getInstance().load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Astral Addons has been disabled!");
    }

    /**
     * Get the instance of the AstralAddons class
     * @return the instance of the AstralAddons class
     */
    public static AstralAddons getInstance() {
        return getPlugin(AstralAddons.class);
    }
}
