package com.lcavalle.ft_hangouts.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ft_hangouts.R

@Composable
fun SettingsDropdownMenu(onChangeHeaderColorClick: () -> Unit) {
    val expanded = remember { mutableStateOf(false) }

    Box(Modifier.wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = {
            expanded.value = true
        }) {
            Icon(
                Icons.Filled.MoreVert, contentDescription = "Settings menu"
            )
        }
    }


    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false }) {
        DropdownMenuItem(
            text = { Text(stringResource(id = R.string.change_header_color)) },
            onClick = {
                expanded.value = false
                onChangeHeaderColorClick()
            })
    }
}