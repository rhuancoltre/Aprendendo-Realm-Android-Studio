package com.teste.admin.realmrhuan.ui;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.teste.admin.realmrhuan.R;
import com.teste.admin.realmrhuan.model.Pessoa;

import java.util.List;

/**
 * Created by Admin on 13/06/2017.
 */

public class PessoaArrayAdapter extends ArrayAdapter<Pessoa> {

    private List<Pessoa> pessoas;
    Context context;

    public PessoaArrayAdapter(Context context, List<Pessoa> pessoas) {
        super(context, R.layout.row_pessoa, pessoas);
        this.pessoas = pessoas;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.row_pessoa, null);

        EditText edtId = (EditText) view.findViewById(R.id.edtIdList);
        EditText edtNome = (EditText) view.findViewById(R.id.edtNomeList);
        EditText edtDtCad = (EditText) view.findViewById(R.id.edtSobrenomeList);

        final Pessoa pessoa = pessoas.get(position);

        edtId.setText(pessoa.getId()+"");
        edtNome.setText(pessoa.getNome());
        edtDtCad.setText(pessoa.getSobrenome());


        return view;
    }

}
