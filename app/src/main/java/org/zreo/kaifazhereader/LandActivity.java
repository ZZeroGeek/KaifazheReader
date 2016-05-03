package org.zreo.kaifazhereader;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 85789 on 2016/4/27.
 */
public class LandActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    private ListView landList;
    List<SaveLandWay> list;
    private Button backButton;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_select);
        landList= (ListView) findViewById(R.id.landList);
        getList();
        landList.setAdapter(new ListAdapter());
        backButton= (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        landList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SaveLandWay saveLandWay=list.get(position);
                final ProgressDialog progressDialog=new ProgressDialog(LandActivity.this);
                progressDialog.setTitle("正在授权中...");
                progressDialog.setCancelable(false);
                progressDialog.show();
               Thread t = new Thread(new Runnable() {
                    public void run() {
                      try {
                          Thread.sleep(3000);//让他显示10秒后，取消ProgressDialog
                           } catch (InterruptedException e) {
                          // TODO Auto-generated catch block
                          e.printStackTrace();
                         }
                       progressDialog.dismiss();
                       SharedPreferences sp=getSharedPreferences("flag",MODE_PRIVATE);
                        SharedPreferences.Editor ed=sp.edit();
                        ed.putBoolean("flag",true);
                        ed.commit();
                        String flagNew="intent to 2";
                        intent =new Intent(LandActivity.this,MainActivity.class);
                        intent.putExtra("intent to 2",flagNew);
                        startActivity(intent);
                        }
                    });
              t.start();
            }
        });
        readAccount();
        intent=new Intent(LandActivity.this,MainActivity.class);
    }
    public void getList(){
        list=new ArrayList<SaveLandWay>();
        list.add(new SaveLandWay("GitHub",R.drawable.login_ic_github));
        list.add(new SaveLandWay("新浪微博",R.drawable.login_ic_weibo));
        list.add(new SaveLandWay("QQ",R.drawable.login_ic_qq));
        list.add(new SaveLandWay("使用邮箱登录/注册",R.drawable.ic_mail_grey600_24dp));
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
    public void readAccount(){
        SharedPreferences sharedPreferences=getSharedPreferences("flag",MODE_PRIVATE);
        SharedPreferences.Editor ed=sharedPreferences.edit();
        ed.putBoolean("flag",true);

    }

    class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SaveLandWay saveLandWay=list.get(position);
            View view =null;
            ViewHolder viewHolder;
            if(convertView==null){
                view=View.inflate(LandActivity.this,R.layout.login_select_list,null);
                viewHolder=new ViewHolder();
                viewHolder.landImage=(ImageView)view.findViewById(R.id.landImage);
                viewHolder.landWay= (TextView) view.findViewById(R.id.landWay);
                view.setTag(viewHolder);
            }
            else {
                view=convertView;
                viewHolder=(ViewHolder)view.getTag();
            }
            viewHolder.landImage.setImageResource(saveLandWay.getLandImage());
            viewHolder.landWay.setText(saveLandWay.getLandWay());
            return view;
        }
        class ViewHolder {
            ImageView landImage;
            TextView landWay;

        }
    }

}
