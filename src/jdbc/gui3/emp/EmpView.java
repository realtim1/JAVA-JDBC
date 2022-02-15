package jdbc.gui3.emp;

import java.awt.*;
import javax.swing.*;

import z_info.InfoVO;

import java.awt.event.*;
import java.util.ArrayList;

public class EmpView {

	// 화면 관련 멤버변수
	JFrame f;
	JTextField tfEmpno, tfEname, tfSal, tfJob;
	JButton bInsert, bUpdate, bDelete, bSelectAll;
	JTextArea ta;

	EmpModelImpl em;
	// 멤버변수 객체 생성
	EmpView(){
		f = new JFrame("나의 연습");
		tfEmpno = new JTextField(10);
		tfEname = new JTextField(10);
		tfSal = new JTextField(10);
		tfJob = new JTextField(10);
		bInsert = new JButton("입력");
		bUpdate = new JButton("수정");
		bDelete = new JButton("삭제");
		bSelectAll = new JButton("전체검색");
		ta = new JTextArea();
		try {
			em = new EmpModelImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	// 화면 구성
	void addLayout() {
		JPanel pNorth = new JPanel();
		pNorth.setLayout(new GridLayout(1,2));

		JPanel pNorth1 = new JPanel();
		pNorth1.setLayout(new GridLayout(4,2));
		pNorth1.add(new JLabel("사번"));		pNorth1.add(tfEmpno);
		pNorth1.add(new JLabel("사원명"));		pNorth1.add(tfEname);
		pNorth1.add(new JLabel("월급"));		pNorth1.add(tfSal);
		pNorth1.add(new JLabel("업무"));		pNorth1.add(tfJob);

		JPanel pNorth2 = new JPanel();
		pNorth2.setLayout(new GridLayout(4,1));
		pNorth2.add(bInsert);
		pNorth2.add(bUpdate);
		pNorth2.add(bDelete);
		pNorth2.add(bSelectAll);

		pNorth.add(pNorth1);
		pNorth.add(pNorth2);

		f.add(pNorth, BorderLayout.NORTH);
		f.add(new JScrollPane(ta), BorderLayout.CENTER);

		f.setBounds(200, 200, 600, 500);
		f.setVisible(true);
	}

	// ******************************************************************************
	// 버튼 및 텍스트필드 이벤트 관련
	void eventProc() {
		// 입력버튼이 눌렸을 때
		bInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {




				int empno = Integer.parseInt(tfEmpno.getText());			
				String name = tfEname.getText();
				int sal = Integer.parseInt(tfSal.getText());
				String job =tfJob.getText();

				try {
					em = new EmpModelImpl();
				} catch (Exception e) {
					e.printStackTrace();
				}


				//사용자 입력값들을 InfoVO 멤버로 지정
				EmpVO r = new EmpVO(); //덩어리로 묶은거

				r.setEmpno(empno);		//set으로 저장
				r.setName(name);
				r.setSal(sal);
				r.setJob(job);

				// InfoVO vo = new InfoVO(name,id,tel,sex,age,home); --생성자로 해도되는데 순서를 알아야함 비추


				try {
					em.insert(r);
					tfEmpno.setText(null);	
					tfEname.setText(null);
					tfSal.setText(null);
					tfJob.setText(null);


					selectAll(); //자동인거처럼 

				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "입력실패" + ex.getMessage());
					ex.getStackTrace();
				}
			}
		});	

		tfEmpno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				selectByEmpno();

			}
		});
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int empno = Integer.valueOf(tfEmpno.getText()) ;
				try {
					int result =em.delete(empno);

					if (result == 1 ) {
						JOptionPane.showMessageDialog(null, "삭제성공");
					}

					//화면비우기
					tfEmpno.setText(null);	//add 누르면 빈공간으로 
					tfEname.setText(null);
					tfSal.setText(null);
					tfJob.setText(null);

				} catch (Exception ex) {
					System.out.println("예외실패 : " + ex.getMessage());

				}

			}
		});
		
		bSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectAll();
				

			}
		});
		
		
	}
	void selectAll() {
		try {
			ta.setText("--------------------검색결과--------------------\n\n");
			ArrayList<EmpVO> result = em.selectAll();
			
			
			
			
			for(EmpVO vo : result) {
				
				ta.append(vo.toString());
				
//				ta.append(Integer.toString(vo.getEmpno()) +" / ");
//				ta.append(vo.getName()+" / ");
//				ta.append(Integer.toString(vo.getSal())+" / ");
//				ta.append(vo.getJob()+" /n");
				//ta.append(Integer.toString(vo.getAge())+" / ");
			}
			
	} catch(Exception ex) {
		System.out.println("전체검색실패 : " + ex.getMessage());
		ex.printStackTrace();
	}
	
	
	
}
	


	void selectByEmpno() {

		try {
			em = new EmpModelImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int empno = Integer.valueOf((tfEmpno.getText()));

		try {
			EmpVO result = em.selectByEmpno(empno);
			tfEmpno.setText(Integer.toString(result.getEmpno()));
			tfEname.setText(result.getName());
			tfSal.setText(Integer.toString(result.getSal()));
			tfJob.setText(result.getJob());
			//tfAge.setText( Integer.toString(result.getAge()));



			//나머지도 하기

		} catch (Exception ex) {
			System.out.println("예외실패 : " + ex.getMessage());

		}


		

		bUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				 modify();
				
				
			}
		});	
	
	
}
				
				
		void modify() {		
				
				int empno = Integer.parseInt(tfEmpno.getText());			
				String ename = tfEname.getText();
				int sal = Integer.parseInt(tfSal.getText());
				String job =tfJob.getText();



				//사용자 입력값들을 InfoVO 멤버로 지정
				EmpVO vo = new EmpVO(); //덩어리로 묶은거

				vo.setEmpno(empno);		//set으로 저장
				vo.setName(ename);
				vo.setSal(sal);
				vo.setJob(job);
				// InfoVO vo = new InfoVO(name,id,tel,sex,age,home); --생성자로 해도되는데 순서를 알아야함 비추


				try {
					em.modify(vo);
					tfEmpno.setText(null);	//add 누르면 빈공간으로 
					tfEname.setText(null);
					tfSal.setText(null);
					tfJob.setText(null);

				}catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "수정실패" + ex.getMessage());

				}
		}


		// 

		// 



		public static void main(String[] args) {
			EmpView view = new EmpView();
			view.addLayout();
			view.eventProc();

		}

	}
