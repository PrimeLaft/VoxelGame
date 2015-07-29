package com.primelaft.voxelgame.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.primelaft.voxelgame.VoxelGame;

public class Player {
    public static PerspectiveCamera cam;
    public static FirstPersonCameraController camController;

    public void initializePlayer()
    {
        camera();
        controller();
    }
    public void camera()
    {
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
    }
    public void updateCamera()
    {
        Player.camController.update();
        VoxelGame.modelBatch.begin(cam);
    }
    public void controller()
    {
        camController = new FirstPersonCameraController(Player.cam);
        Gdx.input.setInputProcessor(Player.camController);
    }
}
