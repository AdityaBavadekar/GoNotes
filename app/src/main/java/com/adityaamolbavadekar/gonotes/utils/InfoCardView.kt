package com.adityaamolbavadekar.gonotes.utils

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.core.view.size
import com.adityaamolbavadekar.gonotes.databinding.InfoCardBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.properties.Delegates

class InfoCard {

    class Builder(private val context: Context, private val appBarLayout: AppBarLayout) :
        InfoCard(context, appBarLayout) {
        override var title: String? = null
        override var message: String? = ""
        override var positiveButtonText: String? = InfoCard.TEXT_GOT_IT
        override var negativeButtonText: String? = InfoCard.TEXT_LEARN_MORE

        fun build(): InfoCard {
            createLayout()
            if (title != null) {
                infoCardView!!.textViewTitle.text = title!!
                infoCardView!!.textViewTitle.isVisible = true
            }

            if (message != null) {
                infoCardView!!.textViewMessage.text = message!!
                infoCardView!!.textViewMessage.isVisible = true
            }

            if (positiveButtonText != null) {
                infoCardView!!.buttonPositive.text = positiveButtonText!!
                infoCardView!!.buttonPositive.isVisible = true
                infoCardView!!.buttonPositive.setOnClickListener { positiveButtonListener }
            } else {
                infoCardView!!.buttonPositive.text = TEXT_GOT_IT
                infoCardView!!.buttonPositive.isVisible = true
                infoCardView!!.buttonPositive.setOnClickListener {
                    DialogInterface.OnClickListener { _, _ ->
                        removeFromParent()
                    }
                }
            }

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
                infoCardView!!.buttonNegative.setOnClickListener { CLICK_LISTENER }
                infoCardView!!.buttonNegative.isVisible = false
                infoCardView!!.infoIconImageView.setImageResource(image!!)
                infoCardView!!.infoIconFrame1.isVisible = true
                infoCardView!!.infoIconFrame1.setOnClickListener { removeFromParent() }
            }

            val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            indexInParent = appBarLayout.size - 1
            appBarLayout.addView(infoCardView!!.root, indexInParent, params)

            return this
        }
    }

    open class InfoCard(private val context: Context, private val appBarLayout: AppBarLayout) {
        /**
         * Sets title for the Info Card
         *
         * */
        open var title: String? = null

        /**
         * Sets message for the Info Card
         *
         * */
        open var message: String? = null

        /**
         * Sets positive button's text
         *
         * */
        open var positiveButtonText: String? = null

        /**
         * Sets negative button's text
         *
         * */
        open var negativeButtonText: String? = null

        /**
         * Sets click listener for positive button
         * @see InfoCard.positiveButtonText
         *
         * */
        open var positiveButtonListener: DialogInterface.OnClickListener =
            CLICK_LISTENER_ACTION_REMOVE_VIEW

        /**
         * Sets click listener for negative button
         * @see InfoCard.negativeButtonText
         *
         * */
        open var negativeButtonListener: DialogInterface.OnClickListener =
            CLICK_LISTENER_ACTION_REMOVE_VIEW

        /**
         * Sets InfoIcon for instead of using negative button.
         *
         * **Note** : This setting overrides settings for [negativeButtonText] and [negativeButtonListener].
         * @see android.R.drawable.ic_menu_info_details
         *
         * */
        open var useInfoIcon: Boolean = false

        /**
         * Sets icon for the Info Card
         *
         * */
        @DrawableRes
        open var icon: Int? = null

        var indexInParent by Delegates.notNull<Int>()

        /**
         * Sets drawable for the Info Card
         *
         * */
        @DrawableRes
        open var image: Int? = null
        var infoCardView: InfoCardBinding? = null
        var isShown: Boolean = false

        companion object {
            //            POSITIVE
            const val TEXT_ACCEPT = "Accept"
            const val TEXT_GOT_IT = "Got it"
            const val TEXT_OK = "Ok"
            const val TEXT_OKAY = "Okay"

            //            NEGATIVE
            const val TEXT_DENY = "Deny"
            const val TEXT_DECLINE = "Decline"
            const val TEXT_LEARN_MORE = "Learn more"
            const val TEXT_CANCEL = "Cancel"
            const val TEXT_DELETE = "Delete"
            val CLICK_LISTENER = DialogInterface.OnClickListener { _, _ -> }
            val CLICK_LISTENER_ACTION_REMOVE_VIEW = DialogInterface.OnClickListener { _, _ ->

            }
        }

        fun removeFromParent() {
            appBarLayout.removeViewAt(indexInParent)
        }

        /**
         * Builds the Info Card
         * @param context
         *
         * */
        fun createLayout() {
            infoCardView =
                InfoCardBinding.inflate(LayoutInflater.from(context))
        }

        /**
         * Shows the dialog if it was created or built earlier
         *
         * */
        fun show() {
            if (!isShown) {
                infoCardView!!.root.isVisible = true
            } else {
                return
            }
        }

        /**
         * Hides the dialog if it was shown earlier
         *
         * */
        fun hide() {
            if (isShown) {
                infoCardView!!.root.isVisible = false
            } else {
                return
            }
        }
    }


}