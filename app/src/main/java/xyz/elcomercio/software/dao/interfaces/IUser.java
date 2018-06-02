package xyz.elcomercio.software.dao.interfaces;

import xyz.elcomercio.software.entity.User;

public interface IUser {
    public User findOneById(String id);
    public User findLastLogged();
    public User findOneByUsername(String username);
    public long insert(User object);
}
