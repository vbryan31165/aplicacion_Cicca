package com.example.cicaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import com.example.cicaa.adapters.RecyclerAdapter;
import com.example.cicaa.model.Usuario;
import com.example.cicaa.service.ApiClient;
import com.example.cicaa.service.Apiusuarios;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class dashboard extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<Usuario> items;
    private Apiusuarios apiusuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initViews();
        initValues();
        initListener();
    }

    private void initViews(){
        rvLista = findViewById(R.id.rvLista);
        svSearch = findViewById(R.id.svSearch);
    }

    private void initValues() {

        apiusuarios = ApiClient.getPeticionUsuarios();

        LinearLayoutManager manager = new LinearLayoutManager(dashboard.this);
        rvLista.setLayoutManager(manager);
        getusuarios();
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    /*private List<Usuario> getItems() {
        List<Usuario> itemLists = new ArrayList<>();
        itemLists.add(new ItemList("Saga de Broly", "Ultima pelicula de DB, peleas epicas.", R.drawable.saga_broly));
        itemLists.add(new ItemList("Super sayayines 4", "La ultima transformacion de la saga no canon.", R.drawable.ssj4s));
        itemLists.add(new ItemList("Super Sayayiness Blues", "Goku y Vegeta, la transformacion de dioses.", R.drawable.ssj_blues));
        itemLists.add(new ItemList("Goku ultrainstinto", "Infaltablñe power-up a Goku.", R.drawable.ultrainsitinto));
        itemLists.add(new ItemList("Super Vegeta Blue x2", "Diferentes transformaciones de super Vegeta.", R.drawable.super_vegeta));
        itemLists.add(new ItemList("Vegeta sapbe", "Vegeta sapbe o no sapbe xD.", R.drawable.vegeta_blue));
        itemLists.add(new ItemList("Saga de Broly", "Ultima pelicula de DB, peleas epicas.", R.drawable.saga_broly));
        itemLists.add(new ItemList("Super sayayines 4", "La ultima transformacion de la saga no canon.", R.drawable.ssj4s));
        itemLists.add(new ItemList("Super Sayayiness Blues", "Goku y Vegeta, la transformacion de dioses.", R.drawable.ssj_blues));
        itemLists.add(new ItemList("Goku ultrainstinto", "Infaltablñe power-up a Goku.", R.drawable.ultrainsitinto));
        itemLists.add(new ItemList("Super Vegeta Blue x2", "Diferentes transformaciones de super Vegeta.", R.drawable.super_vegeta));
        itemLists.add(new ItemList("Vegeta sapbe", "Vegeta sapbe o no sapbe xD.", R.drawable.vegeta_blue));

        return itemLists;
    }*/
    private void getusuarios() {
        apiusuarios.getUsuarios().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()) {
                    items = response.body();

                    Toast.makeText(dashboard.this, "E: "+items.get(0).getNOMBRES(), Toast.LENGTH_LONG).show();
                    Log.e("...",items.get(0).getNOMBRES());
                    adapter = new RecyclerAdapter(items, dashboard.this);

                    rvLista.setAdapter(adapter);
                }
                else {
                    Toast.makeText(dashboard.this, "Error: "+response.code(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(dashboard.this, "Errror: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void itemClick(Usuario item) {
        //Intent intent = new Intent(this, DetailActivity.class);
       // intent.putExtra("itemDetail", item);
        //startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}
 /*   private RecyclerView recyclerView;
    private List<Usuario> Usuarios;
    private UsuarioAdapter usuarioAdapter;
    private Apiusuarios apiusuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        recyclerView = findViewById(R.id.lst);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        showUsuarios();


    }

    public void showUsuarios(){

        apiusuarios = ApiClient.getPeticionUsuarios();

        apiusuarios.getUsuarios().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                Usuarios = response.body();
                usuarioAdapter = new UsuarioAdapter(Usuarios, dashboard.this);

                recyclerView.setAdapter(usuarioAdapter);


            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

                Toast.makeText(dashboard.this, "Errror: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}*/