/**
 * Created by Hugues on 17/03/2017.
 */

public class VideosListView extends ListView {

    public VideosListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VideosListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideosListView(Context context) {
        super(context);
    }

    public void setVideos(List<Video> videos){
        VideosAdapter adapter = new VideosAdapter(getContext(), videos);
        setAdapter(adapter);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }
}