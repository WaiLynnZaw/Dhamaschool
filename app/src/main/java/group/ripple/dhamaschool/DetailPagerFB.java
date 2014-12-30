package group.ripple.dhamaschool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DetailPagerFB extends PagerAdapter {

	private ArrayList<NewsObj> list;
	private Context context;
	ArrayList<String>al3 = new ArrayList<String>();
	private View v;
	protected File cacheDir;

	String substr;

	public DetailPagerFB(Context context, ArrayList<NewsObj> list) {
		this.context = context;
		this.list = list;

	}

	@Override
	public Object instantiateItem(View container, final int position) {
		// TODO Auto-generated method stub

	LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.viewpager_detail_item, null);


		final ImageView iv = (ImageView) v.findViewById(R.id.imgDetail);

		TextView title = (TextView) v.findViewById(R.id.txtDetailTitle);
		title.setTypeface(Utils.mm(context));

		TextView des = (TextView) v.findViewById(R.id.txtDetailDetail);
		des.setTypeface(Utils.mm(context));

		TextView caption = (TextView) v.findViewById(R.id.detail_caption);
		caption.setTypeface(Utils.mm(context));

		final TextView author = (TextView) v.findViewById(R.id.txtDetailAuthor);
		author.setTypeface(Utils.mm(context));

		TextView date = (TextView) v.findViewById(R.id.detail_date_tv);
		date.setTypeface(Utils.mm(context));

        LinearLayout divider = (LinearLayout) v.findViewById(R.id.divider);

        title.setText(Html.fromHtml(list.get(position).getContent().replace((char) 65532, (char) 32)));

        author.setText("DhamaSchool Foundation");

        date.setText(Utils.DateConverterFB(list.get(position).getUpdated()));

        caption.setVisibility(View.GONE);

        caption.setText(Html.fromHtml(list.get(position).getContent().toString(), new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                // TODO Auto-generated method stub
                Log.e("Img source", source);
                //imageLoader.displayImage(source, iv);

                return null;
            }
        }, null));


		String clean_title = list.get(position).getContent();
		clean_title = clean_title.replace("&#8230;", "");
		clean_title = clean_title.replace("&#8220;", "");
		clean_title = clean_title.replace("&#8221;", "");
		clean_title = clean_title.replace("&#8211;", "");
		clean_title = clean_title.replace("&#x2018;","\'");
		clean_title = clean_title.replace("&#x2019;", "\'");
		clean_title = clean_title.replace("&#x2019;s", "\'s");
		clean_title = clean_title.replace("&#x2013;","-");
		clean_title = clean_title.replace("&#x2014;","-");


		if (list.get(position).getContent() != null
				&& !list.get(position).getContent().equalsIgnoreCase("")) {
			divider.setVisibility(View.VISIBLE);
		} else {
			divider.setVisibility(View.GONE);
		}




		String mydes = Html.fromHtml(list.get(position).getContent()).toString();

		StringTokenizer st3 = new StringTokenizer(mydes,"><");
		while(st3.hasMoreTokens()){

			al3.add(st3.nextToken());
	    }

		mydes = al3.get(0).toString();
		mydes.replaceAll("<img", " ");
		mydes.replaceAll("<a", " ");
		String [] words = mydes.split("<img");
		ArrayList<String> list = new ArrayList<String>();
		for (String string : words) {
			if(!list.contains(string)){
				list.add(string);
			}
		}

	try{	substr = mydes.substring(0,mydes.indexOf(String.valueOf((char)65532)));
		des.setText(substr);
	}catch (Exception e)	{
		e.printStackTrace();
		des.setVisibility(View.GONE);
	/*	des.setText(Html.fromHtml(list.get(position).getContent()));
		iv.setVisibility(View.GONE);
		title.setText(Html.fromHtml(list.get(position).getSummary_text()));
		divider.setVisibility(View.GONE);*/
	}
		al3.clear();
	    v.setTag("" + position);
		((ViewPager) container).addView((View) v, 0);
    	return v;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == ((View) object);
	}

	@Override
	public void destroyItem(View collection, int position, Object view) {

	((ViewPager) collection).removeView((View) view);

	}



}
