package com.rune.houdbaarheid.events;

import com.rune.houdbaarheid.utils.Files;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EetEvent implements Listener {

    FileConfiguration messages = Files.MESSAGES.getFileConfiguration();

    @EventHandler
    public void onRightCLick(PlayerInteractEvent event) throws ParseException {
        if(event.getItem().getItemMeta().getLore().get(0).equalsIgnoreCase("§7Officieel Minetopia Voedsel")){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = formatter.parse(event.getItem().getItemMeta().getLore().get(1).replace("§8Houdbaar tot: §7", ""));
            Date now = new Date();
            now.setTime(System.currentTimeMillis());
            if(date.before(now)){
                //todo: delete or rotten flesh?
                event.getPlayer().getInventory().setItemInMainHand(creeateRottenFlesh(event.getItem().getAmount()));
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("overdatum")));
            }
        }
    }

    private ItemStack creeateRottenFlesh(int amount){
        ItemStack item = new ItemStack(Material.ROTTEN_FLESH, amount);
        item.getItemMeta().setDisplayName("Bedorven Vlees");
        return item;
    }
}
