package authority.util;

public class GarbageMethod {

    /*role*/

//    /**
//     * 向表中添加记录
//     * @param role
//     * @return 是否成功
//     * @throws SQLException
//     */
//    public boolean add(Role role)
//            throws SQLException {
//        //获得连接对象
//        Connection connection = JdbcHelper.getConn();
//        //创建sql查询语句
//        String addRole_sql =
//                "INSERT INTO Role(no, " +
//                        "description, " +
//                        "VALUES" +
//                        " (?,?)";
//        PreparedStatement pstmt =
//                connection.prepareStatement(addRole_sql);
//        pstmt.setString(1,role.getNo());
//        pstmt.setString(2,role.getDescription());
//
//        int affectedRowNum = pstmt.executeUpdate();
//
//        System.out.println("添加了" + affectedRowNum + "条记录...");
//        JdbcHelper.close(pstmt,connection);
//        return affectedRowNum > 0;
//    }
//
//    public boolean delete(Integer id) throws SQLException {
//        Connection connection = JdbcHelper.getConn();
//        String addRole_sql =
//                "delete from Role where id=?";
//        PreparedStatement pstmt =
//                connection.prepareStatement(addRole_sql);
//        pstmt.setInt(1,id);
//        int affectedRowNum = pstmt.executeUpdate();
//        System.out.println("删除了" + pstmt.executeUpdate() + "条记录...");
//        JdbcHelper.close(pstmt,connection);
//        return affectedRowNum>0;
//    }
//
//    /**
//     * 按照对象删除(本质是删除同id记录)
//     * @param role
//     * @return 是否执行成功
//     * @throws SQLException
//     */
//    public boolean delete(Role role)
//            throws SQLException {
//        return this.delete(role.getId());
//    }


    /*menu*/
//    /**
//     * 向表中添加记录
//     * @param menu
//     * @return 是否成功
//     * @throws SQLException
//     */
//    public boolean add(Menu menu)
//            throws SQLException {
//        //获得连接对象
//        Connection connection = JdbcHelper.getConn();
//        //创建sql查询语句
//        String addMenu_sql =
//                "INSERT INTO Menu(no, " +
//                        "description, " +
//                        "VALUES" +
//                        " (?,?)";
//        PreparedStatement pstmt =
//                connection.prepareStatement(addMenu_sql);
//        pstmt.setString(1,menu.getNo());
//        pstmt.setString(2,menu.getDescription());
//
//        int affectedRowNum = pstmt.executeUpdate();
//
//        JdbcHelper.close(pstmt,connection);
//        return affectedRowNum > 0;
//    }

//    public boolean delete(Integer id) throws SQLException {
//        Connection connection = JdbcHelper.getConn();
//        String deleteMenu_sql =
//                "delete from menu where id=?";
//        PreparedStatement pstmt =
//                connection.prepareStatement(deleteMenu_sql);
//        pstmt.setInt(1,id);
//        int affectedRowNum = pstmt.executeUpdate();
//        JdbcHelper.close(pstmt,connection);
//        return affectedRowNum>0;
//    }

//    /**
//     * 按照对象删除(本质是删除同id记录)
//     * @param menu
//     * @return 是否执行成功
//     * @throws SQLException
//     */
//    public boolean delete(Menu menu)
//            throws SQLException {
//        return this.delete(menu.getId());
//    }
}
