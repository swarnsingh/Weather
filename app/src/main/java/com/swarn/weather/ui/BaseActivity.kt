package com.swarn.weather.ui

import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.swarn.weather.R

/**
 * @author Swarn Singh.
 */
open class BaseActivity : AppCompatActivity() {

    private var mProgressDialog: ProgressDialog? = null

    protected fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.dismiss()
        }
    }

    protected fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog?.setMessage(getString(R.string.progress_message))
            mProgressDialog?.isIndeterminate = true
        }

        mProgressDialog?.show()
    }

    fun hideSoftKeyboard(view: View) {
        val imm = getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}