package authority.dao;

import authority.domain.*;
import authority.domain.RoleAllocationStatus;
import authority.service.RoleAllocationStatusService;
import authority.service.RoleService;
import com.alibaba.fastjson.JSONObject;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class RoleAllocationStatusDao {
    private static RoleAllocationStatusDao
            roleAllocationStatusDao = new RoleAllocationStatusDao();
    private static Set<RoleAllocationStatus>
            roleAllocationStatusSet;

    public RoleAllocationStatusDao(){}

    public static RoleAllocationStatusDao getInstance(){
        return roleAllocationStatusDao;
    }


    public Collection<RoleAllocationStatus> findAll()
            throws SQLException {
        roleAllocationStatusSet = new TreeSet<>();
        Collection<Role> roles = RoleService.getInstance().findAll();
        int counter = 0;
        for (Role role: roles){
            roleAllocationStatusSet.add(new RoleAllocationStatus(
                   ++counter, false, role
            ));
        }
        return RoleAllocationStatusDao.roleAllocationStatusSet;
    }

    public RoleAllocationStatus find(Integer id) throws SQLException {
        RoleAllocationStatus roleAllocationStatus = null;

        return roleAllocationStatus;
    }


    public static void main(String[] args) throws SQLException {
        String json =
                JSONObject.toJSONString(RoleAllocationStatusService.getInstance().findAll());
        System.out.println(json);
    }
}
