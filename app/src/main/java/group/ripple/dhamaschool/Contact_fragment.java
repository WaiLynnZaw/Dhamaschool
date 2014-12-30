package group.ripple.dhamaschool;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WaiLynnZaw on 12/14/14.
 */
public class Contact_fragment extends Fragment implements View.OnClickListener {
    @InjectView(R.id.contact_add)
    TextView tvAddress;
    @InjectView(R.id.contact_title)
    TextView tvTitle;
    @InjectView(R.id.ph1)
    TextView ph1;
    @InjectView(R.id.ph2)
    TextView ph2;
    @InjectView(R.id.ph3)
    TextView ph3;
    @InjectView(R.id.ph4)
    TextView ph4;
    @InjectView(R.id.ph5)
    TextView ph5;
    @InjectView(R.id.ph6)
    TextView ph6;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_contact,container,false);
        ButterKnife.inject(this,v);
        tvTitle.setTypeface(Utils.mm(getActivity()));
        tvAddress.setTypeface(Utils.mm(getActivity()));
        tvTitle.setText("ဆက္သြယ္ရန္လိပ္စာ");
        tvAddress.setText("ျမိတ္က်ားေက်ာင္း၊ ျမိဳ႕မေက်ာင္းလမ္း၊ ဒဂံုျမိဳ႕နယ္၊ ရန္ကုန္ျမိဳ႕။");
        ph1.setClickable(true);
        ph2.setClickable(true);
        ph3.setClickable(true);
        ph4.setClickable(true);
        ph5.setClickable(true);
        ph6.setClickable(true);

        ph1.setTypeface(Utils.mm(getActivity()));
        ph2.setTypeface(Utils.mm(getActivity()));
        ph3.setTypeface(Utils.mm(getActivity()));
        ph4.setTypeface(Utils.mm(getActivity()));
        ph5.setTypeface(Utils.mm(getActivity()));
        ph6.setTypeface(Utils.mm(getActivity()));

        ph1.setOnClickListener(this);
        ph2.setOnClickListener(this);
        ph3.setOnClickListener(this);
        ph4.setOnClickListener(this);
        ph5.setOnClickListener(this);
        ph6.setOnClickListener(this);

        ph1.setText("၀၁-၃၇၇၂၆၇");
        ph2.setText("၀၉-၅၀၉၈၄၆၄");
        ph3.setText("၀၉-၂၅၀၁၆၆၉၈၄");
        ph4.setText("၀၉-၄၄၈၀၂၇၆၁၈");
        ph5.setText("၀၉-၄၂၀၀၀၈၉၀၁");
        ph6.setText("၀၉-၈၅၇၈၄၃၇");

        return v;
    }

    @Override
    public void onClick(View v) {
        String number=null;
        switch(v.getId()){
            case R.id.ph1:
                number = "01377267";
                break;
            case R.id.ph2:
                number = "095098464";
                        break;
            case R.id.ph3:
                number = "09250166984";
                break;
            case R.id.ph4:
                number = "09448027619";
                break;
            case R.id.ph5:
                number = "09420008901";
                break;
            case R.id.ph6:
                number = "098578437";
                break;
        }
        Utils.PhoneCall(getActivity(),number);
    }
}
