package cw2;


public class RecoverTest {

	public static void main(String[] args) {
		int Dwhich=3;//Dwhich指从起点A站出发数第几站，Dwhich=1说明是B站，B站将不停车
		int Rwhich=1;
		Recover myrecover= new Recover(Rwhich,Dwhich);
		myrecover.RecoverStation();
	}

}
