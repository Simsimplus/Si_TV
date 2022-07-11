package io.simsim.iptv.ui.detail

import android.os.Bundle
import io.simsim.iptv.base.BaseFragmentHostActivity

/**
 * Details activity class that loads [VideoDetailsFragment] class.
 */
class DetailsActivity : BaseFragmentHostActivity(TvWatchFragment::class.java.name) {
    override val initBundle: Bundle? by lazy { intent.extras }

    companion object {
        const val SHARED_ELEMENT_NAME = "hero"
        const val MOVIE = "Movie"
    }
}
