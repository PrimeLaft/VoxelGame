package com.primelaft.voxelgame;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;

public class VoxelGame implements ApplicationListener {
	public static ModelBatch modelBatch;
	public Terrain Terrain = new Terrain();
	public Player Player = new Player();

	@Override
	public void create() {
		//****************************************
		// Initialize game world
		//****************************************
		Terrain.InitializeWorld();

		modelBatch = new ModelBatch();


		//****************************************
		// Initialize player camera
		//****************************************
		Player.InitializeCamera();

		//****************************************
		// Initialize player controller
		//****************************************
		Player.InitializeController();


		//****************************************
		// Generate game terrain
		//****************************************
		Terrain.GenerateTerrain();
	}

	@Override
	public void render() {
		//****************************************
		// Rendering player camera
		//****************************************
		Player.RenderCamera();

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		//****************************************
		// Render player model batch
		//****************************************
		Player.CameraBatch(modelBatch);

		//****************************************
		// Render game world
		//****************************************
		Terrain.RenderTerrain();

		modelBatch.end();
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
