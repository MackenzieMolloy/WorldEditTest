package net.mackenziemolloy.WorldEditTest;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.function.pattern.RandomPattern;
import com.sk89q.worldedit.internal.annotation.Selection;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private final WorldEditTest worldEditTest;

    public Commands(final WorldEditTest worldEditTest) {
        this.worldEditTest = worldEditTest;

        worldEditTest.getCommand("wet").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(commandSender instanceof Player) {

            Player sender = (Player) commandSender;

            Region worldEdit = null;
            try {
                worldEdit = worldEditTest.getWorldEdit().getSession(sender).getSelection(BukkitAdapter.adapt(sender.getWorld()));
            } catch (IncompleteRegionException e) {
                e.printStackTrace();
            }

            try {
                RandomPattern pat = new RandomPattern();
                BlockState iron = BukkitAdapter.adapt(Material.IRON_BLOCK.createBlockData());
                pat.add(iron, 1);

                //Selection selection = (Selection) worldEdit.getSession(sender).getSelection((World) sender.getWorld());

                EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitAdapter.adapt(sender.getWorld()), -1);

                CuboidRegion cuboidRegion = new CuboidRegion(worldEdit.getMaximumPoint(), worldEdit.getMinimumPoint());

                editSession.setBlocks(cuboidRegion, pat);
                editSession.flushSession();
                editSession.close();
                //editSession.commit();
                sender.sendMessage("Done.");

            } catch (MaxChangedBlocksException e) {
                e.printStackTrace();
            }

        }

        return false;

    }
}
