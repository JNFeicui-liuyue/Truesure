package contacts.feicui.edu.truesure.treasure.home.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;

import contacts.feicui.edu.truesure.components.TreasureView;
import contacts.feicui.edu.truesure.treasure.Treasure;
import contacts.feicui.edu.truesure.treasure.home.detail.TreasureDetailActivity;

/**
 * Created by liuyue on 2016/7/23.
 */
public class TreasureListAdapter extends RecyclerView.Adapter<TreasureListAdapter.MyViewHolder>{

    private ArrayList<Treasure> datas = new ArrayList<>();

    //添加宝藏数据
    public final void addItems(Collection<Treasure> items){
        if (items != null){
            datas.addAll(items);
            //通知刷新
            notifyItemChanged(0,datas.size());
        }
    }

    // 创建ViewHolder对象
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TreasureView treasureView = new TreasureView(parent.getContext());
        return new MyViewHolder(treasureView);
    }

    // (将数据)绑定到ViewHolder上
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Treasure treasure = datas.get(position);
        holder.mTreasureView.bindTreasure(treasure);
        holder.mTreasureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreasureDetailActivity.open(v.getContext(),treasure);
            }
        });


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static final class MyViewHolder extends RecyclerView.ViewHolder{
        private TreasureView mTreasureView;
        public MyViewHolder(TreasureView itemView) {
            super(itemView);
            this.mTreasureView = itemView;
        }
    }
}
