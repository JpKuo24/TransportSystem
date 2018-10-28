package cw2;

import java.util.*;


public class Route {
	int NoRoute;
	int ToCoTrofRo=0;
	int CoTrofRo=0;
	int NoTrip=0;
	ArrayList<Train> myTrains = new ArrayList<Train>();
	
	public Route(){}
	
	public Route(int NoRoute){
		
		this.NoRoute = NoRoute;
	}
	public String toString(){
		//return "Route"+this.NoRoute+" has train "+this.myTrains;
		String TrainName="";
		int i=0;
		while(i<myTrains.size()){
			TrainName=TrainName+" Train"+myTrains.get(i).NoTrain;	
			i++;
		}

		return "Route"+this.NoRoute+" has train:"+TrainName;
	}
}
