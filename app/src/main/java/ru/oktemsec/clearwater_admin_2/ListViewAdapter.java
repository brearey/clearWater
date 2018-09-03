package ru.oktemsec.clearwater_admin_2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.oktemsec.clearwater_admin_2.ZakazData;

public class ListViewAdapter extends BaseAdapter {

    Activity activity;
    List<ZakazData> listUsers;
    LayoutInflater inflater;

    public ListViewAdapter(Activity activity, List<ZakazData> listUsers){
        this.activity = activity;
        this.listUsers = listUsers;
    }

    @Override
    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return listUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater = (LayoutInflater) activity
                .getBaseContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item, null);

        TextView txtUser = (TextView) itemView.findViewById(R.id.txt_name);
        TextView txtEmail = (TextView) itemView.findViewById(R.id.txt_email);

        txtUser.setText(listUsers.get(i).getAddress());
        txtEmail.setText(String.valueOf(listUsers.get(i).getPhone()));

        return  itemView;
    }


}
