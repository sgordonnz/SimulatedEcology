package com.nzgordon.daily.simeco.entities;

import com.nzgordon.daily.simeco.reaction.Reaction;
import com.nzgordon.daily.simeco.world.Spot;
import com.nzgordon.daily.simeco.world.World;

import java.util.Set;

public class Bear extends Occupant {

    public Bear(Spot spot) {
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
                for (Occupant neighbour : neighbours) react(neighbour);
            }
        }
    }

    @Override
    protected int maxStepsPerMonth() {
        return 5;
    }

    @Override
    public Reaction react(Occupant other, boolean chain) {
        if (!chain) other.react(this, true);
        return Reaction.NOTHING;
    }

}
