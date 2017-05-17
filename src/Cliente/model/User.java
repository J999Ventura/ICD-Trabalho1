package Cliente.model;

public class User {
	
	private String userName, password;
	private String name, age, birthday, address;
	private boolean isAdmin;
	
	public User(String userName, String password, boolean isAdmin, String name, String age, String birthday, String address){
		this.userName = userName;
		this.password = password;
		this.isAdmin = isAdmin;
		this.name = name;
		this.age = age;
		this.birthday = birthday;
		this.address = address;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getName(){
		return name;
	}
	
	public String getAge(){
		return age;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getBirthday(){
		return birthday;
	}
	
	public boolean getIsAdmin(){
		return isAdmin;
	}
	
	public void setUserName(String name){
		this.userName = name;
	}

}
