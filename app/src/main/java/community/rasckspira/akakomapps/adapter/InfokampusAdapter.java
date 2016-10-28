package community.rasckspira.akakomapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import community.rasckspira.akakomapps.DetailBeritaActivity;
import community.rasckspira.akakomapps.model.Data;
import community.rasckspira.akakomapps.R;

/**
 * Created by kristiawan on 12/12/15.
 */
public class InfokampusAdapter extends RecyclerView.Adapter<InfokampusAdapter.MyViewHolder> {


    private List<Data> mItems;
    private Context mContext;
    private static String urlFoto, link;

    public InfokampusAdapter(Context context, List<Data> mItems) {

        this.mItems = mItems;
        this.mContext = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kampus_view, null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Data item = mItems.get(i);


        myViewHolder.judul.setText(item.getJudul().toString());
        myViewHolder.desJudul.setText(item.getDetail().toString());
        myViewHolder.tgl.setText(item.getWaktu().toString());
        urlFoto = item.getFoto();
        link = item.getLink();
        if (!item.getFoto().equals(""))
            Glide.with(mContext).load(item.getFoto()).asBitmap().placeholder(R.drawable.placeholder).into(myViewHolder.foto);
        else
            myViewHolder.foto.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView judul;
        public TextView desJudul, tgl;
        ImageView foto;
        public CardView cv;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.judul = (TextView) itemView.findViewById(R.id.judul_info);
            this.tgl = (TextView) itemView.findViewById(R.id.tgl_info);
            this.desJudul = (TextView) itemView.findViewById(R.id.deskripsi_info);
            this.foto = (ImageView) itemView.findViewById(R.id.foto_info);
            this.cv = (CardView) itemView.findViewById(R.id.cvkampus);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            Context Mcontext = itemView.getContext();
            Intent intent = new Intent(Mcontext, DetailBeritaActivity.class);
            intent.putExtra(DetailBeritaActivity.KEY_JUDUL, judul.getText());
            intent.putExtra(DetailBeritaActivity.KEY_TANGGAL, tgl.getText());
            intent.putExtra(DetailBeritaActivity.KEY_URL_FOTO, urlFoto);
            intent.putExtra(DetailBeritaActivity.KEY_DESKRIPSI, desJudul.getText());
            intent.putExtra(DetailBeritaActivity.KEY_LINK, link);
            intent.putExtra("title","Info Kampus");
            Mcontext.startActivity(intent);

        }
    }
}