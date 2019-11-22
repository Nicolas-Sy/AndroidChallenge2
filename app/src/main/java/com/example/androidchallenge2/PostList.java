package com.example.androidchallenge2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PostList extends ArrayAdapter<Post> {
    private Activity context;
    List<Post> posts;

    public PostList(Activity context, List<Post> posts) {
        super(context, R.layout.layout_post_list, posts);
        this.context = context;
        this.posts= posts;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_post_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewCategory = (TextView) listViewItem.findViewById(R.id.textViewCategory);
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewDate);

        Post post = posts.get(position);
        textViewName.setText(post.getTitle());
        textViewCategory.setText(post.getCategory());
        textViewDate.setText(post.getDate().toString());

        return listViewItem;
    }
}
