package graduation.project.bzu.cscomunity.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import graduation.project.bzu.cscomunity.Activities.QuestionCardView;
import graduation.project.bzu.cscomunity.Activities.viewPostDetails;
import graduation.project.bzu.cscomunity.DataModels.Post;
import graduation.project.bzu.cscomunity.DataModels.User;
import graduation.project.bzu.cscomunity.R;

public class GetPostsAdapter extends RecyclerView.Adapter<GetPostsAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Post> posts;
    List<User> users;
    Context context;

    public GetPostsAdapter(Context context, List<Post> posts){
        this.inflater=LayoutInflater.from(context);
        this.posts = posts;
        this.context=context;



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.activity_view_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

     holder.userName.setText(posts.get(position).getUser().getUserName());

        if(posts.get(position).getPostType().equals("Question")){
            holder.postType.setText("Q");}
        else if(posts.get(position).getPostType().equals("Topic")){
            holder.postType.setText("T");}

        holder.postTitle.setText(posts.get(position).getPostTitle());
        holder.postContent.setText(posts.get(position).getPostBody());
        String tagsString=posts.get(position).getPostTags();
        String[] tagsArray=tagsString.split(",");
        if(tagsArray.length==1){
            holder.tag1.setText(tagsArray[0]);
            holder.tag1.setVisibility(View.VISIBLE);
            holder.tags.setVisibility(View.VISIBLE);
        }else if(tagsArray.length==2){
            holder.tag1.setText(tagsArray[0]);
            holder.tag2.setText(tagsArray[1]);
            holder.tag1.setVisibility(View.VISIBLE);
            holder.tag2.setVisibility(View.VISIBLE);
            holder.tags.setVisibility(View.VISIBLE);
        }else if(tagsArray.length==3){
            holder.tag1.setText(tagsArray[0]);
            holder.tag2.setText(tagsArray[1]);
            holder.tag3.setText(tagsArray[2]);
            holder.tag1.setVisibility(View.VISIBLE);
            holder.tag2.setVisibility(View.VISIBLE);
            holder.tag3.setVisibility(View.VISIBLE);
            holder.tags.setVisibility(View.VISIBLE);
        }else if(tagsArray.length==4){
            holder.tag1.setText(tagsArray[0]);
            holder.tag2.setText(tagsArray[1]);
            holder.tag3.setText(tagsArray[2]);
            holder.tag4.setText(tagsArray[3]);
            holder.tag1.setVisibility(View.VISIBLE);
            holder.tag2.setVisibility(View.VISIBLE);
            holder.tag3.setVisibility(View.VISIBLE);
            holder.tag4.setVisibility(View.VISIBLE);
            holder.tags.setVisibility(View.VISIBLE);

        }else if(tagsArray.length==5) {
            holder.tag1.setText(tagsArray[0]);
            holder.tag2.setText(tagsArray[1]);
            holder.tag3.setText(tagsArray[2]);
            holder.tag4.setText(tagsArray[3]);
            holder.tag5.setText(tagsArray[4]);
            holder.tag1.setVisibility(View.VISIBLE);
            holder.tag2.setVisibility(View.VISIBLE);
            holder.tag3.setVisibility(View.VISIBLE);
            holder.tag4.setVisibility(View.VISIBLE);
            holder.tag5.setVisibility(View.VISIBLE);
            holder.tags.setVisibility(View.VISIBLE);

        }
        String imagesString=posts.get(position).getPostAttachment();
        String[] imagesArray=imagesString.split(",");
        if(imagesArray.length==1){
            Picasso.get().load(imagesArray[0]).into(holder.image1);
            holder.imagesPreviews.setVisibility(View.VISIBLE);
            holder.image1.setVisibility(View.VISIBLE);
        }else if(imagesArray.length==2){
            Picasso.get().load(imagesArray[0]).into(holder.image1);
            Picasso.get().load(imagesArray[1]).into(holder.image2);
            holder.imagesPreviews.setVisibility(View.VISIBLE);
            holder.image1.setVisibility(View.VISIBLE);
            holder.image2.setVisibility(View.VISIBLE);
        }else if(imagesArray.length==3){
            Picasso.get().load(imagesArray[0]).into(holder.image1);
            Picasso.get().load(imagesArray[1]).into(holder.image2);
            Picasso.get().load(imagesArray[2]).into(holder.image3);
            holder.imagesPreviews.setVisibility(View.VISIBLE);
            holder.image1.setVisibility(View.VISIBLE);
            holder.image2.setVisibility(View.VISIBLE);
            holder.image3.setVisibility(View.VISIBLE);
        }else if(imagesArray.length==4){
            Picasso.get().load(imagesArray[0]).into(holder.image1);
            Picasso.get().load(imagesArray[1]).into(holder.image2);
            Picasso.get().load(imagesArray[2]).into(holder.image3);
            Picasso.get().load(imagesArray[3]).into(holder.image4);
            holder.imagesPreviews.setVisibility(View.VISIBLE);
            holder.image1.setVisibility(View.VISIBLE);
            holder.image2.setVisibility(View.VISIBLE);
            holder.image3.setVisibility(View.VISIBLE);
            holder.image4.setVisibility(View.VISIBLE);

        }else if(imagesArray.length==5) {
            Picasso.get().load(imagesArray[0]).into(holder.image1);
            Picasso.get().load(imagesArray[1]).into(holder.image2);
            Picasso.get().load(imagesArray[2]).into(holder.image3);
            Picasso.get().load(imagesArray[3]).into(holder.image4);
            Picasso.get().load(imagesArray[4]).into(holder.image5);
            holder.imagesPreviews.setVisibility(View.VISIBLE);
            holder.image1.setVisibility(View.VISIBLE);
            holder.image2.setVisibility(View.VISIBLE);
            holder.image3.setVisibility(View.VISIBLE);
            holder.image4.setVisibility(View.VISIBLE);
            holder.image5.setVisibility(View.VISIBLE);
        }
        /*String videosString=posts.get(position).getPostTags();
        String[] videosArray=videosString.split(",");
        if(videosArray.length==1){
            Picasso.get().load(videosArray[0]).into(holder.video1);
            Picasso.get().load(imagesArray[1]).into(holder.image2);
            holder.tag1.setVisibility(View.VISIBLE);
            holder.tags.setVisibility(View.VISIBLE);
        }else if(videosArray.length==2){
            Picasso.get().load(videosArray[0]).into(holder.video1);
            Picasso.get().load(videosArray[1]).into(holder.video2);
            holder.tag1.setVisibility(View.VISIBLE);
            holder.tag2.setVisibility(View.VISIBLE);
            holder.tags.setVisibility(View.VISIBLE);
        }*/

        Picasso.get().load(posts.get(position).getUser().getUserImage()).into(holder.image);

        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, viewPostDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.putExtra("postID",posts.get(position).getPostID());
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView userName,postTime,postType,postTitle,postContent,tag1,tag2,tag3,tag4,tag5,postViews,postComments,postShares;
        ImageView postMoreMenu,image1,image2,image3,image4,image5;
        CircleImageView image;


        VideoView video1,video2;
        ConstraintLayout tags,imagesPreviews,videosPreviews;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName=itemView.findViewById(R.id.userName);
            postTime=itemView.findViewById(R.id.post_time);
            postType=itemView.findViewById(R.id.postType);
            postTitle=itemView.findViewById(R.id.post_Title);
            postContent=itemView.findViewById(R.id.post_content);
            tag1=itemView.findViewById(R.id.tag1);
            tag2=itemView.findViewById(R.id.tag2);
            tag3=itemView.findViewById(R.id.tag3);
            tag4=itemView.findViewById(R.id.tag4);
            tag5=itemView.findViewById(R.id.tag5);

            postViews=itemView.findViewById(R.id.post_views);
            postComments=itemView.findViewById(R.id.post_comments);
            postShares=itemView.findViewById(R.id.post_shares);
            image = (CircleImageView) itemView.findViewById(R.id.userImage);
            postMoreMenu=itemView.findViewById(R.id.post_more_menu);
            image1=itemView.findViewById(R.id.image_preview1);
            image2=itemView.findViewById(R.id.image_preview2);
            image3=itemView.findViewById(R.id.image_preview3);
            image4=itemView.findViewById(R.id.image_preview4);
            image5=itemView.findViewById(R.id.image_preview5);
            video1=itemView.findViewById(R.id.video_preview1);
            video2=itemView.findViewById(R.id.video_preview2);
            tags=itemView.findViewById(R.id.tags);
            imagesPreviews=itemView.findViewById(R.id.images_previews);
            videosPreviews=itemView.findViewById(R.id.videos_previews);
            cardView = itemView.findViewById(R.id.card);

        }
    }
}
