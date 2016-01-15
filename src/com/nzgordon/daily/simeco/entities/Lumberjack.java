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
    public void behave() {
        super.behave();
        wander();
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
