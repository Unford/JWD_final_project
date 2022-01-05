package by.epam.bartenderhelper.entity;

import java.util.Arrays;

public final class Photo extends AbstractDaoEntity {
    private final String name;
    private final byte[] data;

    private Photo(PhotoBuilder builder){
        super(builder.photoId);//todo
        this.name = builder.name;
        this.data = builder.data;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {//todo
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        if (!super.equals(o)) return false;

        Photo photo = (Photo) o;

        if (name != null ? !name.equals(photo.name) : photo.name != null) return false;
        return Arrays.equals(data, photo.data);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Photo{");
        sb.append("photoId=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", data=").append(Arrays.toString(data));
        sb.append('}');
        return sb.toString();
    }

    public static class PhotoBuilder {
        private long photoId;
        private String name;
        private byte[] data;

        public PhotoBuilder photoId(long photoId) {
            this.photoId = photoId;
            return this;
        }

        public PhotoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PhotoBuilder data(byte[] data) {
            this.data = data;
            return this;
        }

        public Photo build() {
           return new Photo(this);
        }
    }
}
