package com.sample.twitter.dao;

import java.util.List;

import com.sample.twitter.model.UserDetails;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Repository
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
//          em.createQuery("INSERT into USER  (username) values (\""+ p.getUsername()+ "\");").getFirstResult();
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

        em.getTransaction().begin();
        UserDetails user = em.find(UserDetails.class,name );
        em.getTransaction().commit();
//        System.out.println(user.getUsername());

        return user;
    }

}