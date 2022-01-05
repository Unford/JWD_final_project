package by.epam.bartenderhelper.entity;

public final class Vote extends AbstractDaoEntity {//todo useless avg sql
    private final int score;
    private final long authorId;

    private Vote(VoteBuilder builder) {
        super(builder.voteId);
        this.score = builder.score;
        this.authorId = builder.authorId;
    }

    public int getScore() {
        return score;
    }

    public long getAuthorId() {
        return authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vote)) return false;
        if (!super.equals(o)) return false;

        Vote vote = (Vote) o;

        if (score != vote.score) return false;
        return authorId == vote.authorId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + score;
        result = 31 * result + (int) (authorId ^ (authorId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vote{");
        sb.append("voteId=").append(id);
        sb.append(", score=").append(score);
        sb.append(", authorId=").append(authorId);
        sb.append('}');
        return sb.toString();
    }

    public static class VoteBuilder {
        private long voteId;
        private int score;
        private long authorId;

        public VoteBuilder voteId(long voteId) {
            this.voteId = voteId;
            return this;
        }

        public VoteBuilder score(int score) {
            this.score = score;
            return this;
        }

        public VoteBuilder authorId(long authorId) {
            this.authorId = authorId;
            return this;
        }

        public Vote build(){
            return new Vote(this);
        }
    }
}
