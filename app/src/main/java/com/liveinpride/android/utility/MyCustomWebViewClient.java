package com.liveinpride.android.utility;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liveinpride.android.ui.home.HomeActivity;

public class MyCustomWebViewClient extends WebViewClient {

    private final String target_url_prefix = "www.liveinpride.app";
    private Context mContext;
    private Utils utils;
    private Intent mIntent;

    public MyCustomWebViewClient(Context context, Utils utils) {
        this.mContext = context;
        this.utils = utils;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        String host = Uri.parse(url).getHost();

        if (url.startsWith("http:") || url.startsWith("https:")) {

            if (host.equals(target_url_prefix)) {
                if (utils.isConnectedToInternet(mContext)) {
                    view.loadUrl(url);
                } else {
                    ((HomeActivity) mContext).displayNoNetworkLayout();
                }
                return false;
            }

            if (host.contains("m.facebook.com")
                    || host.contains("facebook.co")
                    || host.contains("google.co")
                    || host.contains("www.facebook.com")
                    || host.contains(".google.com")
                    || host.contains(".google.co")
                    || host.contains("accounts.google.com")
                    || host.contains("accounts.google.co.in")
                    || host.contains("www.accounts.google.com")
                    || host.contains("www.twitter.com")
                    || host.contains("secure.payu.in")
                    || host.contains("https://secure.payu.in")
                    || host.contains("oauth.googleusercontent.com")
                    || host.contains("content.googleapis.com")
                    || host.equals("accounts.youtube.com")
                    || host.contains("paytm")
                    || host.contains("ssl.gstatic.com")) {
                return false;
            }

            // Otherwise, the link is not for a page on my site, so launch
            // another Activity that handles URLs
            mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mContext.startActivity(mIntent);
            return true;

        } else if (url.startsWith("tel:")) {
            mIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
            mContext.startActivity(mIntent);
            return true;

        } else if (url.startsWith("mailto:")) {
            mIntent = new Intent(Intent.ACTION_SEND);
            mIntent.setType("application/octet-stream");
            String AdressMail = url.replace("mailto:", "");
            mIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{AdressMail});
            mIntent.putExtra(Intent.EXTRA_SUBJECT, "");
            mIntent.putExtra(Intent.EXTRA_TEXT, "");
            mContext.startActivity(mIntent);
            return true;
        }

        return true;
    }


    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        utils.showProgressDialog(mContext);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        utils.hideProgressDialog();
        ((HomeActivity) mContext).hideSwipeToRefreshLayout();
    }


}
