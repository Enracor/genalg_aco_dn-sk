package main;

import java.awt.EventQueue;

import utils.Utils;
import aco.ACO;
import aco.Way;
import map.Map;
import map.PheromoneDrawer;
import map.WayDrawer;
import ga.Gene;
import ga.NoneReplicator;
import ga.PartialCrossover;
import ga.Simulator;
import ga.TSPFitnessFunc;
import gui.Conf;
import gui.GUI;
import gui.GUIListener;

public class Controller implements GUIListener {

	private GUI gui;

	public static void main(String[] args) {
		new Controller();
	}

	public Controller() {
		showGUI();
	}

	@Override
	public void onStartTSP(Conf conf) {
		simulate(conf);
	}

	@Override
	public void onStartACO(Conf conf) {
		gui.clearLog();
		Map map = Map.fromFile(conf.file);
		ACO aco = new ACO(conf.aco_ant_count, map, conf.aco_alpha,
				conf.aco_beta, conf.aco_evaporation);

		for (int i = 0; i < conf.aco_nr_of_runs; i++) {
			gui.log("Starte Durchlauf: " + i);
			Way way = aco.next();
			showMapImages(way, map, true);
			gui.log("Length: " + way.getLength());
		}
	}

	private void simulate(Conf conf) {
		// TODO Protect best
		gui.clearLog();
		Map map = Map.fromFile(conf.file);
		Simulator simulator = new Simulator(conf.population_size, map,
				conf.mutation_rate, conf.recombination_rate, true,
				new NoneReplicator(), new PartialCrossover(),
				new TSPFitnessFunc());

		int generations_count;
		for (generations_count = 0; generations_count < conf.max_generations; generations_count++) {
			Gene best = simulator.getBest();
			gui.log(generations_count + ": length: "
					+ best.getWay().getLength());
			showMapImages(best.getWay(), map, false);
			if (simulator.next()) {
				break;
			}
		}
		gui.log("finished after " + generations_count + "generations!");
	}

	private void showGUI() {
		gui = new GUI(this);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private long lastImage = -1;

	private void showMapImages(Way way, Map map, boolean drawPheromones) {
		if (System.currentTimeMillis() - lastImage > 3000 || lastImage == -1) {
			gui.showMap(WayDrawer.draw(way, map, 450));
			if (drawPheromones)
				gui.showPheromoneMap(PheromoneDrawer.draw(map, 450));
			lastImage = System.currentTimeMillis();

		}
	}

}
