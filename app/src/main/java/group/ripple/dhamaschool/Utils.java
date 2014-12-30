package group.ripple.dhamaschool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;

import com.afollestad.materialdialogs.MaterialDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YMT on 12/8/2014.
 */
public class Utils {
    public static Typeface mm(Context cmx) {
        return Typeface.createFromAsset(cmx.getAssets(),"fonts/SmartZawgyiPro.ttf");
    }


    public static CharSequence DateConverterFB(String date) {
        // TODO Auto-generated method stub

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss+hh:mm");
        Date result_date = null;
        try {
            result_date = dt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String final_date_reslut = df.format(result_date);

        return final_date_reslut;
    }

    public static void SendEmail(Context context,String email_address){



        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:" + email_address));
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getText(R.string.app_name));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email_address });
        intent.setType("message/rfc822");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void PhoneCall(Context c, String number) {
        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:" + number));
        c.startActivity(call);
    }



}
