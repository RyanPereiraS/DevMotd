package dev.gamer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DevMotd extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage("§5§l[DevMotd]" + " §8§l* "+ "§6Plugin Iniciado!");
        getConfig().options().copyDefaults(true);
        saveConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage("§5§l[DevMotd]" + " §8§l* "+ "§6Plugin finalizado!");

    }
    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        String motd = getConfig().getString("motd.ingame");
        motd = motd.replaceAll("&", "§");
        p.sendMessage(motd);

    }
    @EventHandler
    public void OnPlayerServerPing(ServerListPingEvent e) {
        String motd = getConfig().getString("motd.serverlist");
        motd = motd.replaceAll("&", "§");
        e.setMotd(motd);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("motd")){
            if (!sender.hasPermission("dev.motd")) {
                sender.sendMessage("§5§lVoce não tem permissão!");
                return true;
            }
            String motd = getConfig().getString("motd.ingame");
            motd = motd.replaceAll("&", "§");
            String motdserverlist = getConfig().getString("motd.serverlist");
            motdserverlist = motdserverlist.replaceAll("&", "§");
            sender.sendMessage("§5§lMotd InGame: " + motd);
            sender.sendMessage("§6§lServer List Motd: " + motdserverlist);
            return true;

        }
        if (cmd.getName().equalsIgnoreCase("setmotd")){
            if (!sender.hasPermission("dev.setmotd")) {
                sender.sendMessage("§5§lVoce não tem permissão!");
                return true;
            }
            if (args.length == 0){
                sender.sendMessage("§5§lEspecifique a Motd!");
                return true;
            }
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                str.append(args[i] + " ");
            }
            String motd = str.toString();
            getConfig().set("motd.ingame", motd);
            saveConfig();
            String newmotd = getConfig().getString("motd.ingame");
            motd = motd.replaceAll("&", "§");
            sender.sendMessage("§6§lNova Motd Definida: " + newmotd);
            return true;

        }
        if (cmd.getName().equalsIgnoreCase("setserverlist")) {
            if (!sender.hasPermission("dev.setmotd")) {
                sender.sendMessage("§5§lVoce não tem permissão!");
                return true;
            }
            if (args.length == 0){
                sender.sendMessage("§5§lEspecifique a Motd!");
                return true;
            }
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                str.append(args[i] + " ");
            }
            String motd = str.toString();
            getConfig().set("motd.serverlist", motd);
            saveConfig();
            String newmotd = getConfig().getString("motd.serverlist");
            motd = motd.replaceAll("&", "§");
            sender.sendMessage("§6§lNova Motd Definida: " + newmotd);
            return true;
        }

        return true;
    }
}
