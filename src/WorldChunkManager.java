package minecraft.net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

import minecraft.net.minecraft.client.Minecraft;

public class WorldChunkManager
{
    
    public WorldChunkManager(Minecraft world)
    {
    	noise_e = new NoiseGeneratorOctaves2(new Random(world.randomSeed * 9871L), 4);
        noise_f = new NoiseGeneratorOctaves2(new Random(world.randomSeed * 39811L), 4);
        noise_g = new NoiseGeneratorOctaves2(new Random(world.randomSeed * 0x84a59L), 2);
    }

    public int getBiomeForCoords(int i, int j)
    {
        temperature = noise_e.generateNoise(temperature, i, j, 0.025D, 0.025D, 0.25D);
        humidity = noise_f.generateNoise(humidity, i, j, 0.05D, 0.05D, 0.33333333333333331D);
        pressure = noise_g.generateNoise(pressure, i, j, 0.25D, 0.25D, 0.58823529411764708D);
        
        double d = pressure[0] * 1.1D + 0.5D;
        double d1 = 0.01D;
        double d2 = 0.99D;
        double d3 = (temperature[0] * 0.15D + 0.7D) * d2 + d * d1;

        d1 = 0.002D;
        d2 = 0.998D;
        double d4 = (humidity[0] * 0.15D + 0.5D) * d2 + d * d1;
        d3 = 1.0D - (1.0D - d3) * (1.0D - d3);
        if(d3 < 0.0D)
        {
            d3 = 0.0D;
        }
            if(d4 < 0.0D)
        {
             d4 = 0.0D;
        }
                if(d3 > 1.0D)
                {
                    d3 = 1.0D;
                }
                if(d4 > 1.0D)
                {
                    d4 = 1.0D;
                }

        return MobSpawnerBase.getBiomeFromLookup(d3, d4);
    }
    
    public double[] getTemperatures(double ad[], int i, int j, int k, int l)
    {
        if(ad == null || ad.length < k * l)
        {
            ad = new double[k * l];
        }
        ad = noise_e.func_4112_a(ad, i, j, k, l, 0.02500000037252903D, 0.02500000037252903D, 0.25D);
        pressure = noise_g.func_4112_a(pressure, i, j, k, l, 0.25D, 0.25D, 0.58823529411764708D);
        int i1 = 0;
        for(int j1 = 0; j1 < k; j1++)
        {
            for(int k1 = 0; k1 < l; k1++)
            {
                double d = pressure[i1] * 1.1000000000000001D + 0.5D;
                double d1 = 0.01D;
                double d2 = 1.0D - d1;
                double d3 = (ad[i1] * 0.14999999999999999D + 0.69999999999999996D) * d2 + d * d1;
                d3 = 1.0D - (1.0D - d3) * (1.0D - d3);
                if(d3 < 0.0D)
                {
                    d3 = 0.0D;
                }
                if(d3 > 1.0D)
                {
                    d3 = 1.0D;
                }
                ad[i1] = d3;
                i1++;
            }

        }

        return ad;
    }
    
    public int[] loadBlockGeneratorData(int abiomegenbase[], int i, int j, int k, int l)
    {
        if(abiomegenbase == null || abiomegenbase.length < k * l)
        {
            abiomegenbase = new int[k * l];
        }
        temperature = noise_e.func_4112_a(temperature, i, j, k, k, 0.02500000037252903D, 0.02500000037252903D, 0.25D);
        humidity = noise_f.func_4112_a(humidity, i, j, k, k, 0.05000000074505806D, 0.05000000074505806D, 0.33333333333333331D);
        pressure = noise_g.func_4112_a(pressure, i, j, k, k, 0.25D, 0.25D, 0.58823529411764708D);
        int i1 = 0;
        for(int j1 = 0; j1 < k; j1++)
        {
            for(int k1 = 0; k1 < l; k1++)
            {
                double d = pressure[i1] * 1.1000000000000001D + 0.5D;
                double d1 = 0.01D;
                double d2 = 1.0D - d1;
                double d3 = (temperature[i1] * 0.14999999999999999D + 0.69999999999999996D) * d2 + d * d1;
                d1 = 0.002D;
                d2 = 1.0D - d1;
                double d4 = (humidity[i1] * 0.14999999999999999D + 0.5D) * d2 + d * d1;
                d3 = 1.0D - (1.0D - d3) * (1.0D - d3);
                if(d3 < 0.0D)
                {
                    d3 = 0.0D;
                }
                if(d4 < 0.0D)
                {
                    d4 = 0.0D;
                }
                if(d3 > 1.0D)
                {
                    d3 = 1.0D;
                }
                if(d4 > 1.0D)
                {
                    d4 = 1.0D;
                }
                temperature[i1] = d3;
                humidity[i1] = d4;
                abiomegenbase[i1++] = MobSpawnerBase.getBiomeFromLookup(d3, d4);
            }

        }

        return abiomegenbase;
    }

    private NoiseGeneratorOctaves2 noise_e;
    private NoiseGeneratorOctaves2 noise_f;
    private NoiseGeneratorOctaves2 noise_g;
    public double temperature[];
    public double humidity[];
    public double pressure[];
}
