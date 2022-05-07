package me.kzaman.chat_app.utils

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.kzaman.chat_app.R


fun ImageView.loadImage(uri: String) {
    val option = RequestOptions().placeholder(R.drawable.img_avatar).error(R.drawable.img_avatar)
    Glide.with(this.context).setDefaultRequestOptions(option).load(uri).into(this)
}

fun hideSoftKeyboard(context: Context, mEtSearch: EditText) {
    mEtSearch.clearFocus()
    val inputMethod = (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
    inputMethod.hideSoftInputFromWindow(mEtSearch.windowToken, 0)
}

/**
 * start next fragment
 * passing value one fragment to next fragment
 * execute actionId wise
 */
fun goToNextFragment(actionId: Int, mView: View, bundle: Bundle?) {
    bundle?.let {
        Navigation.findNavController(mView).navigate(actionId, bundle)
    } ?: run {
        Navigation.findNavController(mView).navigate(actionId)
    }
}