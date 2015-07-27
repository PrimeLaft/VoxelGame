package com.voxelleaf.game;


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
    public Model[] Blocks = new Model[700];
    public ModelInstance[] BlocksInstances = new ModelInstance[700];

    //****************************************
    // Initialize world
    //****************************************
    public void InitializeWorld()
    {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
    }
    //****************************************
    // Generate terrain
    //****************************************
    public void GenerateTerrain()
    {
        FileHandle imageFileHandle = Gdx.files.internal("grass.jpg");

        ModelBuilder modelBuilder = new ModelBuilder();
        for(int i = 0; i < Blocks.length; i++)
        {
            Blocks[i] = modelBuilder.createBox(2f, 2f, 2f, new Material(),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
            BlocksInstances[i] = new ModelInstance(Blocks[i]);
            BlocksInstances[i].materials.first().set(TextureAttribute.createDiffuse(new Texture(imageFileHandle)));

            BlocksInstances[i].transform.setToTranslation(i * 2, 0, 0f);




			/*//10% terrain
			if(i < (Blocks.length / 100)*10 && i < (Blocks.length / 100)*20)
			{
				BlocksInstances[i].transform.setToTranslation(i * 2, 0, 0f);
			}
			//20% terrain
			else if(i <= (Blocks.length / 100)*20 && i < (Blocks.length / 100)*30)
			{
				BlocksInstances[i].transform.setToTranslation(0, 0, (i - (Blocks.length / 100)*10) * 2);
			}
			//30% terrain
			else if(i <= (Blocks.length / 100)*30 && i < (Blocks.length / 100)*40)
			{
				BlocksInstances[i].transform.setToTranslation((i - (Blocks.length / 100)*20) * 2, 0, 2f);
			}
			//40% terrain
			else if(i <= (Blocks.length / 100)*40 && i < (Blocks.length / 100)*50)
			{
				BlocksInstances[i].transform.setToTranslation(2f, 0, (i - (Blocks.length / 100)*30) * 2);
			}
			//50% terrain
			else if(i <= (Blocks.length / 100)*50 && i < (Blocks.length / 100)*60)
			{
				BlocksInstances[i].transform.setToTranslation((i - (Blocks.length / 100)*40) * 2, 0, 4f);
			}
			//60% terrain
			else if(i <= (Blocks.length / 100)*60 && i < (Blocks.length / 100)*70)
			{
				BlocksInstances[i].transform.setToTranslation(4f, 0, (i - (Blocks.length / 100)*50) * 2);
			}
			//70% terrain
			else if(i <= (Blocks.length / 100)*70 && i < (Blocks.length / 100)*80)
			{
				BlocksInstances[i].transform.setToTranslation((i - (Blocks.length / 100)*60) * 2, 0, 6f);
			}
			//80% terrain
			else if(i <= (Blocks.length / 100)*80 && i < (Blocks.length / 100)*90)
			{
				BlocksInstances[i].transform.setToTranslation(6f, 0, (i - (Blocks.length / 100)*70) * 2);
			}
			//90% terrain
			else if(i <= (Blocks.length / 100)*90 && i < (Blocks.length / 100)*100)
			{
				BlocksInstances[i].transform.setToTranslation((i - (Blocks.length / 100)*80) * 2, 0, 8f);
			}
			//100% terrain
			else if(i <= (Blocks.length / 100)*100 && i <= (Blocks.length / 100)*100)
			{
				BlocksInstances[i].transform.setToTranslation(8f, 0, (i - (Blocks.length / 100)*90) * 2);
			}*/


        }
    }
    //****************************************
    // Real time render terrain
    //****************************************
    public void RenderTerrain()
    {
        for(int i = 0; i < BlocksInstances.length; i++)
        {
            Vector3 vec = new Vector3();
            if(Player.cam.position.x + 50 >= BlocksInstances[i].transform.getTranslation(vec).x &&
                    Player.cam.position.x - 50 <= BlocksInstances[i].transform.getTranslation(vec).x &&
                    Player.cam.position.z + 50 >= BlocksInstances[i].transform.getTranslation(vec).z &&
                    Player.cam.position.z - 50 <= BlocksInstances[i].transform.getTranslation(vec).z &&
                    Player.cam.position.y + 10 >= BlocksInstances[i].transform.getTranslation(vec).y &&
                    Player.cam.position.y - 10 <= BlocksInstances[i].transform.getTranslation(vec).y)
            {
                VoxelLeaf.modelBatch.render(BlocksInstances[i], environment);
            }
        }
    }
}
