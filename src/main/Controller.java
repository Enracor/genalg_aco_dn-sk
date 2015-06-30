package main;

import java.awt.EventQueue;

import utils.Utils;
import aco.ACO;
import aco.Way;
import map.Map;
import map.PheromoneDrawer;
import map.WayDrawer;
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
		// TODO Auto-generated method stub
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onStartACO(Conf conf) {
		gui.log("Start ACO");
		Map map = new Map(100, 100);

		for (int i = 0; i < 100; i++) {
			map.addCity(Utils.randInt(0, 100), Utils.randInt(1, 100), i + 1);
		}

		map.createAllPaths();
		ACO aco = new ACO(conf.aco_ant_count, map, 1.0, 5.0, 0.5);
		for (int i = 0; i < conf.aco_nr_of_runs; i++) {
			gui.log("Starte Durchlauf: " + i);
			Way way = aco.next();
			gui.showMap(WayDrawer.draw(way, map, 450));
			gui.showPheromoneMap(PheromoneDrawer.draw(map, 450));
			gui.log("Length: " + way.getLength());
		}
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

}
