package by.epam.bartenderhelper.model.entity;


/**
 * The type Photo.
 */
public final class Photo extends AbstractDaoEntity {
    private final String name;
    private final String data;

    private Photo(PhotoBuilder builder){
        super(builder.photoId);
        this.name = builder.name;
        this.data = builder.data;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        if (!super.equals(o)) return false;

        Photo photo = (Photo) o;

        if (name != null ? !name.equals(photo.name) : photo.name != null) return false;
        return data != null ? data.equals(photo.data) : photo.data == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Photo{");
        sb.append("photoId=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    /**
     * The type Photo builder.
     */
    public static class PhotoBuilder {
        private long photoId;
        private String name;
        private String data;

        /**
         * Photo id photo builder.
         *
         * @param photoId the photo id
         * @return the photo builder
         */
        public PhotoBuilder photoId(long photoId) {
            this.photoId = photoId;
            return this;
        }

        /**
         * Name photo builder.
         *
         * @param name the name
         * @return the photo builder
         */
        public PhotoBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Data photo builder.
         *
         * @param data the data
         * @return the photo builder
         */
        public PhotoBuilder data(String data) {
            this.data = data;
            return this;
        }

        /**
         * Build photo.
         *
         * @return the photo
         */
        public Photo build() {
           return new Photo(this);
        }
    }
}
