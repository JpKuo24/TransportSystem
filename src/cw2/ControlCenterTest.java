package cw2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Display.GUI_test;

public class ControlCenterTest {
	public static void main(String args[]){
		ControlCenterTest myTest = new ControlCenterTest();
		ThreadTime time1=new ThreadTime();
		time1.start();//这两条语句一定要在最开始执行
		
		ControlCenter Beijing=new ControlCenter();
		myTest.CreateDriver();

		myTest.CreateTrain();
		
		Route R1 =new Route(1);
		Route R2 =new Route(2);
		
		Beijing.OpenRoute(R1);
		Beijing.OpenRoute(R2);
		ControlCenter.RoutePause=new int[ControlCenter.routes.size()][3];//这行语句必须在OpenRoute完后再加
		
		R1.ToCoTrofRo=3;//设置每条线路一天有几辆车 Setting the number of a route 
		R2.ToCoTrofRo=1;
		//删除和恢复站开始=
		System.out.println("Delete Any Station? y/n");  
		Delete myDelete =new Delete(1,2);				//一个站不能被删除两次，GUI要注意
		myDelete.DeleteStation();						
		Recover myrecover= new Recover(1,2);			//一个站不能被恢复两次，GUI要注意，恢复站只能从已经被删除的站里选
		myrecover.RecoverStation();
		//删除和恢复站结束
		
		Beijing.DistrTrain();
		Beijing.DistrDriver();

		System.out.println(ControlCenter.routes);
		System.out.println(ControlCenter.trains);
		System.out.println(ControlCenter.drivers);
		
		for(Train b: ControlCenter.trains){
			b.SearchTableTrain();
			b.GetStop();
		}
		Beijing.sort();
		ThreadTrain[] myThreadTrain = new ThreadTrain[ControlCenter.trains.size()];
		for(int i=0;i<myThreadTrain.length;i++){
			myThreadTrain[i] = new ThreadTrain(ControlCenter.trains.get(i));
			myThreadTrain[i].start();
		}
		
		//-----------------------------------结合GUI-------------------------------------------//		
				GUI_test gui = new GUI_test();
				gui.go(Beijing);
				
		//---------------------------------------------------------------------------------------//		
		
	}
	
	public void CreateDriver(){
		String thisLine;
		try {
			FileReader fr = new FileReader("Driver.txt");
			BufferedReader bfr=new BufferedReader(fr);
			while((thisLine=bfr.readLine())!=null){
				ControlCenter.drivers.add(new Driver(thisLine.substring(0, 4)));
			}
			bfr.close();
		} catch (IOException e) {}
	}
	
	public void CreateTrain(){
		String thisLine;
		try {
			FileReader fr = new FileReader("Train.txt");
			BufferedReader bfr=new BufferedReader(fr);
			while((thisLine=bfr.readLine())!=null){
				ControlCenter.trains.add(new Train(Integer.parseInt(thisLine)));
			}
			bfr.close();
		} catch (IOException e) {}
	}
}
