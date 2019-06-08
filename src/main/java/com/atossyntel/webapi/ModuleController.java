package com.atossyntel.webapi;
import com.atossyntel.connection.ModuleDAO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atossyntel.connection.UserDAO;
import com.atossyntel.entities.Module;
import com.atossyntel.entities.User;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ModuleController {

    @CrossOrigin
    @RequestMapping(value = "/getModule", method = RequestMethod.POST)
    public String display(@RequestParam Map<String, String> data) throws JSONException {
        ModuleDAO getmodule = new ModuleDAO();
        Module module = getmodule.getModule(data.get("moduleId"));
        JSONObject json = new JSONObject();
        json.put("moduleId", module.getModuleId());
        json.put("moduleName", module.getModuleName());
        json.put("categoryId", module.getCategoryId());
        json.put("streamId", module.getStreamId());
        System.out.println(json.toString());
        return json.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/createModule", method = RequestMethod.POST)
    public String process(@RequestParam Map<String, String> data) {
        ModuleDAO createmodule = new ModuleDAO();
        Module module = new Module(data.get("moduleId"), data.get("moduleName"), data.get("categoryId"),data.get("streamId"));
        boolean status = createmodule.addModule(module);
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteModule", method = RequestMethod.POST)
    public String delete(@RequestParam Map<String, String> data) {
        ModuleDAO deletemodule = new ModuleDAO();
        boolean status = deletemodule.deleteModule(data.get("moduleId"));
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/updateModule", method = RequestMethod.POST)
    public String update(@RequestParam Map<String, String> data) {
        ModuleDAO updatemodule = new ModuleDAO();
        Module module = new Module(data.get("moduleId"), data.get("moduleName"), data.get("categoryId"),data.get("streamId"));
        boolean status = updatemodule.updateModule(module);
        JSONObject jObj = new JSONObject();
        jObj.put("status",status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllModules", method = RequestMethod.GET)
    public String getAll() {
        ModuleDAO getmodules = new ModuleDAO();
        ArrayList<Module> moduleList = getmodules.getAllModules();
        JSONArray jList = new JSONArray();
        for(Module m: moduleList) {
            JSONObject jObj = new JSONObject();
            jObj.put("moduleId", m.getModuleId());
            jObj.put("moduleName", m.getModuleName());
            jObj.put("categoryId",m.getCategoryId());
            jObj.put("streamId",m.getStreamId());
            jList.put(jObj);
        }
        System.out.println(jList.toString());
        return jList.toString();

    }
}
