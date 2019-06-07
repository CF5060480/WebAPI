package com.atossyntel.webapi;

import com.atossyntel.connection.StreamDAO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atossyntel.entities.Stream;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class StreamController {

    @CrossOrigin
    @RequestMapping(value = "/getStream", method = RequestMethod.POST)
    public String display(@RequestParam Map<String, String> data) throws JSONException {
        StreamDAO getstream = new StreamDAO();
        Stream stream = getstream.getStream(data.get("streamId"));
        JSONObject json = new JSONObject();
        json.put("streamId", stream.getStreamId());
        json.put("streamName", stream.getStreamName());
        System.out.println(json.toString());
        return json.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/createStream", method = RequestMethod.POST)
    public String process(@RequestParam Map<String, String> data) {
        StreamDAO createstream = new StreamDAO();
        Stream stream = new Stream(data.get("streamId"), data.get("streamName"));
        boolean status = createstream.addStream(stream);
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteStream", method = RequestMethod.POST)
    public String delete(@RequestParam Map<String, String> data) {
        StreamDAO deletestream = new StreamDAO();
        boolean status = deletestream.deleteStream(data.get("streamId"));
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/updateStream", method = RequestMethod.POST)
    public String update(@RequestParam Map<String, String> data) {
        StreamDAO updatestream = new StreamDAO();
        Stream stream = new Stream(data.get("streamId"), data.get("streamName"));
        boolean status = updatestream.updateStream(stream);
        JSONObject jObj = new JSONObject();
        jObj.put("status",status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllStreams", method = RequestMethod.GET)
    public String getAll() {
        StreamDAO getstreams = new StreamDAO();
        ArrayList<Stream> streamList = getstreams.getAllStreams();
        JSONArray jList = new JSONArray();
        for(Stream u: streamList) {
            JSONObject jObj = new JSONObject();
            jObj.put("streamId", u.getStreamId());
            jObj.put("streamName", u.getStreamName());
            jList.put(jObj);
        }
        System.out.println(jList.toString());
        return jList.toString();
    }
}
