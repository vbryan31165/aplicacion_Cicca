package com.example.cicaa.adapters;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cicaa.DetailActivity;
import com.example.cicaa.R;
import com.example.cicaa.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private List<Usuario> items;
    private List<Usuario> originalItems;
    private RecyclerItemClick itemClick;


    public RecyclerAdapter(List<Usuario> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuarios, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final Usuario item = items.get(position);
        String rol_u = "";
        if(item.getID_ROL() == 2){rol_u = "Docente";}
        holder.name.setText(String.format("%s %s", item.getNOMBRES(), item.getAPELLIDOS()));
        holder.rol.setText(rol_u);
        holder.id_x.setText(String.valueOf(item.getID_USUARIO()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            items.clear();
            items.addAll(originalItems);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                items.clear();
                List<Usuario> collect = originalItems.stream()
                        .filter(i -> String.format("%s %s", i.getNOMBRES(), i.getAPELLIDOS()).toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                items.clear();
                for (Usuario i : originalItems) {
                    if (i.getNOMBRES().toLowerCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private TextView name, id_x, rol;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            name = itemView.findViewById(R.id.name_u);
            rol = itemView.findViewById(R.id.rol_u);
            id_x = itemView.findViewById(R.id.id_u);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Usuario item);
    }
}