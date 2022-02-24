package me.Zcamt.zstaffchat;

public enum Permissions {
    STAFF_CHAT("zStaffchat.staffchat"),
    DEV_CHAT("zStaffchat.devchat"),
    ADMIN_CHAT("zStaffchat.adminchat"),
    ADMIN("zStaffchat.admin"),
    OVERRIDE("zStaffchat.*");

    private String string;

    Permissions(String string) {
        this.string = string;
    }

    public String getPermission() {
        return string;
    }
}
