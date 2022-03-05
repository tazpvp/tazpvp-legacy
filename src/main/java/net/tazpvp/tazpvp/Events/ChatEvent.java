package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatEvent implements Listener {

    final HashMap<Player, String> previousMessages = new HashMap<>();
    final ArrayList<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (e.getPlayer().isOp()) return;
        List<String> badWord = new ArrayList<>();
        badWord.add("fuck");
        badWord.add("shit");
        badWord.add("bitch");
        badWord.add("shit");
        badWord.add("dick");
        badWord.add("dildo");
        badWord.add("pussy");
        badWord.add("porn");
        badWord.add("whore");
        badWord.add("fag");
        badWord.add("cock");
        badWord.add("retar");
        badWord.add("cunt");
        badWord.add("penis");
        badWord.add("bitc");
        badWord.add("nigg");
        badWord.add("slut");
        badWord.add("fuk");
        badWord.add("fuc");
        String msg = e.getMessage();
        for (int i = 0; i < badWord.size(); i++) {
            if (msg.contains(badWord.get(i))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.GOLD + "You cannot say: " + ChatColor.RED + badWord.get(i));
            }
        }
    }

    @EventHandler
    public void onChatSend(AsyncPlayerChatEvent e) {
        if (e.getPlayer().isOp()) return;
        Player p = e.getPlayer();
        String message = e.getMessage();
        if (cooldown.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "Please don't spam the chat!");
        }

        if (previousMessages.containsKey(p)){
            if (message.equalsIgnoreCase(previousMessages.get(p))) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You cannot repeat the same message!");
            }
        }
        previousMessages.put(p, message);
        cooldown.add(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldown.remove(p);
            }
        }.runTaskLater(Tazpvp.getInstance(), 10);
    }
}
