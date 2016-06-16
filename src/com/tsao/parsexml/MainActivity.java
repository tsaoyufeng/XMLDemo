package com.tsao.parsexml;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView mTitle;
	private List<Note> noteList;
	static final String link = "http://192.168.1.105:8080/Chat/xmltest.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DownloadXmlTask load = new DownloadXmlTask();
        load.execute(link);
        System.out.println("onCreate load.execute");
    }


    
    
    private class DownloadXmlTask extends AsyncTask<String, Integer, List<Note>>{

		@Override
		protected List<Note> doInBackground(String... arg0) {
			
			System.out.println("doInBackground");
			
			//下载数据http://192.168.1.105:8080/Chat/xmltest.xml
			String address = arg0[0];
			try {
				URL url = new URL(address);
				URLConnection connection = url.openConnection();
				InputStream is = connection.getInputStream();
				noteList = XmlParse.parseXml(is);
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return noteList;
		}

		@Override
		protected void onPostExecute(List<Note> result) {
			super.onPostExecute(result);
			
			System.out.println("onPostExecute");
			//更新UI
			ListView listview = (ListView) findViewById(R.id.lv_list);
			MyAdapter myAdapter = new MyAdapter(MainActivity.this,result);
			listview.setAdapter(myAdapter);
		}
		
    }
    
    	
    
}
