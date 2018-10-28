package Display;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MLocation {
	public void CheckLocation(){
		
		try{
			 File myFile = new File("ForTable.txt");
		     FileReader fileReader = new FileReader(myFile);
		     BufferedReader reader = new BufferedReader(fileReader);		      	    		
		     String line = null;
		     while( (line = reader.readLine()) != null){
		    	 
		     }
		     
		}catch( Exception ex){
       	ex.printStackTrace();
	    }		
		
		JFrame frame = new JFrame("123");		
		frame.setLayout(null);                   
		frame.setBounds(300, 100, 730, 450);               
		frame.setResizable(false);
		frame.setVisible(true);
		
		JPanel Panel = new JPanel();                       
		Panel.setBounds(90, 25, 550, 350); 
		Panel.setLayout(null);
		Panel.setBorder(BorderFactory.createEtchedBorder());	   
		frame.add(Panel); 

		 JComboBox<String> jb = new JComboBox<String>();
		 jb.setBounds(35, 40, 80, 30);
		 Panel.add(jb);
		 jb.addItem("");
		 jb.addItem("01");
		 jb.addItem("02");
		 jb.addItem("03");
		 jb.addItem("04");
		
		JLabel S1 = new JLabel("Center");
		S1.setBounds( 20, 188, 70, 15);
		Panel.add(S1);	
		JLabel label1 = new JLabel();
		label1.setBounds( 70, 190, 70, 2);
		label1.setBackground(Color.green);
		label1.setOpaque(true);
		Panel.add(label1);
		JLabel label4 = new JLabel();
		label4.setBounds( 70, 197, 70, 2);
		label4.setBackground(Color.green);
		label4.setOpaque(true);
		Panel.add(label4);
		
		JLabel S2 = new JLabel("Station_1A");
		S2.setBounds( 150, 188, 70, 15);
		Panel.add(S2);	
		JLabel label2 = new JLabel();
		label2.setBounds( 220, 190, 70, 2);
		label2.setBackground(Color.green);
		label2.setOpaque(true);
		Panel.add(label2);
		JLabel label5 = new JLabel();
		label5.setBounds( 220, 197, 70, 2);
		label5.setBackground(Color.green);
		label5.setOpaque(true);
		Panel.add(label5);
		
		JLabel S3 = new JLabel("Station_1B");
		S3.setBounds( 300, 188, 70, 15);
		Panel.add(S3);	
		JLabel label3 = new JLabel();
		label3.setBounds( 370, 190, 70, 2);
		label3.setBackground(Color.green);
		label3.setOpaque(true);
		Panel.add(label3);
		JLabel label6 = new JLabel();
		label6.setBounds( 370, 197, 70, 2);
		label6.setBackground(Color.green);
		label6.setOpaque(true);
		Panel.add(label6);
		
		JLabel S4 = new JLabel("Station_1C");
		S4.setBounds( 450, 188, 70, 15);
		Panel.add(S4);	
		
		frame.repaint();
		
	}
	public static void main (String args[]){
		MLocation m = new MLocation();
		m.CheckLocation();
	}
	

}
