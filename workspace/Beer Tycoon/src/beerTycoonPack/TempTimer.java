package beerTycoonPack;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;

public class TempTimer extends JComponent implements ActionListener, Runnable {
	int theTime;
	int timeMode;
	String theTimeDisplay;
	int timeX = 125, timeY = 95; // Coordinates of the message
	
	double theTemp;
	String theTempDisplay;
	int tempX = 225, tempY = 95; // Coordinates of the message
	
	int theMinute;
	String theMinuteDisplay;
	int theHour;
	String theHourDisplay;
	
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
		Thread t = new Thread(this);
		t.start();
	}

	public void paintComponent(Graphics g) {
		theMinute = theTime/60;
		theHour = theTime/60/60;
		int tempInt = (int) theTemp;
		theTimeDisplay = String.valueOf(theTime%60);
		theTempDisplay = String.valueOf(tempInt);
		theMinuteDisplay = String.valueOf(theMinute);
		theHourDisplay = String.valueOf(theHour);
		g.drawString(theHourDisplay + ":" + theMinuteDisplay + ":"+ theTimeDisplay, timeX, timeY);
		
		g.drawString(theTempDisplay + " oF", tempX, tempY);
	}

	public void actionPerformed(ActionEvent e) {
		switch (String.valueOf(e.getActionCommand())) {
		case "Start":
			theStartButton.setText("Stop");
			timeMode = 1;
			//timePauseButton.setEnabled(true);
			break;
		case "5X":
			timeMode = 2;
			//timePauseButton.setEnabled(true);
			break;
		case "50X":
			timeMode = 3;
			//timePauseButton.setEnabled(true);
			break;
		case "Pause":
			timeMode = 0;
			break;
		case "Resume":
			timeMode = 1;
			break;
		}
	}

	public void run() {
		try {
			while (true) {
				switch(timeMode){
				case 1:
					theTime = theTime + 1;
					theTemp = theTime*0.0026 + theTemp;
					repaint();
					Thread.sleep(1000);
					break;
				case 2:
					theTime = theTime + 5;
					theTemp = theTime*0.0026 + theTemp;
					repaint();
					Thread.sleep(1000);
					break;
				case 3:
					theTime = theTime + 50;
					theTemp = theTime*0.0026 + theTemp;
					repaint();
					Thread.sleep(1000);
					break;
				}
				
			}
		} catch (InterruptedException ie) {
		}
	}
}