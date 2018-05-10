package xyz.tianos.software.dao.interfaces;

import xyz.tianos.software.entity.Breadcrumb;
import xyz.tianos.software.entity.User;

public interface IBreadcrumb {

    public long save_1_User(User object);
    public Breadcrumb findLast();
}
