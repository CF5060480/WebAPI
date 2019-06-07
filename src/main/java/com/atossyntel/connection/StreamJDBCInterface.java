package com.atossyntel.connection;
import com.atossyntel.entities.Stream;
import java.util.ArrayList;

public interface StreamJDBCInterface {
    public Stream getStream(String userId);
    public boolean deleteStream(String userId);
    public boolean updateStream(Stream user);
    public ArrayList<Stream> getAllStreams();
    public boolean addStream(Stream stream);
}
