package com.example.routeapp.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.routeapp.R
import com.example.routeapp.fragments.TrailDetailFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DetailActivity : AppCompatActivity() {
    private var photoURI: Uri? = null

    override fun onCreate(savedinstanceState: Bundle?) {
        super.onCreate(savedinstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val trailId = intent.getIntExtra(EXTRA_TRAIL_ID, 0)
        val trailType = intent.getStringExtra(EXTRA_TRAIL_TYPE)
        val fragment: TrailDetailFragment =
            supportFragmentManager.findFragmentById(R.id.detail_frag) as TrailDetailFragment
        if (trailType != null) {
            fragment.setTrailDetails(trailId, trailType)
        }
        val fab: FloatingActionButton = findViewById(R.id.fab_camera)
        fab.setOnClickListener {
            handleCameraAction()
        }
    }

    private fun openCamera() {
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: Exception) {
            Toast.makeText(
                this,
                "Photo file can't be created, please try again",
                Toast.LENGTH_SHORT
            ).show()
            null
        }
        photoFile?.also {
            photoURI = FileProvider.getUriForFile(
                this,
                "com.example.routeapp.fileprovider",
                it
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            cameraResultLauncher.launch(intent)
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    private val cameraResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            photoURI?.let { uri ->
                Toast.makeText(this, "Image saved to: $uri", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(
                this,
                "Camera permission is required to use the camera",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun handleCameraAction() {
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                openCamera()
            }

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    companion object {
        const val EXTRA_TRAIL_ID = "id"
        const val EXTRA_TRAIL_TYPE = "com.example.routeapp.TRAIL_TYPE"
        const val TYPE_EASY = "EASY"
        const val TYPE_HARD = "HARD"
    }
}