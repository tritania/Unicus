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
import java.util.Properties;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import org.tritania.unicus.Unicus;
import org.tritania.unicus.utils.Log;

public class Storage implements Serializable
{
    public Unicus un;

    public Storage(Unicus un)
    {
        this.un = un;
    }
    
    public void savePurges(HashMap<String, String> purges)
    {
        try
        {
            File data =  new File(un.datalocal + "/purge.data");
            FileOutputStream fos   = new FileOutputStream(data);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(purges);
            oos.flush();
            oos.close();
            fos.close();
        }
        catch(Exception ex)
        {
            Log.severe("File IO error: " + ex.getMessage());
        }
    }

    public HashMap<String, String> loadPurges()
    {
        HashMap<String, String> values = new HashMap<String, String>();
        try
        {
            File data =  new File(un.datalocal + "/purge.data");
            FileInputStream fis  = new FileInputStream(data);
            ObjectInputStream ois= new ObjectInputStream(fis);

            values = (HashMap<String, String>)ois.readObject();

            ois.close();
            fis.close();
        }
        catch(Exception ex)
        {
            Log.severe("File IO error: " + ex.getMessage());
        }
        return values;
    }
    
    public void saveCoins(HashMap<String, Integer> coins)
    {
        try
        {
            File data =  new File(un.datalocal + "/coins.data");
            FileOutputStream fos   = new FileOutputStream(data);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(coins);
            oos.flush();
            oos.close();
            fos.close();
        }
        catch(Exception ex)
        {
            Log.severe("File IO error: " + ex.getMessage());
        }
    }

    public HashMap<String, Integer> loadCoins()
    {
        HashMap<String, Integer> values = new HashMap<String, Integer>();
        try
        {
            File data =  new File(un.datalocal + "/coins.data");
            FileInputStream fis  = new FileInputStream(data);
            ObjectInputStream ois= new ObjectInputStream(fis);

            values = (HashMap<String, Integer>)ois.readObject();

            ois.close();
            fis.close();
        }
        catch(Exception ex)
        {
            Log.severe("File IO error: " + ex.getMessage());
        }
        return values;
    }
}
