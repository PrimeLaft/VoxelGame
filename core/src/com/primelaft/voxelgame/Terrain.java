package com.primelaft.voxelgame;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Terrain {
    public Environment environment;
    public Model[][][] Blocks = new Model[10][10][10];
    public ModelInstance[][][] BlocksInstances = new ModelInstance[10][10][10];

    //**************************************************
    // Initialize world
    //**************************************************
    public void InitializeWorld()
    {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
    }
    //**************************************************
    // Generate terrain
    //**************************************************
    public void GenerateTerrain()
    {
        FileHandle imageFileHandle = Gdx.files.internal("grass.jpg");

        ModelBuilder modelBuilder = new ModelBuilder();
        for(int x = 0; x < Blocks.length; x++){
            for(int y = 0; y < Blocks.length; y++)
            {
                for(int z = 0; z < Blocks.length; z++)
                {
                    Blocks[z][x][y] = modelBuilder.createBox(1f, 1f, 1f, new Material(),
                            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
                    BlocksInstances[z][x][y] = new ModelInstance(Blocks[z][x][y]);
                    BlocksInstances[z][x][y].materials.first().set(TextureAttribute.createDiffuse(new Texture(imageFileHandle)));

                    BlocksInstances[z][x][y].transform.setToTranslation(x * 2, y * 2, z * 2);
                }
            }
        }
    }
    //**************************************************
    // Real time render terrain
    //**************************************************
    public void RenderTerrain()
    {
        for(int x = 0; x < BlocksInstances.length; x++)
        {
            for(int y = 0; y < BlocksInstances.length; y++)
            {
                for(int z = 0; z < BlocksInstances.length; z++)
                {
                    Vector3 vec = new Vector3();
                    if(Player.cam.position.x + 200 >= BlocksInstances[z][x][y].transform.getTranslation(vec).x &&
                            Player.cam.position.x - 200 <= BlocksInstances[z][x][y].transform.getTranslation(vec).x &&
                            Player.cam.position.z + 200 >= BlocksInstances[z][x][y].transform.getTranslation(vec).z &&
                            Player.cam.position.z - 200 <= BlocksInstances[z][x][y].transform.getTranslation(vec).z &&
                            Player.cam.position.y + 50 >= BlocksInstances[z][x][y].transform.getTranslation(vec).y &&
                            Player.cam.position.y - 50 <= BlocksInstances[z][x][y].transform.getTranslation(vec).y) {
                        VoxelGame.modelBatch.render(BlocksInstances[z][x][y], environment);

                    }
                }
            }
        }

    }
}
