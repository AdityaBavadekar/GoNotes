package com.adityaamolbavadekar.gonotes.utils

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.adityaamolbavadekar.gonotes.databinding.InfoCardBinding

class InfoCardView {

    class Builder(private val context: Context) :
        InfoCardViewInterface {
        private val clickListenerDismissBehavior = View.OnClickListener { hideFromParent() }
        override var title: String? = null
        override var message: String? = null
        override var positiveButtonText: String = InfoCardViewInterface.TEXT_GOT_IT
        override var negativeButtonText: String? = null
        override var icon: Int? = null
        override var positiveButtonListener: View.OnClickListener = clickListenerDismissBehavior
        override var negativeButtonListener: View.OnClickListener = clickListenerDismissBehavior
        override var infoButtonListener: View.OnClickListener = clickListenerDismissBehavior
        override var useInfoIcon: Boolean = false
        override var image: Int? = null
        override var infoCardView: InfoCardBinding? = null

        fun build(): View {
            createLayout(context)
            if (title != null) {
                infoCardView!!.textViewTitle.text = title!!
                infoCardView!!.textViewTitle.isVisible = true
            }

            if (message != null) {
                infoCardView!!.textViewMessage.text = message!!
                infoCardView!!.textViewMessage.isVisible = true
            }

            infoCardView!!.buttonPositive.text = positiveButtonText
            infoCardView!!.buttonPositive.isVisible = true
            infoCardView!!.buttonPositive.setOnClickListener { positiveButtonListener }

            if (negativeButtonText != null) {
                infoCardView!!.buttonNegative.text = negativeButtonText!!
                infoCardView!!.buttonNegative.isVisible = true
                infoCardView!!.buttonNegative.setOnClickListener { negativeButtonListener }
            } else {
                useInfoIcon = true
            }

            if (icon != null) {
                infoCardView!!.iconImageView.setImageResource(icon!!)
                infoCardView!!.iconImageView.isVisible = true
            }

            if (image != null) {
                infoCardView!!.imageView.setImageResource(image!!)
                infoCardView!!.imageView.isVisible = true
            }

            if (useInfoIcon) {
                infoCardView!!.buttonNegative.isVisible = false
                infoCardView!!.infoIconImageView.setImageResource(image!!)
                infoCardView!!.infoIconFrame1.isVisible = true
                infoCardView!!.infoIconFrame1.setOnClickListener { infoButtonListener }
            }

/*
            indexInParent = appBarLayout.size - 1
            appBarLayout.addView(infoCardView!!.root, indexInParent, params)
*/
            return infoCardView!!.root
        }

        fun withTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun withMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun withPositiveButton(text: String, clickListener: View.OnClickListener?): Builder {
            this.positiveButtonText = text
            if (clickListener != null) {
                this.positiveButtonListener = clickListener
            }
            return this
        }

        fun withNegativeButton(text: String, clickListener: View.OnClickListener?): Builder {
            this.negativeButtonText = text
            if (clickListener != null) {
                this.negativeButtonListener = clickListener
            }
            return this
        }

        fun withInfoIconButton(clickListener: View.OnClickListener?): Builder {
            this.useInfoIcon = true
            if (clickListener != null) {
                this.infoButtonListener = clickListener
            }
            return this
        }
    }

    interface InfoCardViewInterface {
        /**
         * Sets title for the Info Card
         *
         * */
        open var title: String?

        /**
         * Sets message for the Info Card
         *
         * */
        open var message: String?

        /**
         * Sets positive button's text
         *
         * */
        open var positiveButtonText: String

        /**
         * Sets negative button's text
         *
         * */
        open var negativeButtonText: String?

        /**
         * Sets click listener for positive button
         * @see InfoCardViewInterface.positiveButtonText
         *
         * */
        open var positiveButtonListener: View.OnClickListener

        /**
         * Sets click listener for negative button
         * @see InfoCardViewInterface.negativeButtonText
         *
         * */
        open var negativeButtonListener: View.OnClickListener

        /**
         * Sets click listener for info button
         * @see InfoCardViewInterface.useInfoIcon
         *
         * */
        open var infoButtonListener: View.OnClickListener

        /**
         * Sets InfoIcon for instead of using negative button.
         *
         * **Note** : This setting overrides settings for [negativeButtonText] and [negativeButtonListener].
         * @see android.R.drawable.ic_menu_info_details
         *
         * */
        open var useInfoIcon: Boolean

        /**
         * Sets icon for the Info Card
         *
         * */
//        @DrawableRes
        open var icon: Int?

        /**
         * Sets drawable for the Info Card
         *
         * */
//        @DrawableRes
        open var image: Int?
        var infoCardView: InfoCardBinding?

        companion object {
            /*POSITIVE*/
            const val TEXT_ACCEPT = "Accept"
            const val TEXT_GOT_IT = "Got it"
            const val TEXT_OK = "Ok"
            const val TEXT_OKAY = "Okay"

            /*NEGATIVE*/
            const val TEXT_DENY = "Deny"
            const val TEXT_DECLINE = "Decline"
            const val TEXT_LEARN_MORE = "Learn more"
            const val TEXT_CANCEL = "Cancel"
            const val TEXT_DELETE = "Delete"
            val CLICK_LISTENER = DialogInterface.OnClickListener { _, _ -> }
        }

        fun hideFromParent() {
            infoCardView!!.root.visibility = View.GONE
        }

        /**
         * Inflates the Info Card
         *
         * */
        fun createLayout(context: Context) {
            infoCardView =
                InfoCardBinding.inflate(LayoutInflater.from(context))
        }
/*
        */
        /**
         * Shows the dialog if it was created or built earlier
         *
         * *//*
        fun show() {
            if (!isShown) {
                infoCardView!!.root.isVisible = true
            } else {
                return
            }
        }

        */
        /**
         * Hides the dialog if it was shown earlier
         *
         * *//*
        fun hide() {
            if (isShown) {
                infoCardView!!.root.isVisible = false
            } else {
                return
            }
        }*/
    }
}