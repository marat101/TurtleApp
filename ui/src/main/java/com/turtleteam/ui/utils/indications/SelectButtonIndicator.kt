package com.turtleteam.ui.utils.indications

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SelectButtonIndicator(val color: Color, val radius: Dp) : IndicationNodeFactory {

    private inner class BorderIndicationNode(
        private val interactionSource: InteractionSource
    ) : Modifier.Node(), DrawModifierNode {
        private val isPressed: MutableState<Boolean> = mutableStateOf(false)

        override fun onAttach() {
            coroutineScope.launch {
                interactionSource.interactions.collectLatest {
                    isPressed.value = when (it) {
                        is PressInteraction.Press -> true
                        is PressInteraction.Release -> false
                        is PressInteraction.Cancel -> false
                        else -> false
                    }
                }
            }
        }

        override fun ContentDrawScope.draw() {
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

    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return BorderIndicationNode(interactionSource)
    }

    override fun hashCode(): Int = -1

    override fun equals(other: Any?) = other === this
}