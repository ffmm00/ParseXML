package com.fm_example.parsexml;

import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text1 = (TextView) findViewById(R.id.text1);

        //text1.setText(parseNonoichi());

        text1.setText(parseSabae());
    }

    public String parseNonoichi() {
        StringBuilder stringBuilder = new StringBuilder();

        AssetManager assetManager = getResources().getAssets();

        try {
            InputStream is = assetManager.open("refuge_nonoichi.xml");
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inputStreamReader);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String tag = parser.getName();
                        if ("marker".equals(tag)) {
                            stringBuilder.append(parser.getAttributeValue(null, "title"));
                            stringBuilder.append(",");
                            stringBuilder.append(parser.getAttributeValue(null, "lat"));
                            stringBuilder.append(",");
                            stringBuilder.append(parser.getAttributeValue(null, "lng"));
                            stringBuilder.append(",");
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = parser.getName();
                        if ("marker".equals(endTag)) {
                            stringBuilder.append("\n");
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String parseSabae() {
        StringBuilder stringBuilder = new StringBuilder();
        String type = "";
        String name = "";
        String lat = "";
        String lng = "";

        AssetManager assetManager = getResources().getAssets();

        try {
            InputStream is = assetManager.open("refuge_sabae.xml");
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inputStreamReader);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String tag = parser.getName();
                        if ("type".equals(tag)) {
                            type = parser.nextText();
                        } else if ("name".equals(tag)) {
                            name = parser.nextText();
                        } else if ("latitude".equals(tag)) {
                            lat = parser.nextText();
                        }else if("longitude".equals(tag)){
                            lng=parser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = parser.getName();
                        if ("refuge".equals(endTag)) {
                            stringBuilder.append(name);
                            stringBuilder.append(" ");
                            stringBuilder.append(type);
                            stringBuilder.append(" ");
                            stringBuilder.append(lat);
                            stringBuilder.append(" ");
                            stringBuilder.append(lng);
                            stringBuilder.append("\n");
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
