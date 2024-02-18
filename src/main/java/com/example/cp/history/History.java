package com.example.cp.history;



import java.util.List;

public interface History {

    List<HistoryDB> getAll(Integer id);

    void deleteById(Integer id);

    void save(HistoryDB historyDB);

    void update(HistoryDB historyDB);
}
