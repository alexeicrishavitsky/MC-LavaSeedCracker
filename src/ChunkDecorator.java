package minecraft.net.minecraft.src;

import minecraft.net.minecraft.NotSoRandom;
import minecraft.net.minecraft.client.Minecraft;

public class ChunkDecorator
{
	NotSoRandom rand = new NotSoRandom();
	Minecraft mc;
	WorldGenLakesTest WGLT;
	
	public ChunkDecorator(Minecraft mc)
	{
		this.mc = mc;
		WGLT = new WorldGenLakesTest();
	}
	
	public boolean scanForMarkers()
	{
		return scanForSlimes(717224138).nextInt(10) == 0 // for (3;-7)
				&& hasWater() //Cave water
				&& hasLava(); //Lava + Stone Pattern
	}
	
	
	public NotSoRandom _scanForSlimes(int xPosition, int zPosition)
	{
		
    	long l1 = (long)(xPosition * xPosition * 0x4c1906) + 
        		(long)(xPosition * 0x5ac0db) + 
        		(long)(zPosition * zPosition) * 0x4307a7L + 
        		(long)(zPosition * 0x5f24f) ^ 0x3ad8025fL;
    		
        return new NotSoRandom(mc.randomSeed + l1);
	}
	
    public NotSoRandom scanForSlimes(long l2)
    { 	
        return new NotSoRandom(mc.randomSeed + l2);
    }
    
    public boolean populateTest(int i, int j)
    {
        rand.setSeed(mc.randomSeed);
        long l1 = (rand.nextLong() /2) *2 + 1L;
        long l2 = (rand.nextLong() /2) *2 + 1L;
        rand.setSeed((long)i * l1 + (long)j * l2 ^ mc.randomSeed);
        
        if(rand.nextInt(4) != 0) return false;
        
        int i1 = rand.nextInt(16);
        int l4 = rand.nextInt(128);
        int i8 = rand.nextInt(16);
        
        System.out.println("X = " + i1);
        System.out.println("Y = " + l4);
        System.out.println("Z = " + i8);
        
        return WGLT.generate(mc, rand, i1, l4, i8);
    }
    
    public boolean checkSubterraineanWater(int i, int j) // (-2;-7)
    {
    	rand.setSeed(mc.randomSeed);
        long l1 = (rand.nextLong() /2) *2 + 1L;
        long l2 = (rand.nextLong() /2) *2 + 1L;
        rand.setSeed((long)i * l1 + (long)j * l2 ^ mc.randomSeed);
        
        if(rand.nextInt(4) == 0)
        {
        	int x = rand.nextInt(16);	
    		int y = rand.nextInt(128);
    		
        	return y < 54;
        }
        
    	return false;
    }
	
    
    public boolean hasWater()
    {        
        rand.setSeed(mc.randomSeed);
        long l1 = (rand.nextLong() /2) *2 + 1L;
        long l2 = (rand.nextLong() /2) *2 + 1L;
        rand.setSeed((long)Minecraft.waterX * l1 + (long)Minecraft.waterZ * l2 ^ mc.randomSeed);
        
        if(rand.nextInt(4) == 0)
        {
        	int x = rand.nextInt(16);
        	
        	if(x < 6) return false; 
        	
    		int y = rand.nextInt(128);
    		
        	return y > 62;
        }
        
    	return false;
    }
    
    public boolean replaceBlocksForChosenChunk()
    {
    	ChunkProviderGenerate chunkProvider = new ChunkProviderGenerate(mc.randomSeed);
		return chunkProvider.replaceBlocksForChunk(-5, -5);
    }
    
    public boolean hasLava()
    {
        rand.setSeed(mc.randomSeed);
        long l1 = (rand.nextLong() /2) *2 + 1L;
        long l2 = (rand.nextLong() /2) *2 + 1L;
        rand.setSeed((long)Minecraft.lavaX * l1 + (long)Minecraft.lavaZ * l2 ^ mc.randomSeed);
        
        //Checks if a Water Pool can be generated
        if(rand.nextInt(4) == 0)
        {
        	//Gets the coordinates
        	rand.advance(3);
        	
            int l = rand.nextInt(4) + 4;
            
            //Gets the size
            rand.advance(12*l);
        }
        
        //Generates a Lava Pool
        if(rand.nextInt(8) == 0)
        {
            int dx = rand.nextInt(16);
            
            if(dx > 11) return false;
            if(dx < 2)	return false;
            
            int i4 = rand.nextInt(120) + 8;
            int y = rand.nextInt(i4); 
            
            if(y < 62) return false;
            
            int dz = rand.nextInt(16);
            
            if(dz < 5)  return false; //DZ>=5
            if(dz > 11) return false; //DZ<=11
            
            boolean biomeFlag = false; //Should be forest!
            boolean water_Flag = false; //Unused
            
            boolean cangen = y < 64 || rand.nextInt(10) == 0 ? (new WorldGenLakes()).generate(mc, rand, dx, y, dz) : false;
            
            if(cangen) 
            {
            	WorldChunkManager wcm = mc.getWorldChunkManager();
            	int mobspawnerbase = wcm.getBiomeForCoords((Minecraft.lavaX*16) + 16, (Minecraft.lavaZ*16) + 16);
            	int centralBiome = wcm.getBiomeForCoords(0,0);
            	biomeFlag = mobspawnerbase < 6 && mobspawnerbase != 4 && centralBiome!=6 && centralBiome!=7;
            }
            
            //System.out.println("LAVA");
            
            return cangen && biomeFlag;
            
        }
        
        return false;
    }
    
}
