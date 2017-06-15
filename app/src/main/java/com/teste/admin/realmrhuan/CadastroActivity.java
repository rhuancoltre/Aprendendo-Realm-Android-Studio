package com.teste.admin.realmrhuan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.teste.admin.realmrhuan.model.Pessoa;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CadastroActivity extends AppCompatActivity {

    EditText edtId;
    EditText edtNome;
    EditText edtSobrenome;
    Realm mRealm;
    Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        edtId = (EditText) findViewById(R.id.edtId);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSobrenome = (EditText) findViewById(R.id.edtSobrenome);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        mRealm = Realm.getDefaultInstance();

        try {
            int id = getIntent().getIntExtra("id", -1);
            if (id > 0)
                edtId.setText(id+"");
            edtNome.setText(getIntent().getStringExtra("nome"));
            edtSobrenome.setText(getIntent().getStringExtra("fundacao"));

        } catch (Exception e) {
            Log.v("Pessoa", "Não veio pessoa para alteração");
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_salvar) {

            mRealm.beginTransaction();
            pessoa = retornaPessoa();
            mRealm.copyToRealmOrUpdate(pessoa);
            mRealm.commitTransaction();
            mRealm.close();
            Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();

            onBackPressed();
            return true;
        }

        if (id == R.id.action_sair) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int autoIncrementId() {
        int key = 1;
        mRealm = Realm.getDefaultInstance();
        try {
            key = mRealm.where(Pessoa.class).max("id").intValue() + 1;
        } catch (NullPointerException e) {
            key = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public Pessoa retornaPessoa() {
        Pessoa t = new Pessoa();
        if (edtId.getText().toString().equals("")) {
            t.setId(autoIncrementId());
        } else {
            t.setId(Integer.parseInt(edtId.getText().toString()));
        }

        t.setNome(edtNome.getText().toString());
        t.setSobrenome(edtSobrenome.getText().toString());
        return t;
    }
}