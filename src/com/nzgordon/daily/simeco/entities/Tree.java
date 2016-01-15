package com.nzgordon.daily.simeco.entities;

import com.nzgordon.daily.simeco.reaction.Reaction;
import com.nzgordon.daily.simeco.world.Spot;
import com.nzgordon.daily.simeco.world.World;

import java.util.Set;

public class Tree extends Occupant {
    public Tree(Spot spot) {
        super(spot);
    }

    @Override
    public void behave(World world) {
        super.behave(world);
    }

    @Override
    protected int maxStepsPerMonth() {
        return 0;
    }

    @Override
    public Reaction react(Occupant other, boolean chain) {
        if (!chain) other.react(this, true);
        if (other instanceof Lumberjack) {
            System.out.println(String.format("A Tree %s was chopped by a Lumberjack %s", _id, other._id));
            die();
            return Reaction.DIE;
        }
        return Reaction.NOTHING;
    }

}
