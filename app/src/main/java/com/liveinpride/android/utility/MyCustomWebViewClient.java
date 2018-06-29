package com.liveinpride.android.utility;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liveinpride.android.ui.home.HomeActivity;

public class MyCustomWebViewClient extends WebViewClient {

    private final String target_url_prefix = "liveinpride.app";
    private Context mContext;
    private Utils utils;

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

        } else if (url.startsWith("tel:")) {
            Intent tel = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
            mContext.startActivity(tel);
            return true;

        } else if (url.startsWith("mailto:")) {
            Intent mail = new Intent(Intent.ACTION_SEND);
            mail.setType("application/octet-stream");
            String AdressMail = url.replace("mailto:", "");
            mail.putExtra(Intent.EXTRA_EMAIL, new String[]{AdressMail});
            mail.putExtra(Intent.EXTRA_SUBJECT, "");
            mail.putExtra(Intent.EXTRA_TEXT, "");
            mContext.startActivity(mail);
            return true;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        mContext.startActivity(intent);

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
