package cw2;


public class ThreadTime extends Thread{
	
	public void run(){
		while(true){
			Timer.showTime();
			try {
				sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
}
