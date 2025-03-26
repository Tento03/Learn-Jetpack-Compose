package com.example.cameralauncher

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.net.Uri
import android.os.Build
import android.provider.MediaStore.Images.Media
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun CameraLauncher(modifier: Modifier = Modifier) {
    val context= LocalContext.current
    var uri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val imageLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){selectedUri->
        selectedUri?.let {
            uri=it

            uri?.let {uris->
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap=Media.getBitmap(context.contentResolver,it)
                }
                else{
                    val source=ImageDecoder.createSource(context.contentResolver,it)
                    bitmap=ImageDecoder.decodeBitmap(source)
                }
            }
        }
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) {selectedBitmap->
        selectedBitmap?.let {
            bitmap=it
        }
    }

    Column(modifier.padding(16.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Button(onClick = {imageLauncher.launch("image/*")},modifier.fillMaxWidth().padding(8.dp)) {
            Text("Pick From Gallery")
        }
        Button(onClick = {cameraLauncher.launch(null)},modifier.fillMaxWidth().padding(8.dp)) {
            Text("Open Camera")
        }
        bitmap?.let { Image(bitmap = it.asImageBitmap(),contentDescription = null,modifier.clip(shape = CircleShape).size(75.dp)) }
    }
}