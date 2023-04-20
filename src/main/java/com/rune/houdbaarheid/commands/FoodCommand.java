package com.rune.houdbaarheid.commands;

import com.rune.houdbaarheid.utils.Files;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FoodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration messages = Files.MESSAGES.getFileConfiguration();

        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("usage")));
            return true;
        }

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("players-only")));
            return true;
        }

        Player player = (Player) sender;
        if(!player.hasPermission("houdbaar.command.use")){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("noperms")));
            return true;
        }

        //todo: check of item is food.

        int dagen = Integer.parseInt(args[0]) * 86400000;
        Date date = new Date();
        date.setTime(System.currentTimeMillis() + dagen);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        List<String> lore = Arrays.asList("§7Officieel Minetopia Voedsel", "§8Houdbaar tot: §7" + formatter.format(date));
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        player.getInventory().setItemInMainHand(item);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("added-date").replace("%days%", String.valueOf((dagen / 86400000))).replace("%datum%", formatter.format(date))));

        return true;
    }
}
