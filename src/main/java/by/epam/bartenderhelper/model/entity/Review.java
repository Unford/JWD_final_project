package by.epam.bartenderhelper.model.entity;

import java.time.Instant;

public final class Review extends AbstractDaoEntity {
    private final String message;
    private final int score;
    private final Instant timestamp;
    private final long authorId;

    private Review(CommentBuilder builder){
        super(builder.commentId);
        this.message = builder.message;
        this.score = builder.score;
        this.timestamp = builder.timestamp;
        this.authorId = builder.authorId;
    }

    public String getMessage() {
        return message;
    }

    public int getScore() {
        return score;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public long getAuthorId() {
        return authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        if (!super.equals(o)) return false;

        Review review = (Review) o;

        if (score != review.score) return false;
        if (authorId != review.authorId) return false;
        if (message != null ? !message.equals(review.message) : review.message != null) return false;
        return timestamp != null ? timestamp.equals(review.timestamp) : review.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (int) (authorId ^ (authorId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Review{");
        sb.append("reviewId=").append(id);
        sb.append(", message='").append(message).append('\'');
        sb.append(", score=").append(score);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", authorId=").append(authorId);
        sb.append('}');
        return sb.toString();
    }

    public static class CommentBuilder {
        private long commentId;
        private String message;
        private int score;
        private Instant timestamp;
        private long authorId;

        public CommentBuilder commentId(long commentId){
            this.commentId = commentId;
            return this;
        }

        public CommentBuilder message(String message){
            this.message = message;
            return this;
        }

        public CommentBuilder score(int score){
            this.score = score;
            return this;
        }

        public CommentBuilder timestamp(Instant timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public CommentBuilder authorId(long authorId){
            this.authorId = authorId;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }

}
