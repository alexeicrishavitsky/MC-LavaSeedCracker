package minecraft.net.minecraft.src;

import minecraft.net.minecraft.NotSoRandom;
import minecraft.net.minecraft.client.Minecraft;

public class WorldGenLakesTest {
	
	int matrix[][];
	final int pattern[][] = 	
		{		{2,2,2,0,0,2,2,2},
				{0,0,2,1,1,2,2,2},
				{0,0,2,0,0,2,2,2},
				{0,0,2,0,0,1,2,2},
				{0,0,2,0,1,1,2,2},
				{2,1,2,1,0,1,2,2},
				{2,1,2,2,2,2,2,2},
				{2,1,1,1,2,2,2,2}
		};
	
	final byte PATTERN[] = 
		{		2,2,2,0,0,2,2,2,
				0,0,2,1,1,2,2,2,
				0,0,2,0,0,2,2,2,
				0,0,2,0,0,1,2,2,
				0,0,2,0,1,1,2,2,
				2,1,2,1,0,1,2,2,
				2,1,2,2,2,2,2,2,
				2,1,1,1,2,2,2,2
		};
	
	final byte _PATTERN[] = 
		{		2,2,2,2,2,2,2,2,
				2,0,0,0,0,0,0,2,
				2,0,1,0,0,1,0,2,
				2,0,0,0,0,0,0,2,
				2,0,1,1,1,1,0,2,
				2,0,0,1,1,0,0,2,
				2,0,0,0,0,0,0,2,
				2,2,2,2,2,2,2,2
		};
			
	public boolean generate(Minecraft mc, NotSoRandom random, int i, int j, int k)
    {
		
        int l = random.nextInt(4) + 4;
        
        matrix = new int[32][32];
    	boolean aflag[] = new boolean[2048];
        
        for(int i1 = 0; i1 < l; i1++)
        {
            double d = random.nextDouble() * 6D + 3D;
            double d1 = random.nextDouble() * 4D + 2D;
            double d2 = random.nextDouble() * 6D + 3D;
            double d3 = random.nextDouble() * (16D - d - 2D) + 1.0D + d / 2D;
            double d4 = random.nextDouble() * (8D - d1 - 4D) + 2D + d1 / 2D;
            double d5 = random.nextDouble() * (16D - d2 - 2D) + 1.0D + d2 / 2D;
            for(int j4 = 1; j4 < 15; j4++)
            {
                for(int k4 = 1; k4 < 15; k4++)
                {
                    for(int l4 = 1; l4 < 7; l4++) // l4<8
                    {
                        double d6 = ((double)j4 - d3) / (d / 2D);
                        double d7 = ((double)l4 - d4) / (d1 / 2D);
                        double d8 = ((double)k4 - d5) / (d2 / 2D);
                        double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                        if(d9 < 1.0D)
                        {
                            aflag[(j4 * 16 + k4) * 8 + l4] = true;
                        }
                    }

                }

            }

        }
            	
        for(int k1 = 0; k1 < 16; k1++)
        {
            for(int k2 = 0; k2 < 16; k2++)
            {
                for(int k3 = 0; k3 < 8; k3++)
                {
                    if(aflag[(k1 * 16 + k2) * 8 + k3])
                    {
                    	if(k3 == 3)
                    		matrix[k2+k][k1+i] = 1;
                    }
                }

            }

        }  
   
        //if(compareWithPattern(7,17))
        {
        	printPattern();
        	//return true;
        }

        return false;
    }
	
    void printPattern()
    {
        for(int i2 = 0; i2 < 32; i2++)
        {
            for(int i3 = 0; i3 < 32; i3++)
            {
            	System.out.print(matrix[i2][i3] == 1 ? "X " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    boolean compareWithPattern(int i, int j)
    {
    	int flags = 0;
    	for(int i0 = 0; i0 < 8; i0 ++)
    	{
    		for(int j0 = 0; j0 < 8; j0 ++)
    		{
    			int val = PATTERN[i0*8+j0];
    			if(val == 2 || matrix[i + i0][j + j0] == val)
    			{
    				flags ++;
    			}
    			else return false;
    		}
    	}
    	
    	return flags == 64;
    }

}
