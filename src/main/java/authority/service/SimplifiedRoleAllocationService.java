package authority.service;


import authority.dao.SimplifiedRoleAllocationDao;
import authority.domain.SimplifiedRoleAllocation;

public class SimplifiedRoleAllocationService {
    private static SimplifiedRoleAllocationDao simplifiedRoleAllocationDao = SimplifiedRoleAllocationDao.getInstance();
    private static SimplifiedRoleAllocationService simplifiedRoleAllocationService =new SimplifiedRoleAllocationService();
    private SimplifiedRoleAllocationService(){}

    public static SimplifiedRoleAllocationService getInstance(){
        return simplifiedRoleAllocationService;
    }

    public boolean alterRole(SimplifiedRoleAllocation sra){
        return simplifiedRoleAllocationDao.alterRole(sra);
    }
}
