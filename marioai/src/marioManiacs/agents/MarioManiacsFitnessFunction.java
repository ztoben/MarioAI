package marioManiacs.agents;

import ch.idsia.tools.EvaluationInfo;

public class MarioManiacsFitnessFunction 
{
	final public int distance = 1;
	final public int win = 1024;
	final public int mode = 32;
	final public int coins = 1000;
	final public int hiddenItems = 24;
	final public int flowerFire = 64;  // not used for now
	final public int kills = 1000;
	final public int killedByFire = 5;
	final public int killedByShell = 5;
	final public int killedByStomp = 5;
	final public int timeLeft = 0;
	final public int hiddenBlocks = 24;

	public interface timeLengthMapping
	{
	    final public static int TIGHT = 10;
	    final public static int MEDIUM = 20;
	    final public static int FLEXIBLE = 30;
	}
	
	public int computeScore(EvaluationInfo results)
	{
		return
				
			results.distancePassedPhys * distance +
	        results.flowersDevoured * flowerFire +
	        results.marioStatus * win +
	        results.marioMode * mode +
	        results.coinsGained * coins +
	        results.killsTotal * kills +
	        results.killsByStomp * killedByStomp +
	        results.killsByFire * killedByFire +
	        results.killsByShell * killedByShell +
	        results.timeLeft * timeLeft;
	}
}
