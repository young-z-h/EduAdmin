package domain;

import java.io.Serializable;

public class Role implements Comparable<Role>, Serializable {
    @Override
    public int compareTo(Role o) {
        return this.id-o.id;
    }

    public Role(int id, String no, String description) {
        this.id = id;
        this.description = description;
        this.no = no;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getNo() {
        return no;
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

    private String description;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     * @ordered
     */

    private String no;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     * @generated
     */
    public Role(){
        super();
    }
}
