package com.video.alivideo

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.content.pm.PackageManager
import android.support.v4.content.PermissionChecker
import android.Manifest.permission
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.Manifest.permission.READ_PHONE_STATE
import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.BLUETOOTH
import android.os.Build
import android.support.v4.app.ActivityCompat


class MainActivity : AppCompatActivity(), View.OnClickListener {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    pushBtn!!.setOnClickListener(this)
    playBtn!!.setOnClickListener(this)
    if (!permissionCheck()) {
      if (Build.VERSION.SDK_INT >= 23) {
        ActivityCompat.requestPermissions(this, permissionManifest, PERMISSION_REQUEST_CODE)
      } else {
        showNoPermissionTip(getString(noPermissionTip[mNoPermissionIndex]))
        finish()
      }
    }
  }


  private var mNoPermissionIndex = 0
  private val PERMISSION_REQUEST_CODE = 1
  private val permissionManifest = arrayOf<String>(Manifest.permission.CAMERA, Manifest.permission.BLUETOOTH, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)

  private val noPermissionTip = intArrayOf(R.string.no_camera_permission, R.string.no_record_bluetooth_permission, R.string.no_record_audio_permission, R.string.no_read_phone_state_permission, R.string.no_write_external_storage_permission, R.string.no_read_external_storage_permission)

  private fun permissionCheck(): Boolean {
    var permissionCheck = PackageManager.PERMISSION_GRANTED
    var permission: String
    for (i in permissionManifest.indices) {
      permission = permissionManifest[i]
      mNoPermissionIndex = i
      if (PermissionChecker.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
        permissionCheck = PackageManager.PERMISSION_DENIED
      }
    }
    return if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
      false
    } else {
      true
    }
  }

  private fun showNoPermissionTip(tip: String) {
    Toast.makeText(this, tip, Toast.LENGTH_LONG).show()
  }

  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.pushBtn -> {
        var mIntent: Intent = Intent(this, PushConfigActivity::class.java)
        startActivity(mIntent)
      }
      R.id.playBtn -> {

      }
    }
  }
}
