package xyz.vertex101.hoppermagnet;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.vertex101.hoppermagnet.items.ItemHopMagnet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class HopperMagnet extends JavaPlugin {
    public static double maxRange = 8.0D;

    public final List<Block> magnetBlocks = new ArrayList<Block>();

    public static HopperMagnet getPlugin() {
        return JavaPlugin.getPlugin(HopperMagnet.class);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.maxRange = getConfig().getInt("max-range");
        ItemHopMagnet.magnetHopper();

        getServer().getPluginManager().registerEvents(new ItemHopMagnet(), this);

        ItemSearch itemSearch = new ItemSearch();
        getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)this, itemSearch, 5L, 5L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public class ItemSearch implements Runnable {
        private ItemSearch() {}

        public void run() {
            for (World world : HopperMagnet.getPlugin().getServer().getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (!(entity instanceof Item))
                        continue;
                    Item item = (Item)entity;
                    ItemStack stack = item.getItemStack();
                    Location location = item.getLocation();
                    if (stack.getAmount() <= 0 || item.isDead() || item.getPickupDelay() > item.getTicksLived())
                        continue;
                    Block closestBlock = null;
                    double distanceSmall = HopperMagnet.maxRange;
                    for (Block block : magnetBlocks) {
                        if (block != null)
                            if (block.getWorld().getName().equals(world.getName())) {
                                double playerDistance = block.getLocation().distance(location);
                                if (playerDistance < distanceSmall) {
                                    distanceSmall = playerDistance;
                                    closestBlock = block;
                                }
                            }
                    }
                    if (closestBlock == null)
                        continue;
                    item.setVelocity(closestBlock.getLocation().toVector().subtract(item.getLocation().toVector()).normalize());
                }
            }
        }
    }

}
