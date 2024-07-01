package crm06.entity;

public class RoleEntity {
	private int id;
	private String nameString;
	private String descripString;

	public RoleEntity(int id, String nameString, String descripString) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.descripString = descripString;
	}

	public RoleEntity(String nameString, String descripString) {
		super();
		this.nameString = nameString;
		this.descripString = descripString;
	}

	public RoleEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getDescripString() {
		return descripString;
	}

	public void setDescripString(String descripString) {
		this.descripString = descripString;
	}

}
