package com.example.wlwlxgg.mygridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2016/12/27.
 */

public class NewGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Test> otherList;
    /*当前类*/
    private ArrayList<Test> list;
    private int tag;

    public NewGridViewAdapter(Context context, ArrayList<Test> otherList, ArrayList<Test> list, int tag) {
        this.mContext = context;
        this.list = list;
        this.tag = tag;
        this.otherList = otherList;
    }


    @Override
    public int getCount() {
        return otherList.size();
    }

    @Override
    public Object getItem(int position) {
        return otherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder viewHolder = null;
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(mContext, R.layout.activity_application_all_item, null);
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) view.findViewById(R.id.image_view_grid_view);
            viewHolder.delete = (ImageView) view.findViewById(R.id.delete_application);
            viewHolder.selected = (ImageView) view.findViewById(R.id.selected_application);
            viewHolder.add = (ImageView) view.findViewById(R.id.add_application);
            viewHolder.tv = (TextView) view.findViewById(R.id.text_view_grid_view);
            view.setTag(viewHolder);
        }
        if (tag == 1) {
            viewHolder.add.setVisibility(View.INVISIBLE);
            viewHolder.delete.setVisibility(View.INVISIBLE);
            viewHolder.selected.setVisibility(View.INVISIBLE);
            viewHolder.iv.setBackgroundResource(otherList.get(position).getImgId());
            viewHolder.tv.setText(otherList.get(position).getTitle());
        } else {
            viewHolder.iv.setBackgroundResource(otherList.get(position).getImgId());
            viewHolder.tv.setText(otherList.get(position).getTitle());
            /**
             * 如果可以修改，则要去判断是否在常用类
             * 1、如果当前是常用类里的可修改项目则显示-
             * 2、如果当前是非常用类的项目
             *  a.如果当前项目在常用类里也存在，则当前项目显示>
             *  b.如果当前项目不在常用类，则显示+
             */
            if (otherList.get(position).getCanModify().equals("1")) {
                if (otherList.get(position).getType().equals("11")) {
                    viewHolder.delete.setVisibility(View.VISIBLE);
                    viewHolder.add.setVisibility(View.INVISIBLE);
                    viewHolder.selected.setVisibility(View.INVISIBLE);
                } else {
                    boolean b = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getTitle().equals(otherList.get(position).getTitle())) {
                            b = true;
                        }
                    }
                    if (b) {
                        viewHolder.selected.setVisibility(View.VISIBLE);
                        viewHolder.add.setVisibility(View.INVISIBLE);
                        viewHolder.delete.setVisibility(View.INVISIBLE);
                    } else {
                        viewHolder.add.setVisibility(View.VISIBLE);
                        viewHolder.delete.setVisibility(View.INVISIBLE);
                        viewHolder.selected.setVisibility(View.INVISIBLE);
                    }
                }
            }else {
                viewHolder.add.setVisibility(View.INVISIBLE);
                viewHolder.delete.setVisibility(View.INVISIBLE);
                viewHolder.selected.setVisibility(View.INVISIBLE);
            }

        }
        return view;
    }
    class ViewHolder {
        public ImageView iv, delete, selected, add;
        public TextView tv;
    }
}

