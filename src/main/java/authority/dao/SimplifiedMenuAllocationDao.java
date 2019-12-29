package authority.dao;


import authority.domain.Menu;
import authority.domain.SimplifiedMenuAllocation;
import authority.service.MenuService;
import authority.util.JdbcHelper;
import com.alibaba.fastjson.JSON;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class SimplifiedMenuAllocationDao {
    private static SimplifiedMenuAllocationDao
        simplifiedMenuAllocationDao = new SimplifiedMenuAllocationDao();
    private static Set<SimplifiedMenuAllocation>
            simplifiedMenuAllocations;

    public SimplifiedMenuAllocationDao(){}

    public static SimplifiedMenuAllocationDao getInstance(){
        return simplifiedMenuAllocationDao;
    }

    public boolean alterMenu(SimplifiedMenuAllocation sma){
        System.out.println(sma.toString());
        boolean affectedRowNum = false;
        Connection connection = null;
        PreparedStatement pstmt = null;

        /*根据发送过来的极简对象的id生成的menu集合(menus---来自前台)*/
        Collection<Menu> menus = new TreeSet<>();

        /*根据数据库中的表的menu_id记录来获得现在已经有的menu对象集合(comparedMenus---来自数据库)*/
        Collection<Menu> comparedMenus = new TreeSet<>();


        /*需要从数据库中删除的menu集合--删除menu_id*/
        Collection<Menu> removeMenus = new TreeSet<>();
        /*需要添加到数据库中的menu集合--增加menu_id*/
        Collection<Menu> addMenus = new TreeSet<>();


        try{
            connection = JdbcHelper.getConn();
            connection.setAutoCommit(false);

            for (int counter: sma.getMenuIds())
                menus.add(MenuService.getInstance().find(counter));

            String getSimplifiedMenu_sql =
                    "select menu_id from rolemenuass where role_id = ?";
            pstmt = connection.prepareStatement(getSimplifiedMenu_sql);
            pstmt.setInt(1, sma.getRoleId());

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()){
                comparedMenus.add(MenuService.getInstance().find(resultSet.getInt("menu_id")));
            }

            addMenus.addAll(menus);
            removeMenus.addAll(comparedMenus);
            addMenus.removeAll(comparedMenus);
            removeMenus.removeAll(menus);

            for (Menu menu: removeMenus){
                String remove_sql = "delete from rolemenuass " +
                        "where role_id=? " +
                        "and menu_id=?;";
                pstmt = connection.prepareStatement(remove_sql);
                pstmt.setInt(2, menu.getId());
                pstmt.setInt(1, sma.getRoleId());
                pstmt.executeUpdate();
                System.out.println("delete menu_id:" + sma.getRoleId() + " menu_id:" + menu.getId());
            }

            for (Menu menu: addMenus){
                String add_sql = "INSERT INTO rolemenuass(role_id, menu_id) VALUES" +
                        " (?,?)";
                pstmt = connection.prepareStatement(add_sql);
                pstmt.setInt(1, sma.getRoleId());
                pstmt.setInt(2,menu.getId());
                affectedRowNum = pstmt.executeUpdate()>0;
                System.out.println("add menu_id:" + sma.getRoleId() + " menu_id:" + menu.getId());
            }

            connection.commit();

        }catch (SQLException e){
            e.printStackTrace();
            try {
                if (connection != null)
                    connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }finally {
            try {
                if (connection != null){
                    connection.setAutoCommit(true);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        JdbcHelper.close(pstmt,connection);

        return affectedRowNum;
    }

    public static void main(String[] args) throws SQLException {
        int[] list = new int[]{1,2,4};
        SimplifiedMenuAllocation sma = new SimplifiedMenuAllocation(1,list);
        System.out.println(JSON.toJSONString(sma));
        SimplifiedMenuAllocationDao.getInstance().alterMenu(sma);
    }

}
