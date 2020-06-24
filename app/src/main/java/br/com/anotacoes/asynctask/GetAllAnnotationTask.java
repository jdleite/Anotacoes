package br.com.anotacoes.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.anotacoes.database.dao.SheetDAO;
import br.com.anotacoes.model.Sheet;
import br.com.anotacoes.ui.adapter.AnnotationAdapter;

public class GetAllAnnotationTask extends AsyncTask<Void,Void, List<Sheet>> {

    private final SheetDAO dao;
    private final AnnotationAdapter adapter;

    public GetAllAnnotationTask(SheetDAO dao, AnnotationAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Sheet> doInBackground(Void[] voids) {

        return dao.all();
    }

    @Override
    protected void onPostExecute(List<Sheet> sheetList) {
        super.onPostExecute(sheetList);
        adapter.load(sheetList);
    }
}
