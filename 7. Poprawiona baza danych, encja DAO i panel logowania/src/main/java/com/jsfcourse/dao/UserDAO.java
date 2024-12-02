package com.jsfcourse.dao;

import com.jsfcourse.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Map;

public class UserDAO {
    @PersistenceContext
    protected EntityManager em;

    public void create(User user) {
            em.persist(user);
    }

    public User merge(User user) {
            return em.merge(user);
    }

    public void remove(User user) {
            em.remove(em.merge(user));
    }

    public User find(Object id) {
            return em.find(User.class, id);
    }

    public List<User> getFullList() {
            List<User> list = null;
            Query query = em.createQuery("select p from User p");
            try {
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
            }

           return list;
    }

    public List<User> getList(Map<String, Object> searchParams) {
            List<User> list = null;

            String select = "select p ";
            String from = "from User p ";
            String where = "";
            String orderby = "order by p.surname asc, p.name";

            String surname = (String) searchParams.get("surname");
            if (surname != null) {
                    if (where.isEmpty()) {
                            where = "where ";
                    } else {
                            where += "and ";
                    }
                    where += "p.surname like :surname ";
            }

            Query query = em.createQuery(select + from + where + orderby);

            if (surname != null) {
                    query.setParameter("surname", surname+"%");
            }

            try {
                    list = query.getResultList();
            } catch (Exception e) {
                    e.printStackTrace();
            }

            return list;
    }
}
