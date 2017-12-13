package geon.follow_app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import geon.follow_app.R;
import geon.follow_app.vo.GoodsItem;

import java.util.ArrayList;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<GoodsItem> goodsItems;
    private View.OnClickListener onClickListener;

    public GoodsListAdapter(Context context, ArrayList<GoodsItem> goodsItems, View.OnClickListener onClickListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.goodsItems = goodsItems;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.goods_list_item, parent, false);

        return new ViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvBrand.setText(goodsItems.get(position).getBrand());
        holder.tvGoodsName.setText(goodsItems.get(position).getName());
        holder.tvBuyCount.setText(goodsItems.get(position).getCount() + "명 구매");
    }

    @Override
    public int getItemCount() {
        return goodsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llParent)
        LinearLayout llParent;
        @BindView(R.id.tvBrand)
        TextView tvBrand;
        @BindView(R.id.tvGoodsName)
        TextView tvGoodsName;
        @BindView(R.id.tvBuyCount)
        TextView tvBuyCount;

        public ViewHolder(View itemView, View.OnClickListener onClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            llParent.setOnClickListener(onClickListener);
        }
    }
}
