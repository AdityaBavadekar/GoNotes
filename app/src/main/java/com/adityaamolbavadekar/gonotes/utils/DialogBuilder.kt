package com.adityaamolbavadekar.gonotes.utils

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import com.adityaamolbavadekar.gonotes.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

interface DialogBuilder {

    class MessageTwoAction : Dialog {
        override var title: String = ""
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            val b = MaterialAlertDialogBuilder(context)
            b.setMessage(message)
            b.setPositiveButton(positiveButtonText, positiveButtonListener)
            b.setNegativeButton(negativeButtonText, negativeButtonListener)
            dialog = b.create()
            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }
    }

    class LongMessageTwoAction : Dialog {
        override var title: String = ""

        /**
         * Specify the long long message here
         *
         * */
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            val b = MaterialAlertDialogBuilder(context)
            b.setMessage(message)
            b.setPositiveButton(positiveButtonText, positiveButtonListener)
            b.setNegativeButton(negativeButtonText, negativeButtonListener)
            dialog = b.create()

            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }
    }

    class TitleTwoAction : Dialog {
        override var title: String = ""
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            //TODO
            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }
    }

    class TitleMessageTwoAction : Dialog {
        override var title: String = ""
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            //TODO
            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }
    }

    class IconTitleTwoAction : Dialog {
        override var title: String = ""
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            //TODO
            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }
    }

    class IconTitleMessageTwoAction : Dialog {
        override var title: String = ""
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            //TODO
            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }
    }

    class TitleEditTextTwoAction : Dialog {
        override var title: String = ""
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            //TODO
            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }
    }

    class TitleMultiSelectTwoAction() : Dialog {
        override var title: String = ""
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            //TODO
            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }
    }

    class TitleViewTwoAction : Dialog {
        override var title: String = ""
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            //TODO
            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }
    }

    class TitleScrollingContentTwoAction : Dialog {
        override var title: String = ""
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            //TODO
            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }
    }

    class TermsDialog : Dialog {
        override var title: String = ""
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            //TODO
            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }
    }

    class FullScreenDialog : Dialog {
        override var title: String = ""
        override var message: String = ""
        override var positiveButtonText: String = Dialog.TEXT_ACCEPT
        override var negativeButtonText: String = Dialog.TEXT_DECLINE
        override var positiveButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        override var negativeButtonListener: DialogInterface.OnClickListener = Dialog.CLICK_LISTENER
        @DrawableRes
        override var icon: Int = R.drawable.play_store_icon
        override var list: MutableList<String> = mutableListOf()
        override var dialog: AlertDialog? = null
        override fun build(context: Context): Dialog {
            //TODO
            return this
        }

        override fun show() {
            super.show()
        }

        override fun hide() {
            super.hide()
        }

    }

    interface Dialog {
        /**
         * Sets title for dialog
         *
         * */
        var title: String

        /**
         * Sets message for dialog
         *
         * */
        var message: String

        /**
         * Sets positive button's text
         *
         * */
        var positiveButtonText: String

        /**
         * Sets negative button's text
         *
         * */
        var negativeButtonText: String

        /**
         * Sets click listener for positive button
         * @see Dialog.positiveButtonText
         *
         * */
        var positiveButtonListener: DialogInterface.OnClickListener

        /**
         * Sets click listener for negative button
         * @see Dialog.negativeButtonText
         *
         * */
        var negativeButtonListener: DialogInterface.OnClickListener

        /**
         * Sets icon for dialog
         *
         * */
        var icon: Int

        /**
         * Sets long scrollable list for dialog
         *
         * */
        var list: MutableList<String>
        var dialog: AlertDialog?

        companion object {
            //            POSITIVE
            const val TEXT_ACCEPT = "Accept"
            const val TEXT_GOT_IT = "Got it"
            const val TEXT_OK = "Ok"
            const val TEXT_OKAY = "Okay"

            //            NEGATIVE
            const val TEXT_DENY = "Deny"
            const val TEXT_DECLINE = "Decline"
            const val TEXT_CANCEL = "Cancel"
            const val TEXT_DELETE = "Delete"
            val CLICK_LISTENER = DialogInterface.OnClickListener { _, _ -> }
        }

        /**
         * Builds the dialog
         * @param context
         *
         * */
        fun build(@NonNull context: Context): Dialog

        /**
         * Shows the dialog if it was created or built earlier
         *
         * */
        fun show() {
            if (dialog != null) {
                dialog!!.show()
            } else {
                return
            }
        }

        /**
         * Hides the dialog if it was shown earlier
         *
         * */
        fun hide() {
            if (dialog != null) {
                dialog!!.hide()
            } else {
                return
            }
        }

        /**
         * Sets title's text
         * @param title
         *
         * */
        fun withTitle(title: String): Dialog {
            this.title = title
            return this
        }

        /**
         * Sets message's text
         * @param message
         *
         * */
        fun withMessage(message: String): Dialog {
            this.message = message
            return this
        }

        /**
         * Sets icon's drawable
         * @param icon
         *
         * */
        fun withIcon(@DrawableRes icon: Int): Dialog {
            this.icon = icon
            return this
        }

        /**
         * Sets positive button's text
         * @param positiveButtonText
         *
         * */
        fun withPositiveButton(positiveButtonText: String): Dialog {
            this.positiveButtonText = positiveButtonText
            return this
        }

        /**
         * Sets negative button's text
         * @param negativeButtonText
         *
         * */
        fun withNegativeButtonText(negativeButtonText: String): Dialog {
            this.negativeButtonText = negativeButtonText
            return this
        }

        /**
         * Sets list for scrolling text
         * @param list
         *
         * */
        fun withList(list: MutableList<String>): Dialog {
            this.list = list
            return this
        }
    }

    class GoNotesDialog(context: Context) : AlertDialog.Builder(context) {

    }


}