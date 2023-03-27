package com.service;

import com.model.UsersLoginCreds;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserLCServiceImpl implements UserLoginCredsService {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<UsersLoginCreds> getUsers(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<UsersLoginCreds> usersRecord = session.createQuery("from UsersLoginCreds",UsersLoginCreds.class).list();
        transaction.commit();
        session.close();
        return usersRecord;
    }

    @Override
    public UsersLoginCreds getUserById(String id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsersLoginCreds usersLoginCreds = session.get(UsersLoginCreds.class,id);
        transaction.commit();
        session.close();
        return usersLoginCreds;
    }
}
