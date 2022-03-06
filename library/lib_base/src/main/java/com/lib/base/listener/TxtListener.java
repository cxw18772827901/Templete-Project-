package com.lib.base.listener;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * PackageName  com.hycg.company.utils
 * ProjectName  HYCGCompanyProject
 *@author      xwchen
 * Date         2019/4/17.
 */
public class TxtListener implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        afterChange(editable.toString());
    }

    public void afterChange(String s) {

    }
}
