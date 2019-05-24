package com.video.alivideo

import android.hardware.Camera
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.alivc.live.pusher.*
import com.alivc.live.pusher.AlivcAudioChannelEnum.AUDIO_CHANNEL_TWO
import com.alivc.live.pusher.AlivcPreviewOrientationEnum.ORIENTATION_PORTRAIT
import com.alivc.live.pusher.AlivcVideoEncodeGopEnum.GOP_TWO
import kotlinx.android.synthetic.main.push_config_layout.*


class PushConfigActivity : AppCompatActivity(), View.OnClickListener {

  private var mAlivcLivePushConfig: AlivcLivePushConfig? = null
  private val mOrientationEnum = ORIENTATION_PORTRAIT
  private var mCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT
  private var mAsyncValue: Boolean = true //异步


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.push_config_layout)
    initView()
  }

  private fun initView() {
    mAlivcLivePushConfig = AlivcLivePushConfig()
    //显示模式
    mAlivcLivePushConfig!!.setQualityMode(AlivcQualityModeEnum.QM_RESOLUTION_FIRST);
    SharedPreferenceUtils.setHintTargetBit(getApplicationContext(), AlivcLivePushConstants.BITRATE_720P_FLUENCY_FIRST.DEFAULT_VALUE_INT_TARGET_BITRATE.getBitrate());
    SharedPreferenceUtils.setHintMinBit(getApplicationContext(), AlivcLivePushConstants.BITRATE_720P_FLUENCY_FIRST.DEFAULT_VALUE_INT_MIN_BITRATE.getBitrate());

    //目标码率
    mAlivcLivePushConfig!!.setTargetVideoBitrate(1200);
    SharedPreferenceUtils.setTargetBit(getApplicationContext(), 1200);
    //最小码率
    mAlivcLivePushConfig!!.setMinVideoBitrate(300);
    SharedPreferenceUtils.setMinBit(getApplicationContext(), 300);
    //初始码率
    mAlivcLivePushConfig!!.setInitialVideoBitrate(1000);

    //音频码率
    mAlivcLivePushConfig!!.setAudioBitRate(1000 * Integer.valueOf(64));


    //音频编码
    mAlivcLivePushConfig!!.setAudioProfile(AlivcAudioAACProfileEnum.AAC_LC);

    //声道
    mAlivcLivePushConfig!!.setAudioChannels(AUDIO_CHANNEL_TWO);

    //横屏推流
    mAlivcLivePushConfig!!.setPreviewOrientation(ORIENTATION_PORTRAIT);

    //Gop
    mAlivcLivePushConfig!!.setVideoEncodeGop(GOP_TWO);


    //前置摄像头
    mAlivcLivePushConfig!!.setCameraType(if (true) AlivcLivePushCameraTypeEnum.CAMERA_TYPE_FRONT else AlivcLivePushCameraTypeEnum.CAMERA_TYPE_BACK)
    mCameraId = if (true) AlivcLivePushCameraTypeEnum.CAMERA_TYPE_FRONT.cameraId else AlivcLivePushCameraTypeEnum.CAMERA_TYPE_BACK.cameraId

    //自动对焦
    mAlivcLivePushConfig!!.setAutoFocus(true);
    SharedPreferenceUtils.setAutofocus(getApplicationContext(), true);
    //美颜
    mAlivcLivePushConfig!!.setBeautyOn(false);
    SharedPreferenceUtils.setBeautyOn(getApplicationContext(), false);

    mAsyncValue = false;
    //硬编码
    mAlivcLivePushConfig!!.setVideoEncodeMode(if (true) AlivcEncodeModeEnum.Encode_MODE_HARD else AlivcEncodeModeEnum.Encode_MODE_SOFT)

    beginPublish!!.setOnClickListener(this)

  }

  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.beginPublish -> {
        LivePushActivity.startActivity(this, mAlivcLivePushConfig, "rtmp://testpush.rhrsh.com/app001/stream001?auth_key=1558586249-0-0-235e0b06bc6a2358e374d02011215ebe", mAsyncValue, false, false, mOrientationEnum, mCameraId, false, "", "", false, mAlivcLivePushConfig!!.isExternMainStream())
      }
    }
  }


}


