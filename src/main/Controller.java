package main;

import java.awt.EventQueue;

import utils.Utils;
import aco.ACO;
import aco.Way;
import map.Map;
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

		for (int i = 0; i < 50; i++) {
			map.addCity(Utils.randInt(0, 100), Utils.randInt(1, 100), i + 1);
		}

		map.createAllPaths();
		ACO aco = new ACO(10, map, 1.0, 5.0, 0.5);
		for (int i = 0; i < 100; i++) {
			gui.log("Starte Durchlauf: " + i);
			Way way = aco.next();
			gui.showMap(WayDrawer.draw(way, map, 500));
			
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
