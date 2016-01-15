package com.nzgordon.daily.simeco.world;

import com.nzgordon.daily.simeco.entities.Bear;
import com.nzgordon.daily.simeco.entities.Lumberjack;
import com.nzgordon.daily.simeco.entities.Occupant;
import com.nzgordon.daily.simeco.entities.Tree;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class World {
    private Set<Occupant> _occupants = new HashSet<Occupant>();
    private Spot[][] _map;
    private int _boundary;

    public World(int size) {
        _map = new Spot[size][size];
        for (int x=0;x<_map.length;++x) {
            for (int y=0;y<_map[x].length;++y) {
                _map[x][y] = new Spot(x, y, this);
            }
        }
        _boundary = size;
        addToRandomSpot(Bear.class, INITIAL_BEARS);
        addToRandomSpot(Tree.class, INITIAL_TREES);
        addToRandomSpot(Lumberjack.class, INITIAL_LUMBERJACKS);
    }

    private void addToRandomSpot(Class<?> occupant, double percentage) {
        Random rnd = new Random();
        for (int i=0;i<(int)Math.floor(percentage*(double)(_boundary*_boundary)); ++i) {
            int x = rnd.nextInt(_boundary);
            int y = rnd.nextInt(_boundary);
            Spot spot = getSpot(x, y);
            if (spot.isOccupied()) {
                --i;
                continue;
            }
            try {
                _occupants.add((Occupant)occupant.getConstructor(Spot.class).newInstance(spot));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Spot getSpot(int x, int y) throws ArrayIndexOutOfBoundsException {
        if (x < 0 || y < 0 || x > getBoundaryLimit() || y > getBoundaryLimit()) {
            throw new ArrayIndexOutOfBoundsException("Requested Spot is outside the world boundaries.");
        }
        return _map[x][y];
    }

    public void tick() {
        Set<Occupant> dead = new HashSet<>();
        for (Occupant o : _occupants) {
            if (!o.isAlive()) dead.add(o);
            else o.behave();
        }
        _occupants.removeAll(dead);
    }

    public int getBoundaryLimit() {
        return _boundary;
    }

    static double INITIAL_LUMBERJACKS = 0.1;
    static double INITIAL_TREES = 0.5;
    static double INITIAL_BEARS = 0.02;
}
