package net.tazpvp.tazpvp.Utils.Functionality;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ItemFactory {
    private static final List<XMaterial> DYES = new ArrayList<>(16);

    static {
        for (XMaterial mat : XMaterial.VALUES) {
            if (mat.name().endsWith("_DYE")) {
                DYES.add(mat);
            }
        }
    }

    public static ItemStack getRandomDye() {
        return randomStack(DYES);
    }
    private static ItemStack randomStack(List<XMaterial> mats) {
        return randomXMaterial(mats).parseItem();
    }

    private static XMaterial randomXMaterial(List<XMaterial> mats) {
        return mats.get(ThreadLocalRandom.current().nextInt(mats.size()));
    }
}
