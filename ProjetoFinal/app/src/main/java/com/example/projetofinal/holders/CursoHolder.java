package com.example.projetofinal.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinal.R;

public class CursoHolder extends RecyclerView.ViewHolder {
    public TextView txtModalidade;
    public TextView txtNome;

    public CursoHolder(@NonNull View itemView) {
        super(itemView);
        txtModalidade = (TextView)itemView.findViewById(R.id.textModalidade);
        txtNome = (TextView) itemView.findViewById(R.id.textNome);
    }
}
