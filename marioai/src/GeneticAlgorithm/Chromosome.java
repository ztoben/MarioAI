package GeneticAlgorithm;

import java.io.FileWriter;
import java.io.IOException;

public class Chromosome {
	public float[] chromosome;
	
	public Chromosome(float[] child){
		chromosome = child;
	}

	public void chromosomeToString(){
		for (int i = 0; i < chromosome.length;i++){
			System.out.print(chromosome[i] + ", ");
		}
	}

	public void chromosomeToFile() throws IOException{
		FileWriter writer = new FileWriter(System.getProperty("user.dir") + "//src//bestChromo.csv");		
		for (int i = 0; i < chromosome.length;i++){
			if (i==chromosome.length-1){
				writer.append(Float.toString(chromosome[i]));
			}
			else{
				writer.append(Float.toString(chromosome[i])+ ",");
			}
		}
		writer.flush();
		writer.close();
	}

}


