package com.xdaoebike.smartbike.activity

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.xdaoebike.smartbike.viewmodel.BaseViewModel
import com.xdaoebike.smartbike.widget.ProgressDialogUtil
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VM : BaseViewModel<*>, VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var mViewModel: VM
    protected lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        initViewModel()
        initData()
        initView(savedInstanceState)
        loadData()
        initEvent()
        mViewModel.toastLiveData.observe(this, Observer<String> {
            it.apply {
                showToast(it)
            }
        })
        mViewModel.apiLoading.observe(this, Observer<Boolean> {
            if (it) ProgressDialogUtil.showProgressDialog(this) else ProgressDialogUtil.dismiss()
        })

    }

    protected abstract fun getViewBinding(): VB
    private fun initViewModel() {
        var type: Class<VM>? = null
        if (this.javaClass.genericSuperclass != null && this.javaClass.genericSuperclass is ParameterizedType) {
            ((this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>).also {
                type = it
            }
        } else if (this.javaClass.superclass != null && this.javaClass.superclass.genericSuperclass != null && this.javaClass.superclass.genericSuperclass is ParameterizedType) {
            type =
                (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        }
        type?.let {
            mViewModel = ViewModelProvider(this)[it]
            if (::mViewModel.isInitialized) {
            }
        }
    }

    private fun initViewBinding() {

    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    open fun initView(savedInstanceState: Bundle?) {

    }

    open fun initData() {

    }

    open fun loadData() {

    }

    open fun initEvent() {
    }

    override fun getResources(): Resources {
        val resources = super.getResources()
        if (resources.configuration.fontScale != 1f) {
            val configuration = Configuration()
            configuration.setToDefaults()
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
        return resources
    }

}