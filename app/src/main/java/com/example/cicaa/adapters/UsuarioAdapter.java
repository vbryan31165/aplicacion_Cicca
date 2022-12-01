package com.example.cicaa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cicaa.R;
import com.example.cicaa.model.Usuario;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {


    private List<Usuario> listUsuarios;
    private Context context;


    public UsuarioAdapter(List<Usuario> listUsuarios, Context context) {
        this.listUsuarios = listUsuarios;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.usuarios, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Usuario usuario = listUsuarios.get(position);
        if (usuario.getID_ROL() == 2){
            holder.rol_u.setText("Docente");
        }

        holder.name_u.setText(String.format("%s%s", usuario.getNOMBRES(), usuario.getAPELLIDOS()));
        holder.id_u.setText(usuario.getID_USUARIO());



    }

    @Override
    public int getItemCount() {
        return listUsuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_u, rol_u, id_u;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_u = itemView.findViewById(R.id.name_u);
            rol_u = itemView.findViewById(R.id.rol_u);
            id_u = itemView.findViewById(R.id.id_u);

        }
    }
}
