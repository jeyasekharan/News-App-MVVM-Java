package com.dxminds.newsapp.views.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.dxminds.newsapp.R;
import com.dxminds.newsapp.model.ArticlesModel;
import com.dxminds.newsapp.model.source_models.SourceModel;

import java.util.List;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    Context context;
    List<ArticlesModel.Articles> articlesList;
    OnArticleClicked onArticleClicked;

    public ArticlesAdapter(OnArticleClicked onArticleClicked) {
        this.onArticleClicked = onArticleClicked;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_articles, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final ArticlesModel.Articles single = articlesList.get(position);

        holder.tv_article_name.setText(single.getTitle());
        holder.tv_desc.setText(single.getDescription());
        holder.tv_timestamp.setText(single.getPublishedAt());
        Glide.with(context)
                        .load(single.getUrlToImage())
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(holder.iv_image);

        holder.cv_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onArticleClicked.articleClicked(single.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (articlesList != null && articlesList.size() > 0) {
            return articlesList.size();
        } else {
            return 0;
        }
    }

    public void setData(List<ArticlesModel.Articles> articlesList) {
        this.articlesList =  articlesList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image;
        TextView tv_article_name;
        TextView tv_desc;
        TextView tv_timestamp;
        CardView cv_card_view;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_article_name = itemView.findViewById(R.id.tv_article_name);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_timestamp = itemView.findViewById(R.id.tv_timestamp);
            cv_card_view = itemView.findViewById(R.id.cv_card_view);
        }
    }

}