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
package net.milkbowl.vault.chat;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jspecify.annotations.Nullable;

/**
 * The main Chat API - allows for Prefix/Suffix nodes along with generic Info nodes if the linked Chat system supports them
 */
public abstract class Chat {

    private final Permission perms;

    public Chat(Permission perms) {
        this.perms = perms;
    }

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
     * @param world  World name
     * @param player Player name
     * @return Prefix
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerPrefix(String, OfflinePlayer)} instead.
     * <p>
     * Get players prefix
     */
    @Deprecated(since = "1.4")
    abstract public String getPlayerPrefix(@Nullable String world, String player);

    /**
     * Get a players prefix in the given world
     * Use NULL for world if requesting a global prefix
     *
     * @param world  World name
     * @param player OfflinePlayer
     * @return Prefix
     */
    public String getPlayerPrefix(@Nullable String world, OfflinePlayer player) {
        return getPlayerPrefix(world, player.getName());
    }

    /**
     * @param world  World Object
     * @param player Player name
     * @return Prefix
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerPrefix(String, OfflinePlayer)} instead.
     * <p>
     * Get players prefix
     */
    @Deprecated(since = "1.4")
    public String getPlayerPrefix(@Nullable World world, String player) {
        return getPlayerPrefix(world != null ? world.getName() : null, player);
    }

    /**
     * Get players prefix from the world they are currently in.
     * May or may not return the global prefix depending on implementation.
     *
     * @param player Player Object
     * @return Prefix
     */
    public String getPlayerPrefix(Player player) {
        return getPlayerPrefix(player.getWorld().getName(), player);
    }

    /**
     * @param world  World name
     * @param player Player name
     * @param prefix Prefix
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerPrefix(String, OfflinePlayer, String)} instead.
     * <p>
     * Set players prefix
     */
    @Deprecated(since = "1.4")
    abstract public void setPlayerPrefix(@Nullable String world, String player, String prefix);

    /**
     * Sets players prefix in the given world.
     * Use NULL for world for setting in the Global scope.
     *
     * @param world  World name
     * @param player OfflinePlayer
     * @param prefix Prefix
     */
    public void setPlayerPrefix(@Nullable String world, OfflinePlayer player, String prefix) {
        setPlayerPrefix(world, player.getName(), prefix);
    }

    /**
     * @param world  World Object
     * @param player Player name
     * @param prefix Prefix
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerPrefix(String, OfflinePlayer, String)} instead.
     * <p>
     * Set players prefix in the given world.
     */
    @Deprecated(since = "1.4")
    public void setPlayerPrefix(@Nullable World world, String player, String prefix) {
        setPlayerPrefix(world != null ? world.getName() : null, player, prefix);
    }

    /**
     * Set players prefix in the world they are currently in.
     *
     * @param player Player Object
     * @param prefix Prefix
     */
    public void setPlayerPrefix(Player player, String prefix) {
        setPlayerPrefix(player.getWorld().getName(), player, prefix);
    }

    /**
     * @param world  World name
     * @param player Player name
     * @return Suffix
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerSuffix(String, OfflinePlayer)} instead.
     * <p>
     * Get players suffix
     */
    @Deprecated(since = "1.4")
    abstract public String getPlayerSuffix(@Nullable String world, String player);

    /**
     * Get players suffix in the specified world.
     *
     * @param world  World name
     * @param player OfflinePlayer name
     * @return Suffix
     */
    public String getPlayerSuffix(@Nullable String world, OfflinePlayer player) {
        return getPlayerSuffix(world, player.getName());
    }

    /**
     * @param world  World Object
     * @param player Player name
     * @return Suffix
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerSuffix(String, OfflinePlayer)} instead.
     * <p>
     * Get players suffix
     */
    @Deprecated(since = "1.4")
    public String getPlayerSuffix(@Nullable World world, String player) {
        return getPlayerSuffix(world != null ? world.getName() : null, player);
    }

    /**
     * Get players suffix in the world they are currently in.
     *
     * @param player Player Object
     * @return Suffix
     */
    public String getPlayerSuffix(Player player) {
        return getPlayerSuffix(player.getWorld().getName(), player);
    }

    /**
     * @param world  World name
     * @param player Player name
     * @param suffix Suffix
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerSuffix(String, OfflinePlayer, String)} instead.
     * <p>
     * Set players suffix
     */
    @Deprecated(since = "1.4")
    abstract public void setPlayerSuffix(@Nullable String world, String player, String suffix);

    /**
     * Set players suffix for the world specified
     *
     * @param world  World name
     * @param player OfflinePlayer
     * @param suffix Suffix
     */
    public void setPlayerSuffix(@Nullable String world, OfflinePlayer player, String suffix) {
        setPlayerSuffix(world, player.getName(), suffix);
    }

    /**
     * @param world  World Object
     * @param player Player name
     * @param suffix Suffix
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerSuffix(String, OfflinePlayer, String)} instead.
     * <p>
     * Set players suffix
     */
    @Deprecated(since = "1.4")
    public void setPlayerSuffix(@Nullable World world, String player, String suffix) {
        setPlayerSuffix(world != null ? world.getName() : null, player, suffix);
    }

    /**
     * Set players suffix in the world they currently occupy.
     *
     * @param player Player Object
     * @param suffix Suffix
     */
    public void setPlayerSuffix(Player player, String suffix) {
        setPlayerSuffix(player.getWorld().getName(), player, suffix);
    }

    /**
     * Get group prefix
     *
     * @param world World name
     * @param group Group name
     * @return Prefix
     */
    abstract public String getGroupPrefix(@Nullable String world, String group);

    /**
     * Get group prefix
     *
     * @param world World Object
     * @param group Group name
     * @return Prefix
     */
    public String getGroupPrefix(@Nullable World world, String group) {
        return getGroupPrefix(world != null ? world.getName() : null, group);
    }

    /**
     * Set group prefix
     *
     * @param world  World name
     * @param group  Group name
     * @param prefix Prefix
     */
    abstract public void setGroupPrefix(@Nullable String world, String group, String prefix);

    /**
     * Set group prefix
     *
     * @param world  World Object
     * @param group  Group name
     * @param prefix Prefix
     */
    public void setGroupPrefix(@Nullable World world, String group, String prefix) {
        setGroupPrefix(world != null ? world.getName() : null, group, prefix);
    }

    /**
     * Get group suffix
     *
     * @param world World name
     * @param group Group name
     * @return Suffix
     */
    abstract public String getGroupSuffix(@Nullable String world, String group);

    /**
     * Get group suffix
     *
     * @param world World Object
     * @param group Group name
     * @return Suffix
     */
    public String getGroupSuffix(@Nullable World world, String group) {
        return getGroupSuffix(world.getName(), group);
    }

    /**
     * Set group suffix
     *
     * @param world  World name
     * @param group  Group name
     * @param suffix Suffix
     */
    abstract public void setGroupSuffix(@Nullable String world, String group, String suffix);

    /**
     * Set group suffix
     *
     * @param world  World Object
     * @param group  Group name
     * @param suffix Suffix
     */
    public void setGroupSuffix(@Nullable World world, String group, String suffix) {
        setGroupSuffix(world != null ? world.getName() : null, group, suffix);
    }

    /**
     * Get a players informational node (Integer) value
     *
     * @param world        World name
     * @param player       OfflinePlayer
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public int getPlayerInfoInteger(@Nullable String world, OfflinePlayer player, String node, int defaultValue) {
        return getPlayerInfoInteger(world, player.getName(), node, defaultValue);
    }

    /**
     * @param world        World name
     * @param player       Player name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerInfoInteger(String, OfflinePlayer, String, int)} instead.
     * Get a players informational node (Integer) value
     */
    @Deprecated(since = "1.4")
    abstract public int getPlayerInfoInteger(@Nullable String world, String player, String node, int defaultValue);

    /**
     * @param world        World Object
     * @param player       Player name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerInfoInteger(String, OfflinePlayer, String, int)} instead.
     * <p>
     * Get a players informational node (Integer) value
     */
    @Deprecated(since = "1.4")
    public int getPlayerInfoInteger(@Nullable World world, String player, String node, int defaultValue) {
        return getPlayerInfoInteger(world != null ? world.getName() : null, player, node, defaultValue);
    }

    /**
     * Get a players informational node (Integer) value
     *
     * @param player       Player Object
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public int getPlayerInfoInteger(Player player, String node, int defaultValue) {
        return getPlayerInfoInteger(player.getWorld().getName(), player, node, defaultValue);
    }

    /**
     * Set a players informational node (Integer) value
     *
     * @param world  World name
     * @param player OfflinePlayer
     * @param node   Permission node
     * @param value  Value to set
     */
    public void setPlayerInfoInteger(@Nullable String world, OfflinePlayer player, String node, int value) {
        setPlayerInfoInteger(world, player.getName(), node, value);
    }

    /**
     * @param world  World name
     * @param player Player name
     * @param node   Permission node
     * @param value  Value to set
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerInfoInteger(String, OfflinePlayer, String, int)} instead.
     * <p>
     * Set a players informational node (Integer) value
     */
    @Deprecated(since = "1.4")
    abstract public void setPlayerInfoInteger(@Nullable String world, String player, String node, int value);

    /**
     * @param world  World Object
     * @param player Player name
     * @param node   Permission node
     * @param value  Value to set
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerInfoInteger(String, OfflinePlayer, String, int)} instead.
     * <p>
     * Set a players informational node (Integer) value
     */
    @Deprecated(since = "1.4")
    public void setPlayerInfoInteger(@Nullable World world, String player, String node, int value) {
        setPlayerInfoInteger(world != null ? world.getName() : null, player, node, value);
    }

    /**
     * Set a players informational node (Integer) value
     *
     * @param player Player Object
     * @param node   Permission node
     * @param value  Value to set
     */
    public void setPlayerInfoInteger(Player player, String node, int value) {
        setPlayerInfoInteger(player.getWorld().getName(), player, node, value);
    }

    /**
     * Get a groups informational node (Integer) value
     *
     * @param world        World name
     * @param group        Group name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public int getGroupInfoInteger(@Nullable String world, String group, String node, int defaultValue);

    /**
     * Get a groups informational node (Integer) value
     *
     * @param world        World Object
     * @param group        Group name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public int getGroupInfoInteger(@Nullable World world, String group, String node, int defaultValue) {
        return getGroupInfoInteger(world != null ? world.getName() : null, group, node, defaultValue);
    }

    /**
     * Set a groups informational node (Integer) value
     *
     * @param world World name
     * @param group Group name
     * @param node  Permission node
     * @param value Value to set
     */
    abstract public void setGroupInfoInteger(@Nullable String world, String group, String node, int value);

    /**
     * Set a groups informational node (Integer) value
     *
     * @param world World Object
     * @param group Group name
     * @param node  Permission node
     * @param value Value to set
     */
    public void setGroupInfoInteger(@Nullable World world, String group, String node, int value) {
        setGroupInfoInteger(world != null ? world.getName() : null, group, node, value);
    }

    /**
     * Get a players informational node (Double) value
     *
     * @param world        World name
     * @param player       OfflinePlayer
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public double getPlayerInfoDouble(@Nullable String world, OfflinePlayer player, String node, double defaultValue) {
        return getPlayerInfoDouble(world, player.getName(), node, defaultValue);
    }

    /**
     * @param world        World name
     * @param player       Player name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerInfoDouble(String, OfflinePlayer, String, double)} instead.
     * <p>
     * Get a players informational node (Double) value
     */
    @Deprecated(since = "1.4")
    abstract public double getPlayerInfoDouble(@Nullable String world, String player, String node, double defaultValue);

    /**
     * @param world        World Object
     * @param player       Player name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerInfoDouble(String, OfflinePlayer, String, double)} instead
     * <p>
     * Get a players informational node (Double) value
     */
    @Deprecated(since = "1.4")
    public double getPlayerInfoDouble(@Nullable World world, String player, String node, double defaultValue) {
        return getPlayerInfoDouble(world != null ? world.getName() : null, player, node, defaultValue);
    }

    /**
     * Get a players informational node (Double) value
     *
     * @param player       Player Object
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public double getPlayerInfoDouble(Player player, String node, double defaultValue) {
        return getPlayerInfoDouble(player.getWorld().getName(), player, node, defaultValue);
    }

    /**
     * Set a players informational node (Double) value
     *
     * @param world  World name
     * @param player OfflinePlayer
     * @param node   Permission node
     * @param value  Value to set
     */
    public void setPlayerInfoDouble(@Nullable String world, OfflinePlayer player, String node, double value) {
        setPlayerInfoDouble(world, player.getName(), node, value);
    }

    /**
     * @param world  World name
     * @param player Player name
     * @param node   Permission node
     * @param value  Value to set
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerInfoDouble(String, OfflinePlayer, String, double)} instead.
     * Set a players informational node (Double) value
     */
    @Deprecated(since = "1.4")
    abstract public void setPlayerInfoDouble(@Nullable String world, String player, String node, double value);

    /**
     * @param world  World Object
     * @param player Player name
     * @param node   Permission node
     * @param value  Value to set
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerInfoDouble(String, OfflinePlayer, String, double)} instead.
     * Set a players informational node (Double) value
     */
    @Deprecated(since = "1.4")
    public void setPlayerInfoDouble(@Nullable World world, String player, String node, double value) {
        setPlayerInfoDouble(world != null ? world.getName() : null, player, node, value);
    }

    /**
     * Set a players informational node (Double) value
     *
     * @param player Player Object
     * @param node   Permission node
     * @param value  Value to set
     */
    public void setPlayerInfoDouble(Player player, String node, double value) {
        setPlayerInfoDouble(player.getWorld().getName(), player, node, value);
    }

    /**
     * Get a groups informational node (Double) value
     *
     * @param world        World name
     * @param group        Group name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public double getGroupInfoDouble(@Nullable String world, String group, String node, double defaultValue);

    /**
     * Get a groups informational node (Double) value
     *
     * @param world        World Object
     * @param group        Group name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public double getGroupInfoDouble(@Nullable World world, String group, String node, double defaultValue) {
        return getGroupInfoDouble(world != null ? world.getName() : null, group, node, defaultValue);
    }

    /**
     * Set a groups informational node (Double) value
     *
     * @param world World name
     * @param group Group name
     * @param node  Permission node
     * @param value Value to set
     */
    abstract public void setGroupInfoDouble(@Nullable String world, String group, String node, double value);

    /**
     * Set a groups informational node (Double) value
     *
     * @param world World Object
     * @param group Group name
     * @param node  Permission node
     * @param value Value to set
     */
    public void setGroupInfoDouble(@Nullable World world, String group, String node, double value) {
        setGroupInfoDouble(world != null ? world.getName() : null, group, node, value);
    }

    /**
     * Get a players informational node (Boolean) value
     *
     * @param world        World name
     * @param player       OfflinePlayer
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public boolean getPlayerInfoBoolean(@Nullable String world, OfflinePlayer player, String node, boolean defaultValue) {
        return getPlayerInfoBoolean(world, player.getName(), node, defaultValue);
    }

    /**
     * @param world        World name
     * @param player       Player name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerInfoBoolean(String, OfflinePlayer, String, boolean)} instead.
     * <p>
     * Get a players informational node (Boolean) value
     */
    @Deprecated(since = "1.4")
    abstract public boolean getPlayerInfoBoolean(@Nullable String world, String player, String node, boolean defaultValue);

    /**
     * @param world        World Object
     * @param player       Player name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerInfoBoolean(String, OfflinePlayer, String, boolean)} instead.
     * <p>
     * Get a players informational node (Boolean) value
     */
    @Deprecated(since = "1.4")
    public boolean getPlayerInfoBoolean(@Nullable World world, String player, String node, boolean defaultValue) {
        return getPlayerInfoBoolean(world != null ? world.getName() : null, player, node, defaultValue);
    }

    /**
     * Get a players informational node (Boolean) value
     *
     * @param player       Player Object
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public boolean getPlayerInfoBoolean(Player player, String node, boolean defaultValue) {
        return getPlayerInfoBoolean(player.getWorld().getName(), player, node, defaultValue);
    }

    /**
     * Set a players informational node (Boolean) value
     *
     * @param world  World name
     * @param player OfflinePlayer
     * @param node   Permission node
     * @param value  Value to set
     */
    public void setPlayerInfoBoolean(@Nullable String world, OfflinePlayer player, String node, boolean value) {
        setPlayerInfoBoolean(world, player.getName(), node, value);
    }

    /**
     * @param world  World name
     * @param player Player name
     * @param node   Permission node
     * @param value  Value to set
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerInfoBoolean(String, OfflinePlayer, String, boolean)} instead.
     * Set a players informational node (Boolean) value
     */
    @Deprecated(since = "1.4")
    abstract public void setPlayerInfoBoolean(@Nullable String world, String player, String node, boolean value);

    /**
     * @param world  World Object
     * @param player Player name
     * @param node   Permission node
     * @param value  Value to set
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerInfoBoolean(String, OfflinePlayer, String, boolean)} instead.
     * Set a players informational node (Boolean) value
     */
    @Deprecated(since = "1.4")
    public void setPlayerInfoBoolean(@Nullable World world, String player, String node, boolean value) {
        setPlayerInfoBoolean(world != null ? world.getName() : null, player, node, value);
    }

    /**
     * Set a players informational node (Boolean) value
     *
     * @param player Player Object
     * @param node   Permission node
     * @param value  Value to set
     */
    public void setPlayerInfoBoolean(Player player, String node, boolean value) {
        setPlayerInfoBoolean(player.getWorld().getName(), player, node, value);
    }

    /**
     * Get a groups informational node (Boolean) value
     *
     * @param world        Name of the world
     * @param group        Name of the group
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public boolean getGroupInfoBoolean(@Nullable String world, String group, String node, boolean defaultValue);

    /**
     * Set a players informational node (Boolean) value
     *
     * @param world        World Object
     * @param group        Group name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public boolean getGroupInfoBoolean(@Nullable World world, String group, String node, boolean defaultValue) {
        return getGroupInfoBoolean(world != null ? world.getName() : null, group, node, defaultValue);
    }

    /**
     * Set a groups informational node (Boolean) value
     *
     * @param world World name
     * @param group Group name
     * @param node  Permission node
     * @param value Value to set
     */
    abstract public void setGroupInfoBoolean(@Nullable String world, String group, String node, boolean value);

    /**
     * Set a players informational node (Boolean) value
     *
     * @param world World Object
     * @param group Group name
     * @param node  Permission node
     * @param value Value to set
     */
    public void setGroupInfoBoolean(@Nullable World world, String group, String node, boolean value) {
        setGroupInfoBoolean(world != null ? world.getName() : null, group, node, value);
    }

    /**
     * Get a players informational node (String) value
     *
     * @param world        World name
     * @param player       OfflinePlayer
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public String getPlayerInfoString(@Nullable String world, OfflinePlayer player, String node, String defaultValue) {
        return getPlayerInfoString(world, player.getName(), node, defaultValue);
    }

    /**
     * @param world        World name
     * @param player       Player name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerInfoString(String, OfflinePlayer, String, String)} instead.
     * <p>
     * Get a players informational node (String) value
     */
    @Deprecated(since = "1.4")
    abstract public String getPlayerInfoString(@Nullable String world, String player, String node, String defaultValue);

    /**
     * @param world        World Object
     * @param player       Player name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerInfoString(String, OfflinePlayer, String, String)} instead.
     * Get a players informational node (String) value
     */
    @Deprecated(since = "1.4")
    public String getPlayerInfoString(@Nullable World world, String player, String node, String defaultValue) {
        return getPlayerInfoString(world != null ? world.getName() : null, player, node, defaultValue);
    }

    /**
     * Get a players informational node (String) value
     *
     * @param player       Player Object
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public String getPlayerInfoString(Player player, String node, String defaultValue) {
        return getPlayerInfoString(player.getWorld().getName(), player, node, defaultValue);
    }

    /**
     * Set a players informational node (String) value
     *
     * @param world  World name
     * @param player OfflinePlayer
     * @param node   Permission node
     * @param value  Value to set
     */
    public void setPlayerInfoString(@Nullable String world, OfflinePlayer player, String node, String value) {
        setPlayerInfoString(world, player.getName(), node, value);
    }

    /**
     * @param world  World name
     * @param player Player name
     * @param node   Permission node
     * @param value  Value to set
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerInfoString(String, OfflinePlayer, String, String)} instead.
     * Set a players informational node (String) value
     */
    @Deprecated(since = "1.4")
    abstract public void setPlayerInfoString(@Nullable String world, String player, String node, String value);

    /**
     * @param world  World name
     * @param player Player name
     * @param node   Permission node
     * @param value  Value to set
     * @deprecated As of VaultAPI 1.4 use {@link #setPlayerInfoString(String, OfflinePlayer, String, String)} instead.
     * Set a players informational node (String) value
     */
    @Deprecated(since = "1.4")
    public void setPlayerInfoString(@Nullable World world, String player, String node, String value) {
        setPlayerInfoString(world != null ? world.getName() : null, player, node, value);
    }

    /**
     * Set a players informational node (String) value
     *
     * @param player Player Object
     * @param node   Permission node
     * @param value  Value ot set
     */
    public void setPlayerInfoString(Player player, String node, String value) {
        setPlayerInfoString(player.getWorld().getName(), player, node, value);
    }

    /**
     * Get a groups informational node (String) value
     *
     * @param world        Name of the world
     * @param group        Name of the group
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    abstract public String getGroupInfoString(@Nullable String world, String group, String node, String defaultValue);

    /**
     * Set a players informational node (String) value
     *
     * @param world        World Object
     * @param group        Group name
     * @param node         Permission node
     * @param defaultValue Default value
     * @return Value
     */
    public String getGroupInfoString(@Nullable World world, String group, String node, String defaultValue) {
        return getGroupInfoString(world != null ? world.getName() : null, group, node, defaultValue);
    }

    /**
     * Set a groups informational node (String) value
     *
     * @param world World name
     * @param group Group name
     * @param node  Permission node
     * @param value Value to set
     */
    abstract public void setGroupInfoString(@Nullable String world, String group, String node, String value);

    /**
     * Set a groups informational node (String) value
     *
     * @param world World name
     * @param group Group name
     * @param node  Permission node
     * @param value Value to set
     */
    public void setGroupInfoString(@Nullable World world, String group, String node, String value) {
        setGroupInfoString(world != null ? world.getName() : null, group, node, value);
    }

    /**
     * Check if player is member of a group.
     *
     * @param world  World name
     * @param player OfflinePlayer
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerInGroup(@Nullable String world, OfflinePlayer player, String group) {
        return perms.playerInGroup(world, player, group);
    }

    /**
     * @param world  World name
     * @param player Player name
     * @param group  Group name
     * @return Success or Failure
     * @deprecated As of VaultAPI 1.4 use {@link #playerInGroup(String, OfflinePlayer, String)} instead.
     * Check if the player is a member of a group.
     */
    @Deprecated(since = "1.4")
    public boolean playerInGroup(@Nullable String world, String player, String group) {
        return perms.playerInGroup(world, player, group);
    }

    /**
     * @param world  World Object
     * @param player Player name
     * @param group  Group name
     * @return Success or Failure
     * @deprecated As of VaultAPI 1.4 use {@link #playerInGroup(String, OfflinePlayer, String)} instead.
     * Check if the player is a member of a group.
     */
    @Deprecated(since = "1.4")
    public boolean playerInGroup(@Nullable World world, String player, String group) {
        return playerInGroup(world != null ? world.getName() : null, player, group);
    }

    /**
     * Check if player is member of a group.
     *
     * @param player Player Object
     * @param group  Group name
     * @return Success or Failure
     */
    public boolean playerInGroup(Player player, String group) {
        return playerInGroup(player.getWorld().getName(), player, group);
    }

    /**
     * Gets the list of groups that this player has
     *
     * @param world  World name
     * @param player OfflinePlayer
     * @return Array of groups
     */
    public String[] getPlayerGroups(@Nullable String world, OfflinePlayer player) {
        return perms.getPlayerGroups(world, player);
    }

    /**
     * @param world  World name
     * @param player Player name
     * @return Array of groups
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerGroups(String, OfflinePlayer)} instead.
     * Gets the list of groups that this player has
     */
    @Deprecated(since = "1.4")
    public String[] getPlayerGroups(@Nullable String world, String player) {
        return perms.getPlayerGroups(world, player);
    }

    /**
     * @param world  World Object
     * @param player Player name
     * @return Array of groups
     * @deprecated As of VaultAPI 1.4 use {@link #getPlayerGroups(String, OfflinePlayer)} instead.
     * Gets the list of groups that this player has
     */
    @Deprecated(since = "1.4")
    public String[] getPlayerGroups(@Nullable World world, String player) {
        return getPlayerGroups(world != null ? world.getName() : null, player);
    }

    /**
     * Gets the list of groups that this player has
     *
     * @param player Player Object
     * @return Array of groups
     */
    public String[] getPlayerGroups(Player player) {
        return getPlayerGroups(player.getWorld().getName(), player);
    }

    /**
     * Gets players primary group
     *
     * @param world  World name
     * @param player OfflinePlayer
     * @return Players primary group
     */
    public String getPrimaryGroup(@Nullable String world, OfflinePlayer player) {
        return perms.getPrimaryGroup(world, player);
    }

    /**
     * @param world  World name
     * @param player Player name
     * @return Players primary group
     * @deprecated As of VaultAPI 1.4 use {@link #getPrimaryGroup(String, OfflinePlayer)} instead.
     * Gets players primary group
     */
    @Deprecated(since = "1.4")
    public String getPrimaryGroup(@Nullable String world, String player) {
        return perms.getPrimaryGroup(world, player);
    }

    /**
     * @param world  World Object
     * @param player Player name
     * @return Players primary group
     * @deprecated As of VaultAPI 1.4 use {@link #getPrimaryGroup(String, OfflinePlayer)} instead.
     * Gets players primary group
     */
    @Deprecated(since = "1.4")
    public String getPrimaryGroup(@Nullable World world, String player) {
        return getPrimaryGroup(world != null ? world.getName() : null, player);
    }

    /**
     * Get players primary group
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
    public String[] getGroups() {
        return perms.getGroups();
    }
}
