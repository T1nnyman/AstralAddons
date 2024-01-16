package com.tinnyman.astralwarsaddons;

import org.bukkit.configuration.file.YamlConfiguration;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AstralAddonsSettings {
    private final static AstralAddonsSettings instance = new AstralAddonsSettings();
    private File file;
    private YamlConfiguration config;

    // List of bosses in the config
    private List<String> bosses = new ArrayList<>();


    private AstralAddonsSettings() {
    }

    /**
     * Load the config file
     */
    public void load() {
        // Get the config file
        file = new File(AstralAddons.getInstance().getDataFolder(), "config.yml");

        // If the config does not exist, save the default config
        if (!file.exists()) {
            AstralAddons.getInstance().saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true); // Allow comments in the config

        try {
            config.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load each boss under the "bosses" section of the config
        bosses.addAll(config.getConfigurationSection("bosses").getKeys(false));
    }

    /**
     * Get the instance of the AstralAddonsSettings class
     * @return the instance of the AstralAddonsSettings class
     */
    public static AstralAddonsSettings getInstance() {
        return instance;
    }

    public void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the list of bosses in the config
     * @return the list of bosses in the config
     */
    public List<String> getBosses() {
        return bosses;
    }

    /**
     * Get the string at the given path in the config
     * @param path the path to get
     * @return the string at the given path in the config
     */
    public String getString(String path) {
        return config.getString(path);
    }

    /**
     * Check if the given path is set in the config
     * @param path the path to check
     * @return true if the given path is set in the config, false otherwise
     */
    public boolean isSet(String path) {
        return config.isSet(path);
    }

    /**
     * Set the value at the given path in the config
     * @param path the path to set
     * @param value the value to set
     */
    public void set(String path, Object value) {
        config.set(path, value);

        save();
    }
}
