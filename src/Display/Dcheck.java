package Display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import cw2.ControlCenter;
import cw2.Driver;
import cw2.Train;

public class Dcheck implements ActionListener{
	
	ControlCenter controlcenter;
	String DriverIDID;
	JButton button1 = new JButton("");
	 
	public Dcheck(ControlCenter C, String driverID){
		controlcenter = C;
		DriverIDID = driverID;
	}
	public JPanel DriverCheck(){	
		
	
		//-------------------------------------创建Panel------------------------------------------------------------//
		 	JPanel DCheckPanel = new JPanel();
		    DCheckPanel.setBounds(90, 25, 550, 350);  
		    DCheckPanel.setLayout(null);
		    DCheckPanel.setBorder(BorderFactory.createEtchedBorder());	   
			
			JPanel Nouse = new JPanel();
			Nouse.setBounds(0, 148, 230, 202);  
			Nouse.setLayout(null);
			Nouse.setBorder(BorderFactory.createEtchedBorder());	
			DCheckPanel.add(Nouse);
			 
			JLabel label1 = new JLabel("Hello Driver "+DriverIDID);
			label1.setBounds( 40, 60, 150, 15);
			Nouse.add(label1);
			
			JLabel label2 = new JLabel("");
			label2.setBounds(35, 145, 150, 15);
			Nouse.add(label2);
			
			
			 button1.setBounds(350, 275, 80, 30);
				    
			 JLabel label3 = new JLabel("");
			 label3.setBounds(300, 210, 250, 15);
			 DCheckPanel.add(label3);
			 
			 JLabel label4 = new JLabel("                            CONTROL         BOARD");
			 
			 label4.setBackground(Color.ORANGE);
			 label4.setBounds(226, 150, 321, 27);
			 label4.setOpaque(true);
			 DCheckPanel.add(label4);
		
//------------------------------------------		生成 panel-------------------------------------------------------------------------//
		Vector<String> Names = new Vector<String>();		
		Vector<Vector<Comparable>> Data   = new Vector<Vector<Comparable>>();            //所有排加起来
		
		Names.add("Driver ID");
		Names.add("Work State");
		Names.add("Route");
		Names.add("Train ID");
		Names.add("Working time");
		
		for (Driver c : controlcenter.drivers) {
			
			Vector<Comparable> data    = new Vector<Comparable>();
			data.add("     " + c.DriveID);
			data.add("         " + c.StateDrive);
			data.add("         " + c.DofR);
			data.add("         " + c.TofD);
//----------------------------------------------从文件fortable中读时间----------------------------------------------//			
		 try{
			 File myFile = new File("ForTable.txt");
		     FileReader fileReader = new FileReader(myFile);
		     BufferedReader reader = new BufferedReader(fileReader);		      	    		
		     String line1 = null;
			 String jobs = "" ;     	
			 Boolean WORK = false;
			 
		     while( (line1 = reader.readLine()) != null){
		    	  if(c.StateDrive=='0'){
		    		  data.add("      No job today~");	  
		    			if(c.DriveID.equals(DriverIDID) ){
		    				  label2.setText("No job Today~");	
		    			}		
		    	  }else if( (line1.substring(1,2).equals(String.valueOf(c.DofR)) )&&(line1.substring(4,5).equals(String.valueOf(c.TofD)) )  ){	
		      	    	StringTokenizer myTokenizer= new StringTokenizer(line1);  		
		      	    	for (int i = 0; i<4; i ++){ 
		      	    		myTokenizer.nextToken();      
		      	    	}
		      	    	//-------------------------------------计算工作时间--------------------------------------------------//
		      	    	String job = myTokenizer.nextToken();
		      	    	String[] s = job.split(":");
		      	    	int a = Integer.parseInt(s[0]);
		      	    	int b = Integer.parseInt(s[1]);		      	    	
		      	    	int Time1 = a*60+b;
		      	    	
		      	    	b = b +10;
		      	    	if (b ==60 ){		a=a+4;		b=0;		} else {a=a+3;}		      	    	
		      	    	int Time2 =a*60 +b;
		      	    	
		      	    	if (b!=0){
		      	    		jobs = jobs + "      From "+job +" to"+ (a+":"+b+".");
		      	    	}else{
		      	    		jobs = jobs + "      From " + job+" to"+ (a+":00.");
		      	    	}
		      	      //----------------------------------------------------------------------------------------------------//	
		      	    	if(c.DriveID.equals(DriverIDID) ){	   				
		   							label2.setText("Today your Train is: " + c.TofD);				
		   					 Calendar calendar = Calendar.getInstance();//可以对每个时间域单独修改
		   					 int hour = calendar.get(Calendar.HOUR_OF_DAY); 
		   					 int minute = calendar.get(Calendar.MINUTE); 
		   					 int Time = hour*60 +minute;
		   			
		   					 if(WORK == false){
		   						 if((Time>Time1)&&(Time<Time2)){
		   							 label3.setText("Your Train "+c.TofD+" is running now!");
		   							 button1.setText("STOP");
		   							 DCheckPanel.add(button1);
		   							 WORK = true;
		   						 }else{
		   							 label3.setText("Your Train "+c.TofD+" is stop now!");
		   						 }
		   					 }	 		   					 
		   				 }	   			
		      	   //--------------------------------------------------------------------------------------------------// 	
		      	    }
		      	 }
		      	data.add(jobs);    
		      	reader.close();   
		       } catch( Exception ex){
			        	ex.printStackTrace();
			   }			
			
			Data.add(data);		
		}		
//---------------------------------------给button加监听-----------------------------------------------------------//
		button1.addActionListener(this);
//-------------------------------------------------------------------------------------------------------------------//	
		DefaultTableModel tableModel = new DefaultTableModel(Data,Names);  
		JTable table = new JTable(tableModel);
		table.setSelectionBackground (Color.white);//设置所选择行的背景色
	    table.setSelectionForeground (Color.blue);//设置所选择行的前景色
	    table.setGridColor (Color.red); 
	//-----------------------------------设置列宽-------------------------------------------------------------------//    
	    TableColumnModel col = table.getColumnModel();
	    col.getColumn(0).setPreferredWidth(40);
	    col.getColumn(1).setPreferredWidth(40);
	    col.getColumn(2).setPreferredWidth(40);
	    col.getColumn(3).setPreferredWidth(40);
	    col.getColumn(4).setPreferredWidth(250);
	  //---------------------------------------------------------------------------------------------------------------//  
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(0, 0, 550, 150);    
	    DCheckPanel.add(scrollPane);
	    
		return  DCheckPanel;
	}

	public void actionPerformed(ActionEvent e) {
		int che = 0;
		if(button1.getText().equals("STOP")){button1.setText("START");}			
		else{button1.setText("STOP");}
		for (Driver c : controlcenter.drivers) {
			if(c.DriveID.equals(DriverIDID)){	
				che = c.TofD; 
				break;
			}
		}
		for (Train t : controlcenter.trains) {
			if ( t.NoTrain == che){
				System.out.println(t.Pause);
				if(t.Pause==false){ t.Pause  = true; }
				else{t.Pause = false; }
				System.out.println(t.Pause);
			}
		}				
	}
}