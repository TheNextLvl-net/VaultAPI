/* This file is part of Vault.

    Vault is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Vault is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Vault.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.milkbowl.vault.permission;

import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jspecify.annotations.Nullable;

import java.util.logging.Logger;

/**
 * The main Permission API - allows for group and player based permission tests
 */
public abstract class Permission {

    protected static final Logger log = Logger.getLogger("Minecraft");
    protected @Nullable Plugin plugin = null;

    /**
     * Gets name of permission method
     *
     * @return Name of Permission Method
     */
    abstract public String getName();

    /**
     * Checks if permission method is enabled.
     *
     * @return Success or Failure
     */
    abstract public boolean isEnabled();

    /**
     * Returns if the permission system is or attempts to be compatible with super-perms.
     *
     * @return True if this permission implementation works with super-perms
     */
    abstract public boolean hasSuperPermsCompat();

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerHas(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    public boolean has(@Nullable String world, String player, String permission) {
        return playerHas(world, player, permission);
    }

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerHas(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    public boolean has(@Nullable World world, String player, String permission) {
        return playerHas(world != null ? world.getName() : null, player, permission);
    }

    /**
     * Checks if a CommandSender has a permission node.
     * This will return the result of bukkit's, generic .hasPermission() method and is identical in all cases.
     * This method will explicitly fail if the registered permission system does not register permissions in bukkit.
     * <p>
     * For easy checking of a command sender
     *
     * @param sender     to check permissions on
     * @param permission to check for
     * @return true if the sender has the permission
     */
    public boolean has(CommandSender sender, String permission) {
        return sender.hasPermission(permission);
    }

    /**
     * Checks if player has a permission node. (Short for playerHas(...)
     *
     * @param player     Player Object
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean has(Player player, String permission) {
        return player.hasPermission(permission);
    }

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerHas(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    abstract public boolean playerHas(@Nullable String world, String player, String permission);

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerHas(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    public boolean playerHas(@Nullable World world, String player, String permission) {
        return playerHas(world != null ? world.getName() : null, player, permission);
    }

    /**
     * Checks if player has a permission node.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      String world name
     * @param player     to check
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerHas(@Nullable String world, OfflinePlayer player, String permission) {
        return player.getName() != null && has(world, player.getName(), permission);
    }

    /**
     * Checks if player has a permission node.
     * Defaults to world-specific permission check if the permission system supports it.
     * See {@link #playerHas(String, OfflinePlayer, String)} for explicit global or world checks.
     *
     * @param player     Player Object
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerHas(Player player, String permission) {
        return has(player, permission);
    }

    /**
     * @param world      World name
     * @param player     Player name
     * @param permission Permission node
     * @return Success or Failure
     * @deprecated As of VaultAPI 1.4 use {@link #playerAdd(String, OfflinePlayer, String)} instead.
     * Add permission to a player.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     */
    @Deprecated(since = "1.4")
    abstract public boolean playerAdd(@Nullable String world, String player, String permission);

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerAdd(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    public boolean playerAdd(@Nullable World world, String player, String permission) {
        return playerAdd(world != null ? world.getName() : null, player, permission);
    }

    /**
     * Add permission to a player.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      String world name
     * @param player     to add to
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerAdd(@Nullable String world, OfflinePlayer player, String permission) {
        return player.getName() != null && playerAdd(world, player.getName(), permission);
    }

    /**
     * Add permission to a player ONLY for the world the player is currently on.
     * This is a world-specific operation, if you want to add global permission,
     * you must explicitly use NULL for the world.
     * See {@link #playerAdd(String, OfflinePlayer, String)} for global permission use.
     *
     * @param player     Player Object
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerAdd(Player player, String permission) {
        return playerAdd(player.getWorld().getName(), player, permission);
    }

    /**
     * Add transient permission to a player.
     * This implementation can be used by any subclass which implements a "pure" superperms plugin, i.e.
     * one that only needs the built-in Bukkit API to add transient permissions to a player.
     *
     * @param player     to add to
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerAddTransient(OfflinePlayer player, String permission) {
        return player instanceof Player online && playerAddTransient(online, permission);
    }

    /**
     * Add transient permission to a player.
     * This operation adds a permission onto the player object in bukkit via Bukkit's permission interface.
     *
     * @param player     Player Object
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerAddTransient(Player player, String permission) {
        for (PermissionAttachmentInfo paInfo : player.getEffectivePermissions()) {
            if (paInfo.getAttachment() != null && paInfo.getAttachment().getPlugin().equals(plugin)) {
                paInfo.getAttachment().setPermission(permission, true);
                return true;
            }
        }

        PermissionAttachment attach = player.addAttachment(plugin);
        attach.setPermission(permission, true);

        return true;
    }

    /**
     * Adds a world specific transient permission to the player, may only work with some permission managers.
     * Defaults to GLOBAL permissions for any permission system that does not support world-specific transient permissions!
     *
     * @param worldName  to check on
     * @param player     to add to
     * @param permission to test
     * @return Success or Failure
     */
    public boolean playerAddTransient(@Nullable String worldName, OfflinePlayer player, String permission) {
        return playerAddTransient(player, permission);
    }

    /**
     * Adds a world specific transient permission to the player, may only work with some permission managers.
     * Defaults to GLOBAL permissions for any permission system that does not support world-specific transient permissions!
     *
     * @param worldName  to check on
     * @param player     to check
     * @param permission to check for
     * @return Success or Failure
     */
    public boolean playerAddTransient(@Nullable String worldName, Player player, String permission) {
        return playerAddTransient(player, permission);
    }

    /**
     * Removes a world specific transient permission from the player, may only work with some permission managers.
     * Defaults to GLOBAL permissions for any permission system that does not support world-specific transient permissions!
     *
     * @param worldName  to remove for
     * @param player     to remove for
     * @param permission to remove
     * @return Success or Failure
     */
    public boolean playerRemoveTransient(@Nullable String worldName, OfflinePlayer player, String permission) {
        return playerRemoveTransient(player, permission);
    }

    /**
     * Removes a world specific transient permission from the player, may only work with some permission managers.
     * Defaults to GLOBAL permissions for any permission system that does not support world-specific transient permissions!
     *
     * @param worldName  to check on
     * @param player     to check
     * @param permission to check for
     * @return Success or Failure
     */
    public boolean playerRemoveTransient(@Nullable String worldName, Player player, String permission) {
        return playerRemoveTransient((OfflinePlayer) player, permission);
    }

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerRemove(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    abstract public boolean playerRemove(@Nullable String world, String player, String permission);

    /**
     * Remove permission from a player.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World name
     * @param player     OfflinePlayer
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerRemove(@Nullable String world, OfflinePlayer player, String permission) {
        return player.getName() != null && playerRemove(world, player.getName(), permission);
    }

    /**
     * Remove permission from a player.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World name
     * @param player     Player name
     * @param permission Permission node
     * @return Success or Failure
     */
    @Deprecated
    public boolean playerRemove(@Nullable World world, String player, String permission) {
        return playerRemove(world != null ? world.getName() : null, player, permission);
    }

    /**
     * Remove permission from a player.
     * Will attempt to remove permission from the player in the player's current world.
     * This is NOT a global operation.
     *
     * @param player     Player Object
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerRemove(Player player, String permission) {
        return playerRemove(player.getWorld().getName(), player, permission);
    }


    /**
     * Remove transient permission from a player.
     * This implementation can be used by any subclass which implements a "pure" superperms plugin, i.e.
     * one that only needs the built-in Bukkit API to remove transient permissions from a player.
     * Any subclass implementing a plugin which provides its own API for these needs to override this method.
     *
     * @param player     OfflinePlayer
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerRemoveTransient(OfflinePlayer player, String permission) {
        return player instanceof Player online && playerRemoveTransient(online, permission);
    }

    /**
     * Remove transient permission from a player.
     *
     * @param player     Player Object
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean playerRemoveTransient(Player player, String permission) {
        for (PermissionAttachmentInfo paInfo : player.getEffectivePermissions()) {
            if (paInfo.getAttachment() != null && paInfo.getAttachment().getPlugin().equals(plugin)) {
                paInfo.getAttachment().unsetPermission(permission);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if group has a permission node.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World name
     * @param group      Group name
     * @param permission Permission node
     * @return Success or Failure
     */
    abstract public boolean groupHas(@Nullable String world, String group, String permission);

    /**
     * Checks if group has a permission node.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World Object
     * @param group      Group name
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean groupHas(@Nullable World world, String group, String permission) {
        return groupHas(world != null ? world.getName() : null, group, permission);
    }

    /**
     * Add permission to a group.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World name
     * @param group      Group name
     * @param permission Permission node
     * @return Success or Failure
     */
    abstract public boolean groupAdd(@Nullable String world, String group, String permission);

    /**
     * Add permission to a group.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World Object
     * @param group      Group name
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean groupAdd(@Nullable World world, String group, String permission) {
        return groupAdd(world != null ? world.getName() : null, group, permission);
    }

    /**
     * Remove permission from a group.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World name
     * @param group      Group name
     * @param permission Permission node
     * @return Success or Failure
     */
    abstract public boolean groupRemove(@Nullable String world, String group, String permission);

    /**
     * Remove permission from a group.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world      World Object
     * @param group      Group name
     * @param permission Permission node
     * @return Success or Failure
     */
    public boolean groupRemove(@Nullable World world, String group, String permission) {
        return groupRemove(world != null ? world.getName() : null, group, permission);
    }

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerInGroup(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    abstract public boolean playerInGroup(@Nullable String world, String player, String group);

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerInGroup(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    public boolean playerInGroup(@Nullable World world, String player, String group) {
        return playerInGroup(world != null ? world.getName() : null, player, group);
    }

    /**
     * Check if player is member of a group.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world  World Object
     * @param player to check
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerInGroup(@Nullable String world, OfflinePlayer player, String group) {
        return player.getName() != null && playerInGroup(world, player.getName(), group);
    }

    /**
     * Check if player is member of a group.
     * This method will ONLY check groups for which the player is in that are defined for the current world.
     * This may result in odd return behavior depending on what permission system has been registered.
     *
     * @param player Player Object
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerInGroup(Player player, String group) {
        return playerInGroup(player.getWorld().getName(), player, group);
    }

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerAddGroup(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    abstract public boolean playerAddGroup(@Nullable String world, String player, String group);

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerAddGroup(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    public boolean playerAddGroup(@Nullable World world, String player, String group) {
        return playerAddGroup(world != null ? world.getName() : null, player, group);
    }

    /**
     * Add player to a group.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world  String world name
     * @param player to add
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerAddGroup(@Nullable String world, OfflinePlayer player, String group) {
        return player.getName() != null && playerAddGroup(world, player.getName(), group);
    }

    /**
     * Add player to a group.
     * This will add a player to the group in the current World.
     * This may return odd results if the permission system being used on the server does not support world-specific groups,
     * or if the group being added to is a global group.
     *
     * @param player Player Object
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerAddGroup(Player player, String group) {
        return playerAddGroup(player.getWorld().getName(), player, group);
    }

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerRemoveGroup(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    abstract public boolean playerRemoveGroup(@Nullable String world, String player, String group);

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #playerRemoveGroup(String, OfflinePlayer, String)} instead.
     */
    @Deprecated(since = "1.4")
    public boolean playerRemoveGroup(@Nullable World world, String player, String group) {
        return playerRemoveGroup(world != null ? world.getName() : null, player, group);
    }

    /**
     * Remove player from a group.
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world  World Object
     * @param player to remove
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerRemoveGroup(@Nullable String world, OfflinePlayer player, String group) {
        return player.getName() != null && playerRemoveGroup(world, player.getName(), group);
    }

    /**
     * Remove player from a group.
     * This will add a player to the group in the current World.
     * This may return odd results if the permission system being used on the server does not support world-specific groups,
     * or if the group being added to is a global group.
     *
     * @param player Player Object
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerRemoveGroup(Player player, String group) {
        return playerRemoveGroup(player.getWorld().getName(), player, group);
    }

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerGroups(String, OfflinePlayer)} instead.
     */
    @Deprecated(since = "1.4")
    abstract public String[] getPlayerGroups(@Nullable String world, String player);

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerGroups(String, OfflinePlayer)} instead.
     */
    @Deprecated(since = "1.4")
    public String[] getPlayerGroups(@Nullable World world, String player) {
        return getPlayerGroups(world != null ? world.getName() : null, player);
    }

    /**
     * Gets the list of groups that this player has
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world  String world name
     * @param player OfflinePlayer
     * @return Array of groups
     */
    public String[] getPlayerGroups(@Nullable String world, OfflinePlayer player) {
        return getPlayerGroups(world, player.getName());
    }

    /**
     * Returns a list of world-specific groups that this player is currently in. May return unexpected results if
     * you are looking for global groups, or if the registered permission system does not support world-specific groups.
     * See {@link #getPlayerGroups(String, OfflinePlayer)} for better control of World-specific or global groups.
     *
     * @param player Player Object
     * @return Array of groups
     */
    public String[] getPlayerGroups(Player player) {
        return getPlayerGroups(player.getWorld().getName(), player);
    }

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #getPrimaryGroup(String, OfflinePlayer)} instead.
     */
    @Deprecated(since = "1.4")
    abstract public String getPrimaryGroup(@Nullable String world, String player);

    /**
     * @deprecated As of VaultAPI 1.4 use {@link #getPrimaryGroup(String, OfflinePlayer)} instead.
     */
    @Deprecated(since = "1.4")
    public String getPrimaryGroup(@Nullable World world, String player) {
        return getPrimaryGroup(world != null ? world.getName() : null, player);
    }

    /**
     * Gets players primary group
     * Supports NULL value for World if the permission system registered supports global permissions.
     * But May return odd values if the servers registered permission system does not have a global permission store.
     *
     * @param world  String world name
     * @param player to get from
     * @return Players primary group
     */
    public String getPrimaryGroup(@Nullable String world, OfflinePlayer player) {
        return getPrimaryGroup(world, player.getName());
    }

    /**
     * Get players primary group.
     * Defaults to the players current world, so may return only world-specific groups.
     * In most cases {@link #getPrimaryGroup(String, OfflinePlayer)} is preferable.
     *
     * @param player Player Object
     * @return Players primary group
     */
    public String getPrimaryGroup(Player player) {
        return getPrimaryGroup(player.getWorld().getName(), player);
    }

    /**
     * Returns a list of all known groups
     *
     * @return an Array of String of all groups
     */
    abstract public String[] getGroups();

    /**
     * Returns true if the given implementation supports groups.
     *
     * @return true if the implementation supports groups
     */
    abstract public boolean hasGroupSupport();
}