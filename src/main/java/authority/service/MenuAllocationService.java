package authority.service;

import authority.domain.*;
import authority.dao.MenuAllocationDao;
import authority.domain.MenuAllocation;
import authority.domain.MenuAllocationStatus;

import java.sql.SQLException;
import java.util.Collection;

public class MenuAllocationService {
    private static MenuAllocationDao menuAllocationDao= MenuAllocationDao.getInstance();
    private static MenuAllocationService menuAllocationService=new MenuAllocationService();
    private MenuAllocationService(){}

    public static MenuAllocationService getInstance(){
        return menuAllocationService;
    }

    public Collection<MenuAllocation> findAll() throws SQLException {
        return menuAllocationDao.findAll();
    }

    public MenuAllocation find(Integer id) throws SQLException {
        return menuAllocationDao.find(id);
    }


    public Collection<MenuAllocationStatus> getFullMenuStatus(
            Role role) throws SQLException {
        return menuAllocationDao.getFullMenuStatus(role);
    }
}
