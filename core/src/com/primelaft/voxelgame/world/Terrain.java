package com.primelaft.voxelgame.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.primelaft.voxelgame.player.*;
import com.primelaft.voxelgame.VoxelGame;


public class Terrain {
    public Model[][][] Blocks = new Model[2][2][2];
    public ModelInstance[][][] BlocksInstances = new ModelInstance[2][2][2];

    public Terrain()
    {
        generateTerrain();
        //renderTerrain();
    }

    public void generateTerrain()
    {
        FileHandle imageFileHandle = Gdx.files.internal("grass.jpg");

        ModelBuilder modelBuilder = new ModelBuilder();

        for(int x = 0; x < Blocks.length; x++){
            for(int y = 0; y < Blocks.length; y++)
            {
                for(int z = 0; z < Blocks.length; z++)
                {
                    Blocks[x][y][z] = modelBuilder.createBox(1f, 1f, 1f, new Material(),
                            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
                    BlocksInstances[x][y][z] = new ModelInstance(Blocks[x][y][z]);
                    BlocksInstances[x][y][z].materials.first().set(TextureAttribute.createDiffuse(new Texture(imageFileHandle)));

                    BlocksInstances[x][y][z].transform.setToTranslation(x * 2, y * 2, z * 2);
                }
            }
        }
    }
    public void renderTerrain()
    {
        for(int x = 0; x < BlocksInstances.length; x++)
        {
            for(int y = 0; y < BlocksInstances.length; y++)
            {
                for(int z = 0; z < BlocksInstances.length; z++)
                {
                    Vector3 vec = new Vector3();
                    if(Player.cam.position.x + 100 >= BlocksInstances[x][y][z].transform.getTranslation(vec).x &&
                            Player.cam.position.x - 100 <= BlocksInstances[x][y][z].transform.getTranslation(vec).x &&
                            Player.cam.position.z + 100 >= BlocksInstances[x][y][z].transform.getTranslation(vec).z &&
                            Player.cam.position.z - 100 <= BlocksInstances[x][y][z].transform.getTranslation(vec).z &&
                            Player.cam.position.y + 50 >= BlocksInstances[x][y][z].transform.getTranslation(vec).y &&
                            Player.cam.position.y - 50 <= BlocksInstances[x][y][z].transform.getTranslation(vec).y)
                    {
                        VoxelGame.modelBatch.render(BlocksInstances[x][y][z], World.environment);
                    }
                }
            }
        }
    }
}
