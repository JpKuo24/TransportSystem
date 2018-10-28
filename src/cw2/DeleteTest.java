package cw2;


public class DeleteTest {
	public static void main(String args[]){
		int Dwhich=2;//Dwhich指从起点A站出发数第几站，Dwhich=1说明是B站，B站将不停车
		int Rwhich=2;
		Delete myDelete =new Delete(Rwhich,Dwhich);
		myDelete.DeleteStation();
		//myDelete.DeleteTimeTable(Dwhich);
	}
}
