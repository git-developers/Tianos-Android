package xyz.tianos.software.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.tianos.software.rxJava.GitHubClient;
import xyz.tianos.software.rxJava.GitHubRepo;
import xyz.tianos.software.rxJava.GitHubRepoAdapter;
import xyz.tianos.software.activity.implement.IBase;
import xyz.tianos.software.controller.PointOfSaleController;
import xyz.tianos.software.controller.UserController;

public class ApiActivity extends BaseActivity implements IBase {

    private static final String TAG = ApiActivity.class.getSimpleName();
    private GitHubRepoAdapter adapter = new GitHubRepoAdapter();
    private Subscription subscription;
    private ListView listView;
    private UserController userController;
    private PointOfSaleController pointOfSaleController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api);
        toolBar("Api", R.string.app_name);

        initialize();
        getStarredRepos("git-developers");
    }

    private void initialize() {
        userController = new UserController(this);
        pointOfSaleController = new PointOfSaleController(this);
        listView = (ListView) findViewById(R.id.listView);

        final ListView listView = (ListView) findViewById(R.id.list_view_repos);
        listView.setAdapter(adapter);
    }

    private void getStarredRepos(String username) {
        subscription = GitHubClient.getInstance()
                .getStarredRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GitHubRepo>>() {
                    @Override public void onCompleted() {
                        Log.d(TAG, "In onCompleted()");
                    }

                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "In onError()");
                    }

                    @Override public void onNext(List<GitHubRepo> gitHubRepos) {
                        Log.d(TAG, "In onNext()");
                        adapter.setGitHubRepos(gitHubRepos);
                    }
                });
    }

    @Override protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void handleOnResponse(JSONObject jsonOutput) {

    }
}
