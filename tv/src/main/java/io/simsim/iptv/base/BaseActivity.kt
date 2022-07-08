package io.simsim.iptv.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.google.android.exoplayer2.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : FragmentActivity() {
    abstract val binding: ViewBinding
    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { context: CoroutineContext, exception: Throwable ->
            Log.e(this.javaClass.name, "[error in $context]", exception)
        }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun launch(block: suspend CoroutineScope.() -> Unit) =
        (lifecycleScope + coroutineExceptionHandler).launch(block = block)

    inline fun <reified T : ViewBinding> viewBinding(): Lazy<T> = lazy {
        T::class.java.getDeclaredMethod("inflate", LayoutInflater::class.java).invoke(
            null,
            layoutInflater
        ) as T
    }
}
