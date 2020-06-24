package br.com.anotacoes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.anotacoes.R;
import br.com.anotacoes.model.Sheet;
import br.com.anotacoes.ui.listener.AnnotationListener;
import br.com.anotacoes.ui.viewHolder.AnnotationViewHolder;

public class AnnotationAdapter extends RecyclerView.Adapter<AnnotationViewHolder> {

    private final List<Sheet> sheetList;
    private final AnnotationListener listener;

    public AnnotationAdapter(List<Sheet> sheetList, AnnotationListener listener) {
        this.sheetList = sheetList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public AnnotationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_row_list, parent, false);

        return new AnnotationViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnotationViewHolder holder, int position) {
        Sheet sheet = sheetList.get(position);
        holder.bindData(sheet, listener);

    }

    @Override
    public int getItemCount() {
        return sheetList.size();
    }

    public void load(List<Sheet> sheetList){
        this.sheetList.clear();
        this.sheetList.addAll(sheetList);
        notifyDataSetChanged();
    }

    public void removeSheetList(Sheet sheet){
        sheetList.remove(sheet);
        notifyDataSetChanged();
    }


}
