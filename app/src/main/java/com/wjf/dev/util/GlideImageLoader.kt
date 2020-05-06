package com.wjf.dev.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.loader.ImageLoader

/**
 *       Created by Cathu on 2019/4/30 11:01
 */
class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        Glide.with(context).load(path).apply(requestOptions).into(imageView)
    }
}