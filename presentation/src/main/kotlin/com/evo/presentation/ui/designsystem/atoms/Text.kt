package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import androidx.compose.material3.Text as MaterialText

@Composable
fun DesignSystem.Text(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    color: Color = Colors.content.primary,
    style: TextStyle = DesignSystem.TextStyles.body,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    softWrap: Boolean = true,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
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
    inlineContent = inlineContent,
)

@Composable
fun DesignSystem.Text(
    modifier: Modifier = Modifier,
    text: CharSequence,
    color: Color = Colors.content.primary,
    style: TextStyle = DesignSystem.TextStyles.body,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    softWrap: Boolean = false,
) = MaterialText(
    modifier = modifier,
    text = text.toString(),
    color = color,
    style = style,
    textAlign = textAlign,
    overflow = overflow,
    maxLines = if (softWrap) maxLines else 1,
    minLines = minLines,
    softWrap = softWrap,
)
