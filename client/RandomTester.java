package minecraft.net.minecraft.client;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import minecraft.net.minecraft.NotSoRandom;

public class RandomTester {
	
	public void run() throws IOException
	{
		Path filePath = Paths.get("F:\\Minecraft Coding\\b1.2SeedReverser1.1\\MCP-CLEAR\\eclipse\\Client\\bin\\net\\minecraft\\Seeds");
		Scanner scanner = new Scanner(filePath);
		while (scanner.hasNext()) {
			long s = scanner.nextLong();
			s = (s & 1048575) + 8388608;
		}
		
		NotSoRandom nsr = new NotSoRandom(125L);
		
		System.out.println(nsr.nextInt(16));
		System.out.println(nsr.nextInt(16));
		System.out.println(nsr.nextInt(16));
		System.out.println(nsr.nextInt(16));
		
		nsr.setSeed(125L);
		nsr.next2(31);
		System.out.println(nsr.nextInt(16));
		System.out.println(nsr.nextInt(16));
		
	}

}
