package authority.service;

import authority.dao.MailDao;
import authority.domain.Mail;

import java.sql.SQLException;
import java.util.Collection;

public class MailService {
    private static MailDao mailDao= MailDao.getInstance();
    private static MailService mailService=new MailService();
    private MailService(){}

    public static MailService getInstance(){
        return mailService;
    }

    public Collection<Mail> findAll() throws SQLException {
        return mailDao.findAll();
    }

    public boolean add(Mail mail) throws SQLException{
        return mailDao.add(mail);
    }
}
