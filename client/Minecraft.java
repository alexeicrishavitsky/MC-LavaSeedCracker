package minecraft.net.minecraft.client;

import minecraft.net.minecraft.NotSoRandom;
import minecraft.net.minecraft.src.ChunkPreloader;
import minecraft.net.minecraft.src.NoiseGeneratorOctaves;
import minecraft.net.minecraft.src.WorldChunkManager;
import minecraft.net.minecraft.src.WorldProvider;

public class Minecraft extends Thread
{
	NotSoRandom rand = new NotSoRandom();
	int threadIndex = 0;
	
    public Minecraft(Core mc, long worldCounter, int thread) 
    {
        this.worldCounter = worldCounter;
        this.maxWorlds = mc.wtg/mc.threads;	
        this.uni = seedUniquifier + worldCounter;
        this.threadIndex = thread;
    }
    
    @Override
    public void run()
    {
        cp = new ChunkPreloader(this);
                
        currentmillis = System.nanoTime();
        ins = System.currentTimeMillis();
        
        while(worldsGenerated++ < maxWorlds) 
        {
        	this.nextWorld(uni + worldsGenerated);
        	
        	if(cp.scanForMarkers())
        	{
        		System.out.println(randomSeed);
        	}
        }

        shutdown();
    }
    
    /** Switches the seed */
    void nextWorld(long num)
    {
        rand.setSeed(num); 
    	randomSeed = rand.nextLong();
    }
    
    /** Biome generation stuff */
    public WorldChunkManager getWorldChunkManager()
    {
    	WorldProvider worldProvider = new WorldProvider();
        worldProvider.registerWorld(this);
        return worldProvider.worldChunkMgr;
    }
    
    /** Displays final data */
    void shutdown()
    {
    	long dif = System.currentTimeMillis() - ins;
    	double secs = (double)dif/1000;
    	boolean zec = threadIndex > 9;
    	
        if(zec)
        	System.out.print("Thread #" + threadIndex + " finished in " + secs + " secs. ");
        else
        	System.out.print("Thread #0" + threadIndex + " finished in " + secs + " secs. ");
        	
    	System.out.println('\t'+"Initial world counter value: " + worldCounter);
    }

    long currentmillis = 0;
    long maxWorlds;
    long worldCounter; 
	long uni;
	long worldsGenerated = 0;
    
	private static volatile long seedUniquifier = 8682522807148012L;
    
    public long randomSeed;
    
    public static final int lavaX = -5;
    public static final int lavaZ = -3;
    public static final int waterX = -1;
    public static final int waterZ = -6;													
    							
    public long ins = System.currentTimeMillis();
    
    ChunkPreloader cp;
    
    public final byte[][] lavaPattern = 
    	{
    			{2,2,2,0,0,0,1,0},
    			{2,2,2,0,0,0,1,0},
    			{2,2,2,0,0,0,2,0},
    			{2,2,2,2,2,2,2,2},
    			{2,0,0,0,0,1,0,0},
    		    {2,0,0,0,0,1,0,0},
    			{1,0,0,0,0,0,0,0},
    			{0,1,1,1,0,0,0,0}
    	};

    public final byte[] LAVA_PATTERN =    
    	   {2,2,2,0,0,0,1,0,
			2,2,2,0,0,0,1,0,
			2,2,2,0,0,0,0,0,
			2,2,2,2,2,2,2,2,
			2,0,0,0,0,1,0,0,
		    2,0,0,0,0,1,0,0,
			1,0,0,0,0,0,0,0,
			0,1,1,1,0,0,0,0};
    
}
