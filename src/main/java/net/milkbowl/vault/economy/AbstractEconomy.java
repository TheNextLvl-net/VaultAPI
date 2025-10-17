package net.milkbowl.vault.economy;

import org.bukkit.OfflinePlayer;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("deprecation")
public abstract class AbstractEconomy implements Economy {

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return player.getName() != null && hasAccount(player.getName());
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, @Nullable String worldName) {
        return player.getName() != null && hasAccount(player.getName(), worldName);
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return getBalance(player.getName());
    }

    @Override
    public double getBalance(OfflinePlayer player, @Nullable String world) {
        return getBalance(player.getName(), world);
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return player.getName() != null && has(player.getName(), amount);
    }

    @Override
    public boolean has(OfflinePlayer player, @Nullable String worldName, double amount) {
        return player.getName() != null && has(player.getName(), worldName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        return withdrawPlayer(player.getName(), amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, @Nullable String worldName, double amount) {
        return withdrawPlayer(player.getName(), worldName, amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        return depositPlayer(player.getName(), amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, @Nullable String worldName, double amount) {
        return depositPlayer(player.getName(), worldName, amount);
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return createBank(name, player.getName());
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return isBankOwner(name, player.getName());
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return isBankMember(name, player.getName());
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return player.getName() != null && createPlayerAccount(player.getName());
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, @Nullable String worldName) {
        return player.getName() != null && createPlayerAccount(player.getName(), worldName);
    }

}
