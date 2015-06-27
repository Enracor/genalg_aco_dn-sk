package main;

import java.awt.EventQueue;

import aco.ACO;
import map.Map;
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
		new ACO(10, map, 0, 0).next();
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
