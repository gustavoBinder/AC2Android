package com.example.projetofinal.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.projetofinal.MainActivity;
import com.example.projetofinal.models.Curso;

public class ListaCursoAsync extends AsyncTask<String,String,String> {
    private String metodo;
    ProgressDialog progressDialog;
    Context context;

    public ListaCursoAsync(String metodo, Context context){
        this.metodo = metodo;
        this.progressDialog = new ProgressDialog(context);
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Carregando...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(metodo.equals("GET")){
           ((MainActivity)context).setListaCursos(Curso.parseArrayList(s));
            ((MainActivity)context).setupRecyclerCurso();
            progressDialog.dismiss();
        }
        else if(s.equals("OK")){
            Toast.makeText(context,"Operação realizada com sucesso",Toast.LENGTH_SHORT)
                                                                                .show();
            progressDialog.dismiss();
            ((MainActivity)context).buscarCursos();
        }

    }
    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        if(metodo.equals("GET"))
            data = ServiceApi.getService(strings[0],strings[1]);
        else if(metodo.equals("DELETE"))
            data = ServiceApi.deleteService(strings[0]);
        return data;
    }
}
