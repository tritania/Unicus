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
import java.util.Random;

import org.bukkit.permissions.PermissibleBase;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.Location;

import org.tritania.unicus.Unicus;
import org.tritania.unicus.utils.Message;
import org.tritania.unicus.utils.Log;
/*End Imports*/

public class CResource implements CommandExecutor
{
    public Unicus un;
    private int highest;

    public CResource(Unicus un)
    {
        this.un = un;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;
        if (player.getWorld().getName().equals("Resource"))
        {
            Message.info(sender, "You're already in the resource world");
            return true;
        }
        else if (un.land.wipeWorld())
        {
            Message.info(sender, "Please wait a few moments, the resource world is resetting");
            un.land.unloadWorld(un.datalocal);
            return true;
        }
        else if (args.length > 0 && args[0].equals("spawn"))
        {
            Random rand = new Random();
            World world = Bukkit.getWorld("Resource");
            Location location = new Location(world, 0, 64.0, 0);
            if (world.getHighestBlockYAt(location) > 5)
            {
                highest = 64;
            }
            else
            {
                highest = world.getHighestBlockYAt(location);
            }   
            Location prime = new Location(world, 0, highest, 0);
            
            player.teleport(prime);
        }
        
        else
        {
            Random rand = new Random();
            World world = Bukkit.getWorld("Resource");
            double x = 40000 * rand.nextDouble();
            double z = 40000 * rand.nextDouble();
            Location location = new Location(world, x, 64.0, z);
            if (world.getHighestBlockYAt(location) > 5)
            {
                highest = 64;
            }
            else
            {
                highest = world.getHighestBlockYAt(location);
            } 
            Location prime = new Location(world, x, highest, z);
            
            player.teleport(prime);
        }
        
        Message.info(sender, un.land.timeLeft());
        
        return true;
    }
}
