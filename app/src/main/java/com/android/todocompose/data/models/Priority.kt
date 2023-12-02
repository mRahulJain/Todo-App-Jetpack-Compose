package com.android.todocompose.data.models

import androidx.compose.ui.graphics.Color
import com.android.todocompose.ui.theme.HighPriorityColor
import com.android.todocompose.ui.theme.LowPriorityColor
import com.android.todocompose.ui.theme.MediumPriorityColor
import com.android.todocompose.ui.theme.NoPriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NoPriorityColor)
}