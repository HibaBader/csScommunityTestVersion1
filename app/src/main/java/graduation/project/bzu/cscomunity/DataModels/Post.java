package graduation.project.bzu.cscomunity.DataModels;

public class Post {

    private int postID;
    private String postType;
    private String postSubject;
    private String postTitle;
    private String postTags;
    private String postBody;
    private String postAttachment;
    private User user;


    public Post() {
        super();
    }

    public Post(int postID, String postType, String postSubject, String postTitle, String postTags,
                String postBody, String postAttachment,User user) {
        super();
        this.postID = postID;
        this.postType = postType;
        this.postSubject = postSubject;
        this.postTitle = postTitle;
        this.postTags = postTags;
        this.postBody = postBody;
        this.postAttachment = postAttachment;
       this.user = user;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostSubject() {
        return postSubject;
    }

    public void setPostSubject(String postSubject) {
        this.postSubject = postSubject;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostTags() {
        return postTags;
    }

    public void setPostTags(String postTags) {
        this.postTags = postTags;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getPostAttachment() {
        return postAttachment;
    }

    public void setPostAttachment(String postAttachment) {
        this.postAttachment = postAttachment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", postType='" + postType + '\'' +
                ", postSubject='" + postSubject + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postTags='" + postTags + '\'' +
                ", postBody='" + postBody + '\'' +
                ", postAttachment='" + postAttachment + '\'' +
                ", user=" + user +
                '}';
    }
}







