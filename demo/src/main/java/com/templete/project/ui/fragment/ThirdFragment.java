package com.templete.project.ui.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lib.base.ui.activity.BaseActivity;
import com.templete.project.ui.activity.GrassFreshActivity;
import com.templete.project.ui.activity.SunFreshActivity;
import com.lib.base.ui.fragment.BaseFragment;
import com.lib.base.util.intent.IntentData;
import com.templete.project.bean.PBean;
import com.templete.project.databinding.ThirdFragmentBinding;
import com.templete.project.ui.activity.FlexActivity;
import com.templete.project.ui.activity.FlexBoxActivity;
import com.templete.project.ui.activity.HorizontalFreshActivity;
import com.templete.project.ui.activity.MultipleElementActivity;
import com.templete.project.ui.activity.OneElementActivity;
import com.templete.project.ui.activity.SelectorActivity;
import com.templete.project.ui.activity.SheetActivity;
import com.templete.project.ui.activity.TargetActivity;

import java.util.ArrayList;

/**
 * PackageName  com.templete.project.ui.fragment
 * ProjectName  TempleteProject
 * Date         10/10/21.
 *
 * @author xwchen
 */

public class ThirdFragment extends BaseFragment<ThirdFragmentBinding> {
    @Override
    public ThirdFragmentBinding viewBinding(LayoutInflater inflater, ViewGroup container) {
        return ThirdFragmentBinding.inflate(getLayoutInflater(), container, false);
    }

    @Override
    public void initS() {

    }

    @Override
    public void initView() {
    }

    @Override
    public void initEvent() {
        setOnClickListener(v -> {
                    Class<? extends BaseActivity<?>> clazz = null;
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
                        list2.add(new PBean("??????1"));
                        list2.add(new PBean("??????2"));
                        list2.add(new PBean("??????3"));

                        PBean pBean = new PBean("??????");

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
                    } else if (v.equals(mViewBinding.tv7)) {
                        clazz = GrassFreshActivity.class;
                    } else if (v.equals(mViewBinding.tv8)) {
                        clazz = SunFreshActivity.class;
                    } else if (v.equals(mViewBinding.tv9)) {
                        clazz = HorizontalFreshActivity.class;
                    } else if (v.equals(mViewBinding.tv10)) {
                        clazz = FlexActivity.class;
                    }
                    if (clazz != null) {
                        ((BaseActivity<?>) requireActivity()).startAty(requireActivity(), clazz);
                    }
                },
                mViewBinding.tv1, mViewBinding.tv2,
                mViewBinding.tv3, mViewBinding.tv4,
                mViewBinding.tv5, mViewBinding.tv6,
                mViewBinding.tv7, mViewBinding.tv8,
                mViewBinding.tv9, mViewBinding.tv10);
    }

    private void checkPermission() {
        //clazz = CameraActivity.class;
//        String[] arr = {Permission.WRITE_EXTERNAL_STORAGE, Permission.CAMERA, Permission.RECORD_AUDIO};
//        boolean hasPermission = PermissionUtil.isGranted(getContext(), arr);
//        if (hasPermission) {
//            logD(TAG, "permission ok1");
//            ((BaseActivity) requireActivity()).startAty(requireActivity(), SelectorActivity.class);
//        } else {
//            PermissionUtil.requestPermission(getContext(), new OnPermissionCallback() {
//                @Override
//                public void onGranted(List<String> permissions, boolean all) {
//                    if (all) {
//                        logD(TAG, "permission ok2");
        getBaseActivity().startAty(requireActivity(), SelectorActivity.class);
//                    } else {
//                        toast("?????????????????????????????????????????????????????????");
//                    }
//                }
//
//                @Override
//                public void onDenied(List<String> permissions, boolean never) {
//                    // ??????????????????????????????????????????????????????????????????
//                    if (never) {
//                        toast("?????????????????????????????????????????????");
//                        PermissionUtil.startPermissionActivity(getContext(), arr);
//                    } else {
//                        toast("??????????????????");
//                    }
//                }
//            }, arr);
//        }
    }

    @Override
    public void initData() {
    }
}
