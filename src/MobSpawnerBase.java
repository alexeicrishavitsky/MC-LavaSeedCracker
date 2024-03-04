package minecraft.net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

public class MobSpawnerBase
{

	public int id = 0;
	
    public MobSpawnerBase()
    {
    }
    
    public MobSpawnerBase(int idIn)
    {
    	id = idIn;
    }

    public static void generateBiomeLookup()
    {
        for(int i = 0; i < 64; i++)
        {
            for(int j = 0; j < 64; j++)
            {
            	biomes[i + j * 64] = getBiome((float)i / 63F, (float)j / 63F).id;
            }

        }
    }

    public static int getBiomeFromLookup(double d, double d1)
    {
        int i = (int)(d * 63D);
        int j = (int)(d1 * 63D);
        return biomes[i + j * 64];
    }

    public static MobSpawnerBase getBiome(float f, float f1)
    {
        f1 *= f;
        if(f < 0.1F)
        {
            return tundra;
        }
        if(f1 < 0.2F)
        {
            if(f < 0.5F)
            {
                return tundra;
            }
            if(f < 0.95F)
            {
                return savanna;
            } else
            {
                return desert;
            }
        }
        if(f1 > 0.5F && f < 0.7F)
        {
            return swampland;
        }
        if(f < 0.5F)
        {
            return taiga;
        }
        if(f < 0.97F)
        {
            if(f1 < 0.35F)
            {
                return shrubland;
            } else
            {
                return forest;
            }
        }
        if(f1 < 0.45F)
        {
            return plains;
        }
        if(f1 < 0.9F)
        {
            return seasonalForest;
        } else
        {
            return rainforest;
        }
    }

    public static final MobSpawnerBase rainforest = (new MobSpawnerBase(0));
    public static final MobSpawnerBase swampland = (new MobSpawnerBase(1));
    public static final MobSpawnerBase seasonalForest = (new MobSpawnerBase(2));
    public static final MobSpawnerBase forest = (new MobSpawnerBase(3));
    public static final MobSpawnerBase savanna = (new MobSpawnerBase(4));
    public static final MobSpawnerBase shrubland = (new MobSpawnerBase(5));
    public static final MobSpawnerBase taiga = (new MobSpawnerBase(6));
    public static final MobSpawnerBase desert = (new MobSpawnerBase(7));
    public static final MobSpawnerBase plains = (new MobSpawnerBase(8));
    public static final MobSpawnerBase iceDesert = (new MobSpawnerBase(9));
    public static final MobSpawnerBase tundra = (new MobSpawnerBase(10));
    public static final MobSpawnerBase hell = (new MobSpawnerBase(11));
    private static int biomes[] = new int[4096];

    static 
    {
        generateBiomeLookup();
    }
}
