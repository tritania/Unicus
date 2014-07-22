/*
 * Copyright 2014 Erik Wilson <erikwilson@magnorum.com>
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

public class CPurgeList implements CommandExecutor
{
    public Unicus un;

    public CPurgeList(Unicus un)
    {
        this.un = un;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender.hasPermission("unicus.admin")) {
            String names = "Purged players are: ";
            for (Object key : un.purges.keySet())
            {
                names += key.toString() + " ";
            }
        
            Message.info(sender, names);
        } else {
            Message.info(sender, "You don't have permisson for that!");
        }
        return true;
    }
    
}
