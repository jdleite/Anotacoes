package br.com.anotacoes.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.anotacoes.database.dao.SheetDAO;
import br.com.anotacoes.model.Sheet;

@Database(entities = {Sheet.class},version = 1,exportSchema = false)
public abstract class SheetDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "sheet1";
    public abstract SheetDAO getSheetDatabase();


    public static SheetDatabase getInstance(Context context){
        return Room.databaseBuilder(context, SheetDatabase.class,DATABASE_NAME)
                .addMigrations()
                .allowMainThreadQueries()
                .build();
    }




}
