package domain;

import java.io.Serializable;

public class Menu
    implements Comparable<Menu>, Serializable
    {
        @Override
        public int compareTo(Menu o) {
        return this.id-o.id;
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
         * @ordered
         */

        private String url;

        public void setId(int id) {
        this.id = id;
    }

	public Menu(int id, String description, String no, String url) {
        this.id = id;
        this.description = description;
        this.no = no;
        this.url = url;
    }

        public void setDescription(String description) {
        this.description = description;
    }

        public void setNo(String no) {
        this.no = no;
    }

        public void setUrl(String url) {
        this.url = url;
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

        public String getUrl() {
        return url;
    }

        /**
         * <!-- begin-user-doc -->
         * <!--  end-user-doc  -->
         * @generated
         */
	public Menu(){
        super();
    }

    }

