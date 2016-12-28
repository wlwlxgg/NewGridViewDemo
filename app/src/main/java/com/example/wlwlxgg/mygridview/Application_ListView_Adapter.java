package com.example.wlwlxgg.mygridview;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wlwlxgg on 2016/12/27.
 */

public class Application_ListView_Adapter extends BaseAdapter {
    private Context mContex;
    private ArrayList<ArrayList<Test>> otherList;
    private ArrayList<Test> list;
    private List<ApplicationData> data;
    private Handler mHandler;

    public Application_ListView_Adapter(Context context, ArrayList<ArrayList<Test>> otherList, ArrayList<Test> list, List<ApplicationData> data, final Handler mHandler) {
        this.mContex = context;
        this.otherList = otherList;
        this.list = list;
        this.data = data;
        this.mHandler = mHandler;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler;
        if(convertView == null) {
            convertView = View.inflate(mContex, R.layout.activity_applicationall_listitem, null);
            viewHoler = new ViewHoler();
            convertView.setTag(viewHoler);
        }
        else {
            viewHoler = (ViewHoler) convertView.getTag();
        }
        final int p = position;
        viewHoler.textView = (TextView) convertView.findViewById(R.id.application_textview);
        viewHoler.myGridView = (MyGridView) convertView.findViewById(R.id.application_gridview);
        if (p == 0) {
            viewHoler.textView.setText(list.get(0).getTypeName());
            viewHoler.adapter = new NewGridViewAdapter(mContex, list, list, data.get(p).getTag());
            viewHoler.myGridView.setAdapter(viewHoler.adapter);
            viewHoler.myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Test url;
                    switch (data.get(p).getFlag()) {
                        case 3:
                            if (list.get(position).getCanModify().equals("1")) {
                                list.remove(position);
                            }
                            myNotify();
                            break;
                        case 4:
                            boolean b = false;
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getTitle().equals(otherList.get(p).get(position).getTitle())) {
                                    b = true;
                                }
                            }
                            if (!b) {
                                Test test = new Test();
                                DataCopy(test, otherList.get(p).get(position));
                                test.setType("11");
                                list.add(test);
                            }
                            myNotify();
                            break;
                    }
                }
            });
        } else {
            viewHoler.textView.setText(otherList.get(p - 1).get(0).getTypeName());
            viewHoler.adapter = new NewGridViewAdapter(mContex, otherList.get(p - 1), list, data.get(p).getTag());
            viewHoler.myGridView.setAdapter(viewHoler.adapter);
            viewHoler.myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Test url;
                    switch (data.get(p).getFlag()) {
                        case 3:
                            if (list.get(position).getCanModify().equals("1")) {
                                list.remove(position);
                            }
                            myNotify();
                            break;
                        case 4:
                            boolean b = false;
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getTitle().equals(otherList.get(p - 1).get(position).getTitle())) {
                                    b = true;
                                }
                            }
                            if (!b) {
                                Test test = new Test();
                                DataCopy(test, otherList.get(p - 1).get(position));
                                test.setType("11");
                                list.add(test);
                            }
                            myNotify();
                            break;
                    }
                }
            });
        }
        viewHoler.adapter.notifyDataSetChanged();
        return convertView;
    }

    class ViewHoler {
        private TextView textView;
        private MyGridView myGridView;
        private NewGridViewAdapter adapter;

    }

    public void myNotify() {
        this.notifyDataSetChanged();
    }

    private void DataCopy(Test t1, Test t2) {
        t1.setType(t2.getType());
        t1.setCanModify(t2.getCanModify());
        t1.setImgId(t2.getImgId());
        t1.setTitle(t2.getTitle());
        t1.setTypeName(t2.getTypeName());
    }
}
