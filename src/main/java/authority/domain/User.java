package authority.domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class User
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int id;

	public int getId() {
		return id;
	}

	public User(int id, String username, String password, Actor actor) {
		this.id = id;
		this.password = password;
		this.username = username;
		this.actor = actor;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public Actor getActor() {
		return actor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String password;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String username;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Actor actor;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public User(){
		super();
	}

}

