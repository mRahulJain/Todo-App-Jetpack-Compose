package com.android.todocompose.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.android.todocompose.R
import com.android.todocompose.ui.theme.LIST_EMPTY_SAD_ICON_SIZE
import com.android.todocompose.ui.theme.MediumGray

@Composable
fun ListEmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(LIST_EMPTY_SAD_ICON_SIZE),
            painter = painterResource(id = R.drawable.ic_sad),
            contentDescription = stringResource(id = R.string.list_empty_icon),
            tint = MediumGray
        )
        Text(
            text = stringResource(id = R.string.list_empty_content),
            fontSize = MaterialTheme.typography.h5.fontSize,
            color = MediumGray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun ListEmptyContentPreview() {
    ListEmptyContent()
}