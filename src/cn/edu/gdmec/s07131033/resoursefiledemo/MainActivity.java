package cn.edu.gdmec.s07131033.resoursefiledemo;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView mytv1,mytv2;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytv1 = (TextView) findViewById(R.id.textView1);
        mytv2 = (TextView) findViewById(R.id.textView2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void Raw(View v)
    {
    	InputStream is = null;
    	is = getResources().openRawResource(R.raw.raw_file);
    	try {
			byte[]reader = new byte[is.available()];
			while(is.read(reader)!=-1)
			{
				mytv1.setText(new String(reader,"GBK"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void Xml(View v)
    {
    	XmlPullParser xpp = getResources().getXml(R.xml.people);
    	String smg = "";
    	try {
			while(xpp.next()!=XmlPullParser.END_DOCUMENT)
			{
				String nodeName = xpp.getName();
				if(nodeName!=null&&nodeName.equals("person"))
				{
					int attrCount = xpp.getAttributeCount();
					for(int i=0;i<attrCount;i++)
					{
						String attrName = xpp.getAttributeName(i);
						if(attrName!=null&&attrName.equals("name"))
						{
							smg += " 姓名："+xpp.getAttributeValue(i);
						}else if(attrName!=null&&attrName.equals("age"))
						{
							smg += " 年龄："+xpp.getAttributeValue(i);
						}else if(attrName!=null&&attrName.equals("height"))
						{
							smg += " 身高："+xpp.getAttributeValue(i)+"\n";
						}
					}
					
				}
			}
			mytv2.setText(smg);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
}
