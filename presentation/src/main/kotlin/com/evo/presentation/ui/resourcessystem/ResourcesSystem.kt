package com.evo.presentation.ui.resourcessystem

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.evo.presentation.R
import com.evo.presentation.ui.designsystem.theme.LocalLocale
import com.evo.storage.EvoLocale

@Immutable
object ResourcesSystem {

    enum class Strings(private val ru: String, private val en: String) {
        PressAgainToExit(ru = "Повторите для выхода", en = "Press again to exit"),
        Yes(ru = "Да", en = "Yes"),
        No(ru = "Нет", en = "No"),
        Delete(ru = "Удалить", en = "Delete"),
        Confirm(ru = "Подтвердить", en = "Confirm"),
        Cancel(ru = "Отмена", en = "Cancel"),
        Light(ru = "Светлая", en = "Light"),
        Dark(ru = "Темная", en = "Dark"),
        Theme(ru = "Тема приложения", en = "Theme"),
        Locale(ru = "Язык приложения", en = "Locale"),
        Russian(ru = "Русский", en = "Russian"),
        English(ru = "Английский", en = "English"),
        Copied(ru = "Скопировано!", en = "Copied"),
        Done(ru = "Готово", en = "Done"),
        Save(ru = "Сохранить", en = "Save"),
        Name(ru = "Имя", en = "Name"),
        Phone(ru = "Телефон", en = "Phone"),
        AreYouSureWantToDelete(ru = "Вы действительно хотите удалить?", en = "Are you sure want to delete?"),
        Space(ru = " ", en = " "),
        Ruble(ru = "₽", en = "₽"),
        ;

            open val value
                @Composable
                get() = when (LocalLocale.current) {
                    EvoLocale.English -> en
                    EvoLocale.Russian -> ru
                }
        }

    enum class Icons(@DrawableRes private val res: Int) {
        Ruble(R.drawable.ruble),
        AddSquare(R.drawable.add_square),
        Add(R.drawable.add),
        Remove(R.drawable.remove),
        ArrowBottom(R.drawable.arrow_bottom),
        User(R.drawable.user),
        Calendar(R.drawable.calendar),
        Camera(R.drawable.camera),
        Done(R.drawable.done),
        ChevronLeft(R.drawable.chevron_left),
        ChevronRight(R.drawable.chevron_right),
        Copy(R.drawable.copy),
        Delete(R.drawable.delete),
        Edit(R.drawable.edit),
        Map(R.drawable.map),
        Phone(R.drawable.phone),
        Repeat(R.drawable.repeat),
        ;

        val value @Composable get() = ImageVector.vectorResource(res)
    }
}
