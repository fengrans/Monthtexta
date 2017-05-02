package bawei.com.monthtexta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Myholder> {
    private List<Myinfo.DataBean> list;
    private Context context;
    private ImageView head;
    private TextView title,address,count;
    public MyAdapter(List<Myinfo.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        Myholder holder = new Myholder(LayoutInflater.from(
                context).inflate(R.layout.item_recyclers, parent,
                false));
        return holder;
    }
    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        //     Glide.with(context).load(list.get(position).getImg()).into(head);
        Glide.with(context).load(list.get(position).getImg()).animate(android.R.anim.slide_in_left).diskCacheStrategy(DiskCacheStrategy.ALL).into(head);
        title.setText(list.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class Myholder extends  RecyclerView.ViewHolder{
        public Myholder(View itemView) {
            super(itemView);
            head= (ImageView) itemView.findViewById(R.id.head);
            title= (TextView) itemView.findViewById(R.id.name);
        }
    }
}
