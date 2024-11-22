package com.nttdata.app.service;

import com.nttdata.app.database.EntityManagerProvider;
import com.nttdata.app.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class UserService {

  public Optional<User> getById(Long id) {
    if (id == null) {
      return Optional.empty();
    }
    EntityManager entityManager = EntityManagerProvider.getEntityManager();
    try {
      entityManager.getTransaction().begin();
      User user = entityManager.find(User.class, id);
      entityManager.getTransaction().commit();
      return Optional.ofNullable(user);
    } catch (Exception e) {
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
      return Optional.empty();
    } finally {
      entityManager.close();
    }
  }

  public List<User> getAll() {
    EntityManager entityManager = EntityManagerProvider.getEntityManager();
    try {
      entityManager.getTransaction().begin();
      List<User> categories =
          entityManager.createQuery("SELECT c FROM User c", User.class).getResultList();
      entityManager.getTransaction().commit();
      return categories;
    } catch (Exception e) {
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
      return List.of();
    } finally {
      entityManager.close();
    }
  }

  public Optional<User> update(Long id, User obj) {
    if (obj == null || id == null) {
      return Optional.empty();
    }
    EntityManager entityManager = EntityManagerProvider.getEntityManager();
    try {
      entityManager.getTransaction().begin();
      User user = entityManager.find(User.class, id);
      if (user != null) {
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        return Optional.of(user);
      }
      entityManager.getTransaction().commit();
      return Optional.empty();
    } catch (Exception e) {
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
      return Optional.empty();
    } finally {
      entityManager.close();
    }
  }

  public User create(User obj) {
    if (obj == null) {
      return null;
    }
    EntityManager entityManager = EntityManagerProvider.getEntityManager();
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(obj);
      entityManager.getTransaction().commit();
      return obj;
    } catch (PersistenceException e) {
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
      return null;
    } finally {
      entityManager.close();
    }
  }

  public int deleteByid(Long id) {
    if (id == null) {
      return 0;
    }
    EntityManager entityManager = EntityManagerProvider.getEntityManager();
    try {
      entityManager.getTransaction().begin();
      User user = entityManager.find(User.class, id);
      if (user != null) {
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        return 1;
      }
      entityManager.getTransaction().commit();
      return 0;
    } catch (Exception e) {
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
      return 0;
    } finally {
      entityManager.close();
    }
  }
}
