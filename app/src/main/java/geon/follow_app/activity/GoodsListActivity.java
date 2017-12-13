package geon.follow_app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import geon.follow_app.R;
import geon.follow_app.adapter.GoodsListAdapter;
import geon.follow_app.popup.ScratchPopup;
import geon.follow_app.util.Preference;
import geon.follow_app.vo.GoodsItem;

import java.util.ArrayList;

public class GoodsListActivity extends AppCompatActivity {
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvWalkCount)
    TextView tvWalkCount;
    @BindView(R.id.tvPickCount)
    TextView tvPickCount;
    @BindView(R.id.rvGoodsList)
    RecyclerView rvGoodsList;

    private GoodsListAdapter goodsListAdapter;
    private ArrayList<GoodsItem> goodsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);

        if(TextUtils.isEmpty(Preference.getStringPreference(Preference.PREFS_KEY.WALK_COUNT))) {
            tvWalkCount.setText("0 걸음");
        } else {
            tvWalkCount.setText(Preference.getStringPreference(Preference.PREFS_KEY.WALK_COUNT) + " 걸음");
        }

        goodsItems = new ArrayList<>();
        setData();

        tvPickCount.setText("지금 바로 참여 할 수 있는 상품 " + "(" + goodsItems.size() + ")");

        goodsListAdapter = new GoodsListAdapter(this, goodsItems, new onClickListener());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvGoodsList.setLayoutManager(linearLayoutManager);
        rvGoodsList.setAdapter(goodsListAdapter);
    }

    @OnClick ({R.id.ivBack})
    public void viewClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
        }
    }

    public class onClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.llParent:
                    ScratchPopup scratchPopup = new ScratchPopup(GoodsListActivity.this);
                    scratchPopup.setCanceledOnTouchOutside(false);
                    scratchPopup.setCancelable(false);
                    scratchPopup.show();
                    break;
            }
        }
    }

    private void setData() {
        for(int i = 0; i < 9; i++) {
            GoodsItem goodsItem = new GoodsItem();

            switch (i) {
                case 0:
                    goodsItem.setBrand("CU");
                    goodsItem.setName("광동비타500");
                    goodsItem.setCount("5642315");
                    break;
                case 1:
                    goodsItem.setBrand("CU");
                    goodsItem.setName("바나나맛우유");
                    goodsItem.setCount("8942142");
                    break;
                case 2:
                    goodsItem.setBrand("스타벅스");
                    goodsItem.setName("아메리카노");
                    goodsItem.setCount("894236");
                    break;
                case 3:
                    goodsItem.setBrand("조스떡볶이");
                    goodsItem.setName("죠스떡볶이 1인세트");
                    goodsItem.setCount("35642");
                    break;
                case 4:
                    goodsItem.setBrand("에뛰드하우스");
                    goodsItem.setName("1만원 상품권");
                    goodsItem.setCount("892311");
                    break;
                case 5:
                    goodsItem.setBrand("BHC");
                    goodsItem.setName("뿌링클 치킨");
                    goodsItem.setCount("756521");
                    break;
                case 6:
                    goodsItem.setBrand("메가박스");
                    goodsItem.setName("메가박스 영화 예매권");
                    goodsItem.setCount("620898");
                    break;
                case 7:
                    goodsItem.setBrand("빕스");
                    goodsItem.setName("1인 식사권");
                    goodsItem.setCount("4821235");
                    break;
                case 8:
                    goodsItem.setBrand("도미노피자");
                    goodsItem.setName("페페로니피자M + 콜라");
                    goodsItem.setCount("456136");
                    break;
            }

            goodsItems.add(goodsItem);
        }
    }
}
