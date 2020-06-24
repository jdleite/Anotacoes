package br.com.anotacoes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Sheet implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String day;
    private String annotation;

    public Sheet(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean validateId(){
        return id > 0;
    }
}
