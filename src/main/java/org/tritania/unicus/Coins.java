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
        
        balance.setScore(un.coins.get(player.getName()));
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
    
    public void add (Player player, int amount) 
    {
        int current = un.coins.get(player.getName());
        un.coins.put(player.getName(), (current + amount));
    }
    
    public void remove (Player player, int amount) 
    {
        int current = un.coins.get(player.getName());
        if (current <= amount) {
            un.coins.put(player.getName(), 0);
        } else {
            un.coins.put(player.getName(), (current - amount));
        }
    }
    
    public boolean transfer (Player to, Player from, int amount)
    {
        if (coinCheck(from, amount)) {
            remove(from, amount);
            add(to, amount);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean coinCheck(Player player, int amount) 
    {
        if (un.coins.get(player.getName()) < amount) {
            return true;
        }
        return false;
    }
}
