package authority.service;

import authority.dao.ActorDao;
import authority.domain.Actor;

import java.sql.SQLException;
import java.util.Collection;

public class ActorService {
    private static ActorDao actorDao= ActorDao.getInstance();
    private static ActorService actorService=new ActorService();
    private ActorService(){}

    public static ActorService getInstance(){
        return actorService;
    }

    public Collection<Actor> findAll() throws SQLException {
        return actorDao.findAll();
    }

    public Actor find(Integer id) throws SQLException {
        return actorDao.find(id);
    }
}
