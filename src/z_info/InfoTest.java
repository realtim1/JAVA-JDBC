package z_info;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.*;



public class InfoTest {
	//------------------------
	// [1] 멤버변수 선언

	JFrame f;
	JTextField tfName, tfId, tfTel, tfSex, tfAge, tfHome;
	JTextArea ta;
	JButton bAdd, bShow, bSearch, bDelete, bModify, bExit;

	Database db;
	//-------------------------
	// [2] 멤버변수 객체 생성

	InfoTest() {
		f = new JFrame("DB Test");		
		bAdd = new JButton("Add");
		bShow = new JButton("Show");
		bSearch = new JButton("Search");
		bDelete = new JButton("Delete");
		bModify = new JButton("Modify");
		bExit = new JButton("Exit");
		ta = new JTextArea();		

		tfName = new JTextField(15);
		tfId = new JTextField();
		tfTel = new JTextField();
		tfSex = new JTextField();
		tfAge = new JTextField();
		tfHome = new JTextField();

		try {							//throws 해서 try catch를 해야함
			db = new Database();
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}

	}


	//---------------------------
	// [3] 화면 붙이기
	void addLayout() {
		f.setLayout(new BorderLayout());

		f.add(ta, BorderLayout.CENTER);

		// 버튼영역-south
		JPanel p = new JPanel();	         
		p.add(bAdd);
		p.add(bShow);
		p.add(bSearch);
		p.add(bDelete);
		p.add(bModify);
		p.add(bExit);
		f.add(p, BorderLayout.SOUTH);

		// 라벨+텍스트필드 -west
		JPanel pwest = new JPanel();
		pwest.setLayout(new GridLayout(6,2));
		pwest.add(new JLabel("Name"));
		pwest.add(tfName);
		pwest.add(new JLabel("Id"));
		pwest.add(tfId);
		pwest.add(new JLabel("Tel"));
		pwest.add(tfTel);
		pwest.add(new JLabel("Sex"));
		pwest.add(tfSex);
		pwest.add(new JLabel("Age"));
		pwest.add(tfAge);
		pwest.add(new JLabel("Home"));
		pwest.add(tfHome);

		f.add(pwest, BorderLayout.WEST);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100,200,800,350);
		f.setVisible(true);
	}


	//----------------------------
	// [4] 이벤트처리
	void eventProc() {
		// ADD 버튼이 눌렀을때 이벤트 처리
		bAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//각각의 텍스트 필드에서 사용자의 입력값을 얻어오기
				String name = tfName.getText();			
				String id = tfId.getText();
				String tel = tfTel.getText();
				String sex =tfSex.getText();
				int age = Integer.parseInt(tfAge.getText()) ;
				String Home = tfHome.getText();



				//사용자 입력값들을 InfoVO 멤버로 지정
				InfoVO vo = new InfoVO(); //덩어리로 묶은거

				vo.setName(name);		//set으로 저장
				vo.setId(id);
				vo.setTel(tel);
				vo.setSex(sex);
				vo.setAge(age);
				vo.setHome(Home);
				// InfoVO vo = new InfoVO(name,id,tel,sex,age,home); --생성자로 해도되는데 순서를 알아야함 비추


				try {
					db.insert(vo);
					tfName.setText(null);	//add 누르면 빈공간으로 
					tfId.setText(null);
					tfTel.setText(null);
					tfSex.setText(null);
					tfAge.setText(null);
					tfHome.setText(null);
					
					selectAll(); //자동인거처럼 
					
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "입력실패" + ex.getMessage());
					
				}


			}
		});
		// 전화번호 텍스트 필드에서 엔터쳤을때

		// Modify 버튼이 눌렀을때 이벤트 처리		//add부분이랑 똑같 몇개 수정만하면됌
		bModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//각각의 텍스트 필드에서 사용자의 입력값을 얻어오기
				String name = tfName.getText();			
				String id = tfId.getText();
				String tel = tfTel.getText();
				String sex =tfSex.getText();
				int age = Integer.parseInt(tfAge.getText()) ;
				String Home = tfHome.getText();



				//사용자 입력값들을 InfoVO 멤버로 지정
				InfoVO vo = new InfoVO(); //덩어리로 묶은거

				vo.setName(name);		//set으로 저장
				vo.setId(id);
				vo.setTel(tel);
				vo.setSex(sex);
				vo.setAge(age);
				vo.setHome(Home);
				// InfoVO vo = new InfoVO(name,id,tel,sex,age,home); --생성자로 해도되는데 순서를 알아야함 비추


				try {
					db.modify(vo);
					tfName.setText(null);	//add 누르면 빈공간으로 
					tfId.setText(null);
					tfTel.setText(null);
					tfSex.setText(null);
					tfAge.setText(null);
					tfHome.setText(null);

				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "수정실패" + ex.getMessage());
				}


			}
		});

		tfTel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchByTel();


			}
		});
		tfId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchById();


			}
		});

		
		//'Search' 버튼이 눌렸을때 //중복적인것은 함수처리
		bSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchByTel();
				searchById();

			}
		});

		//삭제버튼이 눌러졌을때
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tel = tfTel.getText();
				try {
					int result =db.delete(tel);

					if (result == 1 ) {
						JOptionPane.showMessageDialog(null, "삭제성공");
					}

					//화면비우기
					tfName.setText(null);	//add 누르면 빈공간으로 
					tfId.setText(null);
					tfTel.setText(null);
					tfSex.setText(null);
					tfAge.setText(null);
					tfHome.setText(null);

				} catch (Exception ex) {
					System.out.println("예외실패 : " + ex.getMessage());

				}

			}
		});
		
		
		
		bShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAll();
				

			}
		});
	} // end of eventProc
	
	void selectAll() {
		try {
			ta.setText("--------------------검색결과--------------------\n\n");
			ArrayList<InfoVO> result =db.selectAll();
			for(InfoVO vo : result) {
				ta.append(vo.toString()); //InfoVO에 함수를 오버라이딩함
//				ta.append(vo.getName()+" / ");
//				ta.append(vo.getId()+" / ");
//				ta.append(vo.getTel()+" / ");
//				ta.append(vo.getSex()+" / ");
//				ta.append(Integer.toString(vo.getAge())+" / ");
//				ta.append(vo.getHome()+"\n");
				
				
				
			}
			
			
		} catch (Exception ex) {
			System.out.println("전체검색실패 : " + ex.getMessage());
			
		}
		
		
		
	}
	void searchByTel() {
		String tel = tfTel.getText();

		try {
			InfoVO result = db.searchByTel(tel);
			tfName.setText(result.getName());
			tfId.setText(result.getId());
			tfTel.setText(result.getTel());
			tfSex.setText(result.getSex());
			tfAge.setText( Integer.toString(result.getAge()));
			tfHome.setText(result.getHome());



			//나머지도 하기

		} catch (Exception ex) {
			System.out.println("예외실패 : " + ex.getMessage());

		}
	}
	void searchById() {
		String id = tfId.getText();

		try {
			InfoVO result = db.searchById(id);
			tfName.setText(result.getName());
			tfId.setText(result.getId());
			tfTel.setText(result.getTel());
			tfSex.setText(result.getSex());
			tfAge.setText( Integer.toString(result.getAge()));
			tfHome.setText(result.getHome());



			//나머지도 하기

		} catch (Exception ex) {
			System.out.println("예외실패 : " + ex.getMessage());

		}
	}


	public static void main(String[] args) {
		InfoTest info = new InfoTest();
		info.addLayout();
		info.eventProc();

	}

}
