package gui;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import jdk.nashorn.internal.ir.CallNode.EvalArgs;
import javax.swing.SwingConstants;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_file;
	private JTextArea txt_log;
	private GUIListener listener;
	private JTextField txt_genecount;
	private JTextField txt_mutationrate;
	private JTextField txt_aco_antcount;
	private JTextField txt_recombinationrate;
	private JLabel lbl_map;
	private JLabel lbl_pmap;
	private JTextField txt_aco_runs;
	private JTextField txt_alpha;
	private JTextField txt_beta;
	private JTextField txt_evaporation;
	private JTextField txt_maxgener;

	/**
	 * Fügt dem Ausgabe-Bereich eine Nachricht in einer neuen Zeile hinzu.
	 * 
	 * @param message
	 *            Nachricht, die hinzugefügt werden soll.
	 */
	public void log(String message) {
		txt_log.setText(txt_log.getText() + "\n" + message);
	}

	/**
	 * Leert den Ausgabe-Bereich.
	 */
	public void clearLog() {
		txt_log.setText("");
	}

	public void showMap(BufferedImage mapImg) {
		lbl_map.setIcon(new ImageIcon(mapImg));
		lbl_map.setText("");
	}

	public void showPheromoneMap(BufferedImage mapImg) {
		lbl_pmap.setIcon(new ImageIcon(mapImg));
		lbl_pmap.setText("");
	}

	/**
	 * Create the frame.
	 */
	public GUI(GUIListener listener) {
		this.listener = listener;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblAllgemein = new JLabel("Allgemein");
		lblAllgemein.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblAllgemein = new GridBagConstraints();
		gbc_lblAllgemein.anchor = GridBagConstraints.WEST;
		gbc_lblAllgemein.insets = new Insets(0, 0, 5, 5);
		gbc_lblAllgemein.gridx = 0;
		gbc_lblAllgemein.gridy = 0;
		contentPane.add(lblAllgemein, gbc_lblAllgemein);

		JLabel lblFile = new JLabel("Datei");
		GridBagConstraints gbc_lblFile = new GridBagConstraints();
		gbc_lblFile.anchor = GridBagConstraints.EAST;
		gbc_lblFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblFile.gridx = 0;
		gbc_lblFile.gridy = 1;
		contentPane.add(lblFile, gbc_lblFile);

		txt_file = new JTextField();
		txt_file.setHorizontalAlignment(SwingConstants.LEFT);
		txt_file.setText("map_200.txt");
		GridBagConstraints gbc_txt_file = new GridBagConstraints();
		gbc_txt_file.insets = new Insets(0, 0, 5, 5);
		gbc_txt_file.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_file.gridx = 1;
		gbc_txt_file.gridy = 1;
		contentPane.add(txt_file, gbc_txt_file);
		txt_file.setColumns(10);

		JLabel lblKarte = new JLabel("Karte");
		lblKarte.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblKarte = new GridBagConstraints();
		gbc_lblKarte.insets = new Insets(0, 0, 5, 5);
		gbc_lblKarte.gridx = 2;
		gbc_lblKarte.gridy = 0;
		contentPane.add(lblKarte, gbc_lblKarte);

		lbl_map = new JLabel("lbl_map");
		GridBagConstraints gbc_lbl_map = new GridBagConstraints();
		gbc_lbl_map.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_map.gridwidth = 10;
		gbc_lbl_map.gridheight = 15;
		gbc_lbl_map.gridx = 2;
		gbc_lbl_map.gridy = 1;
		contentPane.add(lbl_map, gbc_lbl_map);

		JLabel lblPheromonkarte = new JLabel("Pheromon-Karte");
		GridBagConstraints gbc_lblPheromonkarte = new GridBagConstraints();
		gbc_lblPheromonkarte.insets = new Insets(0, 0, 5, 5);
		gbc_lblPheromonkarte.gridx = 12;
		gbc_lblPheromonkarte.gridy = 0;
		contentPane.add(lblPheromonkarte, gbc_lblPheromonkarte);

		lbl_pmap = new JLabel("");
		GridBagConstraints gbc_lbl_pmap = new GridBagConstraints();
		gbc_lbl_pmap.gridwidth = 10;
		gbc_lbl_pmap.gridheight = 15;
		gbc_lbl_pmap.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_pmap.gridx = 12;
		gbc_lbl_pmap.gridy = 1;
		contentPane.add(lbl_pmap, gbc_lbl_pmap);

		JLabel lblTsp = new JLabel("TSP");
		lblTsp.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblTsp = new GridBagConstraints();
		gbc_lblTsp.anchor = GridBagConstraints.WEST;
		gbc_lblTsp.insets = new Insets(0, 0, 5, 5);
		gbc_lblTsp.gridx = 0;
		gbc_lblTsp.gridy = 3;
		contentPane.add(lblTsp, gbc_lblTsp);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> listener.onStartTSP(getConf())).start();
			}
		});

		JLabel lblGenecount = new JLabel("gene count");
		GridBagConstraints gbc_lblGenecount = new GridBagConstraints();
		gbc_lblGenecount.anchor = GridBagConstraints.EAST;
		gbc_lblGenecount.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenecount.gridx = 0;
		gbc_lblGenecount.gridy = 4;
		contentPane.add(lblGenecount, gbc_lblGenecount);

		txt_genecount = new JTextField();
		txt_genecount.setText("100");
		GridBagConstraints gbc_txt_genecount = new GridBagConstraints();
		gbc_txt_genecount.insets = new Insets(0, 0, 5, 5);
		gbc_txt_genecount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_genecount.gridx = 1;
		gbc_txt_genecount.gridy = 4;
		contentPane.add(txt_genecount, gbc_txt_genecount);
		txt_genecount.setColumns(10);

		JLabel lblTodo = new JLabel("mutation rate");
		GridBagConstraints gbc_lblTodo = new GridBagConstraints();
		gbc_lblTodo.anchor = GridBagConstraints.EAST;
		gbc_lblTodo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTodo.gridx = 0;
		gbc_lblTodo.gridy = 5;
		contentPane.add(lblTodo, gbc_lblTodo);

		txt_mutationrate = new JTextField();
		txt_mutationrate.setText("0.01");
		GridBagConstraints gbc_txt_mutationrate = new GridBagConstraints();
		gbc_txt_mutationrate.insets = new Insets(0, 0, 5, 5);
		gbc_txt_mutationrate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_mutationrate.gridx = 1;
		gbc_txt_mutationrate.gridy = 5;
		contentPane.add(txt_mutationrate, gbc_txt_mutationrate);
		txt_mutationrate.setColumns(10);

		JLabel lblParam = new JLabel("recombination rate");
		GridBagConstraints gbc_lblParam = new GridBagConstraints();
		gbc_lblParam.anchor = GridBagConstraints.EAST;
		gbc_lblParam.insets = new Insets(0, 0, 5, 5);
		gbc_lblParam.gridx = 0;
		gbc_lblParam.gridy = 6;
		contentPane.add(lblParam, gbc_lblParam);

		txt_recombinationrate = new JTextField();
		txt_recombinationrate.setText("0.5");
		GridBagConstraints gbc_txt_recombinationrate = new GridBagConstraints();
		gbc_txt_recombinationrate.insets = new Insets(0, 0, 5, 5);
		gbc_txt_recombinationrate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_recombinationrate.gridx = 1;
		gbc_txt_recombinationrate.gridy = 6;
		contentPane.add(txt_recombinationrate, gbc_txt_recombinationrate);
		txt_recombinationrate.setColumns(10);

		JLabel lblMaxGenerations = new JLabel("max generations");
		GridBagConstraints gbc_lblMaxGenerations = new GridBagConstraints();
		gbc_lblMaxGenerations.anchor = GridBagConstraints.EAST;
		gbc_lblMaxGenerations.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxGenerations.gridx = 0;
		gbc_lblMaxGenerations.gridy = 7;
		contentPane.add(lblMaxGenerations, gbc_lblMaxGenerations);

		txt_maxgener = new JTextField();
		txt_maxgener.setText("3000");
		GridBagConstraints gbc_txt_maxgener = new GridBagConstraints();
		gbc_txt_maxgener.insets = new Insets(0, 0, 5, 5);
		gbc_txt_maxgener.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_maxgener.gridx = 1;
		gbc_txt_maxgener.gridy = 7;
		contentPane.add(txt_maxgener, gbc_txt_maxgener);
		txt_maxgener.setColumns(10);
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.anchor = GridBagConstraints.EAST;
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 1;
		gbc_btnStart.gridy = 8;
		contentPane.add(btnStart, gbc_btnStart);

		JLabel lblCoo = new JLabel("ACO");
		lblCoo.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblCoo = new GridBagConstraints();
		gbc_lblCoo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoo.anchor = GridBagConstraints.WEST;
		gbc_lblCoo.gridx = 0;
		gbc_lblCoo.gridy = 9;
		contentPane.add(lblCoo, gbc_lblCoo);

		JButton btnStart_1 = new JButton("Start");
		btnStart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> listener.onStartACO(getConf())).start();
			}
		});

		JLabel lblTodo_1 = new JLabel("Ant Count");
		GridBagConstraints gbc_lblTodo_1 = new GridBagConstraints();
		gbc_lblTodo_1.anchor = GridBagConstraints.EAST;
		gbc_lblTodo_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblTodo_1.gridx = 0;
		gbc_lblTodo_1.gridy = 10;
		contentPane.add(lblTodo_1, gbc_lblTodo_1);

		txt_aco_antcount = new JTextField();
		txt_aco_antcount.setText("10");
		GridBagConstraints gbc_txt_aco_antcount = new GridBagConstraints();
		gbc_txt_aco_antcount.insets = new Insets(0, 0, 5, 5);
		gbc_txt_aco_antcount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_aco_antcount.gridx = 1;
		gbc_txt_aco_antcount.gridy = 10;
		contentPane.add(txt_aco_antcount, gbc_txt_aco_antcount);
		txt_aco_antcount.setColumns(10);

		JLabel lblRuns = new JLabel("runs");
		GridBagConstraints gbc_lblRuns = new GridBagConstraints();
		gbc_lblRuns.anchor = GridBagConstraints.EAST;
		gbc_lblRuns.insets = new Insets(0, 0, 5, 5);
		gbc_lblRuns.gridx = 0;
		gbc_lblRuns.gridy = 11;
		contentPane.add(lblRuns, gbc_lblRuns);

		txt_aco_runs = new JTextField();
		txt_aco_runs.setText("100000");
		GridBagConstraints gbc_txt_aco_runs = new GridBagConstraints();
		gbc_txt_aco_runs.insets = new Insets(0, 0, 5, 5);
		gbc_txt_aco_runs.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_aco_runs.gridx = 1;
		gbc_txt_aco_runs.gridy = 11;
		contentPane.add(txt_aco_runs, gbc_txt_aco_runs);
		txt_aco_runs.setColumns(10);

		JLabel lblAlpha = new JLabel("Alpha");
		GridBagConstraints gbc_lblAlpha = new GridBagConstraints();
		gbc_lblAlpha.anchor = GridBagConstraints.EAST;
		gbc_lblAlpha.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlpha.gridx = 0;
		gbc_lblAlpha.gridy = 12;
		contentPane.add(lblAlpha, gbc_lblAlpha);

		txt_alpha = new JTextField();
		txt_alpha.setText("1.0");
		GridBagConstraints gbc_txt_alpha = new GridBagConstraints();
		gbc_txt_alpha.anchor = GridBagConstraints.NORTH;
		gbc_txt_alpha.insets = new Insets(0, 0, 5, 5);
		gbc_txt_alpha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_alpha.gridx = 1;
		gbc_txt_alpha.gridy = 12;
		contentPane.add(txt_alpha, gbc_txt_alpha);
		txt_alpha.setColumns(10);

		JLabel lblBeta = new JLabel("Beta");
		GridBagConstraints gbc_lblBeta = new GridBagConstraints();
		gbc_lblBeta.anchor = GridBagConstraints.EAST;
		gbc_lblBeta.insets = new Insets(0, 0, 5, 5);
		gbc_lblBeta.gridx = 0;
		gbc_lblBeta.gridy = 13;
		contentPane.add(lblBeta, gbc_lblBeta);

		txt_beta = new JTextField();
		txt_beta.setText("5.0");
		GridBagConstraints gbc_txt_beta = new GridBagConstraints();
		gbc_txt_beta.insets = new Insets(0, 0, 5, 5);
		gbc_txt_beta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_beta.gridx = 1;
		gbc_txt_beta.gridy = 13;
		contentPane.add(txt_beta, gbc_txt_beta);
		txt_beta.setColumns(10);

		JLabel lblEvaporation = new JLabel("evaporation");
		GridBagConstraints gbc_lblEvaporation = new GridBagConstraints();
		gbc_lblEvaporation.anchor = GridBagConstraints.EAST;
		gbc_lblEvaporation.insets = new Insets(0, 0, 5, 5);
		gbc_lblEvaporation.gridx = 0;
		gbc_lblEvaporation.gridy = 14;
		contentPane.add(lblEvaporation, gbc_lblEvaporation);

		txt_evaporation = new JTextField();
		txt_evaporation.setText("0.5");
		GridBagConstraints gbc_txt_evaporation = new GridBagConstraints();
		gbc_txt_evaporation.insets = new Insets(0, 0, 5, 5);
		gbc_txt_evaporation.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_evaporation.gridx = 1;
		gbc_txt_evaporation.gridy = 14;
		contentPane.add(txt_evaporation, gbc_txt_evaporation);
		txt_evaporation.setColumns(10);
		GridBagConstraints gbc_btnStart_1 = new GridBagConstraints();
		gbc_btnStart_1.anchor = GridBagConstraints.EAST;
		gbc_btnStart_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart_1.gridx = 1;
		gbc_btnStart_1.gridy = 15;
		contentPane.add(btnStart_1, gbc_btnStart_1);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 20;
		gbc_scrollPane.gridheight = 12;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 16;
		contentPane.add(scrollPane, gbc_scrollPane);

		txt_log = new JTextArea();
		DefaultCaret caret = (DefaultCaret) txt_log.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(txt_log);

		showMap(getPlaceholderImg(450, 450));
		showPheromoneMap(getPlaceholderImg(450, 450));
	}

	private Conf getConf() {
		String file = txt_file.getText();
		int aco_ant_count = Integer.parseInt(txt_aco_antcount.getText());
		int aco_nr_of_runs = Integer.parseInt(txt_aco_runs.getText());
		double aco_alpha = Double.parseDouble(txt_alpha.getText());
		double aco_beta = Double.parseDouble(txt_beta.getText());
		double aco_evaporation = Double.parseDouble(txt_evaporation.getText());
		int pop_size = Integer.parseInt(txt_genecount.getText());
		double mutation_rate = Double.parseDouble(txt_mutationrate.getText());
		double recomb_rate = Double
				.parseDouble(txt_recombinationrate.getText());
		int max_gener = Integer.parseInt(txt_maxgener.getText());
		return new Conf(file, aco_ant_count, aco_nr_of_runs, aco_alpha,
				aco_beta, aco_evaporation, pop_size, mutation_rate,
				recomb_rate, max_gener);
	}

	private BufferedImage getPlaceholderImg(int w, int h) {
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g = image.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);

		return image;
	}

}
