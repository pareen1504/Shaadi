package com.pd.shaadi.Util

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pd.shaadi.R
import com.pd.shaadi.model.database.DbData

@BindingAdapter("setName")
fun TextView.setName(item: DbData?) {
    item?.let {
        text = "${item.firstname} ${item.lastname}"
    }
}

@BindingAdapter("setProfileImage")
fun ImageView.setProfileImage(item: DbData?) {
    item?.let {
        if (ConnectionLiveData(context).isOnline()) {
            Glide.with(context).load(item.img_url).optionalCircleCrop()
                .fallback(ContextCompat.getDrawable(context, R.drawable.ic_brokenimage_foreground))
                .into(this)
        } else {
            setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_brokenimage_foreground))
        }
    }
}

@BindingAdapter("setDescription")
fun TextView.setDescription(item: DbData?) {
    item?.let {
        text = item.location
    }
}

@BindingAdapter("app:setAcceptButtonVisibility")
fun ImageButton.setAcceptButtonVisibility(item: DbData) {
    when (item.selection) {
        "reject" -> visibility = View.GONE
        "accept" -> {
            visibility = View.VISIBLE
            background = ContextCompat.getDrawable(
                context, R.drawable.round_button_selected
            )
            setImageDrawable(
                ContextCompat.getDrawable(
                    context, R.drawable.ic_accept_foregroung_selected
                )
            )

            val params = layoutParams as ConstraintLayout.LayoutParams
            params.startToStart = ConstraintSet.PARENT_ID
            params.endToEnd = ConstraintSet.PARENT_ID
        }
        "Action_Pending" -> {
            visibility = View.VISIBLE
            background = ContextCompat.getDrawable(
                context, R.drawable.round_button
            )
            setImageDrawable(
                ContextCompat.getDrawable(
                    context, R.drawable.ic_accept_foreground
                )
            )

            val params = layoutParams as ConstraintLayout.LayoutParams
            params.startToStart = ConstraintSet.PARENT_ID
            params.endToEnd = ConstraintSet.GONE

        }
    }
}

@BindingAdapter("app:setRejectButtonVisibility")
fun ImageButton.setRejectButtonVisibility(item: DbData) {
    when (item.selection) {
        "accept" -> visibility = View.GONE
        "reject" -> {
            visibility = View.VISIBLE
            background = ContextCompat.getDrawable(
                context, R.drawable.ic_reject_selected_background
            )
            setImageDrawable(
                ContextCompat.getDrawable(
                    context, R.drawable.ic_reject_selected_foreground
                )
            )

            val params = layoutParams as ConstraintLayout.LayoutParams
            params.startToStart = ConstraintSet.PARENT_ID
            params.endToEnd = ConstraintSet.PARENT_ID
        }

        "Action_Pending" -> {
            visibility = View.VISIBLE
            background = ContextCompat.getDrawable(
                context, R.drawable.round_button
            )
            setImageDrawable(
                ContextCompat.getDrawable(
                    context, R.drawable.ic_reject_foreground
                )
            )

            val params = layoutParams as ConstraintLayout.LayoutParams
            params.startToStart = ConstraintSet.GONE
            params.endToEnd = ConstraintSet.PARENT_ID
        }
    }
}

@BindingAdapter("setAcceptTextVisibility")
fun TextView.setAcceptTextVisibility(item: DbData) {
    when (item.selection) {
        "reject" -> visibility = View.GONE
        "accept" -> {
            visibility = View.VISIBLE
            text = context.getString(R.string.profile_accepted)
        }
        "Action_Pending" -> {
            text = context.getString(R.string.accept)
            visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("setRejectTextVisibility")
fun TextView.setRejectTextVisibility(item: DbData) {
    when (item.selection) {
        "accept" -> visibility = View.GONE
        "reject" -> {
            visibility = View.VISIBLE
            text = context.getString(R.string.profile_rejected)
        }
        "Action_Pending" -> {
            text = context.getString(R.string.reject)
            visibility = View.VISIBLE
        }
    }
}