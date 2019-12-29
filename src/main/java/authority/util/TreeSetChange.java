package authority.util;


import authority.domain.RoleAllocationStatus;
import authority.service.RoleAllocationStatusService;
import com.alibaba.fastjson.JSONObject;

import java.sql.SQLException;
import java.util.Collection;

public class TreeSetChange {
    public static void main(String[] args) throws SQLException {
        Collection<RoleAllocationStatus> collection
                = RoleAllocationStatusService.getInstance().findAll();
        for (RoleAllocationStatus roleAllocationStatus:collection) {
            roleAllocationStatus.setAllocated(true);
        }
        System.out.println(JSONObject.toJSONString(collection));
    }
}
