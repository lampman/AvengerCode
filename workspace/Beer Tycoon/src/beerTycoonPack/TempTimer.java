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
	int timeModeInt;
	double timeMode;
	String theTimeDisplay;
	String timeSecondDisplay;
	String timeMinuteDisplay;
	String timeHourDisplay;
	
	double theTemp;
	String theTempDisplay;
	double tempArray[] = {60,60,60,60,60,
	                      60,60,60,60,60};

	JButton theStartButton;
	JButton thePauseButton;
	JButton the5XButton;
	JButton the50XButton;
	JButton theStirButton;
	
	JSlider slider;
	double sliderValue;
	JLabel statusLabel;

     int imageSwitch;
	 Image lauter1 = Toolkit.getDefaultToolkit().getImage(
		      "lauter1.jpg");
	 Image lauter2 = Toolkit.getDefaultToolkit().getImage(
		      "lauter2.jpg");
	 Image lauter3 = Toolkit.getDefaultToolkit().getImage(
		      "lauter3.jpg");
	 Image lauter4 = Toolkit.getDefaultToolkit().getImage(
		      "lauter4.jpg");
	 Image lauter5 = Toolkit.getDefaultToolkit().getImage(
		      "lauter5.jpg");
	 Image lauter6 = Toolkit.getDefaultToolkit().getImage(
		      "lauter6.jpg");
	 Image lauter7 = Toolkit.getDefaultToolkit().getImage(
		      "lauter7.jpg");
	 Image lauter8 = Toolkit.getDefaultToolkit().getImage(
		      "lauter8.jpg");
	 Image lauter9 = Toolkit.getDefaultToolkit().getImage(
		      "lauter9.jpg");
	 Image lauter10 = Toolkit.getDefaultToolkit().getImage(
		      "lauter10.jpg");
	
	public TempTimer() {
		theTime = 0;
		timeMode = 0.0;
		
		theTemp = 60;
		theStartButton = new JButton("Start");
		thePauseButton = new JButton("Pause");
		the5XButton = new JButton("5X");
		the50XButton = new JButton("50X");
		theStirButton = new JButton("Stir");
		
		setLayout(new FlowLayout());
		add(theStartButton);
		add(thePauseButton);
		add(the5XButton);
		add(the50XButton);
		add(theStirButton);
		
		theStartButton.addActionListener(this);
		thePauseButton.addActionListener(this);
		the5XButton.addActionListener(this);
		the50XButton.addActionListener(this);
		theStirButton.addActionListener(this);
		
		thePauseButton.setEnabled(false);
		theStirButton.setEnabled(false);
		
	    slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		slider.setMajorTickSpacing(48);
    	slider.setMinorTickSpacing(16);
    	slider.setPaintTicks(true); 
    	add(slider);
    	slider.addChangeListener(this);
    	
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
		
		theTempDisplay = String.valueOf((int)tempArray[0]);
		
		

		if(sliderValue<20){imageSwitch = 1;}
		if(sliderValue >= 20 && sliderValue<30){imageSwitch = 2;}
		if(sliderValue >= 30 && sliderValue<40){imageSwitch = 3;}
		if(sliderValue >= 40 && sliderValue<50){imageSwitch = 4;}
		if(sliderValue >= 50 && sliderValue<60){imageSwitch = 5;}
		if(sliderValue >= 60 && sliderValue<70){imageSwitch = 6;}
		if(sliderValue >= 70 && sliderValue<80){imageSwitch = 7;}
		if(sliderValue >= 80 && sliderValue<90){imageSwitch = 8;}
		if(sliderValue >= 90 && sliderValue<100){imageSwitch = 9;}
		if(sliderValue == 100){imageSwitch = 10;}
		switch(imageSwitch){
		case 1:g.drawImage(lauter1, 120, 100, this);break;
		case 2:g.drawImage(lauter2, 120, 100, this);break;
		case 3:g.drawImage(lauter3, 120, 100, this);break;
		case 4:g.drawImage(lauter4, 120, 100, this);break;
		case 5:g.drawImage(lauter5, 120, 100, this);break;
		case 6:g.drawImage(lauter6, 120, 100, this);break;
		case 7:g.drawImage(lauter7, 120, 100, this);break;
		case 8:g.drawImage(lauter8, 120, 100, this);break;
		case 9:g.drawImage(lauter9, 120, 100, this);break;
		case 10:g.drawImage(lauter10, 120, 100, this);break;
		}
			
		g.drawString(timeHourDisplay + ":" + 
		             String.format("%2s", timeMinuteDisplay).replace(' ', '0') + ":"+
		             String.format("%2s", timeSecondDisplay).replace(' ', '0')
		             , timeX, timeY);

		g.drawString(theTempDisplay + "\u00b0" + "F", tempX, tempY);
	}
	public void stateChanged(ChangeEvent e) {
   		sliderValue = (double) slider.getValue();
   		repaint();
	} 
	public void actionPerformed(ActionEvent e) {
		switch (String.valueOf(e.getActionCommand())) {
		case "Start":
			theStartButton.setText("Stop");
			thePauseButton.setEnabled(true);
			theStirButton.setEnabled(true);
			timeMode = 1.0;
			break;
		case "5X":
			theStartButton.setText("Stop");
			thePauseButton.setEnabled(true);
			theStirButton.setEnabled(false);
			timeMode = 5.0;
			break;
		case "50X":
			theStartButton.setText("Stop");
			thePauseButton.setEnabled(true);
			theStirButton.setEnabled(false);
			timeMode = 50.0;
			break;
		case "Pause":
			thePauseButton.setText("Resume");
			theStirButton.setEnabled(false);
			timeMode = 0.0;
			break;
		case "Resume":
			thePauseButton.setText("Pause");
			theStirButton.setEnabled(true);
			timeMode = 1.0;
			break;
		case "Stop":
			theStartButton.setText("Start");
			thePauseButton.setEnabled(false);
			theStirButton.setEnabled(false);
			theTime = 0;
			timeMode = 0.0;
			theTemp = 60;
			break;
		case "Stir":
			theStirButton.setEnabled(false);
			break;
		}
		repaint();
	}
	public void run() {
		int stirCounter = 0;
		try {
			while (true) {
				timeModeInt = (int) timeMode;
				if(timeMode != 0.0){
						theTime = theTime + (int) timeMode;
						if(theTemp < (sliderValue*1.60 + 60)){theTemp = theTemp + (timeMode * 0.1);}
						if(theTemp > (sliderValue*1.60 + 60)){theTemp = theTemp - (timeMode * 0.05);}
						if(!theStirButton.isEnabled() && timeMode == 1.0){
							stirCounter = stirCounter + 1;
							theTemp = theTemp - 0.5;}
						if(!theStirButton.isEnabled() && timeMode == 1.0 && stirCounter > 4){
						   stirCounter = 0;
						   theStirButton.setEnabled(true);
						   }
				}
			    if(theTemp > 212){theTemp = 212;}
				if(theTemp < 60){theTemp = 60;}
				for ( int i = 1; i <= timeModeInt; i++){
					for ( int j = 0; j < 9; j++ ) {
						tempArray[j] = tempArray[j + 1];
					}
					tempArray[9] = theTemp;
				}
				Thread.sleep(1000);
				repaint();
			}
		} catch (InterruptedException ie) {}
	}
}