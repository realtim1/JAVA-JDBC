package jdbc.gui3.emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import z_info.InfoVO;

public class EmpModelImpl implements EmpModel{
	
	/*---------------------------------------------
	 * 생성자 함수 
	 	1. DB 연동
	 	
	 		- 드라이버 등록
	*/
	
	String driver ="oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; 
	String user= "scott";					
	String pass = "tiger";
	
	
	
	public EmpModelImpl() throws Exception{
		Class.forName(driver); 
		
	}



	
	/*-------------------------------------------------------
	* insert() :  입력한 값 받아서 데이타베이스에 추가
		0. 연결객체 얻어오기
		1. sql문 만들기 ( insert 구문 )
		2. PreparedStatement 객체 생성 
		3. PreparedStatement에 인자 지정
		4. sql문 전송 ( executeUpdate() 이용 )
		5. PreparedStatement 닫기
		6. 연결 닫기
	*/
	public void insert( EmpVO r ) throws SQLException{	
		// 2 연결 객체 얻어오기
				Connection con = null;
				PreparedStatement ps = null;
				
				
				try {
					
					
				 con = DriverManager.getConnection(url,user,pass);
				System.out.println("DB 연결 성공");
				//3
				
				
				String sql = "INSERT INTO emp (empno,ename,sal,job)	 	"	
				+ "VALUES (?, ?, ?, ?)	";
				
				
				
				
				//4 전송 객체 얻어오기
				ps = con.prepareStatement(sql);
			
				ps.setInt(1, r.getEmpno()); 		 //1로 시작함 주로 얘사용함
				ps.setString(2, r.getName());		//insert (vo)했으니 vo.get
				ps.setInt(3, r.getSal());
				ps.setString(4, r.getJob());

				
				 
				//5 전송
				int result = ps.executeUpdate();
				System.out.println(result + "행을 실행");
				
				
				//6 닫기
				} finally {
				
					ps.close();
					con.close();
				} 
			}
		



	/*-------------------------------------------------------
	* modify() : 화면 입력값 받아서 수정
		0. 연결객체 얻어오기
		1. sql문 만들기 ( update 구문 )
		2. PreparedStatement 객체 생성 ( 또는 Statement )
		3. PreparedStatement에 인자 지정
		4. sql문 전송 ( executeUpdate() 이용 )
		5. PreparedStatement 닫기
		6. 연결 닫기
	*/
	public void modify( EmpVO r ) throws SQLException{	
		// 2 연결 객체 얻어오기
				Connection con = null;
				PreparedStatement ps = null;
				
				
				try {
				 con = DriverManager.getConnection(url,user,pass);
				System.out.println("DB 연결 성공");
				//3  <----------
				
													
				String sql = "UPDATE emp SET ename =?,sal=?,job=?  WHERE empno = ? 	";
				
				
				
				
				//4 전송 객체 얻어오기	<------------- ?에 값 지정 부분
				ps = con.prepareStatement(sql);
				ps.setString(1, r.getName()); 		 //1로 시작함 주로 얘사용함
				ps.setInt(2, r.getSal());		//insert (vo)했으니 vo.get
				ps.setString(3, r.getJob());
				ps.setInt(4, r.getEmpno());

				
				 
				//5 전송
				int result = ps.executeUpdate();
				System.out.println(result + "행을 실행");
				
				
				//6 닫기
				} finally {
				
					ps.close();
					con.close();
				} 
			}
		
		


	
	
	
	
	
	
	
	/*-------------------------------------------------------
	* selectByEmpno() :  입력받은 사번을 받아서 해당 레코드 검색
		0. 연결객체 얻어오기
		1. sql문 만들기 ( select 구문 )
		2. PreparedStatement 객체 얻기 ( Statement  가능 )
		4. sql문 전송 ( executeQuery() 이용 )
		5. 결과집합(ResultSet)에서 값을 읽어서 EmpVO에 저장
		6. ResultSet/ PreparedStatement 닫기
		7. 연결 닫기
		8. EmpVO 객체 리턴
	*/	
	public EmpVO selectByEmpno( int empno ) throws SQLException{
		EmpVO vo = new EmpVO();
		
		
		
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		
		try {
		EmpVO evo = new EmpVO();
		
		
		// 2. 연결 객체 얻어오기
		 con = DriverManager.getConnection(url,user,pass);
			System.out.println("DB 연결 성공");
		// 3. sql 문장
		String sql = "SELECT * FROM emp WHERE trim(EMPNO) = ?";
		
		
		
		// 4. 전송객체 얻어오기 - PreparedStatement
		 ps = con.prepareStatement(sql);
		 ps.setInt(1, empno);		//? 값 처리하기
		
		// 5. 전송 executeQuery
		 rs = ps.executeQuery();
		
		 System.out.println(rs + "행을 실행");
		// 6. 결과처리
		if(rs.next()); {
//			evo.setEmpno(rs.getInt("EMPNO"));
//			evo.setName(rs.getString("NAME"));
//			evo.setSal(rs.getInt("SAL"));
//			evo.setJob(rs.getString("JOB"));
			evo.setEmpno(rs.getInt("EMPNO"));
			evo.setName(rs.getString("ENAME"));
			evo.setJob(rs.getString("JOB"));
			evo.setMgr(rs.getInt("MGR"));
			evo.setHiredate(rs.getString("HIREDATE"));
			evo.setSal(rs.getInt("SAL"));
			evo.setComm(rs.getInt("COMM"));			
			evo.setDeptno(rs.getInt("DEPTNO"));
		}
		return evo;	
		//7 닫기
		
		} finally {
			rs.close();
			ps.close();
			con.close();
		

		
	}
	}
	/*--------------------------------------------------------
	* delete() : 사원번호 값을 받아 해당 레코드 삭제
		0. 연결객체 얻어오기
		1. sql문 만들기 ( delete 구문 )
		2. PreparedStatement 객체 얻기
		3. sql문 전송 ( executeUpdate() 이용 )
		4. PreparedStatement 닫기
		5. 연결 닫기
	*/
	public int delete( int empno ) throws SQLException{
		int resultCnt = 0;		//** 0
		Connection con = null;
		PreparedStatement ps = null;		
			
		try {
		//2. 연결 객체 얻어오기
		con = DriverManager.getConnection(url,user,pass);
		//3. sql 문장 만들기
		String sql = "DELETE FROM emp WHERE empno=?";
		
		//4. 전송객체 얻어오기
		ps= con.prepareStatement(sql);
		ps.setInt(1,empno);
		//5. 전송
		resultCnt = ps.executeUpdate();  //delete를 하면 1 안하면 0
		System.out.println(resultCnt + "행을 실행");
		//6 닫기
		
	}finally {
		ps.close();
		con.close();
	}
		
	
		return resultCnt;
	}
	
	/*-------------------------------------------------------
	* selectAll() :  전체 레코드 검색
		0. 연결객체 얻어오기
		1. sql문 만들기 ( select 구문 )
		2. PreparedStatement 객체 얻기 ( Statement  가능 )
		4. sql문 전송 ( executeQuery() 이용 )
		5. 결과집합(ResultSet)에서 값을 읽어서 ArrayList에 저장
		6. ResultSet/ PreparedStatement 닫기
		7. 연결 닫기
		8. ArrayList 객체 리턴
	*/	
	public ArrayList<EmpVO> selectAll() throws SQLException{
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		try {
		con = DriverManager.getConnection(url,user,pass);
		System.out.println("DB 연결 성공");
		
		//<InfoVO> 제너릭스 타입이 다 InfoVO 때문에
		ArrayList<EmpVO> list1 = new ArrayList<EmpVO>();	//배열 갯수를 모르기 떄문에
		String sql = "Select *From emp";
		
		 ps = con.prepareStatement(sql);
		
		 rs = ps.executeQuery();
		 System.out.println(rs + "행을 실행");
		
		//레코드가 여러개니깐
		while (rs.next()) {
			EmpVO evo = new EmpVO();
//			vo.setEmpno(Integer.valueOf(rs.getString("EMPNO")));
//			vo.setName(rs.getString("NAME"));
//			vo.setSal(Integer.valueOf(rs.getString("SAL")) );
//			vo.setJob(rs.getString("JOB"));
			
			
			evo.setEmpno(rs.getInt("EMPNO"));
			evo.setName(rs.getString("ENAME"));
			evo.setJob(rs.getString("JOB"));
			evo.setMgr(rs.getInt("MGR"));
			evo.setHiredate(rs.getString("HIREDATE"));
			evo.setSal(rs.getInt("SAL"));
			evo.setComm(rs.getInt("COMM"));			
			evo.setDeptno(rs.getInt("DEPTNO"));
			list1.add(evo);		// 저장하기위해 while문이 나가면 정보가 삭제가 되기떄문에
		}
		return list1;
		
		}finally {
			rs.close();
			ps.close();
			con.close();
			System.out.println("시스템 종료");
		
		
		
		}
	}
}