package matheusgomes.youtubeapp.youtube.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import matheusgomes.youtubeapp.youtube.R;
import matheusgomes.youtubeapp.youtube.databinding.ActivityMainBinding;
import matheusgomes.youtubeapp.youtube.databinding.ActivityPlayerBinding;
import matheusgomes.youtubeapp.youtube.helper.YoutubeConfig;

public class PlayerActivity extends YouTubeBaseActivity
            implements YouTubePlayer.OnInitializedListener {

    private ActivityPlayerBinding binding;

    private String idVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlayerBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );

        Bundle bundle = getIntent().getExtras();

        if ( bundle != null ){
            idVideo = bundle.getString( "idVideo" );
            binding.playerVideo.initialize( YoutubeConfig.CHAVE_YOUTUBE_API, this );

        }

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setFullscreen( true );
        youTubePlayer.setShowFullscreenButton( false );
        youTubePlayer.loadVideo( idVideo );

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}