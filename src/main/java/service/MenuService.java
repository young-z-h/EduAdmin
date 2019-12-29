package service;
import dao.MenuDao;
import domain.Menu;
import java.sql.SQLException;
import java.util.Collection;

public class MenuService {
    private static MenuDao menuDao= MenuDao.getInstance();
    private static MenuService menuService=new MenuService();
    private MenuService(){}

    public static MenuService getInstance(){
        return menuService;
    }

    public Collection<Menu> findAll() throws SQLException {
        return menuDao.findAll();
    }

    public Menu find(Integer id) throws SQLException {
        return menuDao.find(id);
    }

    public Collection<Menu> findByUser(Integer id) throws SQLException{
        return menuDao.findByUser(id);
    }
}
