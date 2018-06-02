package xyz.elcomercio.software.dao.interfaces;

import xyz.elcomercio.software.entity.Profile;

public interface IProfile {

    public Profile findOneById(String id);
    public Profile findOneByUsername(String username);
    public long insert(String username, Profile object);
}
