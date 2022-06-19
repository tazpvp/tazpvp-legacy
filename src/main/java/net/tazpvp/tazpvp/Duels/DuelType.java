package net.tazpvp.tazpvp.Duels;

public enum DuelType {
    SWORD(KitManager.SWORD),
    CRYSTAL(KitManager.CRYSTAL);

    public KitManager kit;
    private DuelType(KitManager kit) {
        this.kit = kit;
    }
}
