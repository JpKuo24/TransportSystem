package Display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomCheckStation{
	
	public static JPanel ChooseStation (String m){
		int M =Integer.parseInt(m);
	   	String ss[][] = new String [4][4];        //���м��С�
   	
		final JPanel Cpanel = new JPanel();            // ��ʲô��ͬ����������
		Cpanel.setBounds(25, 173, 650, 160);
		Cpanel.setBorder(BorderFactory.createEtchedBorder());	
		Cpanel.setLayout(null);
			
	//  frame.setBounds(300, 100, 730, 450);
	//  scrollPane.setBounds(25, 25, 650, 150); 	
		
		JLabel label = new JLabel("Choose the station:");
		label.setBounds(20, 10, 150, 30);
		Cpanel.add(label);
	
		JComboBox<String> jb = new JComboBox<String>();
		jb.setBounds(35, 40, 80, 30);
		Cpanel.add(jb);
  //-------------------------------�������������-------------------------------------------------------//
		jb.addActionListener(
				new ActionListener(){
					 public void actionPerformed(ActionEvent e){
						 JComboBox temp = (JComboBox) e.getSource();
						  String name = (String) temp.getSelectedItem();
						  
						  DefaultTableModel tableModel = null;
						  Vector<String> columnNames = new Vector<String>();		
						  Vector<Vector<String>> data   = new Vector<Vector<String>>(); 	  
						  String abc[] = {"station", "train ID", "direction", "time to station"};
						 
						  for (int i=0; i<4; i++){   columnNames.add(abc[i]);	 }		
               //-----------------------------------����С���------------------------------------------//			  
						    tableModel = new DefaultTableModel(data,columnNames);  
							JTable table = new JTable(tableModel);
							JScrollPane scrollPanel = new JScrollPane(table);
							table.setSelectionBackground (Color.white);//������ѡ���еı���ɫ
					        table.setSelectionForeground (Color.blue);//������ѡ���е�ǰ��ɫ
					        table.setGridColor (Color.red);		
					        scrollPanel.setBounds(200, 0, 450, 160);     
					        Cpanel.add(scrollPanel);
					}
				}
		);	
//----------------------------���ļ�	������ss[][] ��ά����---------------------------------------------//
		 try{
	        	File fileRouteStation = new File("RouteStation.txt");
	        	FileReader fileReader = new FileReader(fileRouteStation);       	
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(fileReader);	        	        	
				
				String line  = null;	   	        	
	        	int a =0;
	        	while((line = reader.readLine()) != null){
	        		if(line.substring(1,2).equals(m)){
	        			ss[M][a] = line.substring(3);
	        			a++;
	        		}
	        	}	        
		 } catch(Exception ex){
	         	ex.printStackTrace();
	     }		
//---------------------------------------����������--------------------------------------------------------//	
//		if(jb.getItemCount()!=0){
//			   jb.removeAllItems();
//			   jb.updateUI();
//			   jb.setSelectedItem("");
//		}
		if(M==1){ 
			 for(int i=0;i<4;i++){
				 jb.addItem(ss[M][i]);
			 }
		 }else if(M==2){
			 for(int i=0;i<4;i++){
				 jb.addItem(ss[M][i]);
			 }
	     }		
		return Cpanel;	
	}
}


