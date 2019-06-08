package com.atossyntel.webapi;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atossyntel.connection.BatchDAO;
import com.atossyntel.entities.Batch;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BatchController {

    @CrossOrigin
    @RequestMapping(value = "/getBatch", method = RequestMethod.POST)
    public String display(@RequestParam Map<String, String> data) throws JSONException {
        BatchDAO getbatch = new BatchDAO();
        Batch batch = getbatch.getBatch(data.get("batchId"));
        JSONObject json = new JSONObject();
        json.put("batchId", batch.getBatchId());
        json.put("startDate", batch.getStartDate());
        json.put("endDate", batch.getEndDate());
        json.put("streamId", batch.getStreamId());
        json.put("country", batch.getCountry());
        json.put("city", batch.getCity());
        System.out.println(json.toString());
        return json.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/createBatch", method = RequestMethod.POST)
    public String process(@RequestParam Map<String, String> data) {
        BatchDAO createBatch = new BatchDAO();
        Batch batch = new Batch(data.get("batchId"), data.get("startDate"), data.get("endDate"), data.get("streamId"), data.get("country"), data.get("city"));
        boolean status = createBatch.addBatch(batch);
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
    public String delete(@RequestParam Map<String, String> data) {
        BatchDAO deleteBatch = new BatchDAO();
        boolean status = deleteBatch.deleteBatch(data.get("batchId"));
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/updateBatch", method = RequestMethod.POST)
    public String update(@RequestParam Map<String, String> data) {
        BatchDAO updateBatch = new BatchDAO();
        Batch batch = new Batch(data.get("batchId"), data.get("startDate"), data.get("endDate"), data.get("streamId"), data.get("country"), data.get("city"));
        boolean status = updateBatch.updateBatch(batch);
        JSONObject jObj = new JSONObject();
        jObj.put("status",status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllBatches", method = RequestMethod.GET)
    public String getAll() {
        BatchDAO getemps = new BatchDAO();
        ArrayList<Batch> batchList = getemps.getAllBatches();
        System.out.println(batchList.toString());
        JSONArray jList = new JSONArray();
        for(Batch b: batchList) {
            JSONObject jObj = new JSONObject();
            jObj.put("batchId", b.getBatchId());
            jObj.put("startDate", b.getStartDate());
            jObj.put("endDate", b.getEndDate());
            jObj.put("streamId", b.getStreamId());
            jObj.put("country", b.getCountry());
            jObj.put("city", b.getCity());
            jList.put(jObj);
        }
        System.out.println(jList.toString());
        return jList.toString();

    }
}
