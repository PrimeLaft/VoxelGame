package com.primelaft.voxelgame;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

public class Player {
    public static PerspectiveCamera cam;
    public CameraInputController camController;

    //****************************************
    // Initialize camera
    //****************************************
    public void InitializeCamera()
    {
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
    }
    //****************************************
    // Initialize controller
    //****************************************
    public void InitializeController()
    {
        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);
    }
    //****************************************
    // Render camera
    //****************************************
    public void RenderCamera()
    {
        camController.update();
    }
    //****************************************
    // Camera model batch
    //****************************************
    public void CameraBatch(ModelBatch modelBatch)
    {
        modelBatch.begin(cam);
    }
}
