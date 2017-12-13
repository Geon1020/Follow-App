package geon.follow_app.popup;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cooltechworks.views.ScratchTextView;
import geon.follow_app.R;

import java.util.Random;

public class ScratchPopup extends Dialog {
    @BindView(R.id.stRandome)
    ScratchTextView stRandome;
    @BindView(R.id.tvComment)
    TextView tvComment;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;

    private int ranNum = 0;

    public ScratchPopup(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.activity_scratch_popup);
        ButterKnife.bind(this);

        tvConfirm.setClickable(false);

        Random random = new Random(System.currentTimeMillis());
        ranNum = Math.abs(random.nextInt(99) + 1);

        if(ranNum > 50) {
            stRandome.setText("당첨 !!");
        } else {
            stRandome.setText("꽝 !!");
        }

        stRandome.setRevealListener(new ScratchTextView.IRevealListener() {
            @Override
            public void onRevealed(ScratchTextView scratchTextView) {

            }

            @Override
            public void onRevealPercentChangedListener(ScratchTextView scratchTextView, float v) {
                if(v > 0.7) {
                    if(ranNum > 50) {
                        tvComment.setText("축하드립니다 !!");
                    } else {
                        tvComment.setText("다음 기회에...");
                    }

                    tvConfirm.setClickable(true);
                }
            }
        });
    }

    @OnClick({R.id.tvComment, R.id.tvConfirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvConfirm:
                dismiss();
                break;
        }
    }
}
