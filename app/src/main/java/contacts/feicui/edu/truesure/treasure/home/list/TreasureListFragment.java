package contacts.feicui.edu.truesure.treasure.home.list;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import contacts.feicui.edu.truesure.R;
import contacts.feicui.edu.truesure.treasure.TreasureRepo;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;

/**
 * 列表模式宝藏
 *
 * listView
 * TreasureRepo ----
 *
 * 5.0后,出的 RecylerView控件
 *
 * RecylerView控件 封装了ViewHolder,
 * LayoutManager,设定LAYOUT方式
 * 动画 (item,layout)
 * 对指定位置刷新
 */
public class TreasureListFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private TreasureListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(container.getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        mRecyclerView.setItemAnimator(new SlideInDownAnimator());
        mRecyclerView.setBackgroundResource(R.drawable.screen_bg);
        return mRecyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new TreasureListAdapter();
        mRecyclerView.setAdapter(adapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addItems(TreasureRepo.getInstance().getTreasure());
            }
        },50);
    }
}
