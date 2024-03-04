package minecraft.net.minecraft.client;

import java.util.Random;

import minecraft.net.minecraft.NotSoRandom;

public class Core {
	
	long B = 1000000000L;
	public long wtg = 10 *B;
	public final int threads = 14;
	long day = 86400*B;
	
	public void run()
	{
		long cc = 0; 
		
		for(int i = 0; i < threads; i++)
		{
			(new Minecraft(this, cc + (long)((wtg/(long)threads) * i), i)).start();
		}
	}
	
	/** Just testings */
	
	void testSlimeChunks()
	{
		int xPosition = 3;
		int zPosition = -7;
		
		long l2 = (long)(xPosition * xPosition * 0x4c1906) + 
        		(long)(xPosition * 0x5ac0db) + 
        		(long)(zPosition * zPosition) * 0x4307a7L + 
        		(long)(zPosition * 0x5f24f) ^ 0x3ad8025fL;
		
		System.out.println(l2);
	}
	
	void testRandom()
	{
		NotSoRandom rnd = new NotSoRandom(12345L);
		System.out.println(rnd.nextLong());
		
		Random rnd2 = new Random(12345L);
		System.out.println();
		
		System.out.println(rnd2.nextLong());
	}
	
	void testSandAnomalies()
	{
		Minecraft mc = new Minecraft(this, 0, 1);
		
		int cx = -40;
		int cz = 2;
		int x = 11; 
		int z = 14;
		
		for(int j = 0; j < 2; j++)
		{
			for(int i = 0; i < 4; i++)
			{
				testBlockHeight(cx, cz, x+i, z+j);
			}
			System.out.println();
		}
	}
	
    public void testBlockHeight(int cx, int cz, int x, int z)
    {  	
    	NotSoRandom dirtRnd = new NotSoRandom();
    	dirtRnd.setSeed((long)cx * 0x4f9939f508L + (long)cz * 0x1ef1565bd5L);
    	
    	for(int k = 0; k < 16; k++)
        {
            for(int l = 0; l < 16; l++)
            {
            	dirtRnd.nextDouble();
            	dirtRnd.nextDouble();
            	double dirtHeight = (dirtRnd.nextDouble() * 0.25);
            	if(k == z && l == x)
            	{
            		System.out.println("H=" + dirtHeight);
            	}
            	
            	for(int k1 = 127; k1 >= 0; k1--)
                {
            		dirtRnd.nextInt();
                }
            }
        }
    	
    }
}
