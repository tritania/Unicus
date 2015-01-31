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

package org.tritania.unicus;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import org.bukkit.entity.Player;

import org.tritania.unicus.Unicus;
import org.tritania.unicus.utils.Log;

public class UWorld
{
    public Unicus un;
    private long week = 604800000L;

    public UWorld(Unicus un)
    {
        this.un = un;
    }
    
    public void addWorld()
    {
        try
        {
            Bukkit.createWorld(new WorldCreator("Resource").environment(World.Environment.NORMAL));
        }
        catch(Exception e)
        {
            System.out.print("Something went wrong!");
        }
        Date date = new Date();
        un.data.put("Resource", date.getTime());
    }
    
    public void addWorld(String name)
    {
        Date date = new Date();
        un.data.put(name, date.getTime());
    }
    
    public void unloadWorld(String base) 
    {
        World world = Bukkit.getWorld("Resource");
        
        Collection<? extends Player> players = world.getPlayers(); 
        
        for (Iterator iterator = players.iterator(); iterator.hasNext();) 
        {
			Player player = (Player) iterator.next();
            Bukkit.getServer().dispatchCommand(player, "spawn");
        }
        
        if(!world.equals(null)) 
        {
            Bukkit.getServer().unloadWorld(world, true);
            File file = new File(base + "/Resource");
            deleteWorld(file);
        }
    }
    
    public boolean deleteWorld(File path) 
    {
      if(path.exists()) 
      {
          File files[] = path.listFiles();
          for(int i=0; i<files.length; i++) 
          {
              if(files[i].isDirectory()) 
              {
                  deleteWorld(files[i]);
              }
              else 
              {
                  files[i].delete();
              }
          }
      }
      path.delete();
      addWorld();
      return true;
    }
    
    public boolean wipeWorld()
    {
        Date date = new Date();
        if ((un.data.get("Resource") != null) && (un.data.get("Resource") - date.getTime()) >= week)
        {
            return true;
        }
        return false;
    }
}

