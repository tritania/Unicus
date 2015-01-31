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
import org.bukkit.World;

import org.tritania.unicus.utils.Log;
import org.tritania.unicus.utils.Message;
import org.tritania.unicus.Unicus;

public class PlayerListener implements Listener
{
    private Unicus un;

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
        World world = player.getWorld();
        
        if (world.getName().equals("Resource"))
        {
            if (un.land.wipeWorld())
            {
                Message.info(player, "Please wait a few moments, the resource world is resetting");
                un.land.unloadWorld(un.datalocal);
            }
        }
    }
}
