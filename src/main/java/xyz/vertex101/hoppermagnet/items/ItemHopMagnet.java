package xyz.vertex101.hoppermagnet.items;

import org.bukkit.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.vertex101.hoppermagnet.HopperMagnet;

import static xyz.vertex101.hoppermagnet.utils.TextUtils.msgFormat;

public class ItemHopMagnet {
    public static final ItemStack magHopper = new ItemStack(Material.HOPPER,1);

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

}
