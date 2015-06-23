package main;

import java.awt.EventQueue;

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
		map.addCity(0, 0);
		map.addCity(50, 50);
		map.addCity(5, 10);
		map.addCity(20, 60);
		map.addCity(70, 25);
		map.addCity(100, 100);
		map.addPath(50, 50, 20, 60);
		map.addPath(20, 60, 70, 25);
		map.addPath(70, 25, 100, 100);
		gui.showMap(map.getImage(300));
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
