package br.com.anotacoes.asynctask;

import android.os.AsyncTask;

import br.com.anotacoes.database.dao.SheetDAO;
import br.com.anotacoes.model.Sheet;
import br.com.anotacoes.ui.adapter.AnnotationAdapter;

public class RemoveSheetTask extends AsyncTask<Void,Void,Void> {


    private final SheetDAO dao;
    private final AnnotationAdapter adapter;
    private final Sheet sheet;


    public RemoveSheetTask(SheetDAO dao, AnnotationAdapter adapter, Sheet sheet) {
        this.dao = dao;
        this.adapter = adapter;
        this.sheet = sheet;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        dao.remove(sheet);
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        adapter.removeSheetList(sheet);
    }

}
