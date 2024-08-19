package org.arta.vopros.db;

import org.arta.vopros.domain.User;

public interface UserDao {
    public void save(User user);
    public void delete(User user);
    public void update(User user);
    public User getUser(Long id);
}
