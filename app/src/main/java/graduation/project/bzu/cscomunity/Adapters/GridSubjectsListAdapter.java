package graduation.project.bzu.cscomunity.Adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
        import com.squareup.picasso.Picasso;

import java.util.List;
import static androidx.core.content.ContextCompat.startActivity;

import graduation.project.bzu.cscomunity.Activities.Menu;
import graduation.project.bzu.cscomunity.Activities.Question;
import graduation.project.bzu.cscomunity.Activities.QuestionCardView;
import graduation.project.bzu.cscomunity.DataModels.Subject;
import graduation.project.bzu.cscomunity.R;


public class GridSubjectsListAdapter extends RecyclerView.Adapter<GridSubjectsListAdapter.ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<Subject> subjects;


    public GridSubjectsListAdapter(Context context, List<Subject> subjects){
        this.inflater=LayoutInflater.from(context);
        this.subjects = subjects;
        this.context=context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.grid_subject_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.subjectName.setText(subjects.get(position).getName());
        Picasso.get().load(subjects.get(position).getImageURL()).into(holder.subjectImage);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, QuestionCardView.class);
                intent.putExtra("subjectName",subjects.get(position).getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView subjectName;
        ImageView subjectImage;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName=itemView.findViewById(R.id.subjectTitle);
            subjectImage=itemView.findViewById(R.id.coverImage);
            cardView=itemView.findViewById(R.id.card);

        }
    }
}