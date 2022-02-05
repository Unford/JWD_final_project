package by.epam.bartenderhelper.model.entity;


public final class Photo extends AbstractDaoEntity {
    private final String name;
    private final String data;

    private Photo(PhotoBuilder builder){
        super(builder.photoId);
        this.name = builder.name;
        this.data = builder.data;
    }

    public String getName() {
        return name;
    }

    public String getData() {//todo
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

    public static class PhotoBuilder {
        private long photoId;
        private String name;
        private String data;

        public PhotoBuilder photoId(long photoId) {
            this.photoId = photoId;
            return this;
        }

        public PhotoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PhotoBuilder data(String data) {
            this.data = data;
            return this;
        }

        public Photo build() {
           return new Photo(this);
        }
    }
}
