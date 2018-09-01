package ru.oktemsec.clearwater_admin_2;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import ru.oktemsec.clearwater_admin_2.R;

public class SimpleList extends android.app.ListActivity {
    
	String[] mSign =  {"Овен", "Телец", "Близнецы", "Рак", "Лев", "Дева",
			"Весы", "Скорпион", "Стрелец", "Козерог", "Водолей", "Рыбы"};
	String[] mDate =  {"21 марта - 20 апреля", "21 апреля - 20 мая", "21 мая - 21 июня", 
			"22 июня - 22 июля", "23 июля - 23 августа", "24 августа - 23 сентября", 
			"24 сентября - 23 октября", "24 октября - 22 ноября", "23 ноября - 21 декабря", 
			"22 декабря - 20 января", "21 января - 20 февраля", "21 февраля - 20 марта"};

	myAdapter mAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mAdapter = new myAdapter(this);
        setListAdapter(mAdapter);

    }
    
    public void onListItemClick (ListView parent, View v, int position, long id) {
    	
    	// Всплывающее уведомление
    	LayoutInflater mInflater = getLayoutInflater();
    	View mLayout = mInflater.inflate(R.layout.toast, null);

    	TextView sign = (TextView)mLayout.findViewById(R.id.Sign);
    	sign.setText(mSign[position]);

    	TextView date = (TextView)mLayout.findViewById(R.id.Date);
    	date.setText(mDate[position]);

    	Toast toast = new Toast (getApplicationContext());
    	toast.setDuration(Toast.LENGTH_LONG);

    	toast.setView(mLayout);
    	toast.show();

    	// Уведомление в строке состояния
    	CharSequence Title = "Выбран элемент " + mSign[position];
    	//int icon = mImage[position];
    	
    	Intent notificationIntent = new Intent (this, SimpleList.class);
    	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
    	
    	RemoteViews mRemoteView = new RemoteViews(getPackageName(), R.layout.notify);
    	//mRemoteView.setImageViewResource(R.id.Image, mImage[position]);
    	mRemoteView.setTextViewText(R.id.Sign, mSign[position]);
    	mRemoteView.setTextViewText(R.id.Date, mDate[position]);
    }
    
    public class myAdapter extends BaseAdapter {
		private LayoutInflater mLayoutInflater;
		
		public myAdapter (Context ctx) {
			mLayoutInflater = LayoutInflater.from(ctx);
		}
		
		public int getCount () {
			return mSign.length;
		}
		
		public Object getItem (int position) {
			return position;
		}
		
		public long getItemId (int position) {
			return position;
		}
		
		public String getString (int position) {
			return mSign[position] + " (" + mDate[position] + ")";
		}
		
		public View getView(int position, View convertView, ViewGroup parent) { 

			 if (convertView == null)
				 convertView = mLayoutInflater.inflate(R.layout.item, null);

			 TextView sign = (TextView)convertView.findViewById(R.id.Sign);
			 sign.setText(mSign[position]);

			 TextView date = (TextView)convertView.findViewById(R.id.Date);
			 date.setText(mDate[position]);			 
			 return convertView;
		}
	} // end myAdapter
}