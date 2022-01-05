package by.epam.bartenderhelper.entity;

import java.time.Instant;

public final class Comment extends AbstractDaoEntity {
    private final String message;
    private final Instant timestamp;
    private final User author;

    private Comment(CommentBuilder builder){
        super(builder.commentId);
        this.message = builder.message;
        this.timestamp = builder.timestamp;
        this.author = builder.author;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public User getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        if (!super.equals(o)) return false;

        Comment comment = (Comment) o;

        if (message != null ? !message.equals(comment.message) : comment.message != null) return false;
        if (timestamp != null ? !timestamp.equals(comment.timestamp) : comment.timestamp != null) return false;
        return author != null ? author.equals(comment.author) : comment.author == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("commentId=").append(id);
        sb.append(", message='").append(message).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append(", author=").append(author);
        sb.append('}');
        return sb.toString();
    }

    public static class CommentBuilder {
        private long commentId;
        private String message;
        private Instant timestamp;
        private User author;

        public CommentBuilder commentId(long commentId){
            this.commentId = commentId;
            return this;
        }

        public CommentBuilder message(String message){
            this.message = message;
            return this;
        }

        public CommentBuilder timestamp(Instant timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public CommentBuilder author(User author){
            this.author = author;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }

}
