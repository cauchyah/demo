package com.lulu.lin.mac;

import android.media.session.MediaController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pili.pldroid.player.widget.PLVideoTextureView;

public class PlayerTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_test);
        PLVideoTextureView mVideoView = (PLVideoTextureView) findViewById(R.id.PLVideoTextureView);


    }
}
