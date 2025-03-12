package com.example.uploadimage

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ImagePickerScreen() {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var uri by remember { mutableStateOf<Uri?>(null) }
    var context= LocalContext.current

    val launcher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){
        uri=it
    }

    Column(modifier = Modifier
        .padding(16.dp, top = 50.dp)
        .fillMaxWidth()
    ) {
        Button(onClick = {launcher.launch("image/*")}) {
            Text("Upload Photo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        uri?.let {
            if (Build.VERSION.SDK_INT < 28){
                bitmap=MediaStore.Images.Media.getBitmap(context.contentResolver,it)
            }
            else{
                val source=ImageDecoder.createSource(context.contentResolver,it)
                bitmap=ImageDecoder.decodeBitmap(source)
            }

            bitmap?.let {bmp->
                Image(
                    bitmap= bmp.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }
}