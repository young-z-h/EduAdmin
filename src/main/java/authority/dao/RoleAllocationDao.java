package authority.dao;

import authority.domain.RoleAllocation;
import authority.domain.RoleAllocationStatus;
import authority.domain.User;
import authority.service.RoleAllocationService;
import authority.service.RoleAllocationStatusService;
import authority.service.UserService;
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

/**
 * RoleAllocation是一个实体类，但是不打算建表
 */
public class RoleAllocationDao {
    private static RoleAllocationDao
            roleAllocationDao = new RoleAllocationDao();
    private static Set<RoleAllocation>
            roleAllocations;

    public RoleAllocationDao(){}

    public static RoleAllocationDao getInstance(){
        return roleAllocationDao;
    }


    /**
     * 暂时不用此方法
     * @return
     * @throws SQLException
     */
    public Collection<RoleAllocation> findAll()
            throws SQLException {

        roleAllocations = new TreeSet<>();

        Collection<User> users = UserService.getInstance().findAll();

        int counter = 0;

        for (User user:users){
//            RoleAllocationService.getInstance()
//                    .setAllAllocated();

            Collection<RoleAllocationStatus> newR
                    = RoleAllocationService.getInstance().getFullRoleStatus(user);

            RoleAllocation roleAllocation = new RoleAllocation(
                    ++counter,newR,user
            );

            System.out.println(JSONObject.toJSONString(roleAllocation));

            roleAllocations.add(roleAllocation);
        }

        System.out.println(JSONObject.toJSONString(roleAllocations, SerializerFeature.DisableCircularReferenceDetect));

        return roleAllocations;
    }


    /**
     * 传入user对象，返回对应这个user对象的roleAllocation对象
     * @param user
     * @return
     * @throws SQLException
     */
    public RoleAllocation find(User user) throws SQLException {
        RoleAllocation roleAllocation = null;
        //本不需要id
        roleAllocation = new RoleAllocation(1,
                RoleAllocationService.getInstance().getFullRoleStatus(user),
                user);
        return roleAllocation;
    }

    public RoleAllocation find(Integer id) throws SQLException {
        return this.find(UserService.getInstance().find(id));
    }


    public Collection<RoleAllocationStatus> getFullRoleStatus(User user)
            throws SQLException {

        Collection<RoleAllocationStatus> roleAllocationStatusSet
                = RoleAllocationStatusService.getInstance().findAll();

        Connection connection = JdbcHelper.getConn();
        String sql1 = "select role_id " +
                "from userroleass " +
                "where user_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql1);
        ps.setInt(1, user.getId());
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()){
            for(RoleAllocationStatus roleAllocationStatus:
                    roleAllocationStatusSet){
                if (roleAllocationStatus.getRole().getId() == resultSet.getInt("role_id")){
                    roleAllocationStatus.setAllocated(true);
                }
            }
        }

        return roleAllocationStatusSet;
    }



    public static void main(String[] args) throws SQLException {
        JSONObject.toJSONString(RoleAllocationService.getInstance().findAll(),
                        SerializerFeature.DisableCircularReferenceDetect);
//        User user = UserService.getInstance().find(1);
//
//        System.out.println(
//                JSONObject.toJSONString(RoleAllocationDao.getInstance().getFullMenuStatus(user))
//        );
    }

}
