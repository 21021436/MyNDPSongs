package sg.edu.rp.c346.id21021436.MyNDPSongs;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowSongsActivity extends AppCompatActivity {
    ListView lv;
    Button btnFiveStars;

    ArrayList<Song> alSongList = new ArrayList<>();

    DBHelper dbh = new DBHelper(ShowSongsActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_songs);


    lv = findViewById(R.id.listSongs);
    btnFiveStars = findViewById(R.id.buttonFiveStars);

    Intent intentReceived = getIntent();

    alSongList = (ArrayList<Song>) intentReceived.getSerializableExtra("songList");

//    ArrayAdapter<Song> aaSongList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alSongList);

    CustomAdapter aaSongList = new CustomAdapter(this, R.layout.row, alSongList);


    DBHelper dbh = new DBHelper(ShowSongsActivity.this);

    btnFiveStars.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    alSongList.clear();
    alSongList.addAll(dbh.getFiveStar());
    aaSongList.notifyDataSetChanged();
    lv.setAdapter(aaSongList);



    //this is a way to make a button disappear programmatically, used in an earlier version
//    btnFiveStars.setVisibility(View.GONE);


        }
    });

    lv.setClickable(true);
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Song songPicked = (Song) lv.getItemAtPosition(position);
            Intent intentToModifySongs = new Intent(ShowSongsActivity.this, ModifySongsActivity.class);
            intentToModifySongs.putExtra("songPicked", songPicked);
            startActivity(intentToModifySongs);

        }
    });

        DBHelper dbhSpinner = new DBHelper(ShowSongsActivity.this);


        ArrayList<Integer> alYears = new ArrayList<>();
        ArrayAdapter<Integer> aaYears = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, alYears);
        Spinner spinYear = findViewById(R.id.spinnerYear);
        spinYear.setAdapter(aaYears);
        alYears.addAll(dbhSpinner.distinctYears());
        aaYears.notifyDataSetChanged();
        spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedYear = spinYear.getSelectedItem().toString();
            DBHelper dbhSongsYear = new DBHelper(ShowSongsActivity.this);
            ArrayList<Song> yearSongs = dbhSongsYear.songsYear(selectedYear);
//            ArrayAdapter<Song> adapterYearSongs = new ArrayAdapter<Song>(ShowSongsActivity.this, android.R.layout.simple_list_item_1, yearSongs);

            CustomAdapter adapterYearSongs = new CustomAdapter(ShowSongsActivity.this, R.layout.row, yearSongs);

            ListView lv = findViewById(R.id.listSongs);
            lv.setAdapter(adapterYearSongs);
            adapterYearSongs.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
