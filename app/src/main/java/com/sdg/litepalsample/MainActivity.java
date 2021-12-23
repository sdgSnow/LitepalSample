package com.sdg.litepalsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.sdg.litepalsample.sample.Person;
import com.sdg.litepalsample.sample.TextUtil;

import org.litepal.LitePal;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String tag = "litepalTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDirectoryIfNotExist(getExternalFilesDir("") + "/litepal/");
    }

    public void add(View view) {
        for (int j = 0;j<2;j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    add();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    query();
                }
            }).start();
        }
    }

    public void delete(View view) {
        delete();
    }

    public void update(View view) {

    }

    public void query(View view) {
        query();
    }

    private void add(){
        for (int j =0;j<3;j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String readFile = TextUtil.readFile(getExternalFilesDir("") + "/111.txt");
                    if (!TextUtils.isEmpty(readFile)) {
                        long start = System.currentTimeMillis();
                        int age = 20;
                        Log.i(tag, "开始新增" + "--->"+Thread.currentThread().getName());
                        for (int i = 0; i < 20; i++) {
                            Person person = new Person();
                            person.setName("张三");
                            person.setDesc("张三的描述");
                            person.setAge(age);
                            person.setCla(2);
                            person.setSuperText(TextUtil.readFile(getExternalFilesDir("") + "/111.txt"));
                            person.save();
                        }
                        age++;
                        long end = System.currentTimeMillis();
                        Log.i(tag, "新增完成 = " + (end - start) + "--->"+Thread.currentThread().getName());
                    }
                }
            }).start();
        }
    }

    private void delete(){
        int i = LitePal.deleteAll(Person.class);
        Log.i(tag, "删除 = " + i);
    }

    private void update(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Person person = new Person();
                person.setSuperText("1");
                person.updateAll();
            }
        }).start();
    }

    private void query(){
        synchronized (this) {
            for (int i = 0; i < 3; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(tag, "开始查询" + "--->" + Thread.currentThread().getName());
                        List<Person> personList = LitePal.select().find(Person.class);
                        Log.i(tag, "查询完成 = " + personList.size() + "--->" + Thread.currentThread().getName());
                    }
                }).start();
            }
        }
    }

    public boolean createDirectoryIfNotExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            //首次创建文件夹则记录时间
            if(file.mkdirs()){
                return true;
            }
        }
        return false;
    }
}