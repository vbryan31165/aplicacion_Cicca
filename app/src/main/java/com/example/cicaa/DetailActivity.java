package com.example.cicaa;
import static androidx.appcompat.widget.SearchView.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.cicaa.adapters.PermisosAdapter;

import com.example.cicaa.model.Permisos;

import com.example.cicaa.model.Usuario;
import com.example.cicaa.service.ApiClient;
import com.example.cicaa.service.Apipermisos;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
DetailActivity extends  AppCompatActivity implements PermisosAdapter.PRecyclerItemClick, OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    private PermisosAdapter adapter;
    private List<Permisos> items;
    private Apipermisos apipermisos;
    private Usuario itemDetail;
    private boolean permiso=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        initValues();
        if(permiso){
            initListener();
        }
    }

    private void initViews(){
        rvLista = findViewById(R.id.rvListaD);
        svSearch = findViewById(R.id.svSearchD);
    }

    private void initValues() {

        apipermisos = ApiClient.getPeticionPermisos();

        LinearLayoutManager manager = new LinearLayoutManager(DetailActivity.this);
        rvLista.setLayoutManager(manager);
        getpermisos();
    }
    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private void getpermisos() {

        itemDetail = (Usuario) getIntent().getExtras().getSerializable("itemDetail");
        int id_usuario = itemDetail.getID_USUARIO();


        apipermisos.getPermiso(id_usuario).enqueue(new Callback<List<Permisos>>() {
            @Override
            public void onResponse(Call<List<Permisos>> call, Response<List<Permisos>> response) {
                if(response.isSuccessful()) {
                    if(response.body().size() > 0){
                        permiso=true;
                        items = response.body();
                        //Toast.makeText(DetailActivity.this, "E: "+items.get(0).getNOMBRES(), Toast.LENGTH_LONG).show();
                        Log.e("...",items.get(0).getNOMBRES());
                        adapter = new PermisosAdapter(items, (PermisosAdapter.PRecyclerItemClick) DetailActivity.this);

                        rvLista.setAdapter(adapter);
                    }else{
                        Toast.makeText(DetailActivity.this, "No tienes permisos", Toast.LENGTH_LONG).show();
                        //finish();
                    }


                }
                else {
                    Toast.makeText(DetailActivity.this, "Error: "+response.code(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Permisos>> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void itemClick(Permisos item) {
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
