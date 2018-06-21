package com.liveinpride.android.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MyCustomWebViewClient extends WebViewClient {

    private Context mContext;
    private Utils utils;

    public MyCustomWebViewClient(Context context, Utils utils) {
        this.mContext = context;
        this.utils = utils;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.loadUrl(request.getUrl().toString());
        } else {
            Toast.makeText(mContext, "Below API level 21", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        utils.showProgressDialog(mContext);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        utils.hideProgressDialog();
    }


}
