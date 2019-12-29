package authority.domain;


import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class MenuAllocation implements Comparable<MenuAllocation>, Serializable {

	@Override
	public int compareTo(MenuAllocation o) {
		// TODO Auto-generated method stub
		return this.id - o.id;
	}


	public MenuAllocation(
			int id,
			Collection<MenuAllocationStatus> RoleAllocationStatusSet,
			Role Role) {
		this.id = id;
		this.menuAllocationStatusSet = RoleAllocationStatusSet;
		this.Role = Role;
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
	
	private Collection<MenuAllocationStatus> menuAllocationStatusSet;

	public void setId(int id) {
		this.id = id;
	}

	public void setMenuAllocationStatusSet(
			Set<MenuAllocationStatus> RoleAllocationStatusSet) {
		this.menuAllocationStatusSet = RoleAllocationStatusSet;
	}

	public void setRole(Role Role) {
		this.Role = Role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Role Role;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public MenuAllocation(){
		super();
	}

	public int getId() {
		return id;
	}

	public Collection<MenuAllocationStatus> getMenuAllocationStatusSet() {
		return menuAllocationStatusSet;
	}

	public Role getRole() {
		return Role;
	}
}

