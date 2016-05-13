package com.gmail.marvelfds.instagramphotoviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by Core i7 on 5/13/2016.
 */
public class InstagramArrayAdapter extends ArrayAdapter<InstagramPhoto> {

    public InstagramArrayAdapter(Context context, int resource, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);
        if (convertView ==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo,parent,false);


        }
        ImageView ivPhoto =(ImageView)convertView.findViewById(R.id.ivPhoto);
        TextView tvCaption =(TextView) convertView.findViewById(R.id.tvCaption);


        ImageView ivProfileUser = (ImageView)(convertView.findViewById(R.id.ivProfileUser));
        TextView tvUsername =(TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvDateCreation = (TextView) convertView.findViewById(R.id.tvDateCreation);
        TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);

        tvDateCreation.setText(photo.getRelativeTimeStam());
        tvCaption.setText(photo.caption);
        tvUsername.setText(photo.userName);
        tvLikes.setText(photo.getLikesCount());
        ivPhoto.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto);


       // ivProfileUser.setImageResource(0);
     //   Picasso.with(getContext()).load(photo.userProfile).into(ivProfileUser);

        Picasso.with(getContext()).load(photo.userProfile)
                .transform(new CropCircleTransformation()).into(ivProfileUser);


        return convertView;
    }
}
