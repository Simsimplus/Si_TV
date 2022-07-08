package io.simsim.iptv.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.google.android.exoplayer2.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : Fragment() {
    abstract val binding: ViewBinding
    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { context: CoroutineContext, exception: Throwable ->
            Log.e(this.javaClass.name, "[error in $context]", exception)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    fun launch(block: suspend CoroutineScope.() -> Unit) =
        (lifecycleScope + coroutineExceptionHandler).launch(block = block)

    inline fun <reified T : ViewBinding> viewBinding(): Lazy<T> = lazy {
        T::class.java.getDeclaredMethod("inflate", LayoutInflater::class.java).invoke(
            null,
            LayoutInflater.from(requireContext())
        ) as T
    }
}
