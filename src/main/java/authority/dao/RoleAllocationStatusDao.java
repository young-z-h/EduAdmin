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


    /**
     * 获得表中所有的Role对象
     * 遍历Role集合
     * 根据每个Role创建RoleAllocationStatus对象
     * 返回RoleAllocationStatus
     * @return 集合类对象,元素类型为RoleAllocationStatusDao
     * @throws SQLException
     */
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
