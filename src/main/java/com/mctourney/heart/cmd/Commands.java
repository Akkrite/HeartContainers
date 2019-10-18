package com.mctourney.heart.cmd;

import com.mctourney.heart.HeartUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.text.BadLocationException;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player)sender;

        if(command.getName().equalsIgnoreCase("maxhealth")) {
            if(!player.hasPermission(" hc.max")) {
                player.sendMessage( ChatColor.GREEN + "Error! You do not have permission to this command." );
                return true;
            }
            if(args.length < 2 || args.length > 2) {
                player.sendMessage(ChatColor.GRAY + "/" + label + " <playerName> <health>");
                return true;
            }
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null) {
                    player.sendMessage(ChatColor.RED + "That player is not online!");
                    return true;
                }
                Double health = Double.valueOf(args[1]);
                target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
                player.sendMessage("Player's max health has been changed.");
        }

        if(command.getName().equalsIgnoreCase("spawnheart")) {
            if(!player.hasPermission(" hc.spawn")) {
                player.sendMessage( ChatColor.GREEN + "Error! You do not have permission to this command." );
                return true;
            }
            if(args.length < 3 || args.length > 3) {
                player.sendMessage(ChatColor.GRAY +"/" + label + " <x> <y> <z>");
                return true;
            }
            Double x = Double.valueOf(args[0]);
            Double y = Double.valueOf(args[1]);
            Double z = Double.valueOf(args[2]);
            player.sendMessage(ChatColor.GRAY + "Heart has been spawned at " + x + ", " + y + ", " + z);
            Location addLocation = new Location(player.getWorld(), x ,y ,z);
            HeartUtil.create(addLocation);
        }
        return false;
    }
}
