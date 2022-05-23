package com.example.projetofinal.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinal.CadCursoActivity;
import com.example.projetofinal.MainActivity;
import com.example.projetofinal.R;
import com.example.projetofinal.holders.CursoHolder;
import com.example.projetofinal.models.Curso;

import java.util.ArrayList;

public class CursoAdapter extends RecyclerView.Adapter<CursoHolder>
{
    private final ArrayList<Curso> cursos;
    public CursoAdapter(ArrayList<Curso> cursos){
        this.cursos = cursos;
    }

    @NonNull
    @Override
    public CursoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CursoHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_listagem_curso,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CursoHolder holder, int position) {
        holder.txtNome.setText(cursos.get(position).getNome());
        holder.txtModalidade.setText(cursos.get(position).getModalidade());
        //Editar Item - Click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), CadCursoActivity.class);
                i.putExtra("id", cursos.get(holder.getAdapterPosition()).getId());
                holder.itemView.getContext().startActivity(i);
            }
        });

        //Excluir Item - LongClick
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setMessage("Deseja Excluir esse item?");
                alert.setTitle("Atenção");
                alert.setNegativeButton("Não",null);
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MainActivity)holder.itemView.getContext())
                                .excluirCurso(cursos.get(
                                        holder.getAdapterPosition()).getId()+"");
                    }
                });
                alert.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursos != null ? cursos.size() : 0;
    }
}
