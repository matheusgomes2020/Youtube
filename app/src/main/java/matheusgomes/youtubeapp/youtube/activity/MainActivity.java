package matheusgomes.youtubeapp.youtube.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import matheusgomes.youtubeapp.youtube.R;
import matheusgomes.youtubeapp.youtube.adapter.AdapterVideo;
import matheusgomes.youtubeapp.youtube.api.YoutubeService;
import matheusgomes.youtubeapp.youtube.databinding.ActivityMainBinding;
import matheusgomes.youtubeapp.youtube.helper.RetrofitConfig;
import matheusgomes.youtubeapp.youtube.helper.YoutubeConfig;
import matheusgomes.youtubeapp.youtube.listener.RecyclerItemClickListener;
import matheusgomes.youtubeapp.youtube.model.Item;
import matheusgomes.youtubeapp.youtube.model.Resultado;
import matheusgomes.youtubeapp.youtube.model.Video;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

private ActivityMainBinding binding;

private List<Item> videos = new ArrayList<>();

private Resultado resultado;

    private AdapterVideo adapterVideo;

//Retrofit
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );

        retrofit = RetrofitConfig.getRetrofit();

        //Configura toolbar
        Toolbar toolbar = findViewById( R.id.toolbar );
        toolbar.setTitle( "Youtube" );
        setSupportActionBar( toolbar );

        //Configura RecyclerView
        recuperarVideos( "" );

        binding.tool.searchView.setQueryHint( "Vídeos" );
        binding.tool.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recuperarVideos( query );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        binding.tool.searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                recuperarVideos( "" );

                return false;
            }
        });


    }

    private void recuperarVideos( String pesquisa ){

        String q = pesquisa.replaceAll( " ", "+" );

        YoutubeService youtubeService = retrofit.create( YoutubeService.class );

        youtubeService.recuperarVideos(
                "snippet", "date", "20",
                YoutubeConfig.CHAVE_YOUTUBE_API, YoutubeConfig.CANAL_ID, q
        ).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                Log.d("resultado", "resultado: " + response.toString() );

                if ( response.isSuccessful() ){

                    resultado = response.body();
                    videos = resultado.items;

                    configurarRecyclerView();

                }

            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                Log.d("resultado", "resultado: " + t.toString() );
            }
        });

    }

    public void  configurarRecyclerView(){

        adapterVideo = new AdapterVideo( videos, this );
        binding.recyclerVideos.setHasFixedSize( true );
        binding.recyclerVideos.setLayoutManager( new LinearLayoutManager( this ));
        binding.recyclerVideos.setAdapter( adapterVideo );

        //Configura evento de clique
        binding.recyclerVideos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        binding.recyclerVideos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Item video = videos.get( position );
                                String idVideo = video.id.videoId;

                                Intent i = new Intent( MainActivity.this, PlayerActivity.class );
                                i.putExtra( "idVideo", idVideo );
                                startActivity( i );


                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }


                ));

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_main, menu );

        MenuItem item = menu.findItem( R.id.menu_search );
        binding.search1

        return super.onCreateOptionsMenu(menu);
    }

     */
}