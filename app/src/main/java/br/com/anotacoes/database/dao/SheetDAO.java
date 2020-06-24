package br.com.anotacoes.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.anotacoes.model.Sheet;

@Dao
public interface SheetDAO {

    @Insert
    Long save(Sheet sheet);

    @Update
    void update(Sheet sheet);

    @Query("SELECT * FROM sheet WHERE id = :sheetId")
    List<Sheet> getId(int sheetId);

    @Query("DELETE FROM sheet")
    void removeAll();

    @Delete
    void remove(Sheet sheet);

    @Query("SELECT * FROM sheet ORDER BY ID DESC")
    List<Sheet> all();
}
