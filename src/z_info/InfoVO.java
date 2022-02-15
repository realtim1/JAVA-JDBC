package z_info;

public class InfoVO {

	
	String name;
	String id;
	String tel;
	String sex;
	int age;
	String home;
	
	

	// Constuctor
	public InfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public InfoVO(String name, String id, String tel, String sex, int age, String home) {
		super();
		this.name = name;
		this.id = id;
		this.tel = tel;
		this.sex = sex;
		this.age = age;
		this.home = home;
	}
	
	public String toString() {			//편안하게 한번에 쓰기위해 Object가 최상위 부모라서 오버라이딩
		return name+"\t" + id + "\t" + tel+ "\t" +
				sex+"\t" +age+"\t" +home+"\n";
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	
	
}
