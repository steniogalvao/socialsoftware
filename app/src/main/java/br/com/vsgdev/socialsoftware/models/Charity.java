package br.com.vsgdev.socialsoftware.models;

import java.util.ArrayList;

/**
 * Created by root on 9/17/15.
 */
public class Charity {

    //TODO: alterar implementação para ficar igual a modelagem após testes com a lista

    private ArrayList<Institution> institutions;

    public Charity(ArrayList<Institution> institutions) {
        this.institutions = institutions;
    }

    public ArrayList<Institution> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(ArrayList<Institution> institutions) {
        this.institutions = institutions;
    }
}
