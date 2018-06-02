package xyz.elcomercio.software.dao.interfaces;

import xyz.elcomercio.software.entity.Role;

public interface IRole {

    public Role findOneById(String id);
    public Role findOneByUsername(String username);
    public long insert(String username, long idProfile, Role object);
}
