package io.simsim.iptv.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.commitNow
import io.simsim.iptv.databinding.ActivityBaseFragmentHostBinding

abstract class BaseFragmentHostActivity(private val fragmentClassName: String) : BaseActivity() {
    override val binding: ActivityBaseFragmentHostBinding by viewBinding()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: let {
            val fragment =
                supportFragmentManager.fragmentFactory.instantiate(classLoader, fragmentClassName)
            supportFragmentManager.commitNow {
                replace(binding.fragmentContainer.id, fragment)
            }
        }
    }
}
