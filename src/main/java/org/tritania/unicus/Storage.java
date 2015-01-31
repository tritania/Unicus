/*
 * Copyright 2014-2015 Erik Wilson <erikwilson@magnorum.com>
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
    
    public void saveData (HashMap<String, Long> worlds)
    {
        try
        {
            File data =  new File(un.datalocal + "/worlds.data");
            FileOutputStream fos   = new FileOutputStream(data);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(worlds);
            oos.flush();
            oos.close();
            fos.close();
        }
        catch(Exception ex)
        {
            Log.severe("File IO error: " + ex.getMessage());
        }
    }

    public HashMap<String, Long> loadData ()
    {
        HashMap<String, Long> values = new HashMap<String, Long>();
        try
        {
            File data =  new File(un.datalocal + "/worlds.data");
            FileInputStream fis  = new FileInputStream(data);
            ObjectInputStream ois= new ObjectInputStream(fis);

            values = (HashMap<String, Long>)ois.readObject();

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
