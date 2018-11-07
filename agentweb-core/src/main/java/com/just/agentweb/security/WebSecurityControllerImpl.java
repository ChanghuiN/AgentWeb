/*
 * Copyright (C)  Justson(https://github.com/Justson/AgentWeb)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.just.agentweb.security;

import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.webkit.WebView;

import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.LogUtils;


/**
 * @author cenxiaozhong
 */
public class WebSecurityControllerImpl implements WebSecurityController {

    private static final String TAG = "WebSecurityControllerIm";

    private WebView mWebView;
    private ArrayMap<String, Object> mMap;
    private SecurityType mSecurityType;

    public WebSecurityControllerImpl(WebView view, ArrayMap<String, Object> map, SecurityType securityType) {
        this.mWebView = view;
        this.mMap = map;
        this.mSecurityType = securityType;
    }

    @Override
    public void check() {
        LogUtils.i(TAG, "check --- ");

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            if (Build.VERSION_CODES.HONEYCOMB > Build.VERSION.SDK_INT || Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return;
            }
            mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
            mWebView.removeJavascriptInterface("accessibility");
            mWebView.removeJavascriptInterface("accessibilityTraversal");
        }

        if (mMap != null && mSecurityType == SecurityType.STRICT_CHECK && !mMap.isEmpty()
                && AgentWebConfig.WEBVIEW_TYPE != AgentWebConfig.WEBVIEW_AGENTWEB_SAFE_TYPE
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            LogUtils.e(TAG, "Give up all inject objects");
            mMap.clear();
            mMap = null;
            System.gc();
        }
    }

}
