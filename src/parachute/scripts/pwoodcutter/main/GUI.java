package parachute.scripts.pwoodcutter.main;

import java.awt.EventQueue;

public class GUI extends JFrame {
	public boolean warning = false;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("pWoodcutter by parachute");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnStartScript = new JButton("Start Script");

		btnStartScript.setBounds(162, 193, 132, 26);
		contentPane.add(btnStartScript);

		final JComboBox cbxMethod = new JComboBox();

		final JLabel lblWarning = new JLabel(
				"Warning! If you're using the ::empty method make sure you have your axe equiped!");
		lblWarning.setForeground(Color.RED);
		lblWarning.setFont(new Font("Dialog", Font.BOLD, 10));
		lblWarning.setBounds(12, 124, 422, 33);
		contentPane.add(lblWarning);
		final JLabel lblNests = new JLabel("Also, nests won't be picked up!");
		lblNests.setForeground(Color.RED);
		lblNests.setHorizontalAlignment(SwingConstants.CENTER);
		lblNests.setBounds(12, 152, 410, 16);
		contentPane.add(lblNests);

		cbxMethod.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if (cbxMethod.getSelectedItem() == "Banking") {
					lblWarning.setVisible(false);
					lblNests.setVisible(false);
				} else {
					lblWarning.setVisible(true);
					lblNests.setVisible(true);
				}

			}
		});
		cbxMethod.setModel(new DefaultComboBoxModel(new String[] { "Banking",
				"::empty command" }));
		cbxMethod.setToolTipText("");
		cbxMethod.setBounds(162, 87, 132, 25);
		contentPane.add(cbxMethod);

		JComboBox cbxLocation = new JComboBox();
		cbxLocation.setModel(new DefaultComboBoxModel(
				new String[] { "Draynor - Willows" }));
		cbxLocation.setBounds(162, 50, 132, 25);
		contentPane.add(cbxLocation);

		JLabel lblMethod = new JLabel("Method");
		lblMethod.setBounds(89, 96, 55, 16);
		contentPane.add(lblMethod);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(89, 54, 55, 16);
		contentPane.add(lblLocation);
		


		btnStartScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbxMethod.getSelectedItem() == "Banking") {
					Boot.Method = "Banking";
				} else {
					Boot.Method = "Empty";
				}
				dispose();
				setVisible(false);

			}
		});
	}
}
