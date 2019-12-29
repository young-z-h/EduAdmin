package authority.dao;

import authority.domain.MenuAllocation;
import authority.domain.MenuAllocationStatus;
import authority.domain.Role;
import authority.service.MenuAllocationService;
import authority.service.MenuAllocationStatusService;
import authority.service.RoleService;
import authority.util.JdbcHelper;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class MenuAllocationDao {
    private static MenuAllocationDao
            menuAllocationDao = new MenuAllocationDao();
    private static Set<MenuAllocation>
            menuAllocations;

    public MenuAllocationDao(){}

    public static MenuAllocationDao getInstance(){
        return menuAllocationDao;
    }

    /**
     * 令目标集合指向一个空TreeSet集合
     * 获得所有的Role放入一个集合中
     * 模拟id赋值
     * 遍历这个role集合
     * 对遍历的每个Role都使用getFullMenuStatus()方法
     * 将修改完成的集合再依次放入返回的集合menuAllocations中
     * @return
     * @throws SQLException
     */
    public Collection<MenuAllocation> findAll()
            throws SQLException {
        menuAllocations = new TreeSet<>();

        Collection<Role> roles = RoleService.getInstance().findAll();

        int counter = 0;

        for (Role role:roles){
            Collection<MenuAllocationStatus> newR
                    = MenuAllocationService.getInstance().getFullMenuStatus(role);

            MenuAllocation menuAllocation = new MenuAllocation(
                    ++counter, newR,role
            );
            menuAllocations.add(menuAllocation);
        }
        return menuAllocations;
    }

    /**
     * 先获得所有的MenuAllocationStatusStatus对象
     * 全部allocated字段的值都为false
     * 通过rolemenuass查找role对应的所有已有的menu
     * 根据查找到的已有的menu,操作MenuAllocationStatusStatus集合
     * 将该role拥有的menu的MenuAllocationStatusStatus对象的allocated字段赋值true
     * 然后返回这个role对应的MenuAllocationStatusStatus集合
     * @param role
     * @return
     * @throws SQLException
     */
    public Collection<MenuAllocationStatus> getFullMenuStatus(
            Role role) throws SQLException {
        Collection<MenuAllocationStatus> menuAllocationStatusSet
                = MenuAllocationStatusService.getInstance().findAll();
        Connection connection = JdbcHelper.getConn();
        String sql1 = "select menu_id " +
                "from rolemenuass " +
                "where role_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql1);
        ps.setInt(1, role.getId());
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            for(MenuAllocationStatus menuAllocationStatus:
                    menuAllocationStatusSet){
                if (menuAllocationStatus.getMenu().getId()
                        == resultSet.getInt("menu_id")){
                    menuAllocationStatus.setAllocated(true);
                }
            }
        }
        return menuAllocationStatusSet;
    }


    /**
     * nothing...
     * @param id
     * @return
     * @throws SQLException
     */
    public MenuAllocation find(Integer id) throws SQLException {
        MenuAllocation menuAllocation = null;

        return menuAllocation;
    }

    public static void main(String[] args) throws SQLException {
        JSONObject.toJSONString(MenuAllocationDao.getInstance().findAll(),
                SerializerFeature.DisableCircularReferenceDetect);
    }
}
