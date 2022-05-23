package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projetofinal.adapters.CursoAdapter;
import com.example.projetofinal.models.Curso;
import com.example.projetofinal.services.ListaCursoAsync;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Curso> listaCursos;
    RecyclerView cursoRecycler;
    CursoAdapter cursoAdapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCursos = new ArrayList<>();
        cursoRecycler = (RecyclerView) findViewById(R.id.cursoRecycler);
        progressDialog = new ProgressDialog(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        buscarCursos();
    }

    public void buscarCursos() {
        new ListaCursoAsync("GET",MainActivity.this).execute("api/cadastroCurso","");
    }
    public void excluirCurso(String id){
        new ListaCursoAsync("DELETE",MainActivity.this)
                                                   .execute("api/cadastroCurso/"+id,"");
    }

    public void addCursoClick(View v){
        Intent i = new Intent(MainActivity.this, CadCursoActivity.class);
        startActivity(i);
    }

    public void setListaCursos(ArrayList<Curso> cursos){
        this.listaCursos = cursos;
    }
    public void setupRecyclerCurso(){
        LinearLayoutManager layout = new LinearLayoutManager(this);
        cursoRecycler.setLayoutManager(layout);
        cursoAdapter = new CursoAdapter(listaCursos);
        cursoRecycler.setAdapter(cursoAdapter);

        cursoRecycler.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }
}