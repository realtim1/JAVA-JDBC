package basic;

import java.sql.*;

public class UpdateTest {

	public static void main(String[] args) {

		
		
//		String sql = "7788사원의 정보를 업무와 급여정보를 수정하세요";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; 
		String user= "scott";					
		String pass = "tiger";
		
		Connection con = null;
		PreparedStatement ps = null;		
					
		
		
		try {
			// 1. 드라이버 로딩(메모리)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. 연결 객체 얻어오기	
			 con = DriverManager.getConnection(url,user,pass);
			System.out.println("DB 연결 성공");
			//3 sql
			int empno = 7788;
			String job = "개발";
			int sal = 8000;
			
			String sql = "UPDATE emp  SET sal= ?, job = ? where empno=? ";
			
			
			
			
			// 4. SQL 전송객체 얻어오기
			
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, sal); 		 //1로 시작함 주로 얘사용함
				ps.setString(2, job);
				ps.setInt(3, empno);
			// 5. 전송
			
				int result = ps.executeUpdate();
				System.out.println(result + "행을 실행");
			
			
		
			
			// 7. 닫기
				
				
				ps.close();
				con.close();
				} catch(Exception ex) {
					System.out.println("예외:" + ex.getMessage());
				}
			}

		}

