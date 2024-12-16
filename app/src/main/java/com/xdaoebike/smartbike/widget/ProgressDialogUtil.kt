package com.xdaoebike.smartbike.widget

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onPreShow
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.xdaoebike.smartbike.R
import java.lang.ref.SoftReference

object ProgressDialogUtil {

    @SuppressLint("StaticFieldLeak")
    internal var mDialog: MaterialDialog? = null
    fun isShowDialog(): Boolean {
        if (mDialog == null) return false
        if (mDialog!!.isShowing) return true
        return false
    }

    /**
     * 弹出耗时对话框
     *
     * @param context
     */
    fun showProgressDialog(activity: AppCompatActivity) {
        mDialog = SoftReference(
            MaterialDialog(activity).customView(
                R.layout.custom_progress_dialog_view,
                noVerticalPadding = true
            )
        ).get()
        mDialog!!.cornerRadius(12f)
        mDialog!!.cancelable(false)
        mDialog!!.lifecycleOwner(activity)
        if (activity.isFinishing || activity.isDestroyed) return
        mDialog!!.show()
    }

    fun showProgressDialog(activity: AppCompatActivity, tip: String) {
        mDialog = SoftReference(
            MaterialDialog(activity).customView(
                R.layout.custom_progress_dialog_view,
                noVerticalPadding = true
            )
        ).get()
        mDialog!!.cornerRadius(12f)
        mDialog!!.maxWidth(literal = 450)
        mDialog!!.cancelable(false)
        val tvTip = mDialog!!.findViewById<TextView>(R.id.tvTip)
        if (TextUtils.isEmpty(tip)) {
            tvTip.text = "加载中..."
        } else {
            tvTip.text = tip
        }
        mDialog!!.lifecycleOwner(activity)
        if (activity.isFinishing || activity.isDestroyed) return
        mDialog!!.show {
            onPreShow {
                it.window!!.setLayout(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
            }
        }
    }

    /**
     * 隐藏耗时对话框
     */
    fun dismiss() {
        if (mDialog != null) {
            if (mDialog!!.isShowing) {
                mDialog!!.dismiss()
            }
        }
    }
}
