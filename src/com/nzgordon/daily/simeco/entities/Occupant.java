package com.nzgordon.daily.simeco.entities;

import com.nzgordon.daily.simeco.reaction.Reaction;
import com.nzgordon.daily.simeco.world.Location;
import com.nzgordon.daily.simeco.world.Spot;
import com.nzgordon.daily.simeco.world.World;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

public abstract class Occupant {
    private Spot _spot = null;
    private int _age; // In months
    private boolean alive = true;
    protected final UUID _id;

    public Occupant(Spot spot) {
        setSpot(spot);
        _id = UUID.randomUUID();
        System.out.println(String.format("Initialised a %s with id %s at x:%d y:%d ",this.getClass().getSimpleName(), _id, spot.getLocation().getX(), spot.getLocation().getY()));
    }

    public void setSpot(Spot spot) {
        if (_spot != null) {
            _spot.removeOccupant(this);
        }
        _spot = spot;
        _spot.addOccupant(this);
    }

    public Location getLocation() {
        return _spot.getLocation();
    }

    public void die() {
        _spot.removeOccupant(this);
        _spot = null;
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void behave(World world) {
        // Increase age
        age();
        // Overrides
    }

    public int getAge() {
        return _age;
    }

    public void setAge(int age) {
        if (age < 0) {
            age = 0;
        }
        _age = age;
    }

    public void age() {
        setAge(getAge()+1);
    }

    public boolean isColliding() {
        return !_spot.isAlone(this);
    }

    public Set<Occupant> getCollidedWith() {
        return _spot.getNeighbours(this);
    }

    protected boolean doRandomStep(World world) {
        Location l = getLocation();
        int x = l.getX();
        int y = l.getY();
        switch(new Random().nextInt(4)) {
            case Location.DIRECTION_LEFT:
                x--;
                break;
            case Location.DIRECTION_TOP:
                y--;
                break;
            case Location.DIRECTION_RIGHT:
                x++;
                break;
            case Location.DIRECTION_DOWN:
            default:
                y++;
                break;
        }
        try {
            Spot newSpot = world.getSpot(x, y);
            setSpot(newSpot);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Reaction react(Occupant other) {
        return react(other, false);
    }

    protected abstract int maxStepsPerMonth();
    public abstract Reaction react(Occupant other, boolean chain);
}
