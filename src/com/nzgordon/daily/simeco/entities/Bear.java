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
    public void behave() {
        super.behave();
        // Walk
        wander();
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
