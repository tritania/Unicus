/*
 * Copyright 2014-2015 Erik Wilson <erikwilson@magnorum.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.tritania.unicus.command;

/*Start Imports*/
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.lang.Long;
import java.util.Random;

import org.bukkit.permissions.PermissibleBase;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.World;
import org.bukkit.Location;

import static org.bukkit.World.Environment;

import org.tritania.unicus.Unicus;
import org.tritania.unicus.utils.Message;
import org.tritania.unicus.utils.Log;
/*End Imports*/

public class CNether implements CommandExecutor
{
    public Unicus un;
    public String nethername;

    public CNether(Unicus un)
    {
        this.un = un;
        
        Collection<? extends World> worlds = Bukkit.getWorlds(); 
        
        for (Iterator iterator = worlds.iterator(); iterator.hasNext();) 
        {
			World world = (World) iterator.next();
            if (world.getEnvironment() == Environment.NETHER)
            {
                nethername = world.getName();
            }
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;
        if (player.getWorld().getName().equals(nethername))
        {
            Message.info(sender, "You're already in the nether");
            return true;
        }
        else if (args.length > 0 && args[0].equals("spawn"))
        {
            Random rand = new Random();
            World worlde = Bukkit.getWorld(nethername);
            Location location = new Location(worlde, 0, 64.0, 0);

            
            player.teleport(location);
        }
        
        else
        {
            Random rand = new Random();
            boolean good = false;
            World worlde = Bukkit.getWorld(nethername);
            Location location = null;
            
            while (good == false)
            {
                double x = 40000 * rand.nextDouble();
                double z = 40000 * rand.nextDouble();
                double y = 60 * rand.nextDouble();
                location = new Location(worlde, x, y, z);
                Location location1 = new Location(worlde, x, y+1, z);
                Location location2 = new Location(worlde, x, y+2, z);
                
                Block block = worlde.getBlockAt(location);
                Block block1 = worlde.getBlockAt(location1);
                Block block2 = worlde.getBlockAt(location2);
                
                if (block.getType() == Material.NETHERRACK && block.getType() != Material.AIR && block1.getType() == Material.AIR && block2.getType() == Material.AIR)
                {
                    good = true;
                }
                else
                {
                    good = false;
                }
            }
            
            player.teleport(location);
        }
        
        Message.info(sender, un.land.timeLeft());
        
        return true;
    }
}
