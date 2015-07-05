package ga;

import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Utils {
	private static Random rand = new Random();
	// Executor
	public static ExecutorService executorService = Executors
			.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	public static CompletionService<?> executor = new ExecutorCompletionService<>(
			executorService);

	/**
	 * Zufallswert zwischen min und max (exklusiv)
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randInt(int min, int max) {
		return rand.nextInt((max - min)) + min;
	}

	public static double randDouble(double min, double max) {
		return min + (max - min) * rand.nextDouble();
	}

	public static boolean randBool(double probability) {
		return rand.nextDouble() <= probability;
	}

	public static void waitForTasks(int task_count) {
		for (int i = 0; i < task_count; i++) {
			try {
				executor.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
