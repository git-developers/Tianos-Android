package xyz.tianos.software.dao.interfaces;

import xyz.tianos.software.entity.Role;

public interface IRole {

    public Role findOneById(String id);
    public Role findOneByUsername(String username);
    public long insert(String username, long idProfile, Role object);
}
