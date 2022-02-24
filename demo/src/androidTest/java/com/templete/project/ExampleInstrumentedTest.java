package com.templete.project;

import android.content.Context;

import com.google.gson.JsonSyntaxException;
import com.lib.base.config.App;
import com.lib.base.rxjava.RxUtils;
import com.lib.base.util.DebugUtil;
import com.lib.base.util.GsonUtil;
import com.lib.base.util.JsonDataUtil;
import com.lib.base.util.txt.label.TxtUtil;
import com.module.a.http.HttpUtil;
import com.templete.project.bean.JBean;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    public static final String TAG = "TEST_LOG";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.templete.project", appContext.getPackageName());
    }

    @Test
    public void http() {
        HttpUtil
                .getInstance()
                .login("18557532484", "000000")
                .compose(RxUtils::toSimpleSingle)
                //.doOnSubscribe(this::addDisposable)
                .doOnSuccess(loginBean -> DebugUtil.logD(TAG, "data message=" + GsonUtil.getNoCrashInstance().toJson(loginBean)))
                .doOnError(throwable -> DebugUtil.logD(TAG, "data message=" + throwable))
                .subscribe();
    }

    @Test
    public void json() {
        try {
            String json = JsonDataUtil.getJson(App.getContext(), "app.json");
            JBean jBean = GsonUtil.getNoCrashInstance().fromJson(json, JBean.class);
            DebugUtil.logD(TAG, GsonUtil.getNoCrashInstance().toJson(jBean));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void numberFormat() {
        String twoPoint = TxtUtil.getTwoPoint("1.23435");
        String point = TxtUtil.getPointNo0(1.001);
        String point1 = TxtUtil.getPointNo0("1.1001");
        DebugUtil.logD(TAG, "twoPoint=" + twoPoint + ",point=" + point + ",point1=" + point1);
    }
}