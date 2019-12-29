package authority.service;


import authority.dao.RoleMenuAssDao;
import authority.domain.RoleMenuAss;

import java.sql.SQLException;
import java.util.Collection;

public class RoleMenuAssService {
    private static RoleMenuAssDao roleMenuAssDao = RoleMenuAssDao.getInstance();
    private static RoleMenuAssService roleMenuAssService =new RoleMenuAssService();
    private RoleMenuAssService(){}

    public static RoleMenuAssService getInstance(){
        return roleMenuAssService;
    }

    public Collection<RoleMenuAss> findAll() throws SQLException {
        return roleMenuAssDao.findAll();
    }

    public RoleMenuAss find(Integer id) throws SQLException {
        return roleMenuAssDao.find(id);
    }
}
