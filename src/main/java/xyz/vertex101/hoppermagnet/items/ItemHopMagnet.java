package xyz.vertex101.hoppermagnet.items;

import org.bukkit.*;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.vertex101.hoppermagnet.HopperMagnet;

import static xyz.vertex101.hoppermagnet.utils.TextUtils.msgFormat;

public class ItemHopMagnet implements Listener {
    public static final ItemStack magHopper = new ItemStack(Material.HOPPER,1);

    public static NamespacedKey key = new NamespacedKey(HopperMagnet.getPlugin(), "magnet_hopper");

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(e.getBlock().getType() == Material.HOPPER) {
            Hopper hop = (Hopper) e.getBlock().getState();
            if (hop.getCustomName() != null) {
                HopperMagnet.getPlugin().getLogger().info("Adding Block: " + e.getBlock());
                HopperMagnet.getPlugin().magnetBlocks.add(e.getBlock());
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getBlock().getType() == Material.HOPPER) {
            HopperMagnet.getPlugin().getLogger().info("Removing Block: " + e.getBlock());
            HopperMagnet.getPlugin().magnetBlocks.remove(e.getBlock());
        }
    }

    public static void magnetHopper() {
        ItemMeta magHop = magHopper.getItemMeta();
        magHop.setDisplayName(msgFormat("&4&l&nMagnet Hopper"));
        magHopper.setItemMeta(magHop);
        magHopper.getMaxStackSize();

        ShapedRecipe recipeMagnet = new ShapedRecipe(key, magHopper.clone());
        recipeMagnet.shape("I I", "IEI", " I ");
        recipeMagnet.setIngredient('I', Material.IRON_BLOCK);
        recipeMagnet.setIngredient('E', Material.ENDER_CHEST);
        Bukkit.addRecipe(recipeMagnet);
    }

}
