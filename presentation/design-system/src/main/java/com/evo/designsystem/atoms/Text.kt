package com.evo.designsystem.atoms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.evo.designsystem.theme.DesignSystem
import androidx.compose.material3.Text as MaterialText

@Composable
fun DesignSystem.Text(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = colors.primary,
    style: TextStyle = textStyles.body,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    softWrap: Boolean = true,
) = MaterialText(
    modifier = modifier,
    text = text,
    color = color,
    style = style,
    textAlign = textAlign,
    overflow = overflow,
    maxLines = if (softWrap) maxLines else 1,
    minLines = minLines,
    softWrap = softWrap,
)
