package com.atossyntel.webapi;
import com.atossyntel.connection.EmployeeTakeModulesDAO;
import com.atossyntel.connection.InstructorTeachBatchDAO;
import com.atossyntel.entities.EmployeeTakeModule;
import com.atossyntel.entities.InstructorTeachBatch;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructorTeachBatchController {
    @CrossOrigin
    @RequestMapping(value = "/getInstructorTeachBatch", method = RequestMethod.POST)
    public String display(@RequestParam Map<String, String> data) throws JSONException {
        InstructorTeachBatchDAO getInsTchBat = new InstructorTeachBatchDAO();
        InstructorTeachBatch insTchBat =getInsTchBat.getInsTchBat(data.get("userId"), data.get("batchId"));
        JSONObject json = new JSONObject();
        json.put("userId", insTchBat.getUserId());
        json.put("batchId", insTchBat.getBatchId());
        System.out.println(json.toString());
        return json.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/createInstructorTeachBatch", method = RequestMethod.POST)
    public String process(@RequestParam Map<String, String> data) {
        InstructorTeachBatchDAO createInsTchBat = new InstructorTeachBatchDAO();
        InstructorTeachBatch insTchBat = new InstructorTeachBatch(data.get("userId"), data.get("batchId"));
        boolean status= createInsTchBat.addInsTchBat(insTchBat);
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteInstructorTeachBatch", method = RequestMethod.POST)
    public String delete(@RequestParam Map<String, String> data) {
        InstructorTeachBatchDAO deleteInsTchBat = new InstructorTeachBatchDAO();
        boolean status=deleteInsTchBat.deleteInsTchBat(data.get("userId"), data.get("batchId"));
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllInstructorTeachBatch", method = RequestMethod.GET)
    public String getAll() {
        
        InstructorTeachBatchDAO getInsTchBat = new InstructorTeachBatchDAO();
        ArrayList<InstructorTeachBatch> insTchBatList = getInsTchBat.getAllInsTchBat();
        System.out.println(insTchBatList.toString());
        JSONArray jList = new JSONArray();
        for(InstructorTeachBatch i: insTchBatList) {
            JSONObject jObj = new JSONObject();
            jObj.put("userId", i.getUserId());
            jObj.put("batchId", i.getBatchId());
            jList.put(jObj);
        }
        System.out.println(jList.toString());
        return jList.toString();
    }
}

