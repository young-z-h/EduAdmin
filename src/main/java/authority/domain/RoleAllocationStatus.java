package authority.domain;


import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class RoleAllocationStatus
		implements Comparable<RoleAllocationStatus>, Serializable {

	@Override
	public int compareTo(RoleAllocationStatus o) {
		// TODO Auto-generated method stub
		return this.id - o.id;
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
	
	private boolean allocated;

	public int getId() {
		return id;
	}

	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isAllocated() {
		return allocated;
	}

	public Role getRole() {
		return role;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Role role;


	public RoleAllocationStatus(int id, boolean allocated, Role role) {
		this.id = id;
		this.allocated = allocated;
		this.role = role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public RoleAllocationStatus(){
		super();
	}

}

