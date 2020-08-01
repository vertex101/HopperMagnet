package xyz.vertex101.hoppermagnet.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Hopper;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.vertex101.hoppermagnet.HopperMagnet;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static xyz.vertex101.hoppermagnet.utils.TextUtils.msgFormat;

public class ItemHopMagnet{
    public static final ItemStack magHopper = new ItemStack(Material.HOPPER,1);
    private final List<Hopper> magnetHop = new CopyOnWriteArrayList<Hopper>();

    public static NamespacedKey key = new NamespacedKey(HopperMagnet.getPlugin(), "magnet_hopper");

    public static void magnetHopper() {
        ItemMeta magHop = magHopper.getItemMeta();
        magHop.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        magHop.setDisplayName(msgFormat("&4&l&nMagnet Hopper"));
        magHopper.setItemMeta(magHop);
        magHopper.getMaxStackSize();

        ShapedRecipe recipeMagnet = new ShapedRecipe(key, magHopper.clone());
        recipeMagnet.shape("I I", "IEI", " I ");
        recipeMagnet.setIngredient('I', Material.IRON_BLOCK);
        recipeMagnet.setIngredient('E', Material.ENDER_CHEST);
        Bukkit.addRecipe(recipeMagnet);
    }


    /*public void run() {
        for (World world : HopperMagnet.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (!(entity instanceof Item))
                    continue;
                Item item = (Item)entity;
                ItemStack stack = item.getItemStack();
                Location location = item.getLocation();
                if (stack.getAmount() <= 0 || item.isDead() || item.getPickupDelay() > item.getTicksLived())
                    continue;
                Hopper closestHopper = null;
                double distanceSmall = HopperMagnet.maxRange;
                for (Player player : AscensionMagnet.this.magnetPlayers) {
                    if (player != null)
                        if (player.getWorld().getName().equals(world.getName())) {
                            double playerDistance = magHopper.getLocation().distance(location);
                            if (playerDistance < distanceSmall) {
                                distanceSmall = playerDistance;
                                magHopper = player;
                            }
                        }
                }
                if (magHopper == null)
                    continue;
                item.setVelocity(magHopper.getLocation().toVector().subtract(item.getLocation().toVector()).normalize());
            }
        }
    }*/
}
