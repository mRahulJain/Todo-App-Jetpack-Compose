package com.android.todocompose.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.todocompose.R
import com.android.todocompose.data.models.Priority
import com.android.todocompose.ui.theme.PRIORITY_DROP_DOWN_BORDER_WIDTH
import com.android.todocompose.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import com.android.todocompose.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityItemDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(PRIORITY_DROP_DOWN_HEIGHT)
            .clickable {
                expanded = true
            }
            .border(
                width = PRIORITY_DROP_DOWN_BORDER_WIDTH,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                shape = MaterialTheme.shapes.small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            modifier = Modifier.weight(8f),
            text = priority.name,
            style = MaterialTheme.typography.subtitle2,
        )
        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .rotate(angle)
                .weight(1.5f),
            onClick = { expanded = true }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(id = R.string.priority_icon_drop_down)
            )
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(0.94f),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            PriorityDropDownMenuItem(priority = Priority.LOW) {
                expanded = false
                onPrioritySelected(Priority.LOW)
            }
            PriorityDropDownMenuItem(priority = Priority.MEDIUM) {
                expanded = false
                onPrioritySelected(Priority.MEDIUM)
            }
            PriorityDropDownMenuItem(priority = Priority.HIGH) {
                expanded = false
                onPrioritySelected(Priority.HIGH)
            }
        }
    }
}

@Composable
fun PriorityDropDownMenuItem(
    priority: Priority,
    onMenuItemClick: () -> Unit
) {
    DropdownMenuItem(
        onClick = onMenuItemClick
    ) {
        PriorityItem(priority = priority)
    }
}

@Composable
@Preview
fun PriorityItemDropDownPreview() {
    PriorityItemDropDown(
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}