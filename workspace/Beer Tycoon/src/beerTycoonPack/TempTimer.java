package beerTycoonPack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*; 
import javax.swing.event.*;

public class TempTimer extends JComponent implements ActionListener, ChangeListener, Runnable {
	private static final long serialVersionUID = 1L;
	int timeX = 130, timeY = 110;
	int tempX = 230, tempY = 110;
	
	int theTime;
	int timeSecond;
	int timeMinute;
	int timeHour;
	double timeMode;
	String theTimeDisplay;
	String timeSecondDisplay;
	String timeMinuteDisplay;
	String timeHourDisplay;
	
	double theTemp;
	String theTempDisplay;

	JButton theStartButton;
	JButton thePauseButton;
	JButton the5XButton;
	JButton the50XButton;
	
	JSlider slider;
	double sliderValue;
	JLabel statusLabel;

	public TempTimer() {
		theTime = 0;
		timeMode = 0.0;
		theTemp = 60;
		
		theStartButton = new JButton("Start");
		thePauseButton = new JButton("Pause");
		the5XButton = new JButton("5X");
		the50XButton = new JButton("50X");
		
		setLayout(new FlowLayout());
		add(theStartButton);
		add(thePauseButton);
		add(the5XButton);
		add(the50XButton);
		
		theStartButton.addActionListener(this);
		thePauseButton.addActionListener(this);
		the5XButton.addActionListener(this);
		the50XButton.addActionListener(this);
		
		thePauseButton.setEnabled(false);
		
	    slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		slider.setMajorTickSpacing(48);
    	slider.setMinorTickSpacing(16);
    	slider.setPaintTicks(true); 
    	add(slider);
    	slider.addChangeListener(this);
    	statusLabel = new JLabel("Heat = 0");
    	add(statusLabel, BorderLayout.SOUTH);
		
		Thread t = new Thread(this);
		t.start();
	}

	public void paintComponent(Graphics g) {
		timeSecond = theTime%60;
		timeMinute = theTime/60;
		timeHour = theTime/60/60;
		
		timeSecondDisplay = String.valueOf(timeSecond);
		timeMinuteDisplay = String.valueOf(timeMinute);
		timeHourDisplay = String.valueOf(timeHour);
		
		theTempDisplay = String.valueOf((int)theTemp);
		
		g.drawString(timeHourDisplay + ":" + 
		             String.format("%2s", timeMinuteDisplay).replace(' ', '0') + ":"+
		             String.format("%2s", timeSecondDisplay).replace(' ', '0')
		             , timeX, timeY);

		g.drawString(theTempDisplay + "\u00b0" + "F", tempX, tempY);
	}

	public void stateChanged(ChangeEvent e) {
   		statusLabel.setText("Heat = " + slider.getValue());
   		sliderValue = (double) slider.getValue();
	} 
	
	public void actionPerformed(ActionEvent e) {
		switch (String.valueOf(e.getActionCommand())) {
		case "Start":
			theStartButton.setText("Stop");
			thePauseButton.setEnabled(true);
			timeMode = 1.0;
			break;
		case "5X":
			theStartButton.setText("Stop");
			thePauseButton.setEnabled(true);
			timeMode = 5.0;
			break;
		case "50X":
			theStartButton.setText("Stop");
			thePauseButton.setEnabled(true);
			timeMode = 50.0;
			break;
		case "Pause":
			thePauseButton.setText("Resume");
			timeMode = 0.0;
			break;
		case "Resume":
			thePauseButton.setText("Pause");
			timeMode = 1.0;
			break;
		case "Stop":
			theStartButton.setText("Start");
			thePauseButton.setEnabled(false);
			theTime = 0;
			timeMode = 0.0;
			theTemp = 60;
			break;
		}
	}

	public void run() {
		try {
			while (true) {
				if(timeMode != 0.0){
						theTime = theTime + (int) timeMode;
						if(theTemp < (sliderValue*1.60 + 60)){theTemp = theTemp + (timeMode * 0.1);}
						if(theTemp > (sliderValue*1.60 + 60)){theTemp = theTemp - (timeMode * 0.05);}
			    }
			    if(theTemp > 212){theTemp = 212;}
				if(theTemp < 60){theTemp = 60;}
				Thread.sleep(1000);
				repaint();
			}
		} catch (InterruptedException ie) {}
	}
}