package com.sample.twitter.dao;

import com.sample.twitter.model.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


@Service
public class UserDaoImpl implements UserDao {

    private EntityManager em ;
    private EntityManagerFactory factory;

    public UserDaoImpl(){

       if (factory== null){
           factory = Persistence.createEntityManagerFactory("USER");
           em = factory.createEntityManager();
        }
    }

    @Override
    public void addUser(UserDetails p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    @Override
    public void updateUser(UserDetails p) {
        //TODO
    }

    public List<UserDetails> listUsers(){
                return em.createQuery("select username from USER ", UserDetails.class).getResultList();
    }

    @Override
    public void removeUser(String name) {
        //TODO

    }

    @Override
    public UserDetails getUserByName(String name) {

        System.out.println(em);
        em.getTransaction().begin();
        UserDetails user = em.find(UserDetails.class,name );
        em.getTransaction().commit();

        if (user == null)
            user = new UserDetails();

        return user;
    }

}