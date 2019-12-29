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

    public Collection<MenuAllocation> findAll()
            throws SQLException {
        menuAllocations = new TreeSet<>();



        Collection<Role> roles = RoleService.getInstance().findAll();

        int counter = 0;

        for (Role role:roles){
//            MenuAllocationService.getInstance().setAllAllocated(menuAllocationStatusSet);

            Collection<MenuAllocationStatus> newR
                    = MenuAllocationService.getInstance().getFullMenuStatus(role);

            MenuAllocation menuAllocation = new MenuAllocation(
                    ++counter, newR,role
            );

//            System.out.println(JSONObject.toJSONString(menuAllocation));

            menuAllocations.add(menuAllocation);
        }

//        System.out.println(JSONObject.toJSONString(menuAllocations, SerializerFeature.DisableCircularReferenceDetect));

        return menuAllocations;
    }

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
