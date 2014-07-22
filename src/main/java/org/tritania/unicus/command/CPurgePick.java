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
    private boolean real;

    public CPurgePick(Unicus un)
    {
        this.un = un;
        this.real = false;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender; //need to check for console
        String name = player.getName();
        this.homes = un.datalocal.replace("Unicus", "Essentials/userdata"); //will be used to check for home
        if (args.length < 1) {
            Message.info(sender, command.getUsage());
            return true;
        } else if (un.purges.containsKey(name)) {
            if (un.purges.get(name).equals("1")) {
                homes = homes + "/" + name.toLowerCase() + ".yml";
                
                BufferedReader br = null;
     
                try 
                {
                    String line;
         
                    br = new BufferedReader(new FileReader(homes));
         
                    while ((line = br.readLine()) != null) {
                        if (line.equals("  " + args[0] + ":")) {
                            real = true;
                        }
                    }
                } 
                catch (IOException e) 
                {
                    Log.severe("Error: %s", e);
                } 
                finally 
                {
                    try 
                    {
                        if (br != null)
                        {
                            br.close();
                        }
                    } 
                    catch (IOException ex) 
                    {
                        Log.severe("Error: %s", ex);
                    }
                }
                
                if (real) {
                    un.purges.put(name, args[0]);
                    Message.info(sender, "Home " + args[0] + " set as main home.");
                } else {
                    Message.info(sender, "No such home exists!");
                }
                
            } else {
                Message.info(sender, "You've already picked your home.");
            }
        } else {
            Message.info(sender, "You don't have access to this command.");
        }
        return true;
    }
}
