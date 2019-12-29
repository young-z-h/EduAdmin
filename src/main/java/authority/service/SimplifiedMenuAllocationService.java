package authority.service;


import authority.dao.SimplifiedMenuAllocationDao;
import authority.domain.SimplifiedMenuAllocation;

public class SimplifiedMenuAllocationService {
    private static SimplifiedMenuAllocationDao simplifiedRoleAllocationDao = SimplifiedMenuAllocationDao.getInstance();
    private static SimplifiedMenuAllocationService simplifiedRoleAllocationService =new SimplifiedMenuAllocationService();
    private SimplifiedMenuAllocationService(){}

    public static SimplifiedMenuAllocationService getInstance(){
        return simplifiedRoleAllocationService;
    }

    public boolean alterRole(SimplifiedMenuAllocation sra){
        return simplifiedRoleAllocationDao.alterMenu(sra);
    }
}
