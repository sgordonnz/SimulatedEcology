package com.nzgordon.daily.simeco;

import com.nzgordon.daily.simeco.world.World;

public class Main {

    public static void main(String[] args) {
        int month = 0;
        World world = new World(WORLD_SIZE);
	    while(month < MAX_WORLD_LIFE) {
            world.tick();
            month++;
        }
    }

    final static int MAX_WORLD_LIFE = 3600;
    final static int WORLD_SIZE = 10;
}
