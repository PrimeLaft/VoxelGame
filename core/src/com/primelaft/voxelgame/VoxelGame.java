package com.primelaft.voxelgame;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.primelaft.voxelgame.world.*;
import com.primelaft.voxelgame.player.*;

public class VoxelGame implements ApplicationListener {
	public static ModelBatch modelBatch;
	World world = new World();
	Player player = new Player();

	@Override
	public void create() {
		modelBatch = new ModelBatch();

		world.createWorld();
		player.initializePlayer();
	}

	@Override
	public void render() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		world.renderTerrain();
		player.updateCamera();
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
