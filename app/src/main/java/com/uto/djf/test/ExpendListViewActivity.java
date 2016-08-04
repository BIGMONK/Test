package com.uto.djf.test;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpendListViewActivity extends BaseActivity {


    @BindView(R.id.elv)
    ExpandableListView elv;

    /**
     * 设置组视图的显示文字
     */
    private String[] generalsTypes = new String[]{"魏", "蜀", "吴"};
    /**
     * 子视图显示文字
     */
    private String[][] generals = new String[][]{
            {"夏侯惇", "甄姬", "许褚", "郭嘉", "司马懿", "杨修"},
            {"马超", "张飞", "刘备", "诸葛亮", "黄月英", "赵云"},
            {"吕蒙", "陆逊", "孙权", "周瑜", "孙尚香"}};
    private ExpandableListAdapter adapter;

    @Override
    int getLayoutId() {
        return R.layout.activity_expendlistview;
    }

    @Override
    void initView() {

        adapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return generalsTypes.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return generals[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return generalsTypes[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return generals[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

                GroupViewHolder groupViewHolder;
                if (convertView == null) {
                    convertView = View.inflate(getApplicationContext(), R.layout.item_group_expendlistview, null);
                    groupViewHolder = new GroupViewHolder(convertView);
                    convertView.setTag(groupViewHolder);
                } else {
                    groupViewHolder = (GroupViewHolder) convertView.getTag();
                }
                groupViewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
                groupViewHolder.textView.setText(getGroup(groupPosition).toString());
                return convertView;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

                ChildViewHolder childViewHolder;
                if (convertView == null) {
                    convertView = View.inflate(getApplicationContext(), R.layout.item_child_expendlistview, null);
                    childViewHolder = new ChildViewHolder(convertView);
                    convertView.setTag(childViewHolder);
                } else {
                    childViewHolder = (ChildViewHolder) convertView.getTag();
                }
                childViewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
                childViewHolder.textView.setText(getChild(groupPosition,childPosition).toString());
                return convertView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };

        elv.setAdapter(adapter);


    }

    class GroupViewHolder {
        ImageView imageView;
        TextView textView;

        public GroupViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.iv_group_item);
            textView = (TextView) view.findViewById(R.id.tv_group_item);
        }
    }

    class ChildViewHolder {
        ImageView imageView;
        TextView textView;

        public ChildViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.iv_child_item);
            textView = (TextView) view.findViewById(R.id.tv_child_item);
        }
    }


    @Override
    void initListener() {
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                ToastUtils.SimpleToast(ExpendListViewActivity.this, "你点击了" + adapter.getChild(groupPosition, childPosition));
                return false;
            }
        });
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                ToastUtils.SimpleToast(ExpendListViewActivity.this, "你点击了" + adapter.getGroup(groupPosition));
                return false;
            }
        });
    }

    @Override
    void initData() {

    }

}
