package com.rune.houdbaarheid;

import com.rune.houdbaarheid.commands.FoodCommand;
import com.rune.houdbaarheid.events.EetEvent;
import com.rune.houdbaarheid.utils.Files;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Optional;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("houdbaar").setExecutor(new FoodCommand());
        getServer().getPluginManager().registerEvents(new EetEvent(), this);
        FileConfiguration messages = Files.MESSAGES.getFileConfiguration();
        messages.addDefault("overdatum", "&cJe eten is over de datum heen.");
        messages.addDefault("players-only", "&cEnkel spelers kunnen dit doen.");
        messages.addDefault("usage", "&2Gebruik: &a/houdbaar (aantal dagen)");
        messages.addDefault("noperms", "&cNo Permissions");
        messages.addDefault("added-date", "&aHet voedsel is nu &2 %days% &adagen houdbaar. (&2%datum%&a)");
        messages.options().copyDefaults(true);
        try {
            messages.save(Files.MESSAGES.getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Files.MESSAGES.save();
        Files.saveAll();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
