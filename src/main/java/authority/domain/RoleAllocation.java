package authority.domain;


import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class RoleAllocation
		implements Comparable<RoleAllocation>, Serializable {
	@Override
	public int compareTo(RoleAllocation o) {
		// TODO Auto-generated method stub
		return this.user.getId() - o.user.getId();
	}

	public RoleAllocation(int id, User user) {
		this.id = id;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<RoleAllocationStatus> getRoleAllocationStatusSet() {
		return roleAllocationStatusSet;
	}

	public void setRoleAllocationStatusSet(Set<RoleAllocationStatus> roleAllocationStatusSet) {
		this.roleAllocationStatusSet = roleAllocationStatusSet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	private Collection<RoleAllocationStatus> roleAllocationStatusSet;

	public RoleAllocation(int id,
						  Collection<RoleAllocationStatus> roleAllocationStatusSet,
						  User user) {
		this.id = id;
		this.roleAllocationStatusSet = roleAllocationStatusSet;
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
	public RoleAllocation(){
		super();
	}

	public void setRoleAllocationStatusSet(Collection<RoleAllocationStatus> roleAllocationStatusSet) {
		this.roleAllocationStatusSet = roleAllocationStatusSet;
	}
}

