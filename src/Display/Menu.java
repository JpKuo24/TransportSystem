package Display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cw2.ControlCenter;
import cw2.Train;

public class Menu implements ActionListener{

	JFrame frame = new JFrame("Train Journey Management System");
	JPanel Welcome = new JPanel();
	
	Menu m2;
	ControlCenter CC;
	DLogin Driverlogin;
	
	JMenuItem route1;
	JMenuItem route2;
	JMenuItem Mlogin;
	JMenuItem Massign;
	JMenuItem Dlogin;
	JMenuItem DCheck;
	
	JScrollPane scrollPane1 ;
	JPanel Cpanel1;
	JPanel scrollPane2;
	JPanel DcontroL;
	JPanel MApanel ;
	
	boolean a = false;
	boolean b = false;
	boolean c = false;
	boolean d = false;
	boolean MA = false;
	boolean MAA = false;

	
	public void register(Menu m, ControlCenter cc){
		this.m2=m;
		CC =cc;
	}
	
	public void perform() {
		frame.setBounds(300, 100, 730, 450);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);     //居中。
		initWelcome();
		frame.setVisible(true);
	
	}
	
	private void initWelcome(){
		
		 JLabel PictureLabel = new JLabel();
	     PictureLabel.setIcon(new ImageIcon("picture.jpg"));
   //      frame.getContentPane().add(PictureLabel);
	   
	      
		Welcome.setBounds(0, 0, 730, 450);
    //	Welcome.setBackground(Color.BLACK);
		frame.getContentPane().add(Welcome);
		Welcome.add(PictureLabel);
		initMenu();	
			}
	
	private void initMenu() {
		  JMenuBar mnuBar = new JMenuBar();
		  JMenu mnuFirst = new JMenu("TimeTable");
		  mnuBar.add(mnuFirst);
	
		  route1 = new JMenuItem("Route 1");
		  mnuFirst.add(route1);
		  mnuFirst.addSeparator(); // 增加菜单分隔符
		  // 创建一个菜单项，并将它追加到菜单mnuFirst中
		  route2 = new JMenuItem("Route 2");
		  mnuFirst.add(route2);
		  
		  //route2.setMnemonic(KeyEvent.VK_E);
		  // 设置快捷键，这里是同时按下CTRL+E键，相当于选中该菜单项
		  //route2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
		 //   InputEvent.CTRL_DOWN_MASK));
		  // 创建第二个菜单，并将它追加到菜单栏中
		  
		  JMenu  mnuSecond = new JMenu("Manage");
		  mnuBar.add(mnuSecond);
		  Mlogin = new JMenuItem("Log in/out");
		  mnuSecond.add(Mlogin);
		  mnuSecond.addSeparator(); 
		  Massign = new JMenuItem("Assign");
		  mnuSecond.add(Massign);
		  
		  JMenu  mnuThird = new JMenu("Driver");
		  mnuBar.add(mnuThird);
		  Dlogin = new JMenuItem("Log in/out");
		  mnuThird.add(Dlogin);
		  mnuThird.addSeparator(); 
		  DCheck = new JMenuItem("Check");
		  mnuThird.add(DCheck);
		  
		  frame.setJMenuBar(mnuBar);
		  
		  route1.addActionListener(this);
		  route2.addActionListener(this);
		  Mlogin.addActionListener(this);
		  Massign.addActionListener(this);
		  Dlogin.addActionListener(this);
		  DCheck.addActionListener(this);
		 }
	
	public void actionPerformed(ActionEvent e) {
   //----------------------------------------Custom---------------------------------------------------------//
		if(e.getSource()==route1){
			 Welcome.setVisible(false);
			if(a){     scrollPane1.setVisible(false);	   Cpanel1.setVisible(false);	 frame.repaint(); }	
			if(d){		scrollPane2.setVisible(false);	   frame.repaint();  }
		 	if(MA){  MApanel.setVisible(false);       MA =false; }
			
			scrollPane1 = CustomTime.intiComponent("1");
			
			frame.add(scrollPane1);
			Cpanel1 = CustomCheckStation.ChooseStation("1");
			frame.add(Cpanel1);
			a = true;
		}
		if(e.getSource()==route2){
			 Welcome.setVisible(false);
			 if(a){    scrollPane1.setVisible(false);	   Cpanel1.setVisible(false);	    frame.repaint();  }	
			 if(d){	scrollPane2.setVisible(false);	   frame.repaint();  }
			 if(MA){  MApanel.setVisible(false);      MA =false; }
			
			scrollPane1 = CustomTime.intiComponent("2");
			
			frame.add(scrollPane1);
			Cpanel1 = CustomCheckStation.ChooseStation("2");
			frame.add(Cpanel1);
			a = true;
		}
//-------------------------------------------Manager---------------------------------------------------------//
		if(e.getSource()==Mlogin){
			if(b){
				b=false;
				Object[] option = {"OK"};
		        JOptionPane.showOptionDialog(null, "Manager log out Susccefully!", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,  option,  option[0]);
		    	if(MA){MApanel.setVisible(false);    MA =false; }
		        Welcome.setVisible(true);
			} else if(c){
				Object[] option = {"OK"};
		        JOptionPane.showOptionDialog(null, "Permission denied!  Driver should log out firstly!", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null,  option,  option[0]);
			} else {
				//--------------------登陆-------------------------//
						MLogin mapp = new MLogin(m2);
						mapp.Mgo();
				//--------------------------------------------------//
			}
		}		
		if(e.getSource()==Massign){
			if(b){
				Welcome.setVisible(false);
				if(a){scrollPane1.setVisible(false);	Cpanel1.setVisible(false);}	
				
				if(MA){
					MApanel.setVisible(false);
					Train t= new Train();	
					ManageAssign Managea =new ManageAssign();
					MApanel = Managea.manageAssign(t, MAA);
					frame.add(MApanel);
				}else{
					Train t= new Train();	
				    ManageAssign Managea =new ManageAssign();
				    MApanel = Managea.manageAssign(t,MAA);
				    frame.add(MApanel);
				    MA = true;
				    MAA = true;
				}
	
			} else {
				Object[] option = { "All right~"};
		        JOptionPane.showOptionDialog(null, "Manager please log in first!", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,  option,  option[0]);
			}
		}
//---------------------------------------------Driver------------------------------------------------------------//
		if(e.getSource()==Dlogin){
			if(c){
				c=false;
				Object[] option = {"OK"};
		        JOptionPane.showOptionDialog(null, "Driver log out Susccefully!", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,  option,  option[0]);
		        frame.remove(scrollPane2);
		        Welcome.setVisible(true);
		        d=false;
//		        frame.revalidate();
                frame.repaint();

		        
			} else if(b){
				Object[] option = {"OK"};
		        JOptionPane.showOptionDialog(null, "Permission denied!  Manager should log out firstly!", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null,  option,  option[0]);
			}
			else{
			//--------------------登陆-------------------------//	
					Driverlogin = new DLogin(m2);
					Driverlogin.Dgo();	
			//--------------------------------------------------//		
			}
		}
		
		if(e.getSource()==DCheck){
			if(c){
				
				Welcome.setVisible(false);
				if(a){scrollPane1.setVisible(false);	Cpanel1.setVisible(false);}			
				if(d==false){
					Dcheck dcheck = new Dcheck(CC,Driverlogin.txt.getText());
					scrollPane2 =dcheck.DriverCheck();	
					frame.add(scrollPane2);
					d =true;
				}
				scrollPane2.setVisible(true);
				frame.repaint();
			} else {
				Object[] option = { "All right~"};
		        JOptionPane.showOptionDialog(null, "Driver please log in first!", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,  option,  option[0]);
			}
		}
	}

}
