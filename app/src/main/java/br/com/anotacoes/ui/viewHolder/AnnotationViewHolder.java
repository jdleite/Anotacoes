package br.com.anotacoes.ui.viewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.anotacoes.R;
import br.com.anotacoes.model.Sheet;
import br.com.anotacoes.ui.listener.AnnotationListener;

public class AnnotationViewHolder extends RecyclerView.ViewHolder {

    private TextView txtDay,txtAnnotation;
    private ImageView imgClose,imgEdit;
    private final Context context;
    public AnnotationViewHolder(@NonNull View itemView,Context context) {
        super(itemView);

         txtDay = itemView.findViewById(R.id.id_txt_row_day);
         txtAnnotation = itemView.findViewById(R.id.id_txt_row_annotation);
         imgClose = itemView.findViewById(R.id.id_img_close);
         imgEdit = itemView.findViewById(R.id.id_img_edit);
         this.context = context;
    }

    public void bindData(final Sheet sheet, final AnnotationListener listener){
        txtDay.setText(sheet.getDay());
        txtAnnotation.setText(sheet.getAnnotation());


        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog
                        .Builder(context)
                        .setTitle(R.string.title_remove)
                        .setMessage(R.string.question_remove)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listener.onDeletelick(sheet);
                            }
                        })
                        .setNeutralButton(context.getString(R.string.dont),null)
                        .show();

            }
        });


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEditClick(sheet);
            }
        });


    }




}
