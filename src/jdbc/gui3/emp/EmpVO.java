package jdbc.gui3.emp;

// emp 사원 테이블의 레코드를 저장할 수 있는 클래스
public class EmpVO {
	// 멤버변수 선언 
	
	int empno;			
	String name ;
	int sal ;
	String job;
	int mgr;
	String hiredate;
	int comm;
	int deptno;
	
	//생성자 함수
	public EmpVO() {
		super();
		
	}
	
	
	
	public String toString() {			//편안하게 한번에 쓰기위해 Object가 최상위 부모라서 오버라이딩
		return empno+"\t" + name + "\t" + sal+ "\t" +
		job+"\t" +mgr+"\t" +hiredate+ "\t"+comm+"\t" + deptno+ "\n";
	}
	
	

	public EmpVO(int empno, String name, int sal, String job, int mgr, String hiredate, int comm, int deptno) {
		super();
		this.empno = empno;
		this.name = name;
		this.sal = sal;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.comm = comm;
		this.deptno = deptno;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	
	
	//setter, getter
	
	
}
