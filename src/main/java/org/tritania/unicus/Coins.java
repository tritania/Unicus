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

import org.tritania.unicus.Unicus;
import org.tritania.unicus.utils.Log;

public class Coins
{
    public Unicus un;

    public Coins (Unicus un)
    {
        this.un = un;
    }
    
    public void add (Player player, int amount) 
    {
        
    }
    
    public void remove (Player player, int amount) 
    {
        
    }
    
    public void transfer (Player to, Player from, int amount)
    {
        
    }
    
    public boolean coinCheck(Player player, int amount) 
    {
        return true; //debugging
    }
}
