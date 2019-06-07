package com.atossyntel.connection;

import com.atossyntel.entities.Stream;
import java.util.ArrayList;

public class StreamDAO implements StreamJDBCInterface {

    @Override
    public Stream getStream(String id) {
        StreamJDBCOps dbObj = new StreamJDBCOps();
        try {
            Stream temp = dbObj.getStream(id);
            System.out.println("Stream Retrieved: " + temp);
            return temp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Stream();
        }
    }

    @Override
    public boolean deleteStream(String id) {
        StreamJDBCOps dbObj = new StreamJDBCOps();
        boolean deleted = dbObj.deleteStream(id);
        if (deleted == true) {
            System.out.println("Stream Successfully deleted");
        } else {
            System.out.println("Stream not deleted...");
        }
        return deleted;
    }

    @Override
    public boolean updateStream(Stream stream) {
        StreamJDBCOps dbObj = new StreamJDBCOps();
        boolean updated = dbObj.updateStream(stream);
        if (updated == true) {
            System.out.println("Stream successfully updated");
        } else {
            System.out.println("Stream not updated...");
        }
        return updated;
    }

    @Override
    public ArrayList<Stream> getAllStreams() {
        StreamJDBCOps dbObj = new StreamJDBCOps();
        try {
            ArrayList<Stream> streamList = dbObj.getAllStreams();
            System.out.println("List of Streams retrieved:" + streamList);
            return streamList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addStream(Stream stream) {
        StreamJDBCOps dbObj = new StreamJDBCOps();
        boolean added = dbObj.addStream(stream);
        if (added == true) {
            System.out.println("Stream Successfully added");
        } else {
            System.out.println("Stream creation failed...");
        }
        return added;
    }
}
