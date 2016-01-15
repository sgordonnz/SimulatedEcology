package com.nzgordon.daily.simeco.world;

import com.nzgordon.daily.simeco.entities.Occupant;

public class Location {
    private int _x;
    private int _y;

    public Location(int x, int y) {
        setLocation(x, y);
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public void setX(int x) {
        _x = x;
    }

    public void setY(int y) {
        _y = y;
    }

    public void setLocation(int x, int y) {
        setX(x);
        setY(y);
    }

    public boolean equals(Location compare) {
        return _x == compare.getX() && _y == compare.getY();
    }

    public boolean equals(Occupant compare) {
        return equals(compare.getLocation());
    }

    public final static int DIRECTION_LEFT = 0;
    public final static int DIRECTION_TOP = 1;
    public final static int DIRECTION_RIGHT = 2;
    public final static int DIRECTION_DOWN = 3;
}
