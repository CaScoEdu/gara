package gara;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class InputValidazioneGara extends JFrame implements ActionListener {

	// componenti per l'input degli attributi della gara
	private JComboBox<String> sportComboBox = new JComboBox<String>(Gara.SPORTS);
	private JTextField postoTextField = new JTextField(20);
	private JTextField dataTextField = new JTextField(14);
	private JTextField numeroConcorrentiTextField = new JTextField(4);
	
	private final String INDOOR = "indoor";
	private JCheckBox indoorCheckBox = new JCheckBox(INDOOR);

	// componenti per il controllo della finestra di input
	private final String IMMETTI = "immetti";
	private JButton immettiButton = new JButton(IMMETTI);
	
	private final String RESET = "reset";
	private JButton resetButton = new JButton(RESET);
	
	private final String NERO = "nero";
	private final String BLU = " blu";
	private final String ROSSO = " rosso";
	private final String COLORI_FOREGROUND[] = { NERO, BLU, ROSSO };
	private JComboBox<String> foregroundComboBox = new JComboBox<String>(COLORI_FOREGROUND);
	
	private final String RICOLORA = "ricolora";
	private JButton ricoloraButton = new JButton(RICOLORA);
	
	private final String ON = "on";
	private final String OFF = "off";
	private JButton onOffButton = new JButton(ON);
	
	// area di visualizzazione messaggi di output
	private JTextArea outputTextArea = new JTextArea(10, 30);

	// costruzione grafica
	public InputValidazioneGara() {
		super("input e validazione gara");
		
		// pannello di input
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5, 2));
		inputPanel.add(new JLabel("sport", JLabel.RIGHT));
		inputPanel.add(sportComboBox);
		inputPanel.add(new JLabel("posto", JLabel.RIGHT));
		inputPanel.add(postoTextField);
		inputPanel.add(new JLabel("data", JLabel.RIGHT));
		inputPanel.add(dataTextField);
		inputPanel.add(new JLabel("numero concorrenti", JLabel.RIGHT));
		inputPanel.add(numeroConcorrentiTextField);
		inputPanel.add(new JLabel());
		inputPanel.add(indoorCheckBox);
		getContentPane().add(inputPanel, BorderLayout.NORTH);

		// pannello di controllo
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(4, 2));		
		immettiButton.addActionListener(this);
		controlPanel.add(new JLabel());
		controlPanel.add(immettiButton);
		resetButton.addActionListener(this);
		controlPanel.add(new JLabel());
		controlPanel.add(resetButton);
		controlPanel.add(foregroundComboBox);
		ricoloraButton.addActionListener(this);
		controlPanel.add(ricoloraButton);
		controlPanel.add(new JLabel());
		onOffButton.addActionListener(this);
		controlPanel.add(onOffButton);
		getContentPane().add(controlPanel, BorderLayout.CENTER);

		// pannello di output
		outputTextArea.setEditable(false);
		getContentPane().add(outputTextArea, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null); // posiziona la finestra al centro
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String etichettaPulsante = event.getActionCommand();
		switch (etichettaPulsante) {
		case IMMETTI:
			convalidaImmetti();
			break;
		case RESET:
			reset();
			break;
		case RICOLORA:
			ricoloraForeground();			
			break;
		case ON:
		case OFF:
			switchOnOff();
			break;
		}
	}
	
	private void convalidaImmetti() {
		int numeroConcorrenti = 0;
		boolean datiValidi = true;
		outputTextArea.setText("");
		if (postoTextField.getText().isEmpty()) {
			datiValidi = false;
			outputTextArea.append("posto non inserito\n");
		}
		if (dataTextField.getText().isEmpty()) {
			datiValidi = false;
			outputTextArea.append("data non inserita\n");
		}
		try {
			numeroConcorrenti =
					Integer.valueOf(numeroConcorrentiTextField.getText()).intValue();
			if (numeroConcorrenti < 1) {
				datiValidi = false;
				outputTextArea.append("numero concorrenti deve essere maggiore di zero\n");
			}
		} catch (Exception exception) {
			datiValidi = false;
			outputTextArea.append("numero concorrenti inserito non correttamente\n");
		}
		if (datiValidi) {
			Gara gara = new Gara(
					(String) sportComboBox.getSelectedItem(),
					postoTextField.getText(),
					dataTextField.getText(),
					numeroConcorrenti,
					indoorCheckBox.isSelected()
					);
			outputTextArea.setText(gara.toString());
		}
	}
	
	private void reset() {
		sportComboBox.setSelectedIndex(0);
		postoTextField.setText("");
		dataTextField.setText("");
		numeroConcorrentiTextField.setText("");
		indoorCheckBox.setSelected(false);
	}
	
	private void ricoloraForeground() {
		String coloreSelezionato = (String)foregroundComboBox.getSelectedItem();
		Color coloreForeground = null;
		switch (coloreSelezionato) {
		case NERO:
			coloreForeground = Color.BLACK;
			break;
		case BLU:
			coloreForeground = Color.BLUE;
			break;
		case ROSSO:
			coloreForeground = Color.RED;
			break;
		}
		sportComboBox.setForeground(coloreForeground);
		postoTextField.setForeground(coloreForeground);
		dataTextField.setForeground(coloreForeground);
		numeroConcorrentiTextField.setForeground(coloreForeground);
		indoorCheckBox.setForeground(coloreForeground);
		foregroundComboBox.setForeground(coloreForeground);
		outputTextArea.setForeground(coloreForeground);		
	}

	private void switchOnOff() {
		boolean isOn;
		// se è ON diventa OFF e viceversa
		if (onOffButton.getText().equals(OFF)) {
			onOffButton.setText(ON);
			isOn = true;
		}
		else {
			onOffButton.setText(OFF);
			isOn = false;			
		}
		// se isOn == true si abilitano tutti i componenti
		// altrimenti si disabilitano
		sportComboBox.setEnabled(isOn);
		postoTextField.setEnabled(isOn);
		dataTextField.setEnabled(isOn);
		numeroConcorrentiTextField.setEnabled(isOn);
		indoorCheckBox.setEnabled(isOn);
		immettiButton.setEnabled(isOn);
		foregroundComboBox.setEnabled(isOn);
		ricoloraButton.setEnabled(isOn);
		outputTextArea.setEnabled(isOn);
	}
}
