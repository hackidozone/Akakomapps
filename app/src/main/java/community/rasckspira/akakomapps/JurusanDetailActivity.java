package community.rasckspira.akakomapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class JurusanDetailActivity extends AppCompatActivity {
    public static final String KEY_NAMA = "nama";
    public static final String KEY_DESKRIPSI = "deskripsi";
    public static final String KEY_LINK = "link";
    public static final String KEY_URL_PHOTO = "photo";

    private TextView judul, deskripsi;
    private Button btnMore;
    private Toolbar toolbar;
    private ImageView featuredPhoto;
    private String urlPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurusan_detail);
        toolbar = (Toolbar) findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initView();
    }

    private void initView(){
        featuredPhoto = (ImageView) findViewById(R.id.featured_jurusan);
        judul = (TextView) findViewById(R.id.judul_jurusan);
        deskripsi = (TextView) findViewById(R.id.deskripsi_jurusan);
        btnMore = (Button) findViewById(R.id.btn_more_jurusan);
        urlPhoto = getIntent().getExtras().getString(KEY_URL_PHOTO);
        judul.setText(getIntent().getExtras().getString(KEY_NAMA));
        deskripsi.setText(getIntent().getExtras().getString(KEY_DESKRIPSI));
        Glide.with(this).load(urlPhoto).placeholder(R.drawable.placeholder).centerCrop().into(featuredPhoto);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JurusanDetailActivity.this, WebViewActivity.class);
                i.putExtra(WebViewActivity.KEY_HEADER, judul.getText());
                i.putExtra(WebViewActivity.KEY_URL, getIntent().getExtras().getString(KEY_LINK));
                startActivity(i);
            }
        });
    }
}
