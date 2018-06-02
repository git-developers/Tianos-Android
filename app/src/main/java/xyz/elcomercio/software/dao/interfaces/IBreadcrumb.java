package xyz.elcomercio.software.dao.interfaces;

import xyz.elcomercio.software.entity.Breadcrumb;
import xyz.elcomercio.software.entity.User;

public interface IBreadcrumb {

    public long insert(Breadcrumb object);
    public Breadcrumb findLast();
}
