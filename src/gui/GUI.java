package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_file;
	private JTextArea txt_log;
	private GUIListener listener;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txt_aco_antcount;
	private JTextField textField_3;
	private JLabel lbl_map;
	private JTextField txt_aco_runs;

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

	/**
	 * Create the frame.
	 */
	public GUI(GUIListener listener) {
		this.listener = listener;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblAllgemein = new JLabel("Allgemein");
		lblAllgemein.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblAllgemein = new GridBagConstraints();
		gbc_lblAllgemein.anchor = GridBagConstraints.WEST;
		gbc_lblAllgemein.insets = new Insets(0, 0, 5, 5);
		gbc_lblAllgemein.gridx = 0;
		gbc_lblAllgemein.gridy = 0;
		contentPane.add(lblAllgemein, gbc_lblAllgemein);

		JLabel lblAusgabe = new JLabel("Ausgabe");
		lblAusgabe.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblAusgabe = new GridBagConstraints();
		gbc_lblAusgabe.insets = new Insets(0, 0, 5, 5);
		gbc_lblAusgabe.gridx = 2;
		gbc_lblAusgabe.gridy = 0;
		contentPane.add(lblAusgabe, gbc_lblAusgabe);

		JLabel lblFile = new JLabel("Datei");
		GridBagConstraints gbc_lblFile = new GridBagConstraints();
		gbc_lblFile.anchor = GridBagConstraints.EAST;
		gbc_lblFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblFile.gridx = 0;
		gbc_lblFile.gridy = 1;
		contentPane.add(lblFile, gbc_lblFile);

		txt_file = new JTextField();
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
		gbc_lblKarte.gridx = 3;
		gbc_lblKarte.gridy = 0;
		contentPane.add(lblKarte, gbc_lblKarte);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridheight = 11;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);

		txt_log = new JTextArea();
		scrollPane.setViewportView(txt_log);

		lbl_map = new JLabel("lbl_map");
		GridBagConstraints gbc_lbl_map = new GridBagConstraints();
		gbc_lbl_map.gridwidth = 10;
		gbc_lbl_map.gridheight = 11;
		gbc_lbl_map.gridx = 3;
		gbc_lbl_map.gridy = 1;
		contentPane.add(lbl_map, gbc_lbl_map);

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

		JLabel lblGenlen = new JLabel("param");
		GridBagConstraints gbc_lblGenlen = new GridBagConstraints();
		gbc_lblGenlen.anchor = GridBagConstraints.EAST;
		gbc_lblGenlen.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenlen.gridx = 0;
		gbc_lblGenlen.gridy = 4;
		contentPane.add(lblGenlen, gbc_lblGenlen);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 4;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblTodo = new JLabel("param");
		GridBagConstraints gbc_lblTodo = new GridBagConstraints();
		gbc_lblTodo.anchor = GridBagConstraints.EAST;
		gbc_lblTodo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTodo.gridx = 0;
		gbc_lblTodo.gridy = 5;
		contentPane.add(lblTodo, gbc_lblTodo);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 5;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblParam = new JLabel("param");
		GridBagConstraints gbc_lblParam = new GridBagConstraints();
		gbc_lblParam.anchor = GridBagConstraints.EAST;
		gbc_lblParam.insets = new Insets(0, 0, 5, 5);
		gbc_lblParam.gridx = 0;
		gbc_lblParam.gridy = 6;
		contentPane.add(lblParam, gbc_lblParam);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 6;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.anchor = GridBagConstraints.EAST;
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 1;
		gbc_btnStart.gridy = 7;
		contentPane.add(btnStart, gbc_btnStart);

		JLabel lblCoo = new JLabel("ACO");
		lblCoo.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblCoo = new GridBagConstraints();
		gbc_lblCoo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoo.anchor = GridBagConstraints.WEST;
		gbc_lblCoo.gridx = 0;
		gbc_lblCoo.gridy = 8;
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
		gbc_lblTodo_1.gridy = 9;
		contentPane.add(lblTodo_1, gbc_lblTodo_1);

		txt_aco_antcount = new JTextField();
		txt_aco_antcount.setText("10");
		GridBagConstraints gbc_txt_aco_antcount = new GridBagConstraints();
		gbc_txt_aco_antcount.insets = new Insets(0, 0, 5, 5);
		gbc_txt_aco_antcount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_aco_antcount.gridx = 1;
		gbc_txt_aco_antcount.gridy = 9;
		contentPane.add(txt_aco_antcount, gbc_txt_aco_antcount);
		txt_aco_antcount.setColumns(10);

		JLabel lblRuns = new JLabel("runs");
		GridBagConstraints gbc_lblRuns = new GridBagConstraints();
		gbc_lblRuns.anchor = GridBagConstraints.EAST;
		gbc_lblRuns.insets = new Insets(0, 0, 5, 5);
		gbc_lblRuns.gridx = 0;
		gbc_lblRuns.gridy = 10;
		contentPane.add(lblRuns, gbc_lblRuns);

		txt_aco_runs = new JTextField();
		txt_aco_runs.setText("100");
		GridBagConstraints gbc_txt_aco_runs = new GridBagConstraints();
		gbc_txt_aco_runs.insets = new Insets(0, 0, 5, 5);
		gbc_txt_aco_runs.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_aco_runs.gridx = 1;
		gbc_txt_aco_runs.gridy = 10;
		contentPane.add(txt_aco_runs, gbc_txt_aco_runs);
		txt_aco_runs.setColumns(10);
		GridBagConstraints gbc_btnStart_1 = new GridBagConstraints();
		gbc_btnStart_1.anchor = GridBagConstraints.EAST;
		gbc_btnStart_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnStart_1.gridx = 1;
		gbc_btnStart_1.gridy = 11;
		contentPane.add(btnStart_1, gbc_btnStart_1);

		showMap(getPlaceholderImg());
	}

	private Conf getConf() {
		int aco_ant_count = Integer.parseInt(txt_aco_antcount.getText());
		int aco_nr_of_runs = Integer.parseInt(txt_aco_runs.getText());
		return new Conf(aco_ant_count, aco_nr_of_runs);
	}

	private BufferedImage getPlaceholderImg() {
		BufferedImage image = new BufferedImage(500, 500,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g = image.createGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 300, 300);

		return image;
	}

}
