package matheusgomes.youtubeapp.youtube.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import matheusgomes.youtubeapp.youtube.R;
import matheusgomes.youtubeapp.youtube.model.Item;
import matheusgomes.youtubeapp.youtube.model.Video;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.MyViewHolder> {

    private List<Item> videos = new ArrayList<>();
    private Context context;

    public AdapterVideo(List<Item> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate(R.layout.adapter_video, parent, false );
        return new MyViewHolder( view );

    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        Item video = videos.get( position );

        holder.titulo.setText( video.snippet.title );

        Uri urlCapa = Uri.parse( video.snippet.thumbnails.high.url );
        Glide.with( context ).load( urlCapa ).into( holder.capa );

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView descricao;
        TextView data;
        ImageView capa;

        public MyViewHolder( View itemView ) {
            super( itemView );

            titulo = itemView.findViewById( R.id.textTitulo );
            capa = itemView.findViewById( R.id.imageCapa );

        }

    }
}
