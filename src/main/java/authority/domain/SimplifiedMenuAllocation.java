package authority.domain;

public class SimplifiedMenuAllocation {
    private int roleId;
    private int[] menuIds;

    public int getRoleId() {
        return roleId;
    }

    public int[] getMenuIds() {
        return menuIds;
    }

    public SimplifiedMenuAllocation(int roleId, int[] menuIds) {
        this.roleId = roleId;
        this.menuIds = menuIds;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setMenuIds(int[] menuIds) {
        this.menuIds = menuIds;
    }
}
