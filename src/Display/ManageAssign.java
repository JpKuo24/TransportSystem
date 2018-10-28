package Display;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import cw2.Train;

public class ManageAssign {
	
  private JComboBox<String> comboBox,comboBox1,comboBox2,comboBox3;
  private JTextField field,field1,field2,field3;
  private JLabel statusLabel,statusLabel1;
  int n;
  public String result,routetime,routetime1,result1;

public static String route,train,driver,item,routeID;
  public static String[] content;
  String contents;
  public static int min_new,h_new,a,i;
  static ArrayList<String> list   = new ArrayList<String>();
  static ArrayList<String> list1 = new ArrayList<String>();
  static ArrayList<String> list2 = new ArrayList<String>();
  static ArrayList<String> list3 = new ArrayList<String>();
  Object[] option={"ok"};
  static boolean mAA;
  
  public JPanel manageAssign(Train t, boolean mAA){
	  	 JPanel panel = new JPanel(); 
		 panel.setBounds(12,3,700,390);
		 panel.setBorder(BorderFactory.createEtchedBorder());	   
		 panel.setLayout(null);
		  
		 JLabel llabel = new JLabel("Manager  please  choose  Driver,  Train,  and  Route  one  by  one  "
		 		                                                 + "to  assign  jobs  in  the  early  morning (OBLIGATORY)");
		 llabel.setBounds(20,20, 670, 30);
		 
		 comboBox= new JComboBox<String>();
		 comboBox.setBounds(410,70,80,25);
		 field = new JTextField("Choose Driver");
		 field.setBounds(210, 70, 120, 25);
		   
		 comboBox1= new JComboBox<String>();
		 comboBox1.setBounds(410, 110, 80, 25);
		 field1 = new JTextField("Choose Train");
		 field1.setBounds(210, 110, 120, 25);
		 
		 comboBox2= new JComboBox<String>();
		 field2 = new JTextField("Choose Route");
		 comboBox2.setBounds(120, 150, 560 ,25);
		 field2.setBounds(20, 150, 90, 25);
		 
		 comboBox3= new JComboBox<String>();
		 field3 = new JTextField("Choose Route");
		 comboBox3.setBounds(410, 150, 80 ,25);
		 field3.setBounds(210, 150, 120, 25);
		 
		  Button btn=new Button("confirm");
		  btn.setBounds(200, 350, 80,20);
		  
		  final Button btn1=new Button("add");
		  btn1.setBounds(400,350,80,20);
		  
		  statusLabel = new JLabel("");    
	      statusLabel1 = new JLabel("");  
	      statusLabel1.setBounds(230,270,100,30);
	      statusLabel.setBounds(420,270,100,30);      
//---------------------------------------------给Driver下拉框填充------------------------------------------------------//	  
  if(mAA == false){
	      try{
	  		File myFile = new File("Driver.txt");
	  	   	 FileReader fileReader = new FileReader(myFile);
	  	 	 BufferedReader reader = new BufferedReader(fileReader);

	  		 contents=reader.readLine();
	  		 while( contents!= null){
	  		    	String[] content=contents.split(" ");
	  		    	list.add(content[0]);
	  		    	contents  = reader.readLine();
	  	      }
	  	} catch(Exception ex){
	  	         	ex.printStackTrace(); }	
	  //----------------------------------------------给Train下拉框填充--------------------------------------------------------//	    
	  	try{
	  		 File myFile1 = new File("Train.txt");
	  	     FileReader fileReader1 = new FileReader(myFile1);
	  		 BufferedReader reader1 = new BufferedReader(fileReader1);
	  		 contents  = reader1.readLine();
	  		 while( contents!= null){
	  				 String[] content=contents.split(" ");
	  				 list1.add(content[0]);
	  				 contents  = reader1.readLine();
	  		 }
	  	} catch(Exception ex){
	  	         	ex.printStackTrace(); }	
	  //----------------------------------------------给Route下拉框填充---------------------------------------------------------//	  
	  	try{
	  		 File myFile2 = new File("TimeTable.txt");
	  	   	 FileReader fileReader2 = new FileReader(myFile2);
	  	 	 BufferedReader reader2 = new BufferedReader(fileReader2);
	  	 	contents=reader2.readLine();
	  		 while(contents!=null){
	  			 list3.add(contents);
	  			 if((list3.get(i)).substring(6,7).equals("2"))	 {
	  				 route=list3.get(i).substring(11,106);}
	  			 else{ route=list3.get(i).substring(11,58); }	 
	  			 list2.add(route);
	  			 i++;
	  			 contents=reader2.readLine();
	  		   }    
	  		 list3.remove(null);
	  	} catch(Exception ex){
	  	         	ex.printStackTrace();}		
  }
//--------------------------------------part 1 -------------------------------------------------------------//	  
	 comboBox.addItem("driver");
	 for(n=0;n<list.size();n++)
	 {
	 comboBox.addItem((String)list.get(n));
	 }
	  comboBox.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e){
			   driver  =(String)comboBox.getSelectedItem();
			 if("driver".equals(driver)){
				 field.setText("");
			 }else{
				 field.setText("select driver  "+driver);
			 }
		   }
		  });
//--------------------------------------------------part 2-------------------------------------------------------//	  
	
	 comboBox1.addItem("train");
	 for(n=0;n<list1.size();n++)
	 {
	 comboBox1.addItem((String)list1.get(n));
	 }
		if(t.StateTrain==0)
		{
	     comboBox1.addItem(train);
		} 
			  comboBox1.addActionListener(new ActionListener() {
				  public void actionPerformed(ActionEvent e){
					  train  =(String)comboBox1.getSelectedItem();
					 if("train".equals(train)){
						 field1.setText("");
					 }else{
						 field1.setText("select train     "+train);
					 }
				   }

				  });		  
//---------------------------------------------------part 3------------------------------------------------------------//			  
		 comboBox2.addItem("route");
		 for(n=0;n<list2.size();n++)
		 {
		 comboBox2.addItem((String)list2.get(n));
		 }
			  comboBox2.addActionListener(new ActionListener() {
				  public void actionPerformed(ActionEvent e){
					  item  =(String)comboBox2.getSelectedItem();
					 if("route".equals(item)){
						 field2.setText("");
					 }else{
						 field2.setText("Success");
					 }
					 if("08:30 09:00 09:30 10:00 10:10 10:40 11:10 11:40 11:50 12:20 12:50 13:20 13:30 14:00 14:30 15:00".equals(item)){
						 routeID="01";
					 }
					 else if("09:00 09:30 10:00 10:30 10:40 11:10 11:40 12:10".equals(item))
						 routeID="01";
					 else if("09:30 10:00 10:30 11:00 11:10 11:40 12:10 12:40".equals(item))
						 routeID="02";
					 else if("08:15 08:45 09:15 09:45 09:55 10:25 10:55 11:25".equals(item))
					     routeID="03";
				   }
				  });
//----------------------------------------------button---------------------------------------------------------------//
	
	  btn.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			 if(driver!=null&&train!=null&&item!=null)
	       {
			 if("driver".equals(driver)||"train".equals(train)||"route".equals(item))
				  JOptionPane.showOptionDialog(null,"Invalid input,you should choose specific objects","warning",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, option, option[0]);
			 else
			 {	 
			 certain();
//	          Train t= new Train();          
//		      new ManageAssign(t);
//	          manageAssign(t);
			 }
	       }
			 else if(driver!=null&&train!=null&&item==null)
			  {
				  if("driver".equals(driver)||"train".equals(train))
			  {
					  JOptionPane.showOptionDialog(null,"Invalid input,you should choose specific objects","warning",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, option, option[0]);
			  }
			    if((h_new!=8||min_new!=30)&&(h_new!=8||min_new!=15)&&(h_new!=9||min_new!=30)&&(h_new!=9||min_new!=0)&&(h_new!=11||min_new!=50))
				  {
					   item="";routetime1="";routetime="";
				    for(n=0;n<8;n++)
				    { 
				    	if(h_new < 10)
				    		routetime="0".concat(String.valueOf(h_new));
				    	else
				    		routetime=String.valueOf(h_new);
				    	if(min_new<10)
				    	    routetime1="0".concat(String.valueOf(min_new));
				    	else
				    		routetime1=String.valueOf(min_new);
				        item=item.concat(routetime);
				        item=item.concat(":");
				        item=item.concat(routetime1);
					    item=item.concat(" ");
				    	if(min_new>29)
				    	{
				    	min_new=min_new-30;
				    	h_new=h_new+1;
				    	}		
				    	else
				    	{
				    	 min_new=min_new+30;
				    	}
				    	if(h_new>23)
				    		h_new=h_new-24;
				    }
				      a=1;
				      certain();
//				         Train t= new Train();
//	//				     new ManageAssign(t);
//				         manageAssign(t);
		  }
         }
			 else 
				 JOptionPane.showOptionDialog(null,"You are supposed to full in all the blanks before confirm"
						 ,"warning",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, option, option[0]);
       }
	  });
//-----------------------------------------------时间---------------------------------------------------------//	  	  
	  Calendar c = Calendar.getInstance();
	  int minute = c.get(Calendar.MINUTE); 
	  int hour=c.get(Calendar.HOUR_OF_DAY);
	  
	  SpinnerModel spinnerModel =new SpinnerNumberModel(minute, minute, 59, 1);//step
		      final JSpinner spinner = new JSpinner(spinnerModel);
		      SpinnerModel spinnerModel1 = new SpinnerNumberModel(hour, hour, 23, 1);
		      final JSpinner spinner1 = new JSpinner(spinnerModel1);
			      spinner1.setBounds(230,230, 50, 30);
			       spinner.setBounds( 425, 230, 50, 30);
		      spinner.setVisible(false);
		      spinner1.setVisible(false);
		      
		      spinner.addChangeListener(new ChangeListener() {
		         public void stateChanged(ChangeEvent e2) {
		        	 min_new=(int) ((JSpinner)e2.getSource()).getValue();
		            statusLabel.setText("Minute : " + min_new);
		         }
		      });
		      spinner1.addChangeListener(new ChangeListener() {
			         public void stateChanged(ChangeEvent e3) {
			        	 h_new=(int) ((JSpinner)e3.getSource()).getValue();
			            statusLabel1.setText("Hour : " + h_new );
			         }
			      });
//----------------------------------------------------part 4-----------------------------------------------------------//		      			
				 comboBox3.addItem("route");
				 comboBox3.addItem("01");
				 comboBox3.addItem("02");		
				 comboBox3.addActionListener(new ActionListener() {
					  public void actionPerformed(ActionEvent e){
						  routeID  =(String)comboBox3.getSelectedItem();
						 if("route".equals(routeID)){
							 field3.setText("");
						 }else{
							 field3.setText("select route    "+routeID);
						 }
					   }
					  });
				  comboBox3.setVisible(false);
				  field3.setVisible(false);
//--------------------------------------------------------------------------------------------------------------------//	      		           
			  btn1.addActionListener(new ActionListener(){
				  public void actionPerformed(ActionEvent e4){					 
					  		  comboBox2.setVisible(false);
					  		  field2.setVisible(false);
					  		  
					  		  comboBox3.setVisible(true);
							  field3.setVisible(true);
						      
						      btn1.setVisible(false);
						      spinner.setVisible(true);
						      spinner1.setVisible(true);	    						      
				  }
				  });	 
	//-------------------------------------------关于frame---------------------------------------------------------//
	panel.add(llabel);
	panel.add(comboBox);
	panel.add(field);
	panel.add(comboBox1);
	panel.add(field1);
	panel.add(comboBox2);
	panel.add(field2);      
	panel.add(btn);
	panel.add(btn1);
	panel.add(comboBox3);
	panel.add(field3);
	panel.add(spinner);
	panel.add(spinner1);
	panel.add(statusLabel);
	panel.add(statusLabel1);
	
	return panel;   
//---------------------------------------------------------------------------------------------------------------//  
	 }
//public static void main(String[] args) {
//		    Train t= new Train();	
//		    ManageAssign Managea =new ManageAssign();
//		    JPanel ppanel = new JPanel();
//	        ppanel = Managea.manageAssign(t);
//
////	        JFrame fframe = new JFrame();
////	        fframe.setBounds(300, 100, 730, 450); 
////	    	fframe.setLayout(null);
////	    	fframe.add(ppanel);
////	    	fframe.setVisible(true);
//   }

public void certain(){
try {
	    list.remove(list.indexOf(driver));
	    list1.remove(list1.indexOf(train));
	    if(a!=1)
	    {
		 list2.remove(list2.indexOf(item));
	    }
		FileWriter write = new FileWriter("assign.txt",true);
		      result=driver+" "+train+" "+routeID+" "+item;
		      write.write(result);
		      write.write("\r\n");
		      write.close();
		FileWriter write1 = new FileWriter("TimeTable.txt",true);
		FileWriter write3 = new FileWriter("TempTime.txt",true);
		   if(a==1)
		   {  
			result1=routeID+" "+train+" "+"1"+" "+"04"+" "+item;
			System.out.println(result1);
		   write3.write(result1);
		   write3.write("\r\n");
		   //list3.add(result1);
		   write3.close();
		   }
		   else
		   {	   
			   FileWriter write2 = new FileWriter("TimeTable.txt");
			      write2.write("");
			      write2.close();
		   for(int i=0;i<list3.size();i++)
		   {
		   if(((list3.get(i)).indexOf(item))!=-1)
		    {
			   result1=list3.get(i).substring(0,3)+train+list3.get(i).substring(5);
			   list3.set(i,result1);
		    }
		     write1.write(list3.get(i));
		     write1.write("\r\n");
		   }
		   }
		   write1.close();
		     item=null;
		     a=0;
	} catch (IOException e1) {
		e1.printStackTrace();
	}
}
}
