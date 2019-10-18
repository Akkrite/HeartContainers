package com.mctourney.heart.events;

import com.mctourney.heart.HeartUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(event.getPlayer().getInventory().getItemInMainHand() != null) {
                Player player = event.getPlayer();
                ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
                if(itemInHand.getType() == HeartUtil.getConsumable()) {

                }
            }
        }
    }
}
