package com.templete.project.ui.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.lib.base.ui.activity.BaseActivity;
import com.lib.base.ui.activity.GrassFreshActivity;
import com.lib.base.ui.activity.SunFreshActivity;
import com.lib.base.ui.fragment.BaseFragment;
import com.lib.base.util.PermissionUtil;
import com.lib.base.util.intent.IntentData;
import com.templete.project.bean.PBean;
import com.templete.project.databinding.ThirdFragmentBinding;
import com.templete.project.ui.activity.FlexBoxActivity;
import com.templete.project.ui.activity.HorizontalFreshActivity;
import com.templete.project.ui.activity.MultipleElementActivity;
import com.templete.project.ui.activity.OneElementActivity;
import com.templete.project.ui.activity.SelectorActivity;
import com.templete.project.ui.activity.SheetActivity;
import com.templete.project.ui.activity.TargetActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * PackageName  com.templete.project.ui.fragment
 * ProjectName  TempleteProject
 * Date         10/10/21.
 *
 * @author chenxiaowu
 */

public class ThirdFragment extends BaseFragment<ThirdFragmentBinding> {
    @Override
    public ThirdFragmentBinding viewBinding(LayoutInflater inflater, ViewGroup container) {
        return ThirdFragmentBinding.inflate(getLayoutInflater(), container, false);
    }

    @Override
    public void inits() {

    }

    @Override
    public void initView() {
    }

    @Override
    public void initEvent() {
        setOnClickListener(v -> {
                    Class clazz = null;
                    if (v.equals(mViewBinding.tv1)) {
                        clazz = FlexBoxActivity.class;
                    } else if (v.equals(mViewBinding.tv2)) {
                        clazz = SheetActivity.class;
                    } else if (v.equals(mViewBinding.tv3)) {
                        checkPermission();
                    } else if (v.equals(mViewBinding.tv4)) {
                        ArrayList<Integer> list1 = new ArrayList<>();
                        list1.add(1);
                        list1.add(2);
                        list1.add(3);

                        ArrayList<PBean> list2 = new ArrayList<>();
                        list2.add(new PBean("张三1"));
                        list2.add(new PBean("张三2"));
                        list2.add(new PBean("张三3"));

                        PBean pBean = new PBean("张三");

                        getBaseActivity().startAty(requireActivity(), TargetActivity.class,
                                IntentData.put("key1", 1),
                                IntentData.put("key2", "1"),
                                IntentData.put("key3", new int[]{1, 2, 3}),
                                IntentData.put("key4", pBean),
                                IntentData.putListInt("key5", list1),
                                IntentData.putListPar("key6", list2)
                        );
                    } else if (v.equals(mViewBinding.tv5)) {
                        clazz = OneElementActivity.class;
                    } else if (v.equals(mViewBinding.tv6)) {
                        clazz = MultipleElementActivity.class;
                    }else if (v.equals(mViewBinding.tv7)) {
                        clazz = GrassFreshActivity.class;
                    } else if (v.equals(mViewBinding.tv8)) {
                        clazz = SunFreshActivity.class;
                    } else if (v.equals(mViewBinding.tv9)) {
                        clazz = HorizontalFreshActivity.class;
                    }
                    if (clazz != null) {
                        ((BaseActivity) requireActivity()).startAty(requireActivity(), clazz);
                    }
                },
                mViewBinding.tv1, mViewBinding.tv2,
                mViewBinding.tv3, mViewBinding.tv4,
                mViewBinding.tv5, mViewBinding.tv6,
                mViewBinding.tv7, mViewBinding.tv8,
                mViewBinding.tv9);
    }

    private void checkPermission() {
        //clazz = CameraActivity.class;
        String[] arr = {Permission.WRITE_EXTERNAL_STORAGE, Permission.CAMERA, Permission.RECORD_AUDIO};
        boolean hasPermission = PermissionUtil.isGranted(getContext(), arr);
        if (hasPermission) {
            logD(TAG, "permission ok1");
            ((BaseActivity) requireActivity()).startAty(requireActivity(), SelectorActivity.class);
        } else {
            PermissionUtil.requestPermission(getContext(), new OnPermissionCallback() {
                @Override
                public void onGranted(List<String> permissions, boolean all) {
                    if (all) {
                        logD(TAG, "permission ok2");
                        getBaseActivity().startAty(requireActivity(), SelectorActivity.class);
                    } else {
                        toast("获取部分权限成功，但部分权限未正常授予");
                    }
                }

                @Override
                public void onDenied(List<String> permissions, boolean never) {
                    // 如果是被永久拒绝就跳转到应用权限系统设置页面
                    if (never) {
                        toast("被永久拒绝授权，请手动授予权限");
                        PermissionUtil.startPermissionActivity(getContext(), arr);
                    } else {
                        toast("获取权限失败");
                    }
                }
            }, arr);
        }
    }

    @Override
    public void initData() {
    }
}
