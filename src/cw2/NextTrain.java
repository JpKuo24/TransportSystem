package cw2;

import java.util.ArrayList;

public class NextTrain {
	
	public ArrayList<Integer> Show(int RouteNum,String StationName){
		ArrayList<Integer> WhenNextTrain = new ArrayList<Integer>();
		int ReachTime = 0;
		for(Train t :  ControlCenter.trains){
			if(t.RofT==RouteNum){
				for(int i=0;i<t.StationAndTime.length;i++){
					if(t.StationAndTime[i][0].equals(StationName)){
						for(int j=1;j<t.StationAndTime[i].length;j++){
							ReachTime=(Integer.parseInt(Timer.nowTime.substring(0, 2))*60+Integer.parseInt(Timer.nowTime.substring(3)))-(Integer.parseInt(t.StationAndTime[i][j].substring(0,2))*60+Integer.parseInt(t.StationAndTime[i][j].substring(3)));
							if(ReachTime>0){
								WhenNextTrain.add(ReachTime);
							}
						}
					}
				}
			}
		}
		return WhenNextTrain;
	}
}
