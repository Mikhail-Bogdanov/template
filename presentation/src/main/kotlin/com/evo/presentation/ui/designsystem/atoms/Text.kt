package com.evo.presentation.ui.designsystem.atoms

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import com.evo.presentation.ui.designsystem.theme.DesignSystem
import org.koin.core.component.KoinComponent
import androidx.compose.material3.Text as MaterialText

@Composable
fun DesignSystem.Text(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
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
    text: CharSequence,
    modifier: Modifier = Modifier,
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

object EvoInlineContent : KoinComponent {

    const val SPACE_ID = "SPACE_ID"

    fun space(width: Dp = DesignSystem.Paddings.DSPx1) = SPACE_ID to InlineTextContent(
        Placeholder(
            width = width.value.sp,
            height = 1.sp,
            placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
        )
    ) {
        Spacer(modifier = Modifier.size(width, 1.dp))
    }
}
