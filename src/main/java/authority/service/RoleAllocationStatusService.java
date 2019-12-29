package authority.service;


import authority.dao.RoleAllocationStatusDao;
import authority.domain.RoleAllocationStatus;

import java.sql.SQLException;
import java.util.Collection;

public class RoleAllocationStatusService {
    private static RoleAllocationStatusDao roleAllocationStatusDao= RoleAllocationStatusDao.getInstance();
    private static RoleAllocationStatusService roleAllocationStatusService=new RoleAllocationStatusService();
    private RoleAllocationStatusService(){}

    public static RoleAllocationStatusService getInstance(){
        return roleAllocationStatusService;
    }

    public Collection<RoleAllocationStatus> findAll() throws SQLException {
        return roleAllocationStatusDao.findAll();
    }

    public RoleAllocationStatus find(Integer id) throws SQLException {
        return roleAllocationStatusDao.find(id);
    }

}
