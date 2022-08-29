package cl.injcristianrojas.swsecintro.service;

import cl.injcristianrojas.swsecintro.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserServiceJPA {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> findUsersByType(String user_type) {
        TypedQuery<User> query = entityManager.createQuery("from User where user_type = " + user_type, User.class);
        return query.getResultList();
    }

    public List<User> authenticateUserList(String username, String password) {
        TypedQuery<User> query = entityManager.createQuery("from User where username = '" + username + "' and password = '" + password + "'", User.class);
        return query.getResultList();
    }

    public User authenticateUser(String username, String password) {
        TypedQuery<User> query = entityManager.createQuery("from User where username = '" + username + "' and password = '" + password + "'", User.class);
        return query.getSingleResult();
    }

    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("from User where username = " + username + "'", User.class);
        return query.getSingleResult();
    }
}
