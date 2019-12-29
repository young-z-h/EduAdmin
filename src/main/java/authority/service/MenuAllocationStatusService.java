package authority.service;


import authority.dao.MenuAllocationStatusDao;
import authority.domain.MenuAllocationStatus;

import java.sql.SQLException;
import java.util.Collection;

public class MenuAllocationStatusService {
    private static MenuAllocationStatusDao menuAllocationStatusDao= MenuAllocationStatusDao.getInstance();
    private static MenuAllocationStatusService menuAllocationStatusService=new MenuAllocationStatusService();
    private MenuAllocationStatusService(){}

    public static MenuAllocationStatusService getInstance(){
        return menuAllocationStatusService;
    }

    public Collection<MenuAllocationStatus> findAll() throws SQLException {
        return menuAllocationStatusDao.findAll();
    }

    public MenuAllocationStatus find(Integer id) throws SQLException {
        return menuAllocationStatusDao.find(id);
    }
}
