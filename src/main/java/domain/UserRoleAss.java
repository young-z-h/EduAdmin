package domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class UserRoleAss
{
	public int getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public User getUser() {
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private int id;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private Role role;

	@Override
	public String toString() {
		return "UserRoleAss{" +
				"id=" + id +
				", role=" + role +
				", user=" + user +
				'}';
	}

	public UserRoleAss(int id, User user, Role role) {
		this.id = id;
		this.role = role;
		this.user = user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private User user;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public UserRoleAss(){
		super();
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

