package by.epam.bartenderhelper.model.entity;

import java.time.Instant;

/**
 * The type Review.
 */
public final class Review extends AbstractDaoEntity {
    private final String message;
    private final double score;
    private final Instant timestamp;
    private final long authorId;

    private Review(ReviewBuilder builder){
        super(builder.reviewId);
        this.message = builder.message;
        this.score = builder.score;
        this.timestamp = builder.timestamp;
        this.authorId = builder.authorId;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public double getScore() {
        return score;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public Instant getTimestamp() {
        return timestamp;
    }

    /**
     * Gets author id.
     *
     * @return the author id
     */
    public long getAuthorId() {
        return authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        if (!super.equals(o)) return false;

        Review review = (Review) o;

        if (Double.compare(review.score, score) != 0) return false;
        if (authorId != review.authorId) return false;
        if (message != null ? !message.equals(review.message) : review.message != null) return false;
        return timestamp != null ? timestamp.equals(review.timestamp) : review.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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

    /**
     * The type Review builder.
     */
    public static class ReviewBuilder {
        private long reviewId;
        private String message;
        private double score;
        private Instant timestamp;
        private long authorId;

        /**
         * Review id review builder.
         *
         * @param reviewId the review id
         * @return the review builder
         */
        public ReviewBuilder reviewId(long reviewId){
            this.reviewId = reviewId;
            return this;
        }

        /**
         * Message review builder.
         *
         * @param message the message
         * @return the review builder
         */
        public ReviewBuilder message(String message){
            this.message = message;
            return this;
        }

        /**
         * Score review builder.
         *
         * @param score the score
         * @return the review builder
         */
        public ReviewBuilder score(double score){
            this.score = score;
            return this;
        }

        /**
         * Timestamp review builder.
         *
         * @param timestamp the timestamp
         * @return the review builder
         */
        public ReviewBuilder timestamp(Instant timestamp){
            this.timestamp = timestamp;
            return this;
        }

        /**
         * Author id review builder.
         *
         * @param authorId the author id
         * @return the review builder
         */
        public ReviewBuilder authorId(long authorId){
            this.authorId = authorId;
            return this;
        }

        /**
         * Build review.
         *
         * @return the review
         */
        public Review build() {
            return new Review(this);
        }
    }

}
