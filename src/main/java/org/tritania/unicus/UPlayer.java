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

public class UPlayer 
{
    private int gains;
    private int coins;
    private String warp;
    
    public UPlayer ()
    {
        this.warp = "000"; //warps cannot start with a number
    }
    
    public void addCoins (int amount)
    {
        this.coins = this.coins + amount;
    }
    
    public void removeCoins (int amount)
    {
        if (this.coins >= amount)
            this.coins = this.coins - amount;
        else
            this.coins = 0;
    }
    
    public int getCoins ()
    {
        return this.coins;
    }
    
    public void saveGains (int gains)
    {
        this.gains = gains;
    }
    
    public boolean getWarp () 
    {
        if (this.warp.equals("000"))
            return false;
        return true;
    }
    
    public void setWarp (String name) 
    {
        this.warp = name;
    }
    
}
