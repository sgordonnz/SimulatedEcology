package com.nzgordon.daily.simeco.entities;

import com.nzgordon.daily.simeco.reaction.Reaction;
import com.nzgordon.daily.simeco.world.Spot;
import com.nzgordon.daily.simeco.world.World;

import java.util.Set;

public class Lumberjack extends Occupant {
    public Lumberjack(Spot spot) {
        super(spot);
    }

    @Override
    public void behave(World world) {
        super.behave(world);
        int max = maxStepsPerMonth();
        // Walk
        for (int step=0;step<max;step++) {
            if (!doRandomStep(world)) {
                step--;
                continue;
            }
            if (isColliding()) {
                Set<Occupant> neighbours = getCollidedWith();
                boolean stop = false;
                for (Occupant neighbour : neighbours) {
                    Reaction reaction = react(neighbour);
                    if (reaction != Reaction.NOTHING) {
                        stop = true;
                    }
                }
                if (stop) break; // Stop walking for the month.
            }
        }
    }

    @Override
    protected int maxStepsPerMonth() {
        return 3;
    }

    @Override
    public Reaction react(Occupant other, boolean chain) {
        if (!chain) other.react(this, true);
        if (other instanceof Bear) {
            die();
            System.out.println(String.format("A Lumberjack %s was killed by a Beer %s", _id, other._id));
            return Reaction.DIE;
        }
        if (other instanceof Tree) {
            return Reaction.CHOP;
        }
        return Reaction.NOTHING;
    }

}
