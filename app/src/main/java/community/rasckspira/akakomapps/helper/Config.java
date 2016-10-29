package community.rasckspira.akakomapps.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by muhwid on 10/26/2016.
 */

public class Config {
    public static final String URL_PROFILE = "http://superjson.pe.hu/profil.json";
    public static final String URL_VISI_MISI = "http://superjson.pe.hu/visimisi.json";
    public static final String URL_JURUSAN = "http://superjson.pe.hu/jurusan.json";
    public static final String URL_JABATAN = "http://superjson.pe.hu/jabatan.json";
    public static final String URL_BERITA = "http://superjson.pe.hu/berita.json";
    public static final String URL_INFO = "http://superjson.pe.hu/infokampus.json";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
