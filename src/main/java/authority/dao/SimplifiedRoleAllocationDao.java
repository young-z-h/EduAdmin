package authority.dao;

import authority.domain.Role;
import authority.domain.SimplifiedRoleAllocation;
import authority.service.RoleService;
import authority.util.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class SimplifiedRoleAllocationDao {
    private static SimplifiedRoleAllocationDao
            simplifiedRoleAllocationDao = new SimplifiedRoleAllocationDao();
    private static Set<SimplifiedRoleAllocation>
            simplifiedRoleAllocations;

    public SimplifiedRoleAllocationDao(){}

    public static SimplifiedRoleAllocationDao getInstance(){
        return simplifiedRoleAllocationDao;
    }

    /**
     * 传入极简的user-role类型对象
     * 根据发送过来的极简对象的id生成的role集合
     * 根据数据库中的表的menu_id记录来获得现在已经有的role对象集合
     * 需要从数据库中删除的menu集合--删除menu_id
     * 需要添加到数据库中的menu集合--增加menu_id
     * 创建数据库连接对象
     * 取消自动提交
     * 获得role-menu对象的menuIds
     * 该链接上执行查询语句，获得role自己的menus
     * 添加到对比用的menus集合中
     * 求对应两个差集（应该添加的menus、以及需要删除的menus）
     * 根据要删除的menus在数据库中删除
     * 根据要添加的menus在数据库中增加
     * 提交
     * 设置自动提交
     * 返回是否执行成功
     * @param sra
     * @return
     */
    public boolean alterRole(SimplifiedRoleAllocation sra){
        boolean affectedRowNum = false;
        Connection connection = null;
        PreparedStatement pstmt = null;

        Collection<Role> roles = new TreeSet<>();
        Collection<Role> comparedRoles = new TreeSet<>();

        Collection<Role> addRoles = new TreeSet<>();
        Collection<Role> removeRoles = new TreeSet<>();

        try{
            connection = JdbcHelper.getConn();
            connection.setAutoCommit(false);

            for (int counter: sra.getRoleIds())
                roles.add(RoleService.getInstance().find(counter));

            String getSimplifiedRole_sql =
                    "select role_id from userroleass where user_id = ?";
            pstmt = connection.prepareStatement(getSimplifiedRole_sql);
            pstmt.setInt(1, sra.getUserId());

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()){
                comparedRoles.add(RoleService.getInstance().find(resultSet.getInt("role_id")));
            }

            addRoles.addAll(roles);
            removeRoles.addAll(comparedRoles);
            addRoles.removeAll(comparedRoles);
            removeRoles.removeAll(roles);

            for (Role role: removeRoles){
                String remove_sql = "delete from userroleass " +
                        "where user_id=? " +
                        "and role_id=?;";
                pstmt = connection.prepareStatement(remove_sql);
                pstmt.setInt(1, sra.getUserId());
                pstmt.setInt(2, role.getId());
                System.out.println("delete role_id:" + sra.getUserId() + " menu_id:" + role.getId());
                pstmt.executeUpdate();
            }

            for (Role role: addRoles){
                String add_sql = "INSERT INTO userroleass(user_id, role_id) VALUES" +
                        " (?,?)";
                pstmt = connection.prepareStatement(add_sql);
                pstmt.setInt(1, sra.getUserId());
                pstmt.setInt(2,role.getId());
                affectedRowNum = pstmt.executeUpdate()>0;
                System.out.println("add role_id:" + sra.getUserId() + " menu_id:" + role.getId());
            }

            connection.commit();

        }catch (SQLException e){
            e.printStackTrace();
            try {
                if (connection != null)
                    connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }finally {
            try {
                if (connection != null){
                    connection.setAutoCommit(true);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        JdbcHelper.close(pstmt,connection);

        return affectedRowNum;
    }

    public static void main(String[] args) throws SQLException {
        int[] list = new int[]{2,3};
        SimplifiedRoleAllocation sma = new SimplifiedRoleAllocation(1,list);
        SimplifiedRoleAllocationDao.getInstance().alterRole(sma);
    }
}
