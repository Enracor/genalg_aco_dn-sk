package ga;

public class TSPFitnessFunc extends FitnessFunction {

	@Override
	public double calculateFitness(Gene gene) {
		return -gene.getWay().getLength();
	}

	@Override
	public double maxFitness(Gene gene) {
		return 0;
	}

}
