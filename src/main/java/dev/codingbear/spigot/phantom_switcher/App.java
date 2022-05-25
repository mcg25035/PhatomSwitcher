package dev.codingbear.spigot.phantom_switcher;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;
import java.util.UUID;

import org.bukkit.entity.*;

public class App extends JavaPlugin
{
    
    JsonArray ep;
    JsonArray dp;
    public void onEnable(){
        JsonObject data;
        FileUtil fileUtil = new FileUtil();
        Bukkit.getPluginCommand("ps").setExecutor(new CommandHandler(this));
        if (!fileUtil.check_path_exist("plugins/phantom_switcher")){
            fileUtil.new_folder("plugins/phantom_switcher");
        }
        if (!fileUtil.check_path_exist("plugins/phantom_switcher/data.json")){
            JsonObject need_write_in = new JsonObject();
            need_write_in.add("ep", new JsonArray());
            fileUtil.write_file("plugins/phantom_switcher/data.json", need_write_in.toString());
        }
        fileUtil.read_file("plugins/phantom_switcher/data.json");
        data = new JsonParser().parse(fileUtil.read_file("plugins/phantom_switcher/data.json")).getAsJsonObject();
        ep = data.get("ep").getAsJsonArray();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            @Override
            public void run(){
                for (int i=0;i<ep.size();i++){
                    Player it = Bukkit.getPlayer(UUID.fromString(ep.get(i).getAsString()));
                    if (it == null){
                        continue;
                    }
                    List<Entity> entities_near_by_it = it.getNearbyEntities(10, 10, 10);
                    for (int ii=0;ii<entities_near_by_it.size();ii++){
                        Entity it_near_by = entities_near_by_it.get(ii);
                        if (it_near_by.getType() == EntityType.PHANTOM){
                            it_near_by.remove();
                        }
                    }

                    
                }
            }
        }, 0, 1);
    }
    public void onDisable(){
        FileUtil fileUtil = new FileUtil();
        JsonObject data = new JsonObject();
        data.add("ep", ep);
        fileUtil.write_file("plugins/phantom_switcher/data.json", data.toString());
    }
}
