package by.epam.bartenderhelper.model.entity.dto;

import java.time.Instant;

public class ReviewDto {
    private long reviewId;
    private String message;
    private double score;
    private Instant timestamp;
    private String username;
    private String photoName;
    private String photoData;



    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoData() {
        return photoData;
    }

    public void setPhotoData(String photoData) {
        this.photoData = photoData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewDto)) return false;

        ReviewDto reviewDto = (ReviewDto) o;

        if (reviewId != reviewDto.reviewId) return false;
        if (Double.compare(reviewDto.score, score) != 0) return false;
        if (message != null ? !message.equals(reviewDto.message) : reviewDto.message != null) return false;
        if (timestamp != null ? !timestamp.equals(reviewDto.timestamp) : reviewDto.timestamp != null) return false;
        if (username != null ? !username.equals(reviewDto.username) : reviewDto.username != null) return false;
        if (photoName != null ? !photoName.equals(reviewDto.photoName) : reviewDto.photoName != null) return false;
        return photoData != null ? photoData.equals(reviewDto.photoData) : reviewDto.photoData == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (reviewId ^ (reviewId >>> 32));
        result = 31 * result + (message != null ? message.hashCode() : 0);
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (photoName != null ? photoName.hashCode() : 0);
        result = 31 * result + (photoData != null ? photoData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReviewDto{");
        sb.append("reviewId=").append(reviewId);
        sb.append(", message='").append(message).append('\'');
        sb.append(", score=").append(score);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", username='").append(username).append('\'');
        sb.append(", photoName='").append(photoName).append('\'');
        sb.append(", photoData='").append(photoData).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
