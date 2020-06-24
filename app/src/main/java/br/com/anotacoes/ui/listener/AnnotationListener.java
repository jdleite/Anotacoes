package br.com.anotacoes.ui.listener;

import br.com.anotacoes.model.Sheet;

public interface AnnotationListener {

    void onEditClick(Sheet sheet);

    void onDeletelick(Sheet sheet);
}
