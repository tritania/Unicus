/*
 * Copyright 2012-2014 Erik Wilson <erikwilson@magnorum.com>
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

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import org.tritania.unicus.utils.Log;
import org.tritania.unicus.utils.Message;
import org.tritania.unicus.Unicus;
import org.tritania.unicus.UPlayer;

public class PlayerListener implements Listener
{
	private Unicus un;
    private int BLOCKS = 576;
    private HashMap<String, Integer> gains = new HashMap<String, Integer>(); //tracks player gains

	public PlayerListener(Unicus un)
	{
		this.un = un;
	}
	
	public void register()
	{
		PluginManager manager;
		
		manager = un.getServer().getPluginManager();
		manager.registerEvents(this, un);
	} 
    
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        if(!un.data.containsKey(player.getName())) {
            un.data.put(player.getName(), new UPlayer());
        }
        gains.put(player.getName(), 0);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerLeave(PlayerQuitEvent event)
    {
       Player player = event.getPlayer();
       gains.remove(player.getName());
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
    public void playerBlockBreak(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        String name = player.getName();
        
        int amount = gains.get(name);
        gains.put(name, amount + 1);
        
        if (amount + 1 == BLOCKS) {
            un.data.get(name).addCoins(1);
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
    public void playerBlockPlace(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        String name = player.getName();
        
        int amount = gains.get(name);
        gains.put(name, amount + 1);
        
        if (amount + 1 == BLOCKS) {
            un.data.get(name).addCoins(1);
        }
    }
    
     @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void playerCommand(PlayerCommandPreprocessEvent event) 
    {
        String raw = event.getMessage();
        String[] args = raw.split("\\s+");
        
        Player player = event.getPlayer();
        
        if (args[0].equals("/setwarp") && un.data.containsKey(player.getName())) {
            
        }
    }
}
