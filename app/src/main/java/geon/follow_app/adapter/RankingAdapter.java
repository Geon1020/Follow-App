package geon.follow_app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import geon.follow_app.R;
import geon.follow_app.vo.RankingItem;

import java.util.ArrayList;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<RankingItem> rankingItems;

    public RankingAdapter (Context context, ArrayList<RankingItem> rankingItems) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.rankingItems = rankingItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.ranking_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvRank.setText(rankingItems.get(position).getRank() + "");
        holder.tvName.setText("ID : " + rankingItems.get(position).getName());
        holder.tvWalk.setText(((position + 1) * 12345) + " 걸음");
    }

    @Override
    public int getItemCount() {
        return rankingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvRank)
        TextView tvRank;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvWalk)
        TextView tvWalk;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
