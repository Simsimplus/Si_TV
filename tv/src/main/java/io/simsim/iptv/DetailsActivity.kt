package io.simsim.iptv

import io.simsim.iptv.base.BaseFragmentHostActivity

/**
 * Details activity class that loads [VideoDetailsFragment] class.
 */
class DetailsActivity : BaseFragmentHostActivity(TvWatchFragment::class.java.name) {
    companion object {
        const val SHARED_ELEMENT_NAME = "hero"
        const val MOVIE = "Movie"
    }
}
