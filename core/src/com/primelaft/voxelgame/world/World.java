package com.primelaft.voxelgame.world;

import com.badlogic.gdx.graphics.g3d.Environment;

public class World {
    public static Environment environment;
    Terrain terrain;


    public void createWorld()
    {
        environment = new Environment();
        initializeLight();
        initializeTerrain();
    }
    public void initializeLight()
    {
        Light light = new Light();
        light.setLightOnWorld();
    }
    public void initializeTerrain()
    {
        terrain = new Terrain();
    }
    public void renderTerrain()
    {
        terrain.renderTerrain();
    }
}
