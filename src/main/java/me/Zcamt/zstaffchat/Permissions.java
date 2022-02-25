package me.Zcamt.zstaffchat;

public enum Permissions {
    STAFF_CHAT("zStaffchat.staffchat"),
    DEV_CHAT("zStaffchat.devchat"),
    ADMIN_CHAT("zStaffchat.adminchat"),
    ADMIN("zStaffchat.admin"),
    OVERRIDE("zStaffchat.*");

    private String permission;

    Permissions(String string) {
        this.permission = string;
    }

    public String getPermission() {
        return permission;
    }
}
