package authority.domain;

public class SimplifiedRoleAllocation {
    private int userId;

    public SimplifiedRoleAllocation(int userId, int[] roleIds) {
        this.userId = userId;
        this.roleIds = roleIds;
    }

    private int[] roleIds;

    public int getUserId() {
        return userId;
    }

    public int[] getRoleIds() {
        return roleIds;
    }
}
