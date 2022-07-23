package sg.edu.rp.c346.id21021436.MyNDPSongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvDispStars = rowView.findViewById(R.id.textViewDispStars);
        TextView tvSinger = rowView.findViewById(R.id.textViewSinger);

        // Obtain the Song List information based on the position
        Song currentSong = songList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentSong.getTitle());
        tvYear.setText(String.valueOf(currentSong.getYear()));
        tvDispStars.setText(displayStars(currentSong.getStars()));
        tvSinger.setText(currentSong.getSingers());

        return rowView;
    }

    private String displayStars(int stars){

        String stringStars = "";

        for (int i = 1; i <= stars; i++){
            stringStars += "* ";
        }

        return stringStars;

    }


}
