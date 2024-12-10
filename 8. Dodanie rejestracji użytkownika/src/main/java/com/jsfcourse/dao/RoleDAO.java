package com.jsfcourse.dao;

import com.jsfcourse.entities.Role;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Stateless
public class RoleDAO {

    @PersistenceContext
    private EntityManager em;
    
    public Role findByName(String name) {
    try {
        return em.createNamedQuery("Role.findByName", Role.class)
                 .setParameter("name", name)
                 .getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}
}
