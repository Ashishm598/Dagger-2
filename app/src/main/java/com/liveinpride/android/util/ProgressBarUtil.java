package com.liveinpride.android.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

public class ProgressBarUtil {

    private Context mContext;
    private ProgressDialog progressDialog;

    ProgressBarUtil(Context context) {
        this.mContext = context;
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideProgressDialog() {
        progressDialog.dismiss();

    }

}
