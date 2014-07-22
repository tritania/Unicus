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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

public class CPurgePick implements CommandExecutor
{
    public Unicus un;
    private String homes;

    public CPurgePick(Unicus un)
    {
        this.un = un;
        this.homes = un.datalocal.replace("Unicus", "Essentials/userdata"); //will be used to check for home
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender; //need to check for console
        String name = player.getName();
        if (args.length < 1) {
            Message.info(sender, command.getUsage());
            return true;
        } else if (un.purges.containsKey(name)) {
            if (un.purges.get(name).equals("1")) {
                //check for home args[0]
            } else {
                Message.info(sender, "You've already picked your home.");
            }
        } else {
            Message.info(sender, "You don't have access to this command.");
        }
        return true;
    }
}
