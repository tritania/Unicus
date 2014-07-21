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
import java.io.FileWriter;

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

public class CPurge implements CommandExecutor
{
    public Unicus un;
    private String homes; 
    private ArrayList<String> data;
    private ArrayList<String> postdata;

    public CPurge(Unicus un)
    {
        this.un = un;
        this.homes = un.datalocal.replace("Unicus", "Essentials/userdata");
        this.data = new ArrayList<String>();
        this.postdata = new ArrayList<String>();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        System.out.println(homes);
        Player player = (Player) sender; //need to check for console
        if (args.length < 1) {
            Message.info(sender, command.getUsage());
            return true;
        } else if (player.hasPermission("unicus.admin")) {
            Player toPurge = Bukkit.getPlayer(args[0]);
            homes = homes + "/" + args[0] + ".yml";
            
            BufferedReader br = null;
 
            try 
            {
                String line;
     
                br = new BufferedReader(new FileReader(homes));
     
                while ((line = br.readLine()) != null) {
                    data.add(line); 
                    System.out.println(line);
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
        }
        
        int i = 1;
        boolean end = false;
        for (String str: data) {
            if (str.equals("afk: false") || str.equals("afk: true")) {
                end = true;
            }
            if (end) {
                postdata.add(str + System.getProperty("line.separator"));
            } else if (i >= 21 && !str.equals("afk: false") && !str.equals("afk: true")) {
                postdata.add("#" + str + System.getProperty("line.separator"));
            }  else {
                postdata.add(str + System.getProperty("line.separator"));
            }
            i++;
        }
        
        try 
            {
            FileWriter writer = new FileWriter(homes); 
            for (String str: postdata) {
                writer.write(str);
            }
            writer.close();
        }
        catch (Exception ex)
        {
            Log.severe("Error: %s", ex);
        }
        
        return true;
    }
}
