package service;


import dao.RoleDao;
import domain.Role;

import java.sql.SQLException;
import java.util.Collection;

public class RoleService {
    private static RoleDao roleDao= RoleDao.getInstance();
    private static RoleService roleService=new RoleService();
    private RoleService(){}

    public static RoleService getInstance(){
        return roleService;
    }

    public Collection<Role> findAll() throws SQLException {
        return roleDao.findAll();
    }

    public Role find(Integer id) throws SQLException {
        return roleDao.find(id);
    }

//    public boolean update(Role role) throws SQLException {
//        return roleDao.update(role);
//    }
//
//    public boolean add(Role role) throws SQLException {
//        return roleDao.add(role);
//    }
//
//    public boolean delete(Integer id) throws SQLException {
//        Role role = this.find(id);
//        return roleDao.delete(role);
//    }
//
//    public boolean delete(Role role) throws SQLException {
//        return roleDao.delete(role);
//    }
}
