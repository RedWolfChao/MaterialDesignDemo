package redwolf.com.materialdesigndemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MeiZiListAct extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private RecyclerView mRecyclerView;
    private MeiZiAdapter mAdapter;
    private GridLayoutManager mManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private CoordinatorLayout mCoordinatorLayout;

    private MeiZi[] meiZis = {
            new MeiZi("R.drawable.icon1", R.drawable.icon1), new MeiZi("R.drawable.icon2", R.drawable.icon2),
            new MeiZi("R.drawable.icon3", R.drawable.icon3), new MeiZi("R.drawable.icon4", R.drawable.icon4),
            new MeiZi("R.drawable.icon5", R.drawable.icon5), new MeiZi("R.drawable.icon6", R.drawable.icon6),
            new MeiZi("R.drawable.icon7", R.drawable.icon7), new MeiZi("R.drawable.icon8", R.drawable.icon8),
            new MeiZi("R.drawable.icon9", R.drawable.icon9), new MeiZi("R.drawable.icon10", R.drawable.icon10),
            new MeiZi("R.drawable.icon11", R.drawable.icon11), new MeiZi("R.drawable.icon12", R.drawable.icon12),
            new MeiZi("R.drawable.icon13", R.drawable.icon_h)
    };
    private List<MeiZi> meiZiList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_meizi_list);
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //  使ActionBar左侧的按钮默认可见 是一个←
            actionBar.setDisplayHomeAsUpEnabled(true);
            //  修改图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_home);
        }
        //
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.design_navigation_view);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        initMeiZi(false);
        mManager = new GridLayoutManager(this, 2);
        mAdapter = new MeiZiAdapter(meiZiList, this);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter.setOnItemClickListener(new MeiZiAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v) {
                MeiZiDetailAct.actionStart(MeiZiListAct.this, meiZiList.get(mRecyclerView.getChildAdapterPosition(v)));
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //  newState RecyclerView的滑动状态
                //  0 滑动停止
                //  1 屏幕没停且手在屏幕上
                //  2 屏幕没停 惯性滑动
                //  当滑动停止并且屏幕最后一个可见的Item的pos+3大于等于ItemCount的时候 下载更多
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mManager.findLastVisibleItemPosition() + 3 >= mManager.getItemCount()) {
                    initMeiZi(true);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        //  轮询颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initMeiZi(false);
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_nav_1:
                    case R.id.menu_nav_2:
                        mDrawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });
    }

    private void initMeiZi(boolean isLoadMore) {
        if (!isLoadMore) {
            meiZiList.clear();
        }
        for (int i = 0; i < 25; i++) {
            Random random = new Random();
            int index = random.nextInt(meiZis.length);
            meiZiList.add(meiZis[index]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
