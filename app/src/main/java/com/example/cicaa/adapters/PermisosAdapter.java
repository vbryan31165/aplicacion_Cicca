package com.example.cicaa.adapters;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cicaa.R;
import com.example.cicaa.model.Permisos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PermisosAdapter extends RecyclerView.Adapter<PermisosAdapter.PermisosHolder> {

    private List<Permisos> items;
    private List<Permisos> originalItems;
    private PRecyclerItemClick itemClick;

    public PermisosAdapter(List<Permisos> items, PRecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public PermisosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.permisos, parent, false);
        return new PermisosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PermisosHolder holder, int position) {
        final Permisos item = items.get(position);
        String p_a = "";
        if(item.getID_PERMISO().equals("1")){
            p_a = "Acceso permitido";
            //holder.cardview.setCardBackgroundColor(Integer.parseInt("#B2F3B0"));

            holder.cardView.setCardBackgroundColor(Color.parseColor("#B2F3B0"));
        }else {
            p_a = "No tiene acceso";
            holder.cardView.setCardBackgroundColor(Color.parseColor("#F38888"));
            //holder.cardview.setCardBackgroundColor(Integer.parseInt("#F38888"));
        }
        holder.namefull.setText(String.format("%s %s", item.getNOMBRES(), item.getAPELLIDOS()));
        holder.permiso_u.setText(p_a);
        holder.id_s.setText(String.valueOf(item.getID_SALON()));
        holder.id_p.setText(String.valueOf(item.getID_PERMISO()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });

   /*     holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });   */
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
                List<Permisos> collect = originalItems.stream()
                        .filter(i -> i.getID_SALON().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                items.clear();
                for (Permisos i : originalItems) {
                    if (i.getNOMBRES().toLowerCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class PermisosHolder extends RecyclerView.ViewHolder {
        private TextView namefull, id_s, id_p, permiso_u ;
        private CardView cardView;
        public PermisosHolder(@NonNull View itemView) {
            super(itemView);
            //Rojo: #F38888
            //Verde: #B2F3B0

            namefull = itemView.findViewById(R.id.name_full);
            id_s = itemView.findViewById(R.id.id_salon);
            id_p = itemView.findViewById(R.id.id_p);
            permiso_u = itemView.findViewById(R.id.permiso);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }

    public interface PRecyclerItemClick {
        void itemClick(Permisos item);
    }
}
