package redwolf.com.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MeiZiDetailAct extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView;
    private TextView textView;

    private MeiZi meiZi;


    public static void actionStart(Context context, MeiZi fruit) {
        Intent intent = new Intent(context, MeiZiDetailAct.class);
        intent.putExtra("MEI_ZI", fruit);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_meizi_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //
        imageView = (ImageView) findViewById(R.id.image);
        textView = (TextView) findViewById(R.id.text);
        //
        meiZi = (MeiZi) getIntent().getSerializableExtra("MEI_ZI");
        if (meiZi == null) {
            return;
        }
        Glide.with(this).load(meiZi.getMeiZiId()).into(imageView);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            stringBuilder.append(meiZi.getMeiZiName() + "_ _" + i + "_ _");
        }
        textView.setText(stringBuilder);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "点我干嘛?", Snackbar.LENGTH_SHORT).setAction("点我点我", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(v, "然而你点我也没用!!(You滑删除~)", Snackbar.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
