package main;

import java.awt.EventQueue;

import aco.ACO;
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
		// TODO Auto-generated method stub
		gui.log("Start ACO");
		Map map = new Map(100, 100);
		map.addCity(1, 1, 1);
		map.addCity(10, 10, 2);
		map.addCity(50, 50, 3);
		map.addCity(100, 100, 4);
		map.addCity(40, 50, 5);
		map.addCity(20, 70, 6);
		map.createAllPaths();
		new ACO(10, map, 1.0, 5.0).next();
		gui.showMap(WayDrawer.draw(null, map, 300));
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
