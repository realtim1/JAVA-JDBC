package basic;

import java.sql.*;

public class InsertTest {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; //셀프 ip 본인한테 있어서
		String user= "scott";					//포트번호1521
		String pass = "tiger";
		
		try {
			// 1. 드라이버 로딩(메모리)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. 연결 객체 얻어오기	
			Connection con = DriverManager.getConnection(url,user,pass);
			System.out.println("DB 연결 성공");
			
			// 3. SQL 만들기
			int empno = 9915;
			String ename = "홍길자";
			String job = "유지";
			
			String sql = "INSERT INTO emp(empno,ename,job)	 	"	
			+ "VALUES(?, ?, ?)	";
			
			
			
			// 4. SQL 전송객체 얻어오기
			//		Statement  : 완벽한 쿼리 PreparedStatement 로 안될때 사용
			// PreparedStatement (*) : 주로 사용
			// CallableStatement : pl-sql의 함수를 호출할 떄
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, empno); 		 //1로 시작함 주로 얘사용함
			ps.setString(2, ename);
			ps.setString(3, job);
			
			
			
			// 5. 전송
			//		-  리건값 int 			 executeUpdate() : INSERT, UPDATE, DELETE DDL을 할려면
			//		- ResultSet 으로 받아야함	 executeQuery() : SELECT
			// 오라클이 던져주는게 다르기 떄문에 함수가 다름
			int result = ps.executeUpdate();
			System.out.println(result + "행을 실행");
			
			// 6. 닫기
			ps.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 못 찾음 : " + e.getMessage());
		} catch(SQLException e) {
			System.out.println("db 관련 에러" + e.getMessage());
		}
		
	}

}
