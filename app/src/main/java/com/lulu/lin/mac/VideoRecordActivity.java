package com.lulu.lin.mac;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qiniu.pili.droid.shortvideo.PLAudioEncodeSetting;
import com.qiniu.pili.droid.shortvideo.PLCameraSetting;
import com.qiniu.pili.droid.shortvideo.PLMicrophoneSetting;
import com.qiniu.pili.droid.shortvideo.PLRecordSetting;
import com.qiniu.pili.droid.shortvideo.PLRecordStateListener;
import com.qiniu.pili.droid.shortvideo.PLShortVideoRecorder;
import com.qiniu.pili.droid.shortvideo.PLVideoEncodeSetting;

import java.io.File;

public class VideoRecordActivity extends AppCompatActivity implements PLRecordStateListener {
    PLShortVideoRecorder mShortVideoRecorder;
    GLSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record);
        surfaceView = (GLSurfaceView) findViewById(R.id.surfaceView);
        mShortVideoRecorder = new PLShortVideoRecorder();
        mShortVideoRecorder.setRecordStateListener(this);
        // 摄像头采集选项
        PLCameraSetting cameraSetting = new PLCameraSetting();
        cameraSetting.setCameraId(PLCameraSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT);
        cameraSetting.setCameraPreviewSizeRatio(PLCameraSetting.CAMERA_PREVIEW_SIZE_RATIO.RATIO_4_3);
        cameraSetting.setCameraPreviewSizeLevel(PLCameraSetting.CAMERA_PREVIEW_SIZE_LEVEL.PREVIEW_SIZE_LEVEL_480P);
        // 麦克风采集选项
        PLMicrophoneSetting microphoneSetting = new PLMicrophoneSetting();
// 视频编码选项
        PLVideoEncodeSetting videoEncodeSetting = new PLVideoEncodeSetting(this);
        videoEncodeSetting.setEncodingSizeLevel(PLVideoEncodeSetting.VIDEO_ENCODING_SIZE_LEVEL.VIDEO_ENCODING_SIZE_LEVEL_480P_1); // 480x480
        videoEncodeSetting.setEncodingBitrate(1000 * 1024); // 1000kbps
        videoEncodeSetting.setEncodingFps(25);
// 音频编码选项
        PLAudioEncodeSetting audioEncodeSetting = new PLAudioEncodeSetting();
        // 录制选项
        PLRecordSetting recordSetting = new PLRecordSetting();
        recordSetting.setMaxRecordDuration(10 * 1000); // 10s
        recordSetting.setVideoCacheDir(getCacheDir());
        File file = new File(getCacheDir(), "record.mp4");
        recordSetting.setVideoFilepath(file.getAbsolutePath());
        mShortVideoRecorder.prepare(surfaceView, cameraSetting, microphoneSetting,
                videoEncodeSetting, audioEncodeSetting, null, recordSetting);


    }

    protected void onResume() {
        super.onResume();
        mShortVideoRecorder.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShortVideoRecorder.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mShortVideoRecorder.destroy();
    }


    ///PLRecordStateListener start

    @Override
    public void onReady() {

    }

    @Override
    public void onError(int i) {

    }

    @Override
    public void onDurationTooShort() {

    }

    @Override
    public void onRecordStarted() {

    }

    @Override
    public void onRecordStopped() {

    }

    @Override
    public void onSectionIncreased(long l, long l1, int i) {

    }

    @Override
    public void onSectionDecreased(long l, long l1, int i) {

    }

    @Override
    public void onRecordCompleted() {

    }
    ///PLRecordStateListener end
}
