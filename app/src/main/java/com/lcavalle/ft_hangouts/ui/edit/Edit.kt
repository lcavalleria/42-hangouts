package com.lcavalle.ft_hangouts.ui.edit

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.R
import com.lcavalle.ft_hangouts.Contact
import com.lcavalle.ft_hangouts.MainActivity
import com.lcavalle.ft_hangouts.Router
import com.lcavalle.ft_hangouts.ui.ProfileImageLoader
import com.lcavalle.ft_hangouts.ui.layout.ProfilePic

private fun saveBitmapIntoInternalStorage(
    context: Context,
    contactId: Long,
    galleryUri: Uri?
): Uri? {
    Log.d(MainActivity.TAG, "attempting to get image and save it in internal storage")
    val bitmap = ProfileImageLoader.loadExternal(context.contentResolver, galleryUri) ?: return null
    Log.d(MainActivity.TAG, "success reading bitmap of ${bitmap.byteCount} bytes")
    val internalUri = ProfileImageLoader.storeInternal(context, contactId, bitmap)
    Log.d(MainActivity.TAG, "success saving bitmap in ${internalUri?.path} ")
    return internalUri
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Edit(
    navController: NavController, id: Long = 0, viewModel: EditViewModel = viewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.selectContactById(id)
    }
    val contactState = viewModel.selectedContactState.collectAsState()
    val contact: Contact = contactState.value
    val bitmapState = remember {
        mutableStateOf<ImageBitmap?>(
            ProfileImageLoader.loadInternal(context, id)
        )
    }
    val bitmap = bitmapState.value
    Scaffold(topBar = {
        EditTopBar(
            isFav = contact.isFavorite,
            onNavigationIcon = { navController.popBackStack() },
            onFavClick = { viewModel.setEditContactIsFav(!contact.isFavorite) },
            onDoneClick = {
                viewModel.storeEditingContact()
                navController.navigate(Router.Home.route)
            }
        )

    }, content = { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .weight(weight = 1f, fill = false),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val editFieldModifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 16.dp)
                    .fillMaxWidth()
                Box(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    val result = remember { mutableStateOf<Uri?>(null) }
                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.OpenDocument(),
                        onResult = { uri ->
                            val internalUri =
                                saveBitmapIntoInternalStorage(context, contact.id, uri)
                            result.value = internalUri
                            bitmapState.value = ProfileImageLoader.loadInternal(context, id)
                        }
                    )

                    ProfilePic(
                        contact = contact,
                        size = DpSize(120.dp, 120.dp),
                        onClick = { if (id != 0L) launcher.launch(arrayOf("image/*")) },
                        bitmap = bitmap,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                    result.value?.let {
                        viewModel.setEditContactPicture(it.toString())
                    }
                }
                TextField(
                    value = contact.name,
                    maxLines = 1,
                    placeholder = {
                        Text(
                            stringResource(id = R.string.name_placeholder),
                            color = Color.Gray
                        )
                    },
                    onValueChange = { viewModel.setEditContactName(it) },
                    modifier = editFieldModifier
                )
                TextField(
                    value = contact.number,
                    maxLines = 1,
                    placeholder = {
                        Text(
                            stringResource(id = R.string.number_placeholder),
                            color = Color.Gray
                        )
                    },
                    onValueChange = { viewModel.setEditContactNumber(it) },
                    modifier = editFieldModifier
                )
                TextField(
                    value = contact.mail,
                    maxLines = 1,
                    placeholder = {
                        Text(
                            stringResource(id = R.string.mail_placeholder),
                            color = Color.Gray
                        )
                    },
                    onValueChange = { viewModel.setEditContactMail(it) },
                    modifier = editFieldModifier
                )

                val openDeleteDialog = remember { mutableStateOf(false) }
                Text(
                    text = stringResource(id = R.string.delete_contact),
                    textAlign = TextAlign.Center,
                    modifier = editFieldModifier
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { openDeleteDialog.value = true }
                        .padding(16.dp),
                    color = Color.Red
                )
                if (openDeleteDialog.value) {
                    AlertDialog(
                        onDismissRequest = { openDeleteDialog.value = false },
                        title = { Text(stringResource(id = R.string.delete_contact_dialog_title)) },
                        dismissButton = {
                            TextButton(
                                onClick = { openDeleteDialog.value = false }) {
                                Text(text = stringResource(id = R.string.no))
                            }
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    viewModel.deleteContact(context)
                                    navController.navigate(Router.Home.route)
                                }) {
                                Text(text = stringResource(id = R.string.yes))
                            }
                        }
                    )
                }
            }
        }
    })
}