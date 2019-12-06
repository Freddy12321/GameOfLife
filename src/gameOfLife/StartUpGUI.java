package gameOfLife;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StartUpGUI extends JFrame implements ActionListener, ChangeListener{
	int height=4;
	int width=4;
	int expand=1;
	int delay=200;
	int random=50;
	String path="";
	boolean randomPressed=false;
	boolean importPressed=false;
	boolean presetPressed=false;
	JButton randomOk, presetOk, importOk;
	JLabel hieghtLabel, hieghtDisplay, widthLabel, widthDisplay, expandLabel, expandDisplay, delayLabel, delayDisplay, randomLabel, randomDisplay;
	JSlider hieghtSlider, widthSlider, expandSlider, delaySlider, randomSlider;
	//starting GUI, contains a variety of Sliders for controlling different aspects of the initial board
	public StartUpGUI() {
		this.setBounds(200, 200, 250, 400);
		this.setLayout(new FlowLayout());
		expandLabel= new JLabel("Expand \nplay area?");
		this.add(expandLabel);
		expandDisplay=new JLabel("Yes");
		this.add(expandDisplay);
		expandSlider=new JSlider();
		expandSlider.setMaximum(1);
		expandSlider.setMinimum(0);
		expandSlider.setValue(expand);
		expandSlider.addChangeListener(this);
		this.add(expandSlider);
		delayLabel=new JLabel("Delay between \nticks?");
		this.add(delayLabel);
		delayDisplay=new JLabel(delay+"");
		this.add(delayDisplay);
		delaySlider= new JSlider();
		delaySlider.setValue(delay);
		delaySlider.setMaximum(1000);
		delaySlider.setMinimum(50);
		delaySlider.addChangeListener(this);
		this.add(delaySlider);
		importOk= new JButton("Import initial board");
		importOk.addActionListener(this);
		this.add(importOk);
		hieghtLabel= new JLabel("Hieght of \nplay area?");
		this.add(hieghtLabel);
		hieghtDisplay=new JLabel(height+"");
		this.add(hieghtDisplay);
		hieghtSlider=new JSlider();
		hieghtSlider.setMaximum(20);
		hieghtSlider.setMinimum(4);
		hieghtSlider.setValue(height);
		hieghtSlider.addChangeListener(this);
		this.add(hieghtSlider);
		widthLabel= new JLabel("Width of \nplay area?");
		this.add(widthLabel);
		widthDisplay=new JLabel(width+"");
		this.add(widthDisplay);
		widthSlider=new JSlider();
		widthSlider.setMaximum(20);
		widthSlider.setMinimum(4);
		widthSlider.setValue(height);
		widthSlider.addChangeListener(this);
		this.add(widthSlider);
		presetOk=new JButton("Manual Board");
		presetOk.addActionListener(this);
		this.add(presetOk);
		randomLabel= new JLabel("Frequency of \ninitial live cells");
		this.add(randomLabel);
		randomDisplay=new JLabel(random+"%");
		this.add(randomDisplay);
		randomSlider=new JSlider();
		randomSlider.setValue(random);
		randomSlider.setMaximum(100);
		randomSlider.setMinimum(1);
		randomSlider.addChangeListener(this);
		this.add(randomSlider);
		randomOk= new JButton("Random Board");
		randomOk.addActionListener(this);
		this.add(randomOk);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	//state change method for updating different sliders
	public void stateChanged(ChangeEvent arg0) {
		if(arg0.getSource().equals(hieghtSlider)) {
			height=hieghtSlider.getValue();
			hieghtDisplay.setText(""+height);
		}
		if(arg0.getSource().equals(widthSlider)) {
			width=widthSlider.getValue();
			widthDisplay.setText(""+width);
		}
		if(arg0.getSource().equals(delaySlider)) {
			delay=delaySlider.getValue();
			delayDisplay.setText(""+delay);
		}
		if(arg0.getSource().equals(randomSlider)){
			random=randomSlider.getValue();
			randomDisplay.setText(random+"%");
		}
		if(arg0.getSource().equals(expandSlider)) {
			expand=expandSlider.getValue();
			if(expand==0) {
				expandDisplay.setText("No");
			}
			if(expand==1) {
				expandDisplay.setText("Yes");
			}
		}
	}

	@Override
	//action performed method for registering which button gets pressed
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(randomOk)) {
			randomPressed=true;
			this.setVisible(false);
		}
		if(e.getSource().equals(importOk)) {
			path=JOptionPane.showInputDialog("Path to preset file? Default directory is project root.");
			importPressed=true;
			this.setVisible(false);
		}
		if(e.getSource().equals(presetOk)) {
			presetPressed=true;
			this.setVisible(false);
		}
	}
	//method to return randomPressed, which shows if randomOk has been pressed
	public boolean getRandomPressed() {
		return randomPressed;
	}
	//method to return presetPressed, which shows if presetOk has been pressed
	public boolean getPresetPressed() {
		return presetPressed;
	}
	//method to return importPressed, which shows if importOk has been pressed
	public boolean getImportPressed() {
		return importPressed;
	}
	//method to return width
	public int getArrayWidth() {
		return width;
	}
	//method to return height
	public int getArrayHeight() {
		return height;
	}
	//method to return expand, which controls whether the play area will expand if there is a live cell on the edge
	public int getExpand() {
		return expand;
	}
	//returns delay, to control how quick ticks are
	public int getDelay() {
		return delay;
	}
	//returns random, which controls the frequency of live cells in a random board
	public int getRandom() {
		return random;
	}
	//returns path, which is the path to a file to import from
	public String getPath() {
		return path;
	}
}
