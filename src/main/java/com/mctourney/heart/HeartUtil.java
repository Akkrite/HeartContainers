package com.mctourney.heart;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class HeartUtil {

    public static List<Location> location = new ArrayList<Location>();

    public static void create(Location loc) {
        location.add(loc);
    }

    public static void remove(Location loc) {
        if(!location.contains(loc))return;
        location.remove(loc);
    }

    public static Material getConsumable() {
        Material check = Material.matchMaterial(HeartContainer.getInstance().getConfig().getString("eatable-item"));
        return check;
    }
}
