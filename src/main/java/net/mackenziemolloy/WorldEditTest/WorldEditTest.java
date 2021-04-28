package net.mackenziemolloy.WorldEditTest;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldEditTest extends JavaPlugin {

    @Override
    public void onEnable() {

        new Commands(this);

    }

    public WorldEditPlugin getWorldEdit() {

        Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if(p instanceof WorldEditPlugin) return (WorldEditPlugin) p;
        else return null;

    }

}
