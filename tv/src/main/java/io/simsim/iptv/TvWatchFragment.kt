package io.simsim.iptv

import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import androidx.core.net.toUri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import io.simsim.iptv.base.BaseFragment
import io.simsim.iptv.databinding.FragmentWatchBinding
import io.simsim.iptv.utils.M3u8Item

class TvWatchFragment : BaseFragment() {
    override val binding: FragmentWatchBinding by viewBinding()
    private val playerView by lazy {
        binding.playerView
    }
    private val player by lazy {
        ExoPlayer.Builder(requireContext()).build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerView.player = player
        val url = arguments?.getParcelable<M3u8Item>(DetailsActivity.MOVIE)?.url ?: let {
            requireActivity().finish()
            return
        }
        when {
//            url.endsWith("m3u8")->{
//                HlsMediaSource.Factory(DefaultDataSourceFactory(requireContext(), "exoplayer-codelab"))
//            }
            URLUtil.isNetworkUrl(url) -> {
                MediaItem.fromUri(url.toUri()).let {
                    player.setMediaItem(it)
                }
                player.prepare()
                player.playWhenReady = true
            }
            url.startsWith("rtmp") -> {
                ProgressiveMediaSource.Factory(RtmpDataSource.Factory()).createMediaSource(
                    MediaItem.fromUri(url.toUri())
                ).let {
                    player.setMediaSource(it)
                }
                player.prepare()
                player.playWhenReady = true
            }
        }


    }
}
