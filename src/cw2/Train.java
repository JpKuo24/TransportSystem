package cw2;

import java.io.*;
import java.util.*;


public class Train {
	ArrayList<String> myStop= new ArrayList<String>();
	
	String nextStop;
	public Boolean Pause=false;
	public int NoTrain=0;
	public char StateTrain='0';
	String DofT="nobody";
	int RofT=0;
	int NoTrip;
	String TrainTable;//文件中的每一行字
	String GoTime=null;
    String BackTime=null;
    String[][] StationAndTime;
	public Train(){}
	
	public Train(int NoTrain){
		this.NoTrain=NoTrain;
	}
	
	public void GetDriver(String DofT){
		this.DofT=DofT;
	}
	public void GetRoute(int RofT){
		
		this.StateTrain = '1';
		this.RofT = RofT;
		
	}
	
	public String toString(){
		return "Train"+this.NoTrain+" of Route "+this.RofT+" driver is "+DofT+" I am "+this.NoTrip;
	}
	
	public void getNoTrip(int NoTrip){
		this.NoTrip=NoTrip;
	}
	
	public void SearchTableTrain(){
		this.TrainTable=TimeTable.SearchTime(RofT, NoTrip);
		this.GoTime=this.TrainTable.substring(11, 16);
		this.BackTime=this.TrainTable.substring(this.TrainTable.length()-6,this.TrainTable.length()-1);
		System.out.println(this.TrainTable);
	}
	public void GetStop(){
		String thisLine;
		FileReader fr;
		int FileRouteNo;
		try{
			fr=new FileReader("RouteStation.txt");
			BufferedReader bfr = new BufferedReader(fr);
			while((thisLine=bfr.readLine())!=null){
				FileRouteNo=Integer.parseInt(thisLine.substring(0, 2));
				if(FileRouteNo==this.RofT){
				myStop.add(thisLine.substring(3));
				}
			}
			fr.close();
		}
		catch(IOException ioe){}
		System.out.println(this.myStop);
		this.MakeStationAndTime();
	}
	
	public void MakeStationAndTime(){
		StationAndTime=new String[myStop.size()][1+(TrainTable.charAt(6)-48)*2];
		int NofStation=Integer.parseInt(TrainTable.substring(8, 10));
		for(int i=0;i<myStop.size();i++){
			this.StationAndTime[i][0]=myStop.get(i);
			for(int j=1;j<(TrainTable.charAt(6)-48)*2;j=j+2){
				this.StationAndTime[i][j]=TrainTable.substring(11+6*i+((j-1)/2)*2*NofStation*6, 16+6*i+((j-1)/2)*2*NofStation*6);
				this.StationAndTime[i][j+1]=TrainTable.substring(11+6*i+((NofStation-i-1)*2+1)*6+((j-1)/2)*2*NofStation*6,16+6*i+((NofStation-i-1)*2+1)*6+((j-1)/2)*2*NofStation*6);
			}
		}
		/*for(int i=0;i<myStop.size();i++){		//打出StationAndTime
			for(int j=0;j<1+(TrainTable.charAt(6)-48)*2;j++){
					System.out.println(StationAndTime[i][j]);
			}
		}*/
	}
}
