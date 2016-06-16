package com.tsao.parsexml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

//操作XML的类
public class XmlParse {
	
	//解析XML的方法
	public static List<Note> parseXml(InputStream in) throws XmlPullParserException, IOException{
		
		System.out.println("parseXml(InputStream in)");
		//获取XML解析器
		XmlPullParser parse = Xml.newPullParser();
		//设置解析器要解析的内容
		parse.setInput(in, null);
		//解析数据，可以直接将调用的方法的逻辑写在这儿，这里我们将这段逻辑写成一个方法
		return readChannel(parse);
	}
	
	//
	private static List<Note> readChannel(XmlPullParser parse) throws XmlPullParserException, IOException{
		
		System.out.println("readChannel(XmlPullParser parse)");
		
		List<Note> notes = null;
		Note note = null;
		
		//获取事件类型
		int type = parse.getEventType();
		//开始解析
		while(type != XmlPullParser.END_DOCUMENT){
			switch(type){
			case XmlPullParser.START_TAG:
				if("channel".equals(parse.getName())){
					//创建一个对象数组
					notes = new ArrayList<Note>();
				}else if("note".equals(parse.getName())){
					//创建一个note对象
					note = new Note();
				}else if("title".equals(parse.getName())){
					note.setTitle(parse.nextText());
				}else if("body".equals(parse.getName())){
					note.setBody(parse.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				if("note".equals(parse.getName())){
					//将note对象插入notes数组
					notes.add(note);
				}
				break;
			}
			
			//不停的向下解析
			type = parse.next();
			
		}
		
		return notes;
	}	

}
