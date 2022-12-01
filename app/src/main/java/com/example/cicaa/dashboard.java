package com.example.cicaa;

import static androidx.appcompat.widget.SearchView.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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


public class dashboard extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, OnQueryTextListener {

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

    private void getusuarios() {
        apiusuarios.getUsuarios().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()) {
                    items = response.body();

                    //Toast.makeText(dashboard.this, "E: "+items.get(0).getNOMBRES(), Toast.LENGTH_LONG).show();
                    Log.e("...",items.get(0).getNOMBRES());
                    adapter = new RecyclerAdapter(items, (RecyclerAdapter.RecyclerItemClick) dashboard.this);

                    rvLista.setAdapter(adapter);
                }
                else {
                    Toast.makeText(dashboard.this, "Error: "+response.code(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(dashboard.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void itemClick(Usuario item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemDetail", item);
        startActivity(intent);
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
