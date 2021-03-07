package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class QuestManagerFrame extends JFrame implements ActionListener {
	
	private LifeRPG motherFrame;
	
	public QuestManagerFrame(LifeRPG _motherFrame){
		motherFrame = _motherFrame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
	}
	
	public QuestManagerFrame(){
		String[][] panels = new String[3][3];
		
		for(int i = 0; i < panels.length; i++)
			for(int j = 0; j < panels[i].length; j++)
				panels[i][j] = i + " " + j;
		
		String[] someColumns = new String[3];
		for(int i = 0; i < 3; i ++)
			someColumns[i] = "Column " + (i + 1);
		
		JTable table = new JTable(panels, someColumns);
		JScrollPane pane = new JScrollPane(table);
		
		add(pane);
		pack();
	}
	
	public static void main(String[] args){
		new QuestManagerFrame().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
