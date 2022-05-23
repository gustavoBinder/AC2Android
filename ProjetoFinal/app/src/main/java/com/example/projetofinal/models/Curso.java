package com.example.projetofinal.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Curso {
    private Integer id;
    private String nome;
    private String modalidade;

    public Curso() {

    }

    public Curso(Integer id, String nome, String modalidade) {
        this.id = id;
        this.nome = nome;
        this.modalidade = modalidade;
    }
    public static Curso parseObject(String json){
        Curso curso = new Curso();
        try{
           JSONObject obj = new JSONObject(json);
          // usuario.setId(obj.getInt("id"));
           curso.setNome(obj.getString("nome"));
           curso.setModalidade(obj.getString("modalidade"));

           return curso;
        }
        catch (Exception ex){
            return curso;
        }
    }
    public static String parseJson(Curso curso){
        JSONObject obj = new JSONObject();
        try{
            obj.put("id",""+ curso.getId());
            obj.put("nome", curso.getNome());
            obj.put("modalidade", curso.getModalidade());
            return obj.toString();
        }
        catch (Exception ex){
            return "";
        }
    }

    public static ArrayList<Curso> parseArrayList(String json){
        ArrayList<Curso> cursos = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(json);
            for(int i = 0; i < array.length(); i++){
                Curso curso = new Curso();
                JSONObject obj = array.getJSONObject(i);
                curso.setModalidade(obj.getString("modalidade"));
                curso.setNome(obj.getString("nome"));
                curso.setId(obj.getInt("id"));
                cursos.add(curso);
            }
            return cursos;
        }
        catch (Exception ex){
            return null;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
}
