package minecraft.net.minecraft.src;

import minecraft.net.minecraft.client.Minecraft;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

public class WorldProvider
{

    public WorldProvider()
    {
    }

    public final void registerWorld(Minecraft world)
    {
        mc = world;
        registerWorldChunkManager();
    }
    
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManager(mc);
    }

    public Minecraft mc;
    public WorldChunkManager worldChunkMgr;
}
