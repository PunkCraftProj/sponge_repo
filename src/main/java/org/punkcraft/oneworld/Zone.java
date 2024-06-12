package org.punkcraft.oneworld;

public class Zone {
    private String name;
    private int x1, y1, z1, x2, y2, z2;

    public Zone(String name, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public String getName() {
        return name;
    }

    public boolean isWithinZone(int x, int y, int z) {
        return (Math.min(x1, x2) <= x && x <= Math.max(x1, x2)) &&
               (Math.min(y1, y2) <= y && y <= Math.max(y1, y2)) &&
               (Math.min(z1, z2) <= z && z <= Math.max(z1, z2));
    }
}