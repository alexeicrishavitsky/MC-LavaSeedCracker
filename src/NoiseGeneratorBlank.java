package minecraft.net.minecraft.src;

import java.util.Random;

import minecraft.net.minecraft.NotSoRandom;

public class NoiseGeneratorBlank {

    public NoiseGeneratorBlank(NotSoRandom random)
    {
    	for(int i = 0; i < 6; i++)
    	{
    		random.nextSeed();
    	}

        for(int j = 0; j < 256; j++)
        {
        	random.nextSeed();
        }
    }
    
    public NoiseGeneratorBlank(NotSoRandom random, int i)
    {
        for(int j = 0; j < i; j++)
        {
            new NoiseGeneratorBlank(random);
        }

    }
}
