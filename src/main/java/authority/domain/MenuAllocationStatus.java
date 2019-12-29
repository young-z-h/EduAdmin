package authority.domain;


import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class MenuAllocationStatus
		implements Comparable<MenuAllocationStatus>, Serializable {

	@Override
	public int compareTo(MenuAllocationStatus o) {
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

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Menu menu;

	public MenuAllocationStatus(int id, boolean allocated, Menu menu) {
		this.id = id;
		this.allocated = allocated;
		this.menu = menu;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}


	public int getId() {
		return id;
	}

	public boolean isAllocated() {
		return allocated;
	}

	public Menu getMenu() {
		return menu;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public MenuAllocationStatus(){
		super();
	}

}

