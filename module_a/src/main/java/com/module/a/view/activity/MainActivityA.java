package com.module.a.view.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lib.base.aroute.ArouteConfig;
import com.lib.base.bean.User;
import com.lib.base.ui.activity.BaseActivity;
import com.module.a.databinding.MaActivityMainBinding;

import io.reactivex.rxjava3.disposables.Disposable;

@Route(path = ArouteConfig.ACTIVITY_MAIN_A/*, group =RouteConfig.GROUP_A*/)
public class MainActivityA extends BaseActivity<MaActivityMainBinding> {
    public static final String TAG = "sssss";
    //    @Autowired
    public int age;
    //    @Autowired
    public String name;
    //    @Autowired
    public User user;
    private Disposable disposable;

    @Override
    public void initS() {
        setTitleStr("module a");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initData() {
       /* Single
                .create((SingleOnSubscribe<Integer>) emitter -> {
                    Thread.sleep(4000);
                    emitter.onSuccess(7);
                })
                .compose(RxUtils::toSimpleSingle)
                .doOnSubscribe(this::addDisposable)
                .doOnSuccess(integer -> DebugUtil.toast(MainActivity.this, "number is " + integer))
                .doOnError(throwable -> DebugUtil.toast(MainActivity.this, "error msg=" + throwable.getMessage()))
                .subscribe();*/
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ma_activity_main);
//        ARouter.getInstance().inject(this);
//        DebugUtil.logD(TAG, "age=" + age + ",name=" + name + ",user=" + user.toString());
//        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ARouter
//                        .getInstance()
//                        .build(RouteConfig.ACTIVITY_MAIN_B)
//                        .navigation();
//            }
//        });
//        DebugUtil.toast("发顺丰大是大非");
        Toast.makeText(App.getContext(), "发顺丰大是大非", Toast.LENGTH_SHORT).show();
//        ModuleAApp.getModuleAApp().toast();
//        GlobalViewModel globalViewModel = ModuleAApp.getContext().getGlobalViewModel();
//        DebugUtil.logD(TAG,"globalViewModel="+(globalViewModel==null));
//        new Thread(() -> {
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        Single
                .create((SingleOnSubscribe<Integer>) emitter -> {

                })
                .compose(RxUtils::toSimpleSingle)
                .doOnSubscribe(disposable -> {

                })
                .doOnSuccess(integer -> {

                })
                .doOnError(throwable -> {

                })
                .subscribe();

    }*/

    @Override
    public MaActivityMainBinding viewBinding() {
        return MaActivityMainBinding.inflate(getLayoutInflater());
    }
}