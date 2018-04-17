package com.example.ppx;

/*
 * import android.os.Bundle; import android.app.Activity; import android.view.Menu; import
 * android.widget.ArrayAdapter; import android.widget.ListView; import java.util.ArrayList; import
 * java.util.List; public class Film extends Activity { private List<Movie> movieList = new
 * ArrayList<Movie>();
 * @Override protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState); setContentView(R.layout.film); initMovies(); // 初始化水果数据
 * MovieAdapter adapter = new MovieAdapter(Film.this, R.layout.movie_item, movieList); ListView
 * listView = (ListView) findViewById(R.id.list_view); listView.setAdapter(adapter); } private void
 * initMovies() { Movie apple = new Movie("Apple", R.drawable.film1,"不喝酒啊不喝酒不厚道",1);
 * movieList.add(apple); Movie banana = new Movie("Banana", R.drawable.film1,"不喝酒啊不喝酒不厚道",1);
 * movieList.add(banana);
 *//*
    * Movie orange = new Movie("Orange", R.drawable.film1,"不喝酒啊不喝酒不厚道"); movieList.add(orange);
    * Movie watermelon = new Movie("Watermelon", R.drawable.film1,"不喝酒啊不喝酒不厚道");
    * movieList.add(watermelon); Movie pear = new Movie("Pear", R.drawable.film1,"不喝酒啊不喝酒不厚道");
    * movieList.add(pear); Movie grape = new Movie("Grape", R.drawable.film1,"不喝酒啊不喝酒不厚道");
    * movieList.add(grape); Movie pineapple = new Movie("Pineapple", R.drawable.film1,"不喝酒啊不喝酒不厚道");
    * movieList.add(pineapple); Movie strawberry = new Movie("Strawberry",
    * R.drawable.film1,"不喝酒啊不喝酒不厚道"); movieList.add(strawberry); Movie cherry = new Movie("Cherry",
    * R.drawable.film1,"不喝酒啊不喝酒不厚道"); movieList.add(cherry); Movie mango = new Movie("Mango",
    * R.drawable.film1,"不喝酒啊不喝酒不厚道"); movieList.add(mango);
    *//*
       * }
       * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the menu; this adds
       * items to the action bar if it is present. getMenuInflater().inflate(R.menu.main, menu);
       * return true; } }
       */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class Film extends Activity
{
    private Context mcontent=null;
    /** Called when the activity is first created. */
    private List<Map<String, Object>> mData;
    private int flag;

    public static int image[]=new int[]
    { R.drawable.m1, R.drawable.m9, R.drawable.m3, R.drawable.m4, R.drawable.m5, R.drawable.m6, R.drawable.m7, R.drawable.m8, R.drawable.m9, R.drawable.m9 };
    public static String title[]=new String[]
    { "一号厅", "二号厅", "三号厅", "四号厅", "巨幕厅", "激光厅", "杜比声效厅", "Vip体验厅", "迷你厅？", "至尊厅" };
    public static String info[]=new String[]
    { "一号厅可容纳400人，它...", "二号厅可容纳500人，它...", "三号厅可容纳600人，它...", "四号厅可容纳400人，它...", "巨幕厅可容纳800人，它...", "激光厅可容纳400人，它...", "杜比声效厅是我们专门为...", "VIP体验厅是我们专门为...", "迷你厅是我们专门为...", "至尊厅厅是我们专门为...", "杜比声效厅是我们专门为..." };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film);
        mData=getData();
        ListView listView=(ListView) findViewById(R.id.listView);
        MyAdapter adapter=new MyAdapter(this);
        listView.setAdapter(adapter);
        /* 点击加号跳转至添加演出厅页面 */
        ImageView img=(ImageView) findViewById(R.id.add);
        img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Film.this, Add.class);
                startActivity(intent);
            }
        });
        /* 点击 我的 跳转至 个人中心页面 My */
        RadioButton rdb=(RadioButton) findViewById(R.id.my);
        rdb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(Film.this, My.class);
                startActivity(intent);
            }
        });
    }

    // 获取动态数组数据 可以由其他地方传来(json等)
    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
        for(int i=0; i < title.length; i++)
        {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("image", image[i]);
            map.put("title", title[i]);
            map.put("info", info[i]);
            list.add(map);
        }

        return list;
    }

    public class MyAdapter extends BaseAdapter
    {

        private LayoutInflater mInflater;

        public MyAdapter(Context context)
        {
            this.mInflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            // TODO Auto-generated method stub
            return 0;
        }

        // ****************************************final方法
        // 注意原本getView方法中的int position变量是非final的，现在改为final
        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder=null;
            if(convertView == null)
            {

                holder=new ViewHolder();
                // 可以理解为从vlist获取view 之后把view返回给ListView
                convertView=mInflater.inflate(R.layout.vlist, null);
                holder.imageView=(ImageView) convertView.findViewById(R.id.sItemIcon);
                holder.title=(TextView) convertView.findViewById(R.id.title);
                holder.info=(TextView) convertView.findViewById(R.id.info);
                holder.viewBtn=(Button) convertView.findViewById(R.id.view_btn);
                convertView.setTag(holder);
            }
            else
            {
                holder=(ViewHolder) convertView.getTag();
            }
            Resources resources=getApplicationContext().getResources();
            Drawable drawable=resources.getDrawable(Integer.parseInt(mData.get(position).get("image").toString()));
            holder.imageView.setImageDrawable(drawable);
            holder.title.setText((String) mData.get(position).get("title"));
            holder.info.setText((String) mData.get(position).get("info"));
            holder.viewBtn.setTag(position);
            // 给Button添加单击事件 添加Button之后ListView将失去焦点 需要的直接把Button的焦点去掉
            holder.viewBtn.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    showInfo(position);
                }
            });

            // holder.viewBtn.setOnClickListener(MyListener(position));

            return convertView;
        }
    }

    // 提取出来方便点
    public final class ViewHolder
    {
        public ImageView imageView;
        public TextView title;
        public TextView info;
        public Button viewBtn;
    }

    public void showInfo(final int position)
    {

        ImageView img=new ImageView(Film.this);
        img.setImageResource(R.drawable.film1);
        new AlertDialog.Builder(this).setTitle("确认删除该演出厅么？").setMessage("信息：" + title[position] + "   简介:" + info[position]).setPositiveButton("确定", new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                // TODO 自动生成的方法存根
                ImageView show=(ImageView) findViewById(R.id.sItemIcon);
                TextView show1=(TextView) findViewById(R.id.title);
                TextView show2=(TextView) findViewById(R.id.info);
                Button show3=(Button) findViewById(R.id.view_btn);
                Button show4=(Button) findViewById(R.id.view_btn1);
                show.setVisibility(View.GONE);
                show1.setVisibility(View.GONE);
                show2.setVisibility(View.GONE);
                show3.setVisibility(View.GONE);
                show4.setVisibility(View.GONE);

            }

        }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
