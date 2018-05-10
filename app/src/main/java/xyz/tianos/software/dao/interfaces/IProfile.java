package xyz.tianos.software.dao.interfaces;

import xyz.tianos.software.entity.Profile;

public interface IProfile {

    public Profile findOneById(String id);
    public Profile findOneByUsername(String username);
    public long insert(String username, Profile object);
}
