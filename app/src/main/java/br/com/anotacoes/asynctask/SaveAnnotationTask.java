package br.com.anotacoes.asynctask;

import android.os.AsyncTask;

import br.com.anotacoes.database.dao.SheetDAO;
import br.com.anotacoes.model.Sheet;

public class SaveAnnotationTask extends AsyncTask<Void,Void,Void> {

    private final SheetDAO dao;
    private final Sheet sheet;

    public SaveAnnotationTask(SheetDAO dao, Sheet sheet) {
        this.dao = dao;
        this.sheet = sheet;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        dao.save(sheet);
        return null;
    }
}
