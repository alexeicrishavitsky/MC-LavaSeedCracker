package minecraft.net.minecraft.src;

import minecraft.net.minecraft.NotSoRandom;
import minecraft.net.minecraft.client.Minecraft;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class WorldGenLakes
{
    
    int pattern[][];

    public boolean generate(Minecraft mc, NotSoRandom random, int i, int j, int k)
    {
        int l = random.nextInt(4) + 4;
        
    	pattern = new int[32][32];
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
            	
            for(int i2 = 0; i2 < 16; i2++)
            {
                for(int i3 = 0; i3 < 16; i3++)
                {
                    for(int i4 = 4; i4 < 8; i4++) // i4<8
                    {
                        boolean flag1 = 
                        		!aflag[(i2 * 16 + i3) * 8 + i4] 
                        				&& (i2 < 15 && aflag[((i2 + 1) * 16 + i3) * 8 + i4] 
                        						|| i2 > 0 && aflag[((i2 - 1) * 16 + i3) * 8 + i4] 
                        								|| i3 < 15 && aflag[(i2 * 16 + (i3 + 1)) * 8 + i4] 
                        										|| i3 > 0 && aflag[(i2 * 16 + (i3 - 1)) * 8 + i4] 
                        												|| i4 < 7 && aflag[(i2 * 16 + i3) * 8 + (i4 + 1)] 
                        														|| i4 > 0 && aflag[(i2 * 16 + i3) * 8 + (i4 - 1)]);
                        //Если оезро глубиной в два блока, то узор берем с 5-го уровня
                        if(flag1 && (random.nextInt(2) != 0))
                        {
	                        if(i4 == 4)
	                        {
	                        	pattern[k+i3][i+i2] = 1;
	                        }
                        }
                    }

                }

            }
            
            if(compareWithPattern(13, 11, mc))
            {
            	//_printPattern(0, 0);
            	return true;
            }

        return false;
    }
    
    void _printPattern(int i, int k)
    {
        for(int i2 = 0; i2 < 32; i2++)
        {
        	//System.out.print("{");
            for(int i3 = 0; i3 < 32; i3++)
            {
            	if(i2 >= 0 && i3 >= 0)
            	{
            		System.out.print(pattern[i2][i3] == 1 ? "X " : ". ");
            		//System.out.print(pattern[i2][i3] + ",");
            	}
            	//else System.out.print(i3+i+" ");
            }
            //System.out.print("},");
            System.out.println();
        }
        System.out.println();
    }
    
    boolean compareWithPattern(int i, int j, Minecraft mc)
    {
    	//int flags = 0;
    	for(int i0 = 0; i0 < 8; i0 ++)
    	{
    		for(int j0 = 0; j0 < 8; j0 ++)
    		{
    			int val = mc.LAVA_PATTERN[i0*8 + j0];
    			if(val == 2 || pattern[i + i0][j + j0] == val)
    			{
    				//flags ++;
    			}
    			else return false;
    		}
    	}
    	
    	return true;
    }
        
}
