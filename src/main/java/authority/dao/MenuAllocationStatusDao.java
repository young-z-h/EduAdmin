package authority.dao;
import authority.domain.*;
import authority.domain.MenuAllocationStatus;
import authority.service.MenuAllocationStatusService;
import authority.service.MenuService;
import com.alibaba.fastjson.JSONObject;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class MenuAllocationStatusDao {
    private static MenuAllocationStatusDao
            menuAllocationStatusDao = new MenuAllocationStatusDao();
    private static Set<MenuAllocationStatus>
            menuAllocationStatusSet;

    public MenuAllocationStatusDao(){}

    public static MenuAllocationStatusDao getInstance(){
        return menuAllocationStatusDao;
    }

    /**
     * 返回MenuAllocationStatusDao表中的所有对象
     * @return 集合类对象,元素类型为MenuAllocationStatusDao
     * @throws SQLException
     */
    public Collection<MenuAllocationStatus> findAll()
            throws SQLException {
        menuAllocationStatusSet = new TreeSet<>();
        Collection<Menu> menus = MenuService.getInstance().findAll();
        int counter = 0;
        for (Menu menu: menus){
            menuAllocationStatusSet.add(new MenuAllocationStatus(
                    ++counter, false, menu
            ));
        }
        return MenuAllocationStatusDao.menuAllocationStatusSet;
    }

    public MenuAllocationStatus find(Integer id) throws SQLException {
        MenuAllocationStatus menuAllocationStatus = null;

        return menuAllocationStatus;
    }


    public static void main(String[] args) throws SQLException {
        String json =
                JSONObject.toJSONString(MenuAllocationStatusService.getInstance().findAll());
        System.out.println(json);
    }
}
