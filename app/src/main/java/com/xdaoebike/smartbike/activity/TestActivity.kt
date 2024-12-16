package com.xdaoebike.smartbike.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.xdaoebike.smartbike.databinding.ActivityTestBinding
import com.xdaoebike.smartbike.viewmodel.TestViewModel

class TestActivity : BaseActivity<TestViewModel, ActivityTestBinding>() {

    override fun getViewBinding(): ActivityTestBinding {
        return ActivityTestBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.test.setOnClickListener {
            mViewModel.test(binding.edit.text.toString())
        }
    }

    override fun initEvent() {
        super.initEvent()
        mViewModel.testSuccess.observe(this, Observer {
            showToast(it.data.toString())
        })
    }
}