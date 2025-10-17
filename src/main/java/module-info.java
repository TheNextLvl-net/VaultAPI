// import org.jspecify.annotations.NullMarked;

// @NullMarked
module net.thenextlvl.vault {
    exports net.milkbowl.vault.chat;
    exports net.milkbowl.vault.economy;
    exports net.milkbowl.vault.permission;

    requires java.logging;
    requires org.bukkit;

    requires static org.jspecify;
}