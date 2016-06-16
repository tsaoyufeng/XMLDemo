package com.tsao.parsexml;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


//自定义Adapter
public class MyAdapter extends BaseAdapter {
	
	//声明一个bean对象数组作为数据源，一个bean对象包含了一个条目需要的数据
	private List<Note> mList;
	//声明一个LayoutInflater，用于将布局文件转换为一个View
	private LayoutInflater inflater;
	
	//构造函数，用于实例化Adapter
	public MyAdapter(Context context,List<Note> list){
		
        mList = list;
        
        //
        inflater = LayoutInflater.from(context);
	}
        
	
	
	//条目数
	@Override
	public int getCount() {
		return mList.size();
	}
	//获取条目
	@Override
	public Object getItem(int arg0) {
		return null;
	}
	//获取条目Id
	@Override
	public long getItemId(int arg0) {
		return 0;
	}
	/*
	 * 获取展示指定位置数据的View,这个View会作为listView的一个条目
	 * 三个参数：
	 * 1.该view要显示的数据在数据集中的位置
	 * 2.复用的view
	 * 3.该view的父布局
	 * */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//通过LayoutInflater将布局文件转换成一个View
        View view = inflater.inflate(R.layout.item,null);
        //找到布局中的控件
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        TextView content = (TextView) view.findViewById(R.id.tv_content);

        Note bean = mList.get(position);
        //填充数据
        //imageView.setImageResource(bean.itemImageResid);
        title.setText(bean.getTitle());
        content.setText(bean.getBody());
		return view;
	}

}
