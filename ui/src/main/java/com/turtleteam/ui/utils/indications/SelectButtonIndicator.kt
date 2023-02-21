package com.turtleteam.ui.utils.indications

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp

class SelectButtonIndicator(val color: Color, val radius: Dp) : Indication {

    private inner class IndInstance(
        private val isPressed: State<Boolean>,
    ) : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {

            drawContent()
            when {
                isPressed.value -> {
                    drawRoundRect(
                        color,
                        style = Stroke(5F),
                        cornerRadius = CornerRadius(radius.toPx(), radius.toPx())
                    )
                }
            }
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        val isPressed = interactionSource.collectIsPressedAsState()
        return remember(interactionSource) {
            IndInstance(isPressed)
        }
    }
}