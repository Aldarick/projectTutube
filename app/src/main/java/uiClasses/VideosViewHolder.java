package uiClasses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.aldarick.projecttutube.R;

import customClasses.Item;


/**
 * Created by Hugues on 22/03/2017.
 */

public class VideosViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView description;
    private TextView publicationDate;
    private TextView author;
    private ImageView thumbnails;

    private OnVideoSelectedListener onVideoSelectedListener;

    public VideosViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        publicationDate= (TextView) itemView.findViewById(R.id.publicationDate);
        author = (TextView) itemView.findViewById(R.id.author);
        thumbnails= (ImageView) itemView.findViewById(R.id.thumbnail);

    }

    public void bind(final Item item) {
        title.setText(item.getSnippet().getTitle());
        description.setText(item.getSnippet().getDescription());
        publicationDate.setText(item.getSnippet().getPublishedAt());
        author.setText(item.getSnippet().getChannelTitle());
        new DownloadImageTask(thumbnails).execute(item.getSnippet().getThumbnails().getDefault().getUrl());

        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (onVideoSelectedListener == null) return;
                onVideoSelectedListener.onVideoSelected(item);
            }
        });

    }


    public void setOnVideoSelectedListener(OnVideoSelectedListener onVideoSelectedListener) {
        this.onVideoSelectedListener = onVideoSelectedListener;
    }
}


