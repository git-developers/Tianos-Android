package xyz.tianos.software.dao.interfaces;

import xyz.tianos.software.entity.Courses;
import xyz.tianos.software.entity.Semester;

public interface ISemester {

    public long insertSemester(String username, Semester semester);
    public Semester findLastSemesterSelected();
}
