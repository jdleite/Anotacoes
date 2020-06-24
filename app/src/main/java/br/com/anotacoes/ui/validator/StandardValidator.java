package br.com.anotacoes.ui.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class StandardValidator implements ValidatorSheet {
    private static final String CAMPO_OBRIGATORIO = "Campo Obrigatório";
    private final TextInputLayout textInputLayout;
    private final EditText editText;

    public StandardValidator(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        this.editText = this.textInputLayout.getEditText();
    }

    private boolean validateFields() {
        String textDay = editText.getText().toString();
        if (textDay.isEmpty()) {
            textInputLayout.setError(CAMPO_OBRIGATORIO);
            return false;
        }
        return true;
    }


    @Override
    public boolean isValid() {
        if (!validateFields()) return false;
        removeError();
        return true;
    }

    public void removeError() {
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
    }
}
