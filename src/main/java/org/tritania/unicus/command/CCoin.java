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
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;

import org.tritania.unicus.Unicus;
import org.tritania.unicus.Coins;
import org.tritania.unicus.utils.Message;
import org.tritania.unicus.utils.Log;
/*End Imports*/

public class CCoin implements CommandExecutor
{
    public Unicus un;

    public CCoin(Unicus un)
    {
        this.un = un;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;
        
        if (args.length < 1) {
            un.coin.balance(player);
            Message.info(sender, command.getUsage());
            return true;
        }
        
        if (args[0].equals("store")) {
            
        } else if (args[0].equals("add")) {
            if (player.hasPermission("unicus.coins.admin")) {
                Player playr = Bukkit.getPlayer(args[1]);
                un.coin.add(playr, Integer.parseInt(args[2]));
            }
        } else if (args[0].equals("remove")) {
            if (player.hasPermission("unicus.coins.admin")) {
                Player playr = Bukkit.getPlayer(args[1]);
                un.coin.remove(playr, Integer.parseInt(args[2]));                
            }
        } else if (args[0].equals("transfer")) {
            if (un.coin.transfer(player, Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]))) {
                Message.info(sender, "Transfer complete");
            } else {
                Message.info(sender, "Transfer could not be made!");
            }
        } else {
            Message.info(sender, command.getUsage());
        }
         
        return true;
    }
}

