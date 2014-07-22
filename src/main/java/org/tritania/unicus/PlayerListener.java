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

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
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
       
	}
    
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerQuit(PlayerQuitEvent event)
	{
       
	}
    
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void playerCommand(PlayerCommandPreprocessEvent event) 
    {
        String raw = event.getMessage();
        String[] args = raw.split("\\s+");
        
        Player player = event.getPlayer();
        
        if (args[0].equals("/home") && args.length == 1 && un.purges.containsKey(player.getName())) {
            if (un.purges.get(player.getName()).equals("1")) {
                Message.info(player, "Please pick your new main home with /pick [homename]");
                Message.info(player, "The others will be locked as your rank has fallen");
                Message.info(player, "To unlock them again simply donate");
            } else if (args.length == 2 && args[1].equals(un.purges.get(player.getName()))) {
            } else {
                event.setCancelled(true);
                Message.info(player, "That home is locked!");
            }
        }
    }
}
