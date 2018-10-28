package Display;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomTime {
	public static JScrollPane intiComponent(String m){
		
		 ArrayList<CustomTimeSort>TIME=new ArrayList<CustomTimeSort>();  
		 DefaultTableModel tableModel = null;
		 Vector<String> columnNames = new Vector<String>();		
		 Vector<Vector<String>> data   = new Vector<Vector<String>>();            //所有排加起来
		 
		 try{
	        	File fileRouteStation = new File("RouteStation.txt");
	        	FileReader fileReader = new FileReader(fileRouteStation);
	        	
				BufferedReader reader = new BufferedReader(fileReader);	        	
	        	String line  = null;

	        	String [] abc = new String [10];
	        	
	        	int a =0;
	        	while((line = reader.readLine()) != null){
	        		if(line.substring(1,2).equals(m)){
	        			abc[a] = line.substring(3);
	        			abc[7-a] = line.substring(3);
	        			a++;
	        		}
	        	}	        
	        	columnNames.add("Train ID");
	        	for (int i = 0; i < 8 ; i++){ 
	        		columnNames.add(abc[i]);
	        		}	   
	        	reader.close();   	    
		 } catch(Exception ex){
	         	ex.printStackTrace();
	     }

		 try{
	        	File myFile = new File("ForTable.txt");
	        	FileReader fileReader = new FileReader(myFile);
	        	BufferedReader reader = new BufferedReader(fileReader);
	        	
	        	String line1 = null;
	      	    while( (line1 = reader.readLine()) != null){
	      	    	if(line1.substring(1,2).equals(m)){	
	      	    		String[] ti = new String[10];
	      	    		StringTokenizer myTokenizer= new StringTokenizer(line1);
     //  --------------------OK-----------------------------------------   		
	      	    		myTokenizer.nextToken();                                                      //第一位不要
	      	    		ti[0] = myTokenizer.nextToken();                               //第二位是车ID
	      	    		for(int i = 0; i<2; i++){                                                        //后两位不要
	      	    			myTokenizer.nextToken();
	      	    		}    
	      	    		for(int i =1; i < 9 ; i++){                                                        //后八位为时间表
	      	    			ti[i]	= myTokenizer.nextToken();
	      	    		}
     	
        			TIME.add( new CustomTimeSort(ti[0], ti[1], ti[2], ti[3], ti[4] ,ti[5] ,ti[6], ti[7], ti[8])   );
        			Collections.sort(TIME);                                                                     //Sorting.
	      	    	}
	      	    }
	      	    	for (int i=0; i< TIME.size(); i++){
	      	    		Vector<String> rowData  = new Vector<String>();     //一排
	      	    		for(int j=0; j<9; j++){
	      	    			rowData.add(TIME.get(i).S[j]);
	      	    		}
	      	    		data.add(rowData);
	      	    }
	      	  	reader.close();   
	       } catch( Exception ex){
		        	ex.printStackTrace();
		   }
		 
		    tableModel = new DefaultTableModel(data,columnNames);  
			JTable table = new JTable(tableModel);
			JScrollPane scrollPane = new JScrollPane(table);
			table.setSelectionBackground (Color.white);//设置所选择行的背景色
	        table.setSelectionForeground (Color.blue);//设置所选择行的前景色
	        table.setGridColor (Color.red);
	        scrollPane.setBounds(25, 25, 650, 150);                    //大小和位置
		return scrollPane;
	      	
	    }
}
