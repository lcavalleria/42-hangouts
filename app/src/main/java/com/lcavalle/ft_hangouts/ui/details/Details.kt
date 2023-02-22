package com.lcavalle.ft_hangouts.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ft_hangouts.R
import com.lcavalle.ft_hangouts.Contact
import com.lcavalle.ft_hangouts.Router
import com.lcavalle.ft_hangouts.ui.ProfileImageLoader
import com.lcavalle.ft_hangouts.ui.layout.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun Details(
    navController: NavController,
    id: Long,
    viewModel: DetailsViewModel = viewModel(),
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.selectContactById(id)
    }
    val contactState = viewModel.selectedContactState.collectAsState()
    val contact: Contact = contactState.value
    val bitmap = ProfileImageLoader.loadInternal(context, id)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = colorsFromStatusBar(),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                },
                title = { Text(stringResource(id = R.string.contact_details)) },

                )
        },
        content = { padding ->
            Column(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .weight(weight = 1f, fill = false),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val detailsFieldModifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 32.dp)
                        .fillMaxWidth()
                    Box(
                        modifier = Modifier
                            .padding(32.dp)
                            .fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        ProfilePic(
                            contact = contact,
                            modifier = Modifier.align(Alignment.TopCenter),
                            bitmap = bitmap,
                            size = DpSize(120.dp, 120.dp),
                        )
                    }
                    Text(
                        text = contact.name,
                        fontSize = 40.sp,
                        lineHeight = 45.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = detailsFieldModifier,
                        textAlign = TextAlign.Center
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = detailsFieldModifier
                    ) {
                        CallButton(
                            contact = contact,
                            context = context,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                        MessageButton(modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f), onClick = {
                            navController.navigate(Router.Chat.withId(contact.id))
                        })
                        EditButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            navController.navigate(Router.Edit.withId(id))
                        }
                    }
                    Text(
                        text = contact.number,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = detailsFieldModifier,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = contact.mail,
                        fontSize = 24.sp,
                        modifier = detailsFieldModifier,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}