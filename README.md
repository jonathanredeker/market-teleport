# Minecraft Market Teleport

Allows a player to teleport to specific set location. The player can then teleport back to their original location prior to teleportation.

This is a custom plugin for my server's marketplace. I set the location to a protected region so the players cannot leave with teleporting back.

## Commands
- <code>/market</code> or <code>/mkt</code> – Teleport the player to or from the set location.
- <code>/marketdelay <seconds></code> or <code>/mktdelay</code> – Set the teleport delay to prevent potential abuse. 0s for no delay and 60 is the max.
- <code>/marketlocation</code> or <code>/mktlocation</code> – Set the location to be teleported to and from.

## Permissions
- <code>market.*</code> – Allows usage of all commands
- <code>market.access (default)</code> – Allows the user teleport to or from the set location.
- <code>market.location</code> - Allows the user to set the location to be teleported to and from.
- <code>market.delay</code> - Allows the user to set the teleport delay.

