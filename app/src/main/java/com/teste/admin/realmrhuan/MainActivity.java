package com.teste.admin.realmrhuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.teste.admin.realmrhuan.model.Pessoa;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    ListView lvPessoa;
    RealmResults<Pessoa> rPessoas;
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvPessoa = (ListView) findViewById(R.id.lvPessoa);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        mRealm = Realm.getDefaultInstance();

        lvPessoa.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

                getMenuInflater().inflate(R.menu.menu_alt, contextMenu);

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCad();
            }
        });
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo pListMenu = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {

            case R.id.action_alt:

                alt(rPessoas.get(pListMenu.position));

                atualizaView();
                break;

            case R.id.action_delete:

                Pessoa t = rPessoas.get(pListMenu.position);
                mRealm.beginTransaction();
                Pessoa pessoa = mRealm.where(Pessoa.class).equalTo("id", t.getId()).findFirst();
                pessoa.deleteFromRealm();
                mRealm.commitTransaction();
                mRealm.close();

                atualizaView();
                break;
        }

        return super.onContextItemSelected(item);
    }

    public void alt(Pessoa pessoa) {
        Intent i = new Intent(this, CadastroActivity.class);
        i.putExtra("id", pessoa.getId());
        i.putExtra("nome", pessoa.getNome());
        i.putExtra("sobrenome", pessoa.getSobrenome());
        startActivity(i);
    }

    public void intentCad() {
        Intent i = new Intent(this, CadastroActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaView();
    }

    public void atualizaView() {
        mRealm = Realm.getDefaultInstance();
        rPessoas = mRealm.where(Pessoa.class).findAll();

        ArrayAdapter<Pessoa> aaPessoa = new ArrayAdapter<Pessoa>(this,
                android.R.layout.simple_list_item_1, rPessoas);

        lvPessoa.setAdapter(aaPessoa);
    }
}
