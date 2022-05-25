package dev.codingbear.spigot.phantom_switcher;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.google.gson.JsonArray;
import org.bukkit.entity.*;
import java.util.UUID;

public class CommandHandler implements CommandExecutor{
    public App app;
    public CommandHandler(App app){
        this.app = app;
    }
    public void remove_list_from_json_array(String target , JsonArray array){
        for (int i=0;i<array.size();i++){
            if (array.get(i).getAsString().equals(target)){
                array.remove(i);
                return;
            }
        }
    }
    public boolean is_string_in_json_array(String target , JsonArray array){
        for (int i=0;i<array.size();i++){
            if (array.get(i).toString().equals(target)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1){
            sender.sendMessage("§cUsage §f: §7/ps <on|off>");

            return true;
        }
        if (args[0].equals("on")){
            Player executor = (Player) sender;
            UUID executor_uuid = executor.getUniqueId();
            if (!is_string_in_json_array(executor_uuid.toString(), app.ep)){
                app.ep.add(executor_uuid.toString());
            }
            sender.sendMessage("§7[§6Phantom Switcher§7]§aPhantoms were disabled to exist 10 blocks from you!");
            return true;
        }
        if (args[0].equals("off")){
            Player executor = (Player) sender;
            UUID executor_uuid = executor.getUniqueId();
            remove_list_from_json_array(executor_uuid.toString(), app.ep);
            
            sender.sendMessage("§7[§6Phantom Switcher§7]§aPhantoms were enabled to exist 10 blocks from you!");
            return true;
        }
        sender.sendMessage("§cUsage §f: §7/ps <on|off>");
        return true;
    }
}
