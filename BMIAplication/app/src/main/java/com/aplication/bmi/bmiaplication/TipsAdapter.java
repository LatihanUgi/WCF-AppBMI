package com.aplication.bmi.bmiaplication;

/**
 * Created by Ugi Ispoyo Widodo on 6/3/2016.
 */
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TipsAdapter extends BaseAdapter {
    private Context mContext;
    private List<Tips> mlistinfo;

    public TipsAdapter(Context c, List<Tips> l) {
        mContext = c;
        mlistinfo = l;

    }

    public int getCount() {
        return mlistinfo.size();
    }

    public Object getItem(int pos) {
        return mlistinfo.get(pos);
    }

    public long getItemId(int pos) {
        return pos;
    }

    public View getView(int pos, View convertView, ViewGroup parent) {
        Tips event = mlistinfo.get(pos);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.tips_event, null);
        }

        // set name
        TextView tvJudul = (TextView) convertView.findViewById(R.id.tvJudul);
        tvJudul.setText(event.getJudul());

        // set almt
        TextView tvTanggal = (TextView) convertView
                .findViewById(R.id.tvTanggal);
        tvTanggal.setText(event.getTanggal());

        // set web
        TextView tvid_artikel = (TextView) convertView
                .findViewById(R.id.tvIDArtikel);
        tvid_artikel.setText(event.getid_artikel());

        return convertView;
    }
}