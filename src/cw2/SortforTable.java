package cw2;

import java.io.*;
import java.util.*;


public class SortforTable {

	public static void sort(ArrayList<Train> train){

		FileWriter fw = null;
		try {
			fw = new FileWriter("ForTable.txt");
		} catch (IOException e1) {}
		
		int Count_Station;
		int Count_GoBack;
		String notrain;
		String tableHead;
		ArrayList<String[]> InputList=new ArrayList<String[]>();
		for(Train t:train){
			Count_Station=Integer.parseInt(t.TrainTable.substring(8, 10));
			Count_GoBack=Integer.parseInt(t.TrainTable.substring(6, 7));
			String[] TrainTable_1=new String[Count_GoBack];
			if(t.NoTrain<10){
				notrain="0"+t.NoTrain;
			}
			else{
				notrain=""+t.NoTrain;
			}
			
			tableHead=t.TrainTable.substring(0, 3)+notrain+" 1"+t.TrainTable.substring(7,11);
			for(int i=0;i<TrainTable_1.length;i++){
				TrainTable_1[i]=tableHead+t.TrainTable.substring(11+i*Count_Station*2*6, 11+Count_Station*2*6+i*Count_Station*2*6);
			}
			InputList.add(TrainTable_1);
		}	
		   
		try {
		    for(int i=0;i<10;i++){
		      for(int j=0;j<InputList.size();j++){
		    	  if(i<InputList.get(j).length){
		          fw.write(InputList.get(j)[i]);
		          fw.write("\r\n");
		          fw.flush();
		    	  }
		      }
		    }
	      } catch (IOException e) {}
		
	}
}
