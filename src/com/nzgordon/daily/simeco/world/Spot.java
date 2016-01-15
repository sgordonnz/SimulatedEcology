package com.nzgordon.daily.simeco.world;

import com.nzgordon.daily.simeco.entities.Occupant;

import java.util.HashSet;
import java.util.Set;

public class Spot {
    private Set<Occupant> occupants = new HashSet<>();
    private Location location;
    private World world;

    public Spot(Location location, World world) {
        this.location = location;
        this.world = world;
    }

    public Spot(int x, int y, World world) {
        this(new Location(x,y), world);
    }

    public Location getLocation() {
        return location;
    }

    public World getWorld() {
        return world;
    }

    public boolean isOccupied() {
        return !occupants.isEmpty();
    }

    public void addOccupant(Occupant occupant) {
        occupants.add(occupant);
    }

    public Occupant removeOccupant(Occupant occupant) {
        occupants.remove(occupant);
        return occupant;
    }

    public boolean isAlone(Occupant occupant) {
        return occupants.stream().filter(i -> i != occupant).count() == 0;
    }

    public Set<Occupant> getNeighbours(Occupant occupant) {
        Set<Occupant> neighbours = new HashSet<>();
        occupants.stream().filter(i -> i != occupant).forEach(i -> neighbours.add(i));
        return neighbours;
    }
}
