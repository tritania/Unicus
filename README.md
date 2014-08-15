Unicus
======
A bukkit plugin created for specific use on glaciem minecraft servers. This plugin interfaces with both dynmap and Essentials.

## Commands ##
* /pick                 Allows the player to pick their new main home
* /purge                Allows admins to purge the multihomes of players (Also works in console)
* /unpurge              Reverses the /purge commands
* /purgelist            Lists all curently purged players
* /coins                Access to all coin commands


## Permissions ##
* unicus.admin          Allows access to all commands 
* unicus.coins.admin    Allows access to all coin commands 

## Installation ##
To build and install this plugin simply install apache maven download this source code and run:
```mvn clean package install```
inside the source code directory. Then move the jar file inside of the target directory to your server's plugin directory.
