package trains.entities.trains;

import net.minecraft.client.audio.ISound;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import trains.TrainsInMotion;
import trains.entities.EntityTrainCore;
import trains.items.trains.ItemFirstTrain;
import trains.models.tmt.Vec3d;
import trains.registry.TrainRegistry;
import trains.registry.URIRegistry;
import trains.utility.LiquidManager;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static trains.TrainsInMotion.MODID;

public class FirstTrain extends EntityTrainCore {
    /**
     * <h2>Basic Train Constructor</h2>
     * To make your own custom train, create a new class that is a copy of this class, in that copy, you only need to change the values of private static final variables defined here.
     * You also have to make your own item class, for that
     * @see ItemFirstTrain
     * lastly you have to register them in
     * @see TrainRegistry#listTrains()
     *
     * The fluid tank has 2 values, one for water/RF/fuel/uranium and another for steam.
     * SOUND_HORN and SOUND_RUNNING are refrences to the sound files for the train horn and running sounds.
     * thisItem is the item for this train that will get registered.
     */
    private LiquidManager tank = new LiquidManager(10000,10000, new Fluid[]{FluidRegistry.WATER},new Fluid[]{FluidRegistry.WATER},true,true);
    private static final ResourceLocation horn = URIRegistry.SOUND_HORN.getResource("h080brigadelok.ogg");
    private static final ResourceLocation running = URIRegistry.SOUND_RUNNING.getResource("r080brigadelok.ogg");
    public static final Item thisItem = new ItemFirstTrain().setUnlocalizedName("itemFirstTrain").setTextureName(MODID + ":itemTests");

    /**
     * these basic constructors only need to have their names changed to that of this class, that is assuming your editor doesn't automatically do that.
     * @see EntityTrainCore
     */
    public FirstTrain(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }
    public FirstTrain(World world){
        super(world);
    }

    /**
     * <h1>Train Variable Overrides</h1>
     * We override the functions defined in the super here, to give them different values.
     * This is more efficient than having to store them in the super because we won't have to store them in the NBT.
     */


    /**
     * <h2>Max speed</h2>
     * @return the value of the max speed in blocks per second
     */
    @Override
    public float getMaxSpeed(){return 0.9834875f;}
    /**
     * <h2>name</h2>
     * @return the name of the train, this is used for the inventory name.
     */
    @Override
    public String getName() {return "0-8-0 Brigadelok";}
    /**
     * <h2>Bogie Offset</h2>
     * @return the list of offsets for the bogies, 0 being the center of the train. negative values are towards the front of the train.
     */
    @Override
    public List<Float> getBogieOffsets(){return  Arrays.asList(-0.75f, 0.75f);}
    /**
     * <h2>Inventory Size</h2>
     * @return the size of the inventory not counting any crafting/fuel slots.
     */
    @Override
    public TrainsInMotion.inventorySizes getInventorySize(){return TrainsInMotion.inventorySizes.THREExTHREE;}
    /**
     * <h2>Train Type</h2>
     * @return the type of train, defines a number of things like fuel management, GUI style, and animations.
     */
    @Override
    public TrainsInMotion.transportTypes getType(){return TrainsInMotion.transportTypes.STEAM;}
    /**
     * <h2>Max Fuel</h2>
     * @return the max fuel the train can store.
     * @see trains.utility.FuelHandler for more information on fuel consumption
     */
    @Override
    public int getMaxFuel(){return 12800;}
    /**
     * <h2>Rider offset</h2>
     * @return defines the offset of the rider in blocks, the first value is how far back, and the second is how high.
     */
    @Override
    public float[] getRiderOffset(){return new float[]{1.3f,1.1f};}
    /**
     * <h2>Acceleration</h2>
     * @return defines the acceleration that is applied to the train in blocks per second.
     */
    @Override
    public float getAcceleration(){return 0.1f;}
    /**
     * <h2>Hitbox offsets</h2>
     * @return defines the positions for the hitboxes in blocks. 0 being the center of the train, negative values being towards the front..
     */
    @Override
    public float[] getHitboxPositions(){return new float[]{-1.15f,0f,1.15f};}
    /**
     * <h2>Lamp offset</h2>
     * @return defines the offset for the lamp in blocks.
     * negative X values are towards the front of the train.
     * a Y value of less than 2 will register the lamp as null (this is to allow for null lamps, and to prevent from breaking the rails the train is on).
     */
    @Override
    public Vec3d getLampOffset(){return new Vec3d(-3.5,2,0);}
    /**
     * <h2>Crafting recipe</h2>
     * @return the list of items used to make the train, the items go from left to right, and then from top row to bottom.
     * must contain 9 values, with or without nulls.
     */
    @Override
    public ItemStack[] getCraftingRecipe(){return new ItemStack[]{
            null,null,null,
            null,null,null,
            null,null,null,
    };}
    /**
     * <h2>Lamp offset</h2>
     * @return defines the radius in microblocks for the piston rotation. Usually only used for steam
     */
    @Override
    public float getPistonOffset(){return 0.5f;}
    /**
     * <h2>Smoke offset</h2>
     * @return defines the array of positions in blocks for smoke.
     * the first number in the position defines the X/Z axis, negative values are towards the front of the train.
     * the second number defined the Y position, 0 is the rails.
     * the third number defines left to right.
     * the forth number defines the color from 255 (white) to 0 (black)
     * the 5th number is for density, there's no min/max but larger numbers will create more lag.
     */
    @Override
    public float[][] getSmokeOffset(){return new float[][]{{-1.40f,2,0,0.2f,12},{-1,0,0.5f,0.9f,1},{-1,0,-0.5f,0.9f,1}};}



    /**
     * <h2>pre-asigned values</h2>
     * These return values defined from the top of the class.
     * These should only need modification for advanced users.
     */
    @Override
    public LiquidManager getTank(){return tank;}
    @Override
    public Item getItem(){
        return thisItem;
    }
    @Override
    public ISound getHorn(){
        return new ISound() {
            @Override
            public ResourceLocation getPositionedSoundLocation() {
                return horn;
            }

            @Override
            public boolean canRepeat() {
                return false;
            }

            @Override
            public int getRepeatDelay() {
                return 0;
            }

            @Override
            public float getVolume() {
                return 100;
            }

            @Override
            public float getPitch() {
                return 0;
            }

            @Override
            public float getXPosF() {
                return (float) posX;
            }

            @Override
            public float getYPosF() {
                return (float) posY;
            }

            @Override
            public float getZPosF() {
                return (float) posZ;
            }

            @Override
            public AttenuationType getAttenuationType() {
                return null;
            }
        };
    }
    @Override
    public ISound getRunning(){
        return new ISound() {
            @Override
            public ResourceLocation getPositionedSoundLocation() {
                return running;
            }

            @Override
            public boolean canRepeat() {
                return true;
            }

            @Override
            public int getRepeatDelay() {
                return 0;
            }

            @Override
            public float getVolume() {
                return 100;
            }

            @Override
            public float getPitch() {
                return 0;
            }

            @Override
            public float getXPosF() {
                return (float) posX;
            }

            @Override
            public float getYPosF() {
                return (float) posY;
            }

            @Override
            public float getZPosF() {
                return (float) posZ;
            }

            @Override
            public AttenuationType getAttenuationType() {
                return null;
            }
        };
    }
}
