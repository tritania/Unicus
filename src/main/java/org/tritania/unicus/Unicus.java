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

import java.io.File;
import java.util.UUID;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.lang.Long;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import org.tritania.unicus.utils.Log;
import org.tritania.unicus.utils.Message;
import org.tritania.unicus.command.*;
import org.tritania.unicus.PlayerListener;
import org.tritania.unicus.Storage;
import org.tritania.unicus.UWorld;

public class Unicus extends JavaPlugin
{
    public Storage store;
    public UWorld land;
    public String datalocal;
    public HashMap<String, Long> data = new HashMap<String, Long>();
	
	public void onLoad()
	{
		saveResource("readme.txt", true); 
	}
	
	public void onEnable()
	{
		PluginManager pm;
		Plugin p;
		
		Log.init(getLogger());
		Message.init(getDescription().getName());
		
		pm = getServer().getPluginManager();
        
        datalocal = getDataFolder().getAbsolutePath();
		
        store = new Storage(this);
        land = new UWorld(this);
        data = store.loadData();
        
		pm.registerEvents(new PlayerListener(this), this);
		
		getCommand("nether").setExecutor(new CNether(this));
		getCommand("resource").setExecutor(new CResource(this));
		getCommand("unicus").setExecutor(new CStart(this));

	}
	
	public void onDisable()
	{
        store.saveData(data);
	}
}
