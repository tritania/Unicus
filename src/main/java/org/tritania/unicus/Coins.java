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

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import org.tritania.unicus.Unicus;
import org.tritania.unicus.UPlayer;
import org.tritania.unicus.utils.Log;

public class Coins
{
    public Unicus un;

    public Coins (Unicus un)
    {
        this.un = un;
    }
    
    public void balance (Player player)
    {
        ScoreboardManager mang = Bukkit.getScoreboardManager();
        Scoreboard board = mang.getNewScoreboard();

        Objective objective = board.registerNewObjective("Stats", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.BLUE +  "Coins!");

        Score balance = objective.getScore(Bukkit.getOfflinePlayer("Balance: "));
        
        balance.setScore(un.data.get(player.getName()).getCoins());
        
        player.setScoreboard(board);
        balanceTimer(player);
    }
    
    public void removeBoard(Player player)
    {
        ScoreboardManager manger = Bukkit.getScoreboardManager();
        Scoreboard board = manger.getNewScoreboard();

        player.setScoreboard(board);
    }
    
    public void balanceTimer(final Player player)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                removeBoard(player);
            }
        }.runTaskLater(un, 1200); 
    }
    
    public boolean transfer (Player to, Player from, int amount)
    {
        if (un.data.containsKey(to.getName())) 
        {
            UPlayer uto = un.data.get(to.getName());
            UPlayer ufrom = un.data.get(from.getName());
            
            uto.removeCoins(amount);
            ufrom.addCoins(amount);
            
            return true;
        } 
        else 
        {
            return false;
        }
    }
    
    /* currently not needed as plugin is overhauled
    public String buy (Player player, String item)  //returning a string allows for variable passing in the future
    {
        if (items.containsKey(item) && coinCheck(player, items.get(item).getPrice())) {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), items.get(item).getCommand());
            remove(player, items.get(item).getPrice());
            return "Your purchase was a success!";
        }
        return "Something went wrong with your purchase!"; 
    }
    
    public void storeList(Player player)
    {
        ScoreboardManager mang = Bukkit.getScoreboardManager();
        Scoreboard board = mang.getNewScoreboard();

        Objective objective = board.registerNewObjective("Stats", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.BLUE +  "Store!");
        
        Iterator<Map.Entry<String, Merchandise>> iterator = items.entrySet().iterator();
        while(iterator.hasNext())
        {
            Map.Entry<String, Merchandise> entry = iterator.next();
            Score temp = objective.getScore(Bukkit.getOfflinePlayer(entry.getKey() + ": "));
            temp.setScore(entry.getValue().getPrice());
        }

        Score balance = objective.getScore(Bukkit.getOfflinePlayer("Balance: "));
        
        balanceTimer(player);
    }
    * */
}
