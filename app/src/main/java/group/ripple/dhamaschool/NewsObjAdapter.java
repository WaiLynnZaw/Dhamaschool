package group.ripple.dhamaschool;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.StringTokenizer;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewsObjAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<NewsObj> al;
    ArrayList<String> al3 = new ArrayList<String>();
    String substr;

    public NewsObjAdapter(Context ctx, ArrayList<NewsObj> al) {
        super();
        this.ctx = ctx;
        this.al = al;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        final ViewHolder vh;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_view_item, null);
            vh = new ViewHolder(convertView);
            // for clean title;
            String clean_title = al.get(position).getContent();
            clean_title = clean_title.replace("&#8230;", "");
            clean_title = clean_title.replace("&#8220;", "");
            clean_title = clean_title.replace("&#8221;", "");
            clean_title = clean_title.replace("&#8211;", "");
            clean_title = clean_title.replace("&#x2018;", "\'");
            clean_title = clean_title.replace("&#x2019;", "\'");
            clean_title = clean_title.replace("&#x2019;s", "\'s");
            clean_title = clean_title.replace("&#x2013;", "-");
            clean_title = clean_title.replace("&#x2014;", "-");
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.title.setTypeface(Utils.mm(ctx));
        vh.detail.setTypeface(Utils.mm(ctx));


        vh.title.setText(Html.fromHtml(al.get(position).getContent().replace((char) 65532, (char) 32), new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                // TODO Auto-generated method stub

                Ion.with(ctx)
                        .load(source)
                        .withBitmap()
                        .placeholder(R.drawable.dhammaschool_background_white)
                        .error(R.drawable.dhammaschool_background_white)
                        .intoImageView(vh.image);
                return null;
            }
        }, null));


        String mydes = Html.fromHtml(al.get(position).getContent()).toString();
        StringTokenizer st3 = new StringTokenizer(mydes, "><");
        while (st3.hasMoreTokens()) {

            al3.add(st3.nextToken());
        }

        mydes = al3.get(0).toString();
        mydes.replaceAll("<img", " ");
        mydes.replaceAll("<a", " ");
        String[] words = mydes.split("<img");
        ArrayList<String> list = new ArrayList<String>();
        for (String string : words) {
            if (!list.contains(string)) {
                list.add(string);
            }
        }

        try {
            substr = mydes.substring(0, mydes.indexOf(String.valueOf((char) 65532)));
            vh.detail.setText(substr);
        } catch (Exception e) {
            e.printStackTrace();
            vh.detail.setVisibility(View.GONE);

        }
        al3.clear();

        return convertView;

    }




   public static class ViewHolder{
        @InjectView(R.id.listItemTitle)
        TextView title;
        @InjectView(R.id.txtSummery)
        TextView detail;
        @InjectView(R.id.imgThumb)
        ImageView image;

       public ViewHolder(View view) {
           ButterKnife.inject(this, view);
       }

    }

}