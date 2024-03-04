package minecraft.net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

import minecraft.net.minecraft.NotSoRandom;
import minecraft.net.minecraft.client.Minecraft;

public class ChunkProviderGenerate
{
	private long seed;
	
    public ChunkProviderGenerate(long l)
    {
        seed = l;
        rand = new NotSoRandom();
        rand.setSeed(seed);
        sandNoise = new double[256];
    }
    
    public void initNoises()
    {
    	rand.setSeed(seed);
    	field_912_k = new NoiseGeneratorBlank(rand, 16);
        field_911_l = new NoiseGeneratorBlank(rand, 16);
        field_910_m = new NoiseGeneratorBlank(rand, 8);
        sandGenerator = new NoiseGeneratorOctaves(rand, 4);
    }

    public boolean replaceBlocksForBiome(int i, int j, byte abyte0[])
    {
        double d = 0.03125D;
        sandNoise = sandGenerator.generateNoiseOctaves(sandNoise, i * 16, j * 16, 0.0D, 16, 16, 1, d, d, 0.0D);
        return sandNoise[0] > 0.0D;
    }
    
    public boolean replaceBlocksForChunk(int i, int j)
    {
    	initNoises();
    	double d = 0.03125D;
    	rand.setSeed((long)i * 0x4f9939f508L + (long)j * 0x1ef1565bd5L);
    	
    	sandNoise = sandGenerator.generateNoiseOctaves(sandNoise, i * 16, j * 16, 0.0D, 16, 16, 1, d, d, 0.0D);
    	
    	int sandPattern[][] = new int[16][16];
    	
    	for(int i0 = 0; i0 < 16; i0 ++)
    	{
    		for(int j0 = 0; j0 < 16; j0 ++)
    		{
    			double val = sandNoise[i0 + j0 * 16] + rand.nextDouble() * 0.2D;
    			sandPattern[i0][j0] = val > 0.0D ? 1 : 0;
    			rand.nextDouble();
    			rand.nextDouble();
    			for(int k1 = 127; k1 >= 0; k1--)
    			{
    				rand.nextInt(5);
    			}
    		}
    	}
    	
    	//printPattern(sandPattern);
    	
    	return conjunction(sandPattern);
    }
    
    boolean conjunction(int sandPattern[][])
    {
    	int flags = 0;
    	for(int i0 = 8; i0 < 16; i0 ++)
    	{
    		for(int j0 = 0; j0 < 16; j0 ++)
    		{
    			
    			if(sandPattern[j0][i0] == pattern[i0][j0] || pattern[i0][j0] == -1)
    			{
    				flags++;
    			}
    			
    		}
    	}
    	
    	return flags > 125;
    }
    
    void printPattern(int sandPattern[][])
    {
    	for(int i0 = 0; i0 < 16; i0 ++)
    	{
    		for(int j0 = 0; j0 < 16; j0 ++)
    		{
    			System.out.print(sandPattern[j0][i0] == 1 ? "1, " :  "0, ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    }
    
    

    private NoiseGeneratorBlank field_912_k;
    private NoiseGeneratorBlank field_911_l;
    private NoiseGeneratorBlank field_910_m;
    private NoiseGeneratorOctaves sandGenerator;
    //private NoiseGeneratorOctaves field_908_o;
    public NoiseGeneratorOctaves oA;
    public NoiseGeneratorOctaves oB;
    double field_4185_d[];
    double field_4184_e[];
    double field_4183_f[];
    double field_4182_g[];
    double field_4181_h[];
    protected NotSoRandom rand;
    private double sandNoise[];
    private double heightNoise[];
    private double generatedTemperatures[];
    private int biomesForGeneration[];
    private double field_4180_q[];
    
    private NoiseGeneratorOctaves field_908_o;
    public NoiseGeneratorOctaves field_922_a;
    public NoiseGeneratorOctaves field_921_b;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    
    public final byte[][] _pattern =
    	{
    			{0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1}, 
    			{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
    			{1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
    			{0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1}, 
    			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1}, 
    			{0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1}, 
    			{0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1}, 
    			{0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1}, 
    			{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 
    			{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1}, 
    			{0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1}, 
    			{0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1}, 
    			{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1}, 
    			{1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1}, 
    			{0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1}, 
    			{1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1}
    	};
    
    public final byte[][] pattern =
    	{
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 0,-1, 0, 0, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,-1, 0, 0, 0, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0,-1,-1, 0}, 
    			{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0}
    	};
}
