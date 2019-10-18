package com.mctourney.heart.events;

import com.mctourney.heart.HeartContainer;
import com.mctourney.heart.HeartUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.List;

public class HeartPickup implements Runnable {

    @Override
    public void run() {
        if (HeartUtil.location.size() < 0) {
            return;
        }
        List<Location> customHeartList = HeartUtil.location;
        for (Iterator<Location> i = customHeartList.iterator(); i.hasNext(); ) {
            Location heartPosition = i.next();
            heartPosition.getWorld().spawnParticle(Particle.HEART, heartPosition, 1);
            List<Entity> entity = (List<Entity>) heartPosition.getWorld().getNearbyEntities(heartPosition, .5,.5,.5);
            entity.stream().forEach(e -> {
                if (e instanceof Player) {
                    Player player = (Player)e;
                    double healthToAdd = HeartContainer.getInstance().getConfig().getDouble("max-heart-add");
                    double currentMaxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(currentMaxHealth + healthToAdd);
                    player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                    player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Your max health has increased!");
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                    i.remove();
                }
            });
        }
    }
}