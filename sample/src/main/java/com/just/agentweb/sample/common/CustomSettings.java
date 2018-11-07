package com.just.agentweb.sample.common;

import android.app.Activity;
import android.os.Build;
import android.webkit.DownloadListener;
import android.webkit.WebView;

//import com.just.agentweb.client.DefaultAgentWebSettings;
import com.just.agentweb.client.DefaultAgentWebSettings;
import com.just.agentweb.client.IAgentWebSettings;
import com.just.agentweb.client.WebListenerManager;
import com.just.agentweb.download.DefaultDownloadImpl;

/**
 * Created by cenxiaozhong on 2017/5/26.
 * source code  https://github.com/Justson/AgentWeb
 */
//WebDefaultSettingsManager 重命名为 DefaultAgentWebSettings 并且抽象出bindAgentWebSupport方法
public class CustomSettings extends DefaultAgentWebSettings {
    public CustomSettings() {
        super();
    }

//    @Override
//    protected void bindAgentWebSupport(AgentWeb agentWeb) {
//
//    }


    @Override
    public IAgentWebSettings toSetting(WebView webView) {
        super.toSetting(webView);

        getWebSettings().setBlockNetworkImage(false);//是否阻塞加载网络图片  协议http or https
        getWebSettings().setAllowFileAccess(false); //允许加载本地文件html  file协议, 这可能会造成不安全 , 建议重写关闭
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getWebSettings().setAllowFileAccessFromFileURLs(false); //通过 file mUrl 加载的 Javascript 读取其他的本地文件 .建议关闭
            getWebSettings().setAllowUniversalAccessFromFileURLs(false);//允许通过 file mUrl 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
        }
        getWebSettings().setNeedInitialFocus(true);
        getWebSettings().setDefaultTextEncodingName("gb2312");//设置编码格式
        getWebSettings().setDefaultFontSize(16);
        getWebSettings().setMinimumFontSize(12);//设置 WebView 支持的最小字体大小，默认为 8
        getWebSettings().setGeolocationEnabled(true);
        getWebSettings().setUserAgentString(getWebSettings().getUserAgentString().concat("agentweb/3.1.0"));
        return this;
    }

    @Override
    public WebListenerManager setDownloader(WebView webView, DownloadListener downloadListener) {
        return super.setDownloader(webView,
                DefaultDownloadImpl.create((Activity) webView.getContext()
                        , webView, null,
                        null, mAgentWeb.getPermissionInterceptor()));
    }
}
