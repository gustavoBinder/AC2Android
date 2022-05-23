package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projetofinal.models.Curso;
import com.example.projetofinal.services.CadCursoAsync;

public class CadCursoActivity extends AppCompatActivity {
    int id = 0;
    Curso curso;
    TextView txtModalidade, txtNome;

    public Curso getCurso(){
        return curso;
    }
    public void setCurso(Curso curso){
        this.curso = curso;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_curso);
        txtModalidade = findViewById(R.id.txtModalidade);
        txtNome = findViewById(R.id.txtNome);
        if(getIntent().hasExtra("id")){
            id = getIntent().getIntExtra("id",0);
            new CadCursoAsync("GET", CadCursoActivity.this).execute("api/cadastroCurso/" +id,"");
        }
        else
            curso = new Curso();
    }
    public void carregarCampos(){
        txtNome.setText(curso.getNome());
        txtModalidade.setText(curso.getModalidade());
    }

    public void btnSalvarClick(View v){
        curso.setId(id);
        curso.setNome(txtNome.getText().toString());
        curso.setModalidade(txtModalidade.getText().toString());

        if(id > 0){
            new CadCursoAsync("PUT", CadCursoActivity.this)
                    .execute("api/cadastroCurso/" + id, Curso.parseJson(curso));
        }
        else{
           new CadCursoAsync("POST", CadCursoActivity.this)
                    .execute("api/cadastroCurso", Curso.parseJson(curso));
        }
    }
}