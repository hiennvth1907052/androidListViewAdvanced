package com.example.listviewadvanced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnChildItemClick {
    private List<ContactModel> listContact = new ArrayList<>();
    private ListView lvContact;
    private ContactAdapter mAdapter;
    private ImageView ivUser;
    private TextView tvName;
    private String[] permissions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

        mAdapter = new ContactAdapter(this, listContact);
        mAdapter.registerChildItemClick(this);
        lvContact.setAdapter(mAdapter);
        permissions = new String[]{
                Manifest.permission.CALL_PHONE
        };
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, permissions,1);
        }
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContactModel model = listContact.get(i);
                Toast.makeText(MainActivity.this, model.getName() + ": " + model.getPhone(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(){
        lvContact = (ListView) findViewById(R.id.lvContact);
        ivUser = (ImageView) findViewById(R.id.ivUser);
        tvName = (TextView) findViewById(R.id.tvName);
    }
    private void initData(){
        listContact.add(new ContactModel("Trần Trường Sơn", "0399492033", R.drawable.ic_avatar1));
        listContact.add(new ContactModel("Trần Thị Bình", "0988111333", R.drawable.ic_avatar2));
        listContact.add(new ContactModel("Hồ Văn Dũng", "0988111444", R.drawable.ic_avatar3));
        listContact.add(new ContactModel("Đào Thị Tiên", "0988111555", R.drawable.ic_avatar4));
        listContact.add(new ContactModel("Ngô Sĩ Kiên", "0999888822", R.drawable.ic_avatar3));
        listContact.add(new ContactModel("Nguyễn Quang Thọ", "0988888888", R.drawable.ic_avatar1));
        listContact.add(new ContactModel("Giang Thiên Hương", "0922222222", R.drawable.ic_avatar4));
        listContact.add(new ContactModel("Phùng Văn Quế", "0988111111", R.drawable.ic_avatar2));
        listContact.add(new ContactModel("Mai Tiến Dũng", "0988666666", R.drawable.ic_avatar1));
        listContact.add(new ContactModel("Đinh Mai Hương", "0977777777", R.drawable.ic_avatar4));
        listContact.add(new ContactModel("Lỗ Tấn Tài", "0987654321", R.drawable.ic_avatar2));
        listContact.add(new ContactModel("Hà Tiên sinh", "0911223344", R.drawable.ic_avatar1));
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mAdapter.unRegisterChildItemClick();
    }

    @Override
    public void onItemChildClick(int position) {
        ContactModel contact = listContact.get(position);
        ivUser.setImageResource(contact.getImage());
        tvName.setText(contact.getName());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Calling Permission is granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Calling Permission is denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}