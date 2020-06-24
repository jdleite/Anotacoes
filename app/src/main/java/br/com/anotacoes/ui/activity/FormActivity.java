package br.com.anotacoes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.com.anotacoes.R;
import br.com.anotacoes.asynctask.EditSheetTask;
import br.com.anotacoes.asynctask.SaveAnnotationTask;
import br.com.anotacoes.database.SheetDatabase;
import br.com.anotacoes.database.dao.SheetDAO;
import br.com.anotacoes.model.Sheet;
import br.com.anotacoes.ui.validator.StandardValidator;
import br.com.anotacoes.ui.validator.ValidatorSheet;
import br.com.anotacoes.utils.Utils;

import static br.com.anotacoes.ui.activity.Constants.SHEET_KEY;

public class FormActivity extends AppCompatActivity {

    private StartFields startFields = new StartFields();
    private SheetDAO sheetDAO;
    private Utils utils = new Utils();
    private final List<StandardValidator> validatorList = new ArrayList<>();
    private Sheet sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        SheetDatabase sheetDatabase = SheetDatabase.getInstance(this);
        sheetDAO = sheetDatabase.getSheetDatabase();
        getFields();
    }

    private void getFields() {
        dayField();
        annotationField();
        loadSheet();
        btnSave();
    }

    private void annotationField() {

        startFields.edtAnnotation = findViewById(R.id.id_edt_annotation);
        AddStandardValidation(startFields.edtAnnotation);
    }

    private void dayField(){

        startFields.edtDay = findViewById(R.id.id_edt_day);
        AddStandardValidation(startFields.edtDay);

    }

    private void AddStandardValidation(final TextInputLayout textInputLayout) {
        final EditText editText = textInputLayout.getEditText();
        final StandardValidator validator = new StandardValidator(textInputLayout);
        validatorList.add(validator);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    validator.isValid();
                }
            }
        });


    }

    private void setDay() {
        utils.setDayOfWeek(startFields.edtDay);
    }

    private void btnSave() {
        startFields.btnSave = findViewById(R.id.id_btn_save);
        startFields.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isValidForm = validAllFields();

                if (isValidForm) {
                    finishForm(sheet);
                    finish();
                }

            }
        });
    }

    private boolean validAllFields() {
        boolean isValidForm = true;
        for (ValidatorSheet validatorSheet :
                validatorList) {
            if (!validatorSheet.isValid()) {
                isValidForm = false;
            }
        }
        return isValidForm;
    }

    private void saveAnnotation(Sheet sheet) {
        setSheet();
        new SaveAnnotationTask(sheetDAO, sheet).execute();

    }

    private void setSheet() {
        String day = startFields.edtDay.getEditText().getText().toString().toUpperCase();
        String annotation = startFields.edtAnnotation.getEditText().getText().toString().toLowerCase();
        sheet.setDay(day);
        sheet.setAnnotation(annotation);

    }

    private class StartFields {
        TextInputLayout edtDay, edtAnnotation;
        Button btnSave;
    }

    private void loadSheet() {
        Intent intent = getIntent();
        if (intent.hasExtra(SHEET_KEY)) {
            sheet = (Sheet) intent.getSerializableExtra(SHEET_KEY);
            fillFields();
        } else {
            sheet = new Sheet();
            setDay();
        }
    }

    private void fillFields() {

        startFields.edtDay.getEditText().setText(sheet.getDay());
        startFields.edtAnnotation.getEditText().setText(sheet.getAnnotation());
    }

    private void finishForm(Sheet sheet) {
        setSheet();
        if (sheet.validateId()) {
            editSheet(sheet);
        } else {
            saveAnnotation(sheet);
        }

    }

    private void editSheet(Sheet sheet) {
        new EditSheetTask(sheetDAO, sheet).execute();
    }


}
