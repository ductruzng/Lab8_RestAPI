package trungndph39729.fpoly.dethithu.handle;


import trungndph39729.fpoly.dethithu.model.Student;

public interface Handle {
    void onDelete(String dd);
    void onUpdate(String Id, Student student);
}
