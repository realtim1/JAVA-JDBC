package videotest;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VideoShop extends JFrame{

	CustomerView cv;
	VideoView vv;
	RentView rv;
	
	
	
	public VideoShop() {
		cv = new CustomerView();
		vv = new VideoView();
		rv = new RentView();
		
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("고객관리", cv); //탭키 만들기
		pane.addTab("비디오관리", vv); //탭키 만들기
		pane.addTab("대여관리", rv); //탭키 만들기
		
		pane.setSelectedIndex(2); //프로그램언어 0부터 샘 먼저 뜨게해주는거
		
		
		add(pane, BorderLayout.CENTER);
		
		
		
		
		setBounds(300,300,600,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x누르면 닫기
		
	}
	
	
	
	
	public static void main(String[] args) {
		new VideoShop();
		
		
		
		
	}

}
