package com.moon.libcommon.dialog

import android.content.Context
//import com.google.android.ads.nativetemplates.NativeTemplateStyle
//import com.google.android.gms.ads.AdListener
//import com.google.android.gms.ads.AdLoader
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R
import com.moon.libcommon.http.ApiConfig
import com.moon.libcommon.http.SimpleHttpObserver
//import kotlinx.android.synthetic.main.xpopup_ad.view.*
import timber.log.Timber

class AdDialog constructor(context: Context) : CenterPopupView(context) {
//    var clickListener: (() -> Unit)? = null
//    var ad: UnifiedNativeAd? = null
//    var adListener: MoonAdListener? = null
//    override fun getImplLayoutId(): Int {
//        return R.layout.xpopup_ad
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        var styles = NativeTemplateStyle.Builder().build();
//        my_template.setStyles(styles)
//        my_template.setNativeAd(ad)
//        cancel.setOnClickListener {
//            dismiss()
//            ad?.destroy()
//        }
//    }
//
//    fun loadAd() {
//        var adLoader = AdLoader.Builder(context, ApiConfig.Ad.ADID_SPLASH)
//            .forUnifiedNativeAd { unifiedNativeAd: UnifiedNativeAd ->
//                this.ad = unifiedNativeAd
//                adListener?.onAdLoaded()
//            }
//            .withAdListener(object : AdListener() {
//                override fun onAdFailedToLoad(errorCode: Int) {
//                    super.onAdFailedToLoad(errorCode)
//                    adListener?.onAdFailedToLoad(errorCode)
//                }
//
//                override fun onAdClosed() {
//                    super.onAdClosed()
//                    adListener?.onAdClosed()
//                }
//
//                override fun onAdOpened() {
//                    super.onAdOpened()
//                    adListener?.onAdOpened()
//                }
//            })
//            .build();
//        adLoader.loadAd(AdRequest.Builder().build())
//    }
//
//    interface MoonAdListener {
//        fun onAdFailedToLoad(errorCode: Int)
//        fun onAdClosed()
//        fun onAdOpened()
//        fun onAdLoaded()
//    }
}