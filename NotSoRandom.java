package minecraft.net.minecraft;

import kaptainwutax.seedutils.lcg.LCG;

public class NotSoRandom {
	
    private final static long multiplier = 0x5DEECE66DL;
    private final static long addend = 0xBL;
    private final static long mask = (1L << 48) - 1;
    public long seed;
    
    public void setSeed(long seed) {
        this.seed = (seed ^ multiplier) & mask;
    }
    
    public void setSeedNoMask(long seed)
    {
    	this.seed = seed;
    }
    
    public long nextLong() {
        return ((long)(next(32)) << 32) + next(32);
    }
    
    public NotSoRandom(long seed) {
        this.seed = 0L;
        setSeed(seed);
    }
    
    public NotSoRandom() {
        setSeed(1);
    }
    
    public int nextInt(int n) {
        if ((n & -n) == n)  // i.e., n is a power of 2
            return (int)((n * (long)next(31)) >> 31);

        int bits = next(31);
        return bits % n;
    }
    
    public int fastNextInt(int bound)
    {
    	int r = next(31);
    	int m = bound - 1;
    	if((bound & m) == 0)
    	{
    		r = (int)((bound * (long)r) >> 31);
    	}
    	else
    	{
    		r %= bound;
    	}
    	
    	return r;
    }
    
	public void advance(long calls) {
		this.advance(LCG.JAVA.combine(calls));
	}

	public void advance(LCG skip) {
		this.seed = skip.nextSeed(this.seed);
	}
    
    public int next(int bits) {
	    long nextseed = (this.seed * multiplier + addend) & mask;
	    this.seed = nextseed;
        return (int)(nextseed >>> (48 - bits));
    }
    
    public int next32()
    {
	    long nextseed = (this.seed * multiplier + addend) & mask;
	    this.seed = nextseed;
        return (int)(nextseed >>> 16);
    }
    
    public int next2(int bits)
    {
    	long a = multiplier;
    	long b = addend;
        long oldseed, nextseed;
        long seed = this.seed;
	    oldseed = seed;
	    nextseed = (oldseed * (a + b) + a*a + 2*a + b*b) & mask;
	    this.seed = nextseed;
        return (int)(nextseed >>> (48 - bits));
    }
    
    public int nextInt() 
    {
    	return next(32);
    }
    
    public double nextDouble() 
    {
        return (((long)(next(26)) << 27) + next(27))
	    / (double)(1L << 53);
    }
    
	public long nextSeed()
	{
		return next(0);
	}

}
