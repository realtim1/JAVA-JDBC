package basic;

import java.sql.*;

public class SelectTest {

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; //셀프 ip 본인한테 있어서
		String user= "scott";					//포트번호1521
		String pass = "tiger";
		
		Connection con = null;
		PreparedStatement ps = null;		//자바의 이론적인 것 수동적으로 초기화해줌
		ResultSet rs = null;				
		
		
		try {
			// 1. 드라이버 로딩(메모리)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. 연결 객체 얻어오기	
			 con = DriverManager.getConnection(url,user,pass);
			System.out.println("DB 연결 성공");
			//3 sql
			String sql = "SELECT *  FROM emp WHERE empno=7788";
			
			
			
			
			// 4. SQL 전송객체 얻어오기
			//		Statement  : 완벽한 쿼리 PreparedStatement 로 안될때 사용
			// PreparedStatement (*) : 주로 사용
			// CallableStatement : pl-sql의 함수를 호출할 떄
			 ps = con.prepareStatement(sql);
			// 5. 전송
			//		-  리건값 int 			 executeUpdate() : INSERT, UPDATE, DELETE
			//		- ResultSet 으로 받아야함	 executeQuery() : SELECT
			// 오라클이 던져주는게 다르기 떄문에 함수가 다름
			
				 rs = ps.executeQuery();
			
			
			// 6. 결과 처리 PRIMARY KEY는  IF문으로 해야함 값이
			if (rs.next()) {		//rs.next 레코드가 많은데 다음 레코드가 있는지 확인할려고 
				String empno = rs.getString("EMPNO");
				String ename = rs.getString("ENAME"); // rs.getString ("컬럼명") ENAME 컬럼명
				String mgr = rs.getString("MGR");
				String hiredate = rs.getString("HIREDATE");
				int comm = rs.getInt("COMM");
				String deptno = rs.getString("DEPTNO");
				int sal = rs.getInt("SAL");
				String job = rs.getString("JOB");
				System.out.println(empno+"/"+ename+"/"+job+"/"+mgr+"/"+hiredate+"/"+sal+"/"+comm+"/"+deptno);
				
			}
			
			// 7. 닫기
				
			System.out.println("프로그램 종료");
			
			
		} catch (Exception e) {
			System.out.println("실패 : " + e.getMessage());
		} finally {
			try {
			rs.close();
			ps.close();
			con.close();
			} catch(Exception e) {}
		}
		
		
	}

}
