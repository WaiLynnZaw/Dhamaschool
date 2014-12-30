package group.ripple.dhamaschool;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.psdev.licensesdialog.LicensesDialog;

/**
 * Created by WaiLynnZaw on 12/14/14.
 */
public class About_Us_Fragment extends Fragment implements View.OnClickListener {


   @InjectView(R.id.facebook)
    ImageView ivFb;
    @InjectView(R.id.mail)
    ImageView ivEmail;
    @InjectView(R.id.osl)
    TextView osl;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about_us_layout,container,false);
        ButterKnife.inject(this,v);
        ivFb.setOnClickListener(this);
        ivEmail.setOnClickListener(this);
        osl.setClickable(true);
        osl.setOnClickListener(this);


           // osl.setClickable(true);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.facebook:
                startActivity(new Intent(getActivity(),WebViewActivity.class));
                break;
            case R.id.mail:


                Utils.SendEmail(getActivity(), Constant.e_mail);
                break;
            case R.id.osl:
                new LicensesDialog.Builder(getActivity()).setNotices(R.raw.notices).build().show();
                break;
        }
    }
}
