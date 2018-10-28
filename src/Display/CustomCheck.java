package Display;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomCheck {
	
	public static JScrollPane intiComponent(String m){
		 DefaultTableModel tableModel = null;
		 Vector<String> columnNames = new Vector();		
		 
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
	        	for (int i = 0; i < 8 ; i++){ 
	        		columnNames.add(abc[i]);
	        		}
	      
		 } catch(Exception ex){
	         	ex.printStackTrace();
	     }
		 
		 Vector<Vector<String>> data   = new Vector<Vector<String>>();            //所有排加起来
		
		 try{
	        	File myFile = new File("TimeTable.txt");
	        	FileReader fileReader = new FileReader(myFile);
	        	BufferedReader reader = new BufferedReader(fileReader);

	      	    String contents  = reader.readLine();
	      	    Vector<String> rowData1  = new Vector<String>();  
	      	    while( contents!= null){
	      	    	if(contents.substring(1,2).equals(m)){
	      	    		Vector<String> rowData  = new Vector<String>();     //一排
	      	    		StringTokenizer myTokenizer= new StringTokenizer(contents);
           			for(int i = 0; i< 4; i++){
           				myTokenizer.nextToken();
           			}            			
           			for(int i =0; i < 8 ; i++){
           				rowData.add(myTokenizer.nextToken());
           			}
           		    data.add(rowData);  
           		    if(contents.substring(6,7).equals("2")){
           				for(int i = 0 ; i < 8 ; i++){
           					rowData1.add(myTokenizer.nextToken());
           		        }
           		    }  
	      	    	}	      	    	
	      	    	contents  = reader.readLine();
				}
	      	  data.add(rowData1);  
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
        scrollPane.setBounds(25, 30, 650, 200);                    //大小和位置
        
  //      scrollPane.setOpaque(false);
        
        return scrollPane;
	}

}
