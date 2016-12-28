package com.example.wlwlxgg.mygridview;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class ApplicationActivityAll  extends Activity implements View.OnClickListener {
    /*返回键*/
    RelativeLayout back;
    /*管理，完成*/
    RelativeLayout manage, complete;
    /*上下文*/
    Context mContext;
    /*ListView*/
    ListView listView;
    /*适配器*/
    Application_ListView_Adapter adapter;
    /*常用服务类数据*/
    ArrayList<Test> list;
    /*标记数据*/
    ArrayList<ApplicationData> listData;
    /*其它类数据*/
    ArrayList<ArrayList<Test>> otherList;
    ArrayList<String> listType;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicationall);
        mContext = this;
        initView();
        initList();
        initData();
    }

    private void initView() {
        back = (RelativeLayout) findViewById(R.id.application_back);
        manage = (RelativeLayout) findViewById(R.id.application_manage);
        complete = (RelativeLayout) findViewById(R.id.application_complete);
        listView = (ListView) findViewById(R.id.application_listview);
    }

    private void initData() {
        manage.setOnClickListener(this);
        complete.setOnClickListener(this);
        back.setOnClickListener(this);
        listData = new ArrayList<>();
        for (int i = 0; i < listType.size(); i++) {
            if (listType.get(i).equals("11")) {
                ApplicationData test = new ApplicationData();
                test.setFlag(1);
                test.setTag(1);
                test.setType(listType.get(i));
                listData.add(test);
            } else {
                ApplicationData test = new ApplicationData();
                test.setFlag(2);
                test.setTag(2);
                test.setType(listType.get(i));
                listData.add(test);
            }
            listData.get(i).setTag(1);
        }
        adapter = new Application_ListView_Adapter(mContext, otherList, list, listData, mHandler);
        listView.setAdapter(adapter);
    }

    /**
     * initList
     */
    private void initList() {
        /**
         * 这个集合我在项目中是从接口拿到的
         * 现在为了展示就先写假数据了
         */
        listType = new ArrayList<>();
        for (int i = 11; i < 20; i++) {
            listType.add(i + "");
        }
        int num = 1;
        list = new ArrayList<>();
        otherList = new ArrayList<>();
        for (int i = 0; i < listType.size(); i++) {
            if (listType.get(i).equals("11")) {
                Test test = new Test();
                test.setCanModify("0");
                test.setTitle(num + "");
                num++;
                test.setType(listType.get(i));
                test.setTypeName(listType.get(i));
                test.setImgId(R.mipmap.application_baoxiao);
                list.add(test);
            } else {
                ArrayList<Test> data = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    Test test = new Test();
                    test.setCanModify("1");
                    test.setTitle(num + "");
                    num++;
                    test.setType(listType.get(i));
                    test.setTypeName(listType.get(i));
                    test.setImgId(R.mipmap.application_baoxiao);
                    data.add(test);
                }
                otherList.add(data);
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.application_back:
                this.finish();
                break;
            case R.id.application_manage:
                manage.setVisibility(View.GONE);
                complete.setVisibility(View.VISIBLE);
                for (int i = 0; i < listData.size(); i++) {
                    if (i == 0) listData.get(i).setFlag(3);
                    else listData.get(i).setFlag(4);
                    listData.get(i).setTag(2);
                }
                //listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
            case R.id.application_complete:
                for (int i = 0; i < listData.size(); i++) {
                    if (i == 0) listData.get(i).setFlag(1);
                    else listData.get(i).setFlag(2);
                    listData.get(i).setTag(1);
                }
                //listView.setAdapter(adapter);
                complete.setVisibility(View.GONE);
                manage.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}

