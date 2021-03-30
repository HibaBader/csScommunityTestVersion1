package graduation.project.bzu.cscomunity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import graduation.project.bzu.cscomunity.DataModels.Subject;
import graduation.project.bzu.cscomunity.R;

public class spinnerAdapter extends ArrayAdapter<Subject> {
private Context context;
private ArrayList<Subject>subjectList;
    public spinnerAdapter(Context context, ArrayList<Subject>subjectList){
        super(context,0,subjectList);
        this.context=context;
        this.subjectList=subjectList;

    }
    @Override
    public int getCount(){
        return subjectList.size();
    }
    @NonNull
    @Override
    public Subject getItem(int position){
        return subjectList.get(position);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view= LayoutInflater.from(getContext()).inflate(
                    R.layout.linear_subject_list_layout,parent,false
            );
        }
       ImageView imageView = view.findViewById(R.id.coverImage);
        TextView txtSubject = view.findViewById(R.id.subjectTitle);

        Subject subjectItem = getItem(position);
        if(subjectItem != null) {
           Picasso.get().load(subjectItem.getImageURL()).into(imageView);
            txtSubject.setText(subjectItem.getName());
        }
        return view;


    }
}
