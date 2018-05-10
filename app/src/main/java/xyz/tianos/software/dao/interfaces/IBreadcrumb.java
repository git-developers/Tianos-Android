package xyz.tianos.software.dao.interfaces;

import xyz.tianos.software.entity.Breadcrumb;
import xyz.tianos.software.entity.User;

public interface IBreadcrumb {

    public long insert(Breadcrumb object);
    public Breadcrumb findLast();
}
