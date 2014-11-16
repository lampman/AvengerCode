package beerTycoonPack;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;

public class TempTimer extends JComponent implements ActionListener, Runnable {
	int timeX = 80, timeY = 95;
	int tempX = 180, tempY = 95;
	
	int theTime;
	int timeSecond;
	int timeMinute;
	int timeHour;
	int timeMode;
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

	public TempTimer() {
		theTime = 0;
		timeMode = 0;
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
		
		if(theTemp > 212){theTemp = 212;}
		if(theTemp < 60){theTemp = 60;}
		theTempDisplay = String.valueOf((int)theTemp);
		
		g.drawString(timeHourDisplay + ":" + 
		             String.format("%2s", timeMinuteDisplay).replace(' ', '0') + ":"+
		             String.format("%2s", timeSecondDisplay).replace(' ', '0')
		             , timeX, timeY);

		g.drawString(theTempDisplay + "\u00b0" + "F", tempX, tempY);
	}

	public void actionPerformed(ActionEvent e) {
		switch (String.valueOf(e.getActionCommand())) {
		case "Start":
			theStartButton.setText("Stop");
			thePauseButton.setEnabled(true);
			timeMode = 1;
			break;
		case "5X":
			theStartButton.setText("Stop");
			thePauseButton.setEnabled(true);
			timeMode = 2;
			break;
		case "50X":
			theStartButton.setText("Stop");
			thePauseButton.setEnabled(true);
			timeMode = 3;
			break;
		case "Pause":
			thePauseButton.setText("Resume");
			timeMode = 0;
			break;
		case "Resume":
			thePauseButton.setText("Pause");
			timeMode = 1;
			break;
		case "Stop":
			theStartButton.setText("Start");
			theTime = 0;
			timeMode = 0;
			theTemp = 60;
			break;
		}
	}

	public void run() {
		try {
			while (true) {
				switch(timeMode){
				case 0:
					repaint();
					break;
				case 1:
					theTime = theTime + 1;
					theTemp = theTime*0.13 + 60;
					repaint();
					Thread.sleep(1000);
					break;
				case 2:
					theTime = theTime + 5;
					theTemp = theTime*0.13 + 60;
					repaint();
					Thread.sleep(1000);
					break;
				case 3:
					theTime = theTime + 50;
					theTemp = theTime*0.13 + 60;
					repaint();
					Thread.sleep(1000);
					break;
				}
			}
		} catch (InterruptedException ie) {}
	}
}