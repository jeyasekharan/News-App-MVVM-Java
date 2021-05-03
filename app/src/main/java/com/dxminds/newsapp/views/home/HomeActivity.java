package com.dxminds.newsapp.views.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dxminds.newsapp.R;
import com.dxminds.newsapp.model.ArticlesModel;
import com.dxminds.newsapp.model.source_models.SourceModel;
import com.dxminds.newsapp.viewmodel.ArticleViewModel;
import com.dxminds.newsapp.viewmodel.SourceViewModel;
import com.dxminds.newsapp.views.article_details.ArticleDetailActivity;

import org.w3c.dom.Text;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rv_articles_list;
    ImageView iv_back;
    ImageView iv_forward;

    int index = 0;
    int sourceLength = 0;
    SourceModel.Sources[] newsSource;
    TextView tv_source_name;
    ProgressBar progress_bar;

    private List<ArticlesModel.Articles> articles;
    ArticlesAdapter articlesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        setAdapters();
        callGetSource();

        setClickListeners();
    }

    private void setAdapters() {
        articlesAdapter = new ArticlesAdapter(new OnArticleClicked() {
            @Override
            public void articleClicked(String url) {
                Intent intent = new Intent(HomeActivity.this, ArticleDetailActivity.class);
                intent.putExtra("url", url);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        rv_articles_list.setAdapter(articlesAdapter);
        rv_articles_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void callGetSource() {
        progress_bar.setVisibility(View.VISIBLE);
        SourceViewModel sourceViewModel = new SourceViewModel();
        sourceViewModel.requestSources().observe(this, new Observer<SourceModel>() {
            @Override
            public void onChanged(SourceModel sourceModel) {
                newsSource = sourceModel.getSources();
                Log.e("taggg", "onChanged: "+ newsSource[0].getName());
                if(newsSource != null && newsSource.length > 0) {
                    progress_bar.setVisibility(View.GONE);
                    Log.e("taggg", "onChanged: "+ newsSource[0].getName());
                    index = 0;
                    sourceLength = newsSource.length;
                    tv_source_name.setText(newsSource[0].getName());

                    callArticles(newsSource[0].getId());
                }
            }
        });
    }


    private void callArticles(String id) {
        progress_bar.setVisibility(View.VISIBLE);
        ArticleViewModel articleViewModel = new ArticleViewModel(id);
        articleViewModel.requestArticles().observe(this, new Observer<ArticlesModel>() {
            @Override
            public void onChanged(ArticlesModel articlesModel) {
                progress_bar.setVisibility(View.GONE);

                articles = articlesModel.getArticles();
                articlesAdapter.setData(articles);
            }
        });
    }

    private void setClickListeners() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newsSource != null && newsSource.length > 0) {
                    if(index != 0) {
                        index -= 1;
                        tv_source_name.setText(newsSource[index].getName());
                        callArticles(newsSource[index].getId());
                    }
                }
            }
        });

        iv_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newsSource != null && newsSource.length > 0) {
                    if(index != 9) {
                        index += 1;
                        tv_source_name.setText(newsSource[index].getName());
                        callArticles(newsSource[index].getId());
                    }
                }

            }
        });
    }

    private void initViews() {
        rv_articles_list = findViewById(R.id.rv_articles_list);
        iv_back = findViewById(R.id.iv_back);
        tv_source_name = findViewById(R.id.tv_source_name);
        iv_forward = findViewById(R.id.iv_forward);
        progress_bar = findViewById(R.id.progress_bar);
    }
}