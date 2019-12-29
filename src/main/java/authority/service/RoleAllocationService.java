package authority.service;

import authority.domain.*;
import authority.dao.RoleAllocationDao;
import authority.domain.RoleAllocation;
import authority.domain.RoleAllocationStatus;

import java.sql.SQLException;
import java.util.Collection;

public class RoleAllocationService {
    private static RoleAllocationDao roleAllocationDao= RoleAllocationDao.getInstance();
    private static RoleAllocationService roleAllocationService=new RoleAllocationService();
    private RoleAllocationService(){}

    public static RoleAllocationService getInstance(){
        return roleAllocationService;
    }

    public Collection<RoleAllocation> findAll() throws SQLException {
        return roleAllocationDao.findAll();
    }

    public RoleAllocation find(Integer id) throws SQLException {
        return roleAllocationDao.find(id);
    }

    public RoleAllocation find(User user) throws SQLException{
        return roleAllocationDao.find(user);
    }

    public Collection<RoleAllocationStatus> getFullRoleStatus(
            User user) throws SQLException {
        return roleAllocationDao.getFullRoleStatus(user);
    }
}
