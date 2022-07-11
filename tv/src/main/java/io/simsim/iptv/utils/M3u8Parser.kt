package io.simsim.iptv.utils

import android.net.Uri
import android.os.Parcelable
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser
import kotlinx.parcelize.Parcelize
import java.io.InputStream

object M3u8Parser {
    fun parse(m3uInputStream: InputStream) =
        (HlsPlaylistParser().parse(Uri.EMPTY, m3uInputStream) as HlsMediaPlaylist).run {
            this.segments.zip(this.tags).map {
                M3u8Item(
                    info = M3u8Item.M3u8ItemInfo(name = it.second.substringAfter(",")),
                    url = it.first.url
                )
            }
        }
}

@Parcelize
data class M3u8Item(
    val info: M3u8ItemInfo = M3u8ItemInfo(),
    val url: String = ""
) : Parcelable {
    @Parcelize
    data class M3u8ItemInfo(
        val name: String = "",
        val logoUrl: String = "",
        val groupTitle: String = "",
        val id: String = ""
    ) : Parcelable {
        fun parse(infoString: String?) {

        }
    }
}