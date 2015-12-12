package community.rasckspira.akakomapps.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import community.rasckspira.akakomapps.AppController;
import community.rasckspira.akakomapps.R;


public class Content2Fragment extends Fragment {

    private TextView txtVisi;
    private TextView txtMisi;
    private TextView txtTujuan;
    private NetworkImageView imgProfil;
    private String urlProfil = "http://service.rackspira.community/rest/rjson/visimisi.json";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content2, container, false);

        imgProfil = (NetworkImageView) v.findViewById(R.id.img_profil);
        txtMisi = (TextView) v.findViewById(R.id.misi);
        txtVisi = (TextView) v.findViewById(R.id.visi);
        txtTujuan = (TextView) v.findViewById(R.id.tujuan);

        final ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        final JsonArrayRequest request = new JsonArrayRequest(urlProfil,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                txtVisi.setText(jsonObject.getString("visi").toString());
                                txtMisi.setText(jsonObject.getString("misi").toString());
                                txtTujuan.setText(jsonObject.getString("tujuan").toString());
                                String urlImage = jsonObject.getString("gambar").toString();


                                imgProfil.setImageUrl(urlImage, imageLoader);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtVisi.setText(error.toString());
                    }
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        AppController.getInstance().addToRequestQueue(request);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


}