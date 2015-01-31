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
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;

import org.tritania.unicus.Unicus;
import org.tritania.unicus.utils.Message;
import org.tritania.unicus.utils.Log;
/*End Imports*/

public class CStart implements CommandExecutor
{
    public Unicus un;

    public CStart(Unicus un)
    {
        this.un = un;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;
        if (!player.hasPermission("unicus.admin"))
        {
            Message.info(sender, "You don't have permission for that");
            return false;
        }
        
        if (args.length < 1) 
        {
            Message.info(sender, command.getUsage());
            return true;
        }
        else if (args[0].equals("start"))
        {
            Message.info(sender, "Adding World");
            un.land.addWorld();
            Message.info(sender, "Adding World Complete");
        }
        else
        {
             un.land.addWorld(args[0]);
             Message.info(sender, "Adding World: " + args[0]);
        }
        
    
        
        return true;
    }
}

