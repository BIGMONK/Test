package com.uto.djf.test;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.uto.djf.test.utils.ToastUtils;

import butterknife.BindView;

public class ExpandableListViewActivity extends BaseActivity {


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
    protected int getLayoutId() {
        return R.layout.activity_expandablelistview;
    }

    @Override
    protected void initView() {

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
                    convertView = View.inflate(getApplicationContext(), R.layout.item_group_expandablelistview, null);
                    groupViewHolder = new GroupViewHolder(convertView);
                    convertView.setTag(groupViewHolder);
                } else {
                    groupViewHolder = (GroupViewHolder) convertView.getTag();
                }
                if (isExpanded){
                    groupViewHolder.imageViewArrow.setImageResource(R.drawable.arrow_down);
                }else{
                    groupViewHolder.imageViewArrow.setImageResource(R.drawable.arrow_right);
                }
                groupViewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
                groupViewHolder.textView.setText(getGroup(groupPosition).toString());
                return convertView;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

                ChildViewHolder childViewHolder;
                if (convertView == null) {
                    convertView = View.inflate(getApplicationContext(), R.layout.item_child_expandablelistview, null);
                    childViewHolder = new ChildViewHolder(convertView);
                    convertView.setTag(childViewHolder);
                } else {
                    childViewHolder = (ChildViewHolder) convertView.getTag();
                }
                childViewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
                childViewHolder.textView.setText(getChild(groupPosition, childPosition).toString());
                return convertView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }

        };

        elv.setAdapter(adapter);
        //去掉左边的箭头
        elv.setGroupIndicator(null);
        //展开所有group
//        int groupCount = elv.getCount();
//        for (int i=0; i<groupCount; i++) {
//            elv.expandGroup(i);
//        };


    }

    class GroupViewHolder {
        ImageView imageView;
        ImageView imageViewArrow;
        TextView textView;

        public GroupViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.iv_group_item);
            imageViewArrow = (ImageView) view.findViewById(R.id.iv_arrow);
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
    protected void initListener() {
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                ToastUtils.SimpleToast(ExpandableListViewActivity.this, "你点击了" + adapter.getChild(groupPosition, childPosition));
                return false;
            }
        });
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                ToastUtils.SimpleToast(ExpandableListViewActivity.this, "你点击了" + adapter.getGroup(groupPosition));
                return false;
            }
        });

        //打开一个关闭其他的
        elv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < elv.getCount(); i++) {
                    if (groupPosition != i) {
                        elv.collapseGroup(i);
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

}
