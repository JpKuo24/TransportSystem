package cw2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Display.GUI_test;

public class ControlCenterTest {
	public static void main(String args[]){
		ControlCenterTest myTest = new ControlCenterTest();
		ThreadTime time1=new ThreadTime();
		time1.start();//���������һ��Ҫ���ʼִ��
		
		ControlCenter Beijing=new ControlCenter();
		myTest.CreateDriver();

		myTest.CreateTrain();
		
		Route R1 =new Route(1);
		Route R2 =new Route(2);
		
		Beijing.OpenRoute(R1);
		Beijing.OpenRoute(R2);
		ControlCenter.RoutePause=new int[ControlCenter.routes.size()][3];//������������OpenRoute����ټ�
		
		R1.ToCoTrofRo=3;//����ÿ����·һ���м����� Setting the number of a route 
		R2.ToCoTrofRo=1;
		//ɾ���ͻָ�վ��ʼ=
		System.out.println("Delete Any Station? y/n");  
		Delete myDelete =new Delete(1,2);				//һ��վ���ܱ�ɾ�����Σ�GUIҪע��
		myDelete.DeleteStation();						
		Recover myrecover= new Recover(1,2);			//һ��վ���ܱ��ָ����Σ�GUIҪע�⣬�ָ�վֻ�ܴ��Ѿ���ɾ����վ��ѡ
		myrecover.RecoverStation();
		//ɾ���ͻָ�վ����
		
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
		
		//-----------------------------------���GUI-------------------------------------------//		
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
