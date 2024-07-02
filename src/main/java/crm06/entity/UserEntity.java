package crm06.entity;

public class UserEntity {
	private int id;
	private String pasString;
	private String fName;
	private String lName;
	private String uName;
	private String phoneString;
	private RoleEntity roleEntity;
	
	public UserEntity(int id, String pasString, String fName, String lName, String uName, String phoneString) {
		super();
		this.id = id;
		this.pasString = pasString;
		this.fName = fName;
		this.lName = lName;
		this.uName = uName;
		this.phoneString = phoneString;
		this.roleEntity = new RoleEntity();
	}
	
	
	
	public UserEntity(String pasString, String fName, String lName, String uName, String phoneString) {
		super();
		this.pasString = pasString;
		this.fName = fName;
		this.lName = lName;
		this.uName = uName;
		this.phoneString = phoneString;
		this.roleEntity = new RoleEntity();
	}
 

	public UserEntity(String pasString, String fName, String lName, String uName, String phoneString, int roleEntity) {
		super();
		this.pasString = pasString;
		this.fName = fName;
		this.lName = lName;
		this.uName = uName;
		this.phoneString = phoneString;
		this.roleEntity = new RoleEntity();
		this.roleEntity.setId(roleEntity); 
	}
	
	public UserEntity(int id,String pasString, String fName, String lName, String uName, String phoneString, int roleEntity) {
		super();
		this.id = id;
		this.pasString = pasString;
		this.fName = fName;
		this.lName = lName;
		this.uName = uName;
		this.phoneString = phoneString;
		this.roleEntity = new RoleEntity();
		this.roleEntity.setId(roleEntity); 
	}


	public UserEntity() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPasString() {
		return pasString;
	}
	public void setPasString(String pasString) {
		this.pasString = pasString;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getPhoneString() {
		return phoneString;
	}
	public void setPhoneString(String phoneString) {
		this.phoneString = phoneString;
	}


	public RoleEntity getRoleEntity() {
		return roleEntity;
	}


	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}



	
	
	
}




//
//package crm06.entity;
//
//public class UserEntity {
//    private int id;
//    private String pasString;
//    private String fName;
//    private String lName;
//    private String uName;
//    private String phoneString;
//    private RoleEntity roleEntity;
//
//    public UserEntity(int id, String pasString, String fName, String lName, String uName, String phoneString) {
//        super();
//        this.id = id;
//        this.pasString = pasString;
//        this.fName = fName;
//        this.lName = lName;
//        this.uName = uName;
//        this.phoneString = phoneString;
//        this.roleEntity = new RoleEntity(); // Initialize roleEntity
//    }
//
//    public UserEntity(String pasString, String fName, String lName, String uName, String phoneString) {
//        super();
//        this.pasString = pasString;
//        this.fName = fName;
//        this.lName = lName;
//        this.uName = uName;
//        this.phoneString = phoneString;
//        this.roleEntity = new RoleEntity(); // Initialize roleEntity
//    }
//
//    public UserEntity(String pasString, String fName, String lName, String uName, String phoneString, int roleEntityId) {
//        super();
//        this.pasString = pasString;
//        this.fName = fName;
//        this.lName = lName;
//        this.uName = uName;
//        this.phoneString = phoneString;
//        this.roleEntity = new RoleEntity(); // Initialize roleEntity
//        this.roleEntity.setId(roleEntityId);
//    }
//
//    public UserEntity() {
//        super();
//        this.roleEntity = new RoleEntity(); // Initialize roleEntity
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getPasString() {
//        return pasString;
//    }
//
//    public void setPasString(String pasString) {
//        this.pasString = pasString;
//    }
//
//    public String getfName() {
//        return fName;
//    }
//
//    public void setfName(String fName) {
//        this.fName = fName;
//    }
//
//    public String getlName() {
//        return lName;
//    }
//
//    public void setlName(String lName) {
//        this.lName = lName;
//    }
//
//    public String getuName() {
//        return uName;
//    }
//
//    public void setuName(String uName) {
//        this.uName = uName;
//    }
//
//    public String getPhoneString() {
//        return phoneString;
//    }
//
//    public void setPhoneString(String phoneString) {
//        this.phoneString = phoneString;
//    }
//
//    public RoleEntity getRoleEntity() {
//        return roleEntity;
//    }
//
//    public void setRoleEntity(RoleEntity roleEntity) {
//        this.roleEntity = roleEntity;
//    }
//}
//
