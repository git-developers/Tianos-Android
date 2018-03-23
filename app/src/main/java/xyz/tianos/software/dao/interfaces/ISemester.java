package xyz.tianos.software.dao.interfaces;

public interface ISemester {

    public long insertSemester(String username, Semester semester);
    public Semester findLastSemesterSelected();
}
