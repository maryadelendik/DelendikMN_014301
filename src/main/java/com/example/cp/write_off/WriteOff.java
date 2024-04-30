package com.example.cp.write_off;



import java.util.List;

public interface WriteOff {

    List<WriteOffDB> getAll();
    List<WriteOffDB> search(String string);

    void deleteById(Integer id);

    void save(WriteOffDB writeOffDB);

    void update(WriteOffDB writeOffDB);
    Float count(WriteOffDB writeOffDB);
}
