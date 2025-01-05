package com.example.imagepickerapp

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PhotoPickerApp(modifier: Modifier = Modifier) {

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    Log.d("TAG", "PhotoPickerApp: $selectedImageUri")

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
        }
    )

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Profile Photo",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.size(16.dp))
        Card(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(150.dp))
                .clickable(
                    onClick = {
                        imagePickerLauncher.launch(
                            PickVisualMediaRequest(
                                mediaType = PickVisualMedia.ImageOnly
                            )
                        )
                    }
                )

        ) {

            if (selectedImageUri == null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        imageVector = Icons.Default.AddAPhoto,
                        contentDescription = "Add Photo"
                    )
                    Text(text = "Add Image")
                }
            } else {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .size(100.dp),
                    model = selectedImageUri,
                    contentDescription = "Selected Image",
                )
            }
        }
    }
}