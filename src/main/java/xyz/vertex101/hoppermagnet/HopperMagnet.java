package xyz.vertex101.hoppermagnet;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.vertex101.hoppermagnet.items.ItemHopMagnet;

public final class HopperMagnet extends JavaPlugin {
    public static double maxRange = 8.0D;

    public static HopperMagnet getPlugin() {
        return JavaPlugin.getPlugin(HopperMagnet.class);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.maxRange = getConfig().getInt("max-range");

        ItemHopMagnet.magnetHopper();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
