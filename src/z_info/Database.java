package z_info;

import java.sql.*;
import java.util.*;

public class Database  {
	
	String driver ="oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; 
	String user= "scott";					
	String pass = "tiger";
	
	
	
	public Database () throws ClassNotFoundException {
		Class.forName(driver);  	//드라이버는 로딩
	}
	
	
	
	
	
	public void insert (InfoVO vo) throws Exception{   //예외처리 필수
		// 2 연결 객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try {
		 con = DriverManager.getConnection(url,user,pass);
		System.out.println("DB 연결 성공");
		//3
		
		
		String sql = "INSERT INTO info_test(name,id,tel,sex,age,home)	 	"	
		+ "VALUES(?, ?, ?, ?, ?, ?)	";
		
		
		
		
		//4 전송 객체 얻어오기
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getName()); 		 //1로 시작함 주로 얘사용함
		ps.setString(2, vo.getId());		//insert (vo)했으니 vo.get
		ps.setString(3, vo.getTel());
		ps.setString(4, vo.getSex());
		ps.setInt(5, vo.getAge());
		ps.setString(6, vo.getHome());

		
		 
		//5 전송
		int result = ps.executeUpdate();
		System.out.println(result + "행을 실행");
		
		
		//6 닫기
		} finally {
		
			ps.close();
			con.close();
		} 
	}

	public void modify (InfoVO vo) throws Exception{   //예외처리 필수 INSERT랑 똑같(몇개만 수정하기)
		// 2 연결 객체 얻어오기
		Connection con = null;
		PreparedStatement ps = null;
		
		
		try {
		 con = DriverManager.getConnection(url,user,pass);
		System.out.println("DB 연결 성공");
		//3  <----------
		
											//1		2	3		4		5			6
		String sql = "UPDATE info_test SET name=?,id=?,sex=?,age=?,home=? WHERE tel = ? 	";
		
		
		
		
		//4 전송 객체 얻어오기	<------------- ?에 값 지정 부분
		ps = con.prepareStatement(sql);
		ps.setString(1, vo.getName()); 		 //1로 시작함 주로 얘사용함
		ps.setString(2, vo.getId());		//insert (vo)했으니 vo.get
		ps.setString(3, vo.getSex());
		ps.setInt(4, vo.getAge());
		ps.setString(5, vo.getHome());
		ps.setString(6, vo.getTel());

		
		 
		//5 전송
		int result = ps.executeUpdate();
		System.out.println(result + "행을 실행");
		
		
		//6 닫기
		} finally {
		
			ps.close();
			con.close();
		} 
	}

	
	
	public InfoVO searchByTel(String tel) throws Exception{
		
		
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		
		try {
		InfoVO vo = new InfoVO();
		
		
		// 2. 연결 객체 얻어오기
		 con = DriverManager.getConnection(url,user,pass);
			System.out.println("DB 연결 성공");
		// 3. sql 문장
		String sql = "SELECT * FROM info_test WHERE tel = ?";
		
		
		
		// 4. 전송객체 얻어오기 - PreparedStatement
		 ps = con.prepareStatement(sql);
		 ps.setString(1, tel);		//? 값 처리하기
		
		// 5. 전송 executeQuery
		 rs = ps.executeQuery();
		 System.out.println(rs + "행을 실행");
		// 6. 결과처리
		if(rs.next()) {	// ; 있는지 확인 없어야함 이걸로 개고생했음
			vo.setName(rs.getString("NAME"));
			vo.setId(rs.getString("ID"));
			vo.setTel(rs.getString("TEL"));
			vo.setSex(rs.getString("SEX"));
			vo.setAge(rs.getInt("AGE"));
			vo.setHome(rs.getString("HOME"));
			
			
		}
		//7 닫기
		
		
		
	
		return vo;
		
	
	} finally {
		
		rs.close();
		ps.close();
		con.close();
		System.out.println("시스템 종료");
		
		
		} 
	
}
	
	public InfoVO searchById(String id) throws Exception{
		
		
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		
		try {
		InfoVO vo = new InfoVO();
		
		
		// 2. 연결 객체 얻어오기
		 con = DriverManager.getConnection(url,user,pass);
			System.out.println("DB 연결 성공");
		// 3. sql 문장
		String sql = "SELECT * FROM info_test WHERE trim(id) = ?";
		
		//char로 잡으면 뒤에 공백도 해줘야해서 공백제거하는 trim 을 해줌 
		
		
		
		// 4. 전송객체 얻어오기 - PreparedStatement
		 ps = con.prepareStatement(sql);
		 ps.setString(1, id);		//? 값 처리하기
		
		// 5. 전송 executeQuery
		 rs = ps.executeQuery();
		 System.out.println(rs + "행을 실행");
		// 6. 결과처리
		if(rs.next()) {
			vo.setName(rs.getString("NAME"));
			vo.setId(rs.getString("ID"));
			vo.setTel(rs.getString("TEL"));
			vo.setSex(rs.getString("SEX"));
			vo.setAge(rs.getInt("AGE"));
			vo.setHome(rs.getString("HOME"));
			
			
		}
		//7 닫기
		
		
		
	
		return vo;
		
	
	} finally {
		
		rs.close();
		ps.close();
		con.close();
		System.out.println("시스템 종료");
		
		
		} 
	
}
	public int delete(String tel) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;		
			
		int result = 0;
		try {
		//2. 연결 객체 얻어오기
		con = DriverManager.getConnection(url,user,pass);
		//3. sql 문장 만들기
		String sql = "DELETE FROM info_test WHERE tel=?";
		
		//4. 전송객체 얻어오기
		ps= con.prepareStatement(sql);
		ps.setString(1,tel);
		//5. 전송
		result = ps.executeUpdate();  //delete를 하면 1 안하면 0
		System.out.println(result + "행을 실행");
		//6 닫기
		
	}finally {
		ps.close();
		con.close();
	}
		return result;
		
	}
	
	
	//전체 검색
	public ArrayList<InfoVO> selectAll() throws Exception{ //리턴값이 타입이 Arraylist라고 해서
		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		try {
		con = DriverManager.getConnection(url,user,pass);
		System.out.println("DB 연결 성공");
		
		//<InfoVO> 제너릭스 타입이 다 InfoVO 때문에
		ArrayList<InfoVO> list = new ArrayList<InfoVO>();	//배열 갯수를 모르기 떄문에
		String sql = "Select *From info_test";
		
		 ps = con.prepareStatement(sql);
		
		 rs = ps.executeQuery();
		 System.out.println(rs + "행을 실행");
		
		//레코드가 여러개니깐
		while (rs.next()) {
			InfoVO vo = new InfoVO();
			vo.setName(rs.getString("NAME"));
			vo.setId(rs.getString("ID"));
			vo.setTel(rs.getString("TEL"));
			vo.setSex(rs.getString("SEX"));
			vo.setAge(rs.getInt("AGE"));
			vo.setHome(rs.getString("HOME"));
			list.add(vo);		// 저장하기위해 while문이 나가면 정보가 삭제가 되기떄문에
		}
		return list;
		
		}finally {
			rs.close();
			ps.close();
			con.close();
			System.out.println("시스템 종료");
		}
	}
}
