package minecraft.net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class Chunk
{

    public Chunk(byte abyte0[])
    {
        blocks = abyte0;
    }

    public int getBlockID(int i, int j, int k)
    {
        return blocks[i << 11 | k << 7 | j];
    }

    public boolean setBlockID(int i, int j, int k, int l)
    {
        byte byte0 = (byte)l;
        int j1 = blocks[i << 11 | k << 7 | j] & 0xff;
        if(j1 == l)
        {
            return false;
        }
        blocks[i << 11 | k << 7 | j] = byte0;
        return true;
    }

    public MobSpawnerBase biome;
    public byte blocks[];
    public boolean dun = false;
    public boolean lava = false;
    public boolean wat = false;
    public boolean sand = false;
}
