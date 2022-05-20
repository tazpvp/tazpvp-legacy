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
        Player p = e.getPlayer();

        if (Tazpvp.chatMuted){
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "Chat is currently muted.");
            return;
        }
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

        if (cooldown.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "Please don't spam the chat!");
        }

        if (previousMessages.containsKey(p)){
            if (msg.equalsIgnoreCase(previousMessages.get(p))) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You cannot repeat the same message!");
            }
        }

        //yes

        if (ServerStore.buying == true){
            String msg = e.getMessage();
            if (msg.contains("buy")){
                ServerStore.type = 1;
            } else if (msg.contains("gift")){
                ServerStore.type = 2;
                p.sendMessage("who to gift?");
                public boolean gifting = true;
            } else {
                p.sendMessage("That is not a choice.");
            }
            e.setCancelled(true);
        }
        if (gifting == true && buying == true){
            String msg = e.getMessage();
            ServerStore.recipient = msg;
            e.setCancelled(true);
        }

        if (!p.hasPermission("tazpvp.staff.level")){
            if(Tazpvp.permissions.getPrimaryGroup(p).equals("default")) {
                String str = (Tazpvp.statsManager.getRebirth(p) > 0) ? ChatColor.GRAY+ "[" + ChatColor.GOLD + Tazpvp.statsManager.getLevel(p) + ChatColor.GRAY + "] " + p.getDisplayName() + ": " + "%2$s" : ChatColor.GRAY+ "[" + Tazpvp.statsManager.getLevel(p) + "] " + p.getDisplayName() + ": " + "%2$s";
                e.setFormat(str);
            } else {
                String str = (Tazpvp.statsManager.getRebirth(p) > 0) ? ChatColor.GRAY+ "[" + ChatColor.GOLD + Tazpvp.statsManager.getLevel(p) + ChatColor.GRAY + "] " + ChatColor.translateAlternateColorCodes('&',Tazpvp.chat.getGroupPrefix((String) null, Tazpvp.permissions.getPrimaryGroup(p))+ p.getDisplayName()) + " " + ChatColor.WHITE + "%2$s" : ChatColor.GRAY+ "[" + Tazpvp.statsManager.getLevel(p) + "] " + ChatColor.translateAlternateColorCodes('&',Tazpvp.chat.getGroupPrefix((String) null, Tazpvp.permissions.getPrimaryGroup(p))+ p.getDisplayName()) + " " + ChatColor.WHITE + "%2$s" ;
                e.setFormat(str);
            }
//            if(p.hasPermission("staff.staffchat") && Tazpvp.staffManager.staffChatToggled(p)){
//                TazPvP.staffManager.sendStaffChat(p, e.getMessage());
//                e.setCancelled(true);
//                return;
//            }
        } else {
            if(Tazpvp.permissions.getPrimaryGroup(p).equals("default")) {
                e.setFormat(p.getDisplayName() + ": " + "%2$s");
            } else {
                e.setFormat(ChatColor.translateAlternateColorCodes('&',Tazpvp.chat.getGroupPrefix((String) null, Tazpvp.permissions.getPrimaryGroup(p))+ p.getDisplayName()) + " " + ChatColor.WHITE + "%2$s");
            }
//            if(p.hasPermission("staff.staffchat") && TazPvP.staffManager.staffChatToggled(p)){
//                TazPvP.staffManager.sendStaffChat(p, e.getMessage());
//                e.setCancelled(true);
//                return;
//            }
        }

        previousMessages.put(p, msg);
        cooldown.add(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldown.remove(p);
            }
        }.runTaskLater(Tazpvp.getInstance(), 10);

    }
}
