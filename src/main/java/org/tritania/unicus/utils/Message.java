/* 
 * Copyright 2012-2014 James Geboski <jgeboski@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Gene ral Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.tritania.unicus.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message
{
    public static String plugin;

    public static void init(String plugin)
    {
        Message.plugin = plugin;
    }

    public static String format(String format, Object ... args)
    {
        String str;
        String rc;
        String rcr;

        str = String.format(format, args);

        if (str.charAt(0) != ChatColor.COLOR_CHAR)
            str = ChatColor.GOLD + str;

        rc  = ChatColor.RESET.toString();
        rcr = rc + str.substring(0, 2);
        str = str.replaceAll(rc, rcr);

        return str;
    }

    public static String hl(Object obj)
    {
        String str;

        str = String.valueOf(obj);
        str = ChatColor.GRAY + str + ChatColor.RESET;

        return str;
    }

    public static void info(CommandSender sender, String format,
                            Object ... args)
    {
        send(sender, format, args);
    }

    public static void warning(CommandSender sender, String format,
                               Object ... args)
    {
        if (sender instanceof Player)
            format = ChatColor.YELLOW + format;

        send(sender, format, args);
    }

    public static void severe(CommandSender sender, String format,
                              Object ... args)
    {
        if (sender instanceof Player)
            format = ChatColor.RED + format;

        send(sender, format, args);
    }

    public static void send(CommandSender sender, String format,
                            Object ... args)
    {
        String str;

        str = format(format, args);

        if (sender instanceof Player) {
            str = String.format("%s%s%s%s %s", hl("["), ChatColor.DARK_AQUA,
                                plugin, hl("]"), str);
        } else {
            str = ChatColor.stripColor(str);
            str = String.format("[%s] %s", plugin, str);
        }

        sender.sendMessage(str);
    }
}
