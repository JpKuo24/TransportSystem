package cw2;

import java.util.*;


public class ControlCenter {
	//static int countDriver=6;
	//static int countTrain=4;
	//static int countRoute=2;
	
	public static ArrayList<Driver> drivers = new ArrayList<Driver>();
	public static ArrayList<Train> trains = new ArrayList<Train>();
	static ArrayList<Route> routes = new ArrayList<Route>();
	static int[][] RoutePause;
	//RoutePause数组存的是突发事件的信息，第一的是route号，第二的是是否有事故0为无1为有，第三的是事故时间长度
	public ControlCenter(){}
	
	public void OpenWork(Driver a){
	  drivers.add(a);	
	}
	
	public void OpenTrain(Train b){
		trains.add(b);
	}
	
	public void OpenRoute(Route r){
		routes.add(r);
	}
	
	
	public String RanDisDriver(int NoTrain,int TofR,int notrip){
		
		ArrayList<Driver> drivers1 = new ArrayList<Driver>();
		int i=0;
		while(i<drivers.size()){
			if(drivers.get(i).StateDrive=='0')
			drivers1.add(drivers.get(i));
			i++;
		}
		//System.out.println(drivers1);
		
		int NoDriver=(int) (Math.random()*drivers1.size());
		String outcome=drivers1.get(NoDriver).DriveID;
		
		for (Driver c : drivers) {
			if(c.DriveID.equals(outcome)){
				c.GetWork(NoTrain,TofR);
				c.getNoTrip(notrip);
			}
		}
			
		return outcome;		
	}
	
	public void DistrDriver(){
		for (Train b : trains) {
			if(b.StateTrain=='1'){
				b.GetDriver(this.RanDisDriver(b.NoTrain,b.RofT,b.NoTrip));
				//System.out.println(b.DofT+" drivers "+b.NoTrain);
			}
			//a.GetWork(this.RanDisDriver());
			//System.out.println(a.StateDrive+" drives "+a.NoTrain);
		}
	}
	
	public  void DistrTrain(){
		
		for (Route r: routes){
			int i=0;
			while(r.CoTrofRo<r.ToCoTrofRo){
				i++;
				this.RanDisTrain(r.NoRoute,r,i);
				r.CoTrofRo++;
			}
			
		}
		
	}
	/*public void DistrTrain(){
		for (Train b : trains) {
			b.GetRoute(this.RanDisDriver().substring(0, 4), this.RanDisDriver().substring(4));
		}
	}*/
	
	public void RanDisTrain(int NoRoute, Route r,int notrip){
		
		ArrayList<Train> trains1 = new ArrayList<Train>();
		int i=0;
		while(i<trains.size()){
			
			if(trains.get(i).StateTrain=='0')
			trains1.add(trains.get(i));
			i++;
		}
		
		int WhichTrain=(int) (Math.random()*trains1.size());
		int outcome=trains1.get(WhichTrain).NoTrain;
		
		for (Train c : trains) {
			if(c.NoTrain==outcome){
				r.myTrains.add(c);
				c.GetRoute(NoRoute);
				c.getNoTrip(notrip);
			}
		}
	}
	
	public void sort(){
		SortforTable.sort(trains);
	}
}
