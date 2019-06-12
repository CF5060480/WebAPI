package com.atossyntel.webapi;
import com.atossyntel.connection.EmployeeTakeModulesDAO;
import com.atossyntel.entities.EmployeeTakeModule;
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
public class EmployeeTakeModulesController {
    @CrossOrigin
    @RequestMapping(value = "/getEmployeeTakeModule", method = RequestMethod.POST)
    public String display(@RequestParam Map<String, String> data) throws JSONException {
        EmployeeTakeModulesDAO getEmpTakeModules = new EmployeeTakeModulesDAO();
       ArrayList<EmployeeTakeModule> empTakeModule=getEmpTakeModules.getEmployeeTakeModule(data.get("batchId"));
       System.out.println(empTakeModule);
       JSONArray jList = new JSONArray();
        for(EmployeeTakeModule e: empTakeModule) {
            JSONObject jObj = new JSONObject();
            jObj.put("moduleId", e.getMduleId());
            jObj.put("employeeId", e.getEmployeeId());
            jObj.put("batchId", e.getBatchId());
            jObj.put("scores", e.getScores());
            jList.put(jObj);
        }
        System.out.println(jList.toString());
        return jList.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/createEmployeeTakeModule", method = RequestMethod.POST)
    public String process(@RequestParam Map<String, String> data) {
        EmployeeTakeModulesDAO createEmpTakeModules = new EmployeeTakeModulesDAO();
        EmployeeTakeModule empTakeMo = new EmployeeTakeModule(data.get("moduleId"), data.get("employeeId"), data.get("batchId"), Double.parseDouble(data.get("scores")));
        boolean status= createEmpTakeModules.addEmployeeTakeModule(empTakeMo);
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteEmployeeTakeModule", method = RequestMethod.POST)
    public String delete(@RequestParam Map<String, String> data) {
        EmployeeTakeModulesDAO deleteEmpTakeModules = new EmployeeTakeModulesDAO();
        boolean status=deleteEmpTakeModules.deleteEmployeeTakeModule(data.get("moduleId"), 
            data.get("employeeId"), data.get("batchId"));
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/updateEmployeeTakeModule", method = RequestMethod.POST)
    public String update(@RequestParam Map<String, String> data) {
        EmployeeTakeModulesDAO updateEmpTakeModules = new EmployeeTakeModulesDAO();
        EmployeeTakeModule empTakeMo= new EmployeeTakeModule(data.get("moduleId"), data.get("employeeId"),data.get("batchId"),Double.parseDouble(data.get("scores")));
        boolean status = updateEmpTakeModules.updateEmployeeTakeModule(empTakeMo);
        JSONObject jObj = new JSONObject();
        jObj.put("status",status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllEmployeeTakeModule", method = RequestMethod.GET)
    public String getAll() {
        EmployeeTakeModulesDAO getEmpTakeModules = new EmployeeTakeModulesDAO();
        ArrayList<EmployeeTakeModule> empTakeMoList = getEmpTakeModules.getAllEmployeeTakeModule();
        System.out.println(empTakeMoList.toString());
        JSONArray jList = new JSONArray();
        for(EmployeeTakeModule e: empTakeMoList) {
            JSONObject jObj = new JSONObject();
            jObj.put("moduleId", e.getMduleId());
            jObj.put("employeeId", e.getEmployeeId());
            jObj.put("batchId", e.getBatchId());
            jObj.put("scores", e.getScores());
            jList.put(jObj);
        }
        System.out.println(jList.toString());
        return jList.toString();

    }
}

