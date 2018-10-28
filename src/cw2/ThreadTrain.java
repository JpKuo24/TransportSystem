package cw2;


public class ThreadTrain extends Thread{
	Train thistt;
	String StartAccident;
	String EndAccident;
	String NowTime;
	int HappenAccident = 1;
	int LengthAccdient;
	boolean Go=false;
	boolean Back=false;
	public ThreadTrain(){}
	public ThreadTrain(Train t){
		this.thistt=t;
	}
	
	public void run(){
		
		while(true&&(!Back)){
			try{
				sleep(6000);
			}
			catch(InterruptedException e){}

			HappenAccident=ControlCenter.RoutePause[this.thistt.RofT-1][1];
			//车是否停了
			if(HappenAccident==1){
				//车停了
				while(ControlCenter.RoutePause[this.thistt.RofT-1][1]==1){
					try {
						sleep(6000);
					} catch(InterruptedException e){}
				}
				//事故结束，开始延时
				this.StartAccident=Timer.nowTime;
				this.thistt.TrainTable=Timer.AllDelay(this.thistt.TrainTable, this.StartAccident, ControlCenter.RoutePause[this.thistt.RofT-1][2]);
				System.out.println("Testing Train"+this.thistt.NoTrain+this.thistt.TrainTable);
				this.Update();
			}
			
			Go=this.thistt.GoTime.equals(Timer.nowTime);
			while(Go&&(!Back)){
				try{
					sleep(6000);
				}
				catch(InterruptedException e){}
				System.out.println("Train"+this.thistt.NoTrain+" is running");
				this.NowTime=Timer.nowTime;
				Back=this.thistt.BackTime.equals(this.NowTime);
				this.myNextStop();
				
				
				if(this.thistt.Pause){
					ControlCenter.RoutePause[this.thistt.RofT-1][1]=1;
					this.StartAccident=this.NowTime;
					while(this.thistt.Pause){
						ControlCenter.RoutePause[this.thistt.RofT-1][1]=1;
						try {
							sleep(6000);
							System.out.println("Train"+this.thistt.NoTrain+" is pausing by accident");
						} 
						catch (InterruptedException e){}
					}
					this.NowTime=Timer.nowTime;
					LengthAccdient=(Integer.parseInt(this.NowTime.substring(0, 2))*60+Integer.parseInt(this.NowTime.substring(3)))-(Integer.parseInt(StartAccident.substring(0, 2))*60+Integer.parseInt(StartAccident.substring(3)));
					this.thistt.TrainTable=Timer.AllDelay(this.thistt.TrainTable, this.StartAccident, LengthAccdient);
					ControlCenter.RoutePause[this.thistt.RofT-1][2]=LengthAccdient;
					ControlCenter.RoutePause[this.thistt.RofT-1][1]=0;
					this.thistt.MakeStationAndTime();
					System.out.println(this.thistt.TrainTable);
				}
				
				
			}
		}
		System.out.println("I am back!");
	}
	
	
	
	public void myNextStop(){
		
		for(int i=1;i<this.thistt.StationAndTime[0].length;i++){
			for(int j=0;j<this.thistt.StationAndTime.length-1;j++){
				double now=Integer.parseInt(Timer.nowTime.substring(0, 2))+Integer.parseInt(Timer.nowTime.substring(3))/60.0;
				double last=Integer.parseInt(this.thistt.StationAndTime[j][i].substring(0, 2))+Integer.parseInt(this.thistt.StationAndTime[j][i].substring(3))/60.0;
				double next=Integer.parseInt(this.thistt.StationAndTime[j+1][i].substring(0, 2))+Integer.parseInt(this.thistt.StationAndTime[j+1][i].substring(3))/60.0;
				if(last<now&&now<next){
					System.out.println("From "+this.thistt.StationAndTime[j][0]);
					System.out.println("To "+this.thistt.StationAndTime[j+1][0]);
				}
				if(next<now&&now<last){
					System.out.println("From "+this.thistt.StationAndTime[j+1][0]);
					System.out.println("To "+this.thistt.StationAndTime[j][0]);
				}

			}
			
		}
	}//myNextStop方法的结束
	
	public void Update(){
		this.thistt.GoTime=this.thistt.TrainTable.substring(11, 16);
		this.thistt.BackTime=this.thistt.TrainTable.substring(this.thistt.TrainTable.length()-6,this.thistt.TrainTable.length()-1);
		this.thistt.MakeStationAndTime();
	}
}
