package com.bigo.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigo.activity.R;


public class MainPageAdapter extends BaseAdapter 
{
    private final Context context;
    private final List<Map<String, Object>> arrList;

    public MainPageAdapter(Context c, List<Map<String, Object>> list) 
    {
        context = c;
        arrList = list;
    }

    @Override
	public int getCount() {
        return arrList.size();
    }

    @Override
	public Object getItem(int position) {
        return position;
    }

    @Override
	public long getItemId(int position) {
        return position;
    }
    
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if (view == null) {
			view = inflater.inflate(R.layout.column_even, null); 
		}
		
		final Map<String,Object> map = arrList.get(position);
		
		final OnClickListener listener = new OnClickListener() {//Declare listener
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, (Class<?>)map.get("Activity"));
				context.startActivity(intent);			
			}
		};
		view.setOnClickListener(listener);
		
		// ColImage
		ImageView imageView = (ImageView) view.findViewById(R.id.ColImgID);
		imageView.getLayoutParams().height = 100;
		imageView.getLayoutParams().width = 100;
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		
		try {
			int imageId = (Integer)(map.get("ImageID"));//get from @drawable/
			imageView.setImageDrawable(view.getResources().getDrawable(imageId));
		} catch (Exception e) {
			// When Error
			imageView.setImageResource(android.R.drawable.ic_menu_report_image);
		}

		// Detail Column
		TextView txtDetail = (TextView) view.findViewById(R.id.ColDesc);
		txtDetail.setPadding(50, 0, 0, 0);
		txtDetail.setText( map.get("ImageDesc").toString() );
		
		return view;
	}

} 