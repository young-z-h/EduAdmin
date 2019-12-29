package domain;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class RoleMenuAss
{
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
	
	private Menu menu;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Role role;

	public void setId(int id) {
		this.id = id;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleMenuAss(int id, Menu menu, Role role) {
		this.id = id;
		this.menu = menu;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public Menu getMenu() {
		return menu;
	}

	public Role getRole() {
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public RoleMenuAss(){
		super();
	}

}

