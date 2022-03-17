package com.templete.project.ui.activity;

import android.annotation.SuppressLint;
import android.os.Build;

import com.lib.base.glide.GlideUtil;
import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.databinding.ElementTargetActivityBinding;

/**
 * 「深坑提醒」
 * 有时从RecyclerView界面进入到详情页，由于详情页加载延迟，可能出现没有效果。例如ImageView从网络加载图片，可能A界面到B界面没效果，B回到A界面有效果。
 * 解决步骤：
 * 1.在setContentView后添加下面代码，延迟加载过渡动画。
 * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
 * postponeEnterTransition()
 * }
 * 2.在共享元素视图加载完毕，或者图片加载完毕后调用下面代码,开始加载过渡动画。
 * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
 * startPostponedEnterTransition()
 * }
 * 3.例如:
 * Glide.with(mContext)
 * .asBitmap()
 * .load(value?.avatar ?: "")
 * .listener(object : RequestListener<Bitmap> {
 * override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
 * animatorCallback?.invoke()//回调开始加载过渡动画
 * return false
 * }
 * <p>
 * override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
 * animatorCallback?.invoke()//回调开始加载过渡动画
 * return false
 * }
 * })
 * .apply(RequestOptions.circleCropTransform())
 * .placeholder(R.mipmap.ic_default)
 * .error(R.mipmap.ic_default)
 * .into(authorBinding!!.ivAvatar)
 * <p>
 * shareElement.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
 * override fun onPreDraw(): Boolean {
 * shareElement!!.viewTreeObserver.removeOnPreDrawListener(this)
 * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
 * animatorCallback?.invoke()
 * }
 * return true
 * }
 * })
 * PackageName  com.templete.project.ui.activity
 * ProjectName  TempleteProject-java
 * @author      xwchen
 * Date         2/13/22.
 */

public class ElementTargetActivity extends BaseActivity<ElementTargetActivityBinding> {
    public static final String TAG = "ElementTargetActivity";
    private int type;

    @Override
    protected ElementTargetActivityBinding viewBinding() {
        return ElementTargetActivityBinding.inflate(getLayoutInflater());
    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    public void initS() {
        setTitleStr("B activity");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            type = ANIM_NO;
        } else {
            type = ANIM_NORMAL;
        }
    }

    @Override
    public void initView() {
        String url1 = "http://images0.zaijiawan.com/nanjing/huazhuangdaquan/2/5-1.jpg@!16app_video_list";
        GlideUtil.loadPic(this, url1, mViewBinding.iv);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {

    }

    /**
     * 关闭finish动画,不然共享动画也会失效
     *
     * @return
     */
    @Override
    public int finishAnimType() {
        return type;
    }
}
