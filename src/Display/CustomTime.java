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
		 Vector<Vector<String>> data   = new Vector<Vector<String>>();            //�����ż�����
		 
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
	      	    		myTokenizer.nextToken();                                                      //��һλ��Ҫ
	      	    		ti[0] = myTokenizer.nextToken();                               //�ڶ�λ�ǳ�ID
	      	    		for(int i = 0; i<2; i++){                                                        //����λ��Ҫ
	      	    			myTokenizer.nextToken();
	      	    		}    
	      	    		for(int i =1; i < 9 ; i++){                                                        //���λΪʱ���
	      	    			ti[i]	= myTokenizer.nextToken();
	      	    		}
     	
        			TIME.add( new CustomTimeSort(ti[0], ti[1], ti[2], ti[3], ti[4] ,ti[5] ,ti[6], ti[7], ti[8])   );
        			Collections.sort(TIME);                                                                     //Sorting.
	      	    	}
	      	    }
	      	    	for (int i=0; i< TIME.size(); i++){
	      	    		Vector<String> rowData  = new Vector<String>();     //һ��
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
			table.setSelectionBackground (Color.white);//������ѡ���еı���ɫ
	        table.setSelectionForeground (Color.blue);//������ѡ���е�ǰ��ɫ
	        table.setGridColor (Color.red);
	        scrollPane.setBounds(25, 25, 650, 150);                    //��С��λ��
		return scrollPane;
	      	
	    }
}
