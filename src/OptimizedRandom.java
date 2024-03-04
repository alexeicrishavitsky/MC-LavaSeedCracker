package minecraft.net.minecraft.src;

import java.util.Random;
import java.util.SplittableRandom;


public class OptimizedRandom extends Random {
	
	public long nextSeed()
	{
		return next(0);
	}
	

}
