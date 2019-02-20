/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epitaquizmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author dinsan
 */
public class curd implements curdOperation {

    utility _utility = new utility();

    @Override
    public void create(String FileName, MCQQuestion mcq) {

        try {

            Gson gsonBuilder = new GsonBuilder().create();
            String jsonFromPojo = gsonBuilder.toJson(mcq);

            if (!_utility.checkFileIsExting(_utility.GetPathNmae(FileName))) {
                SaveJsonToFile(_utility.GetPathNmae(FileName), "[" + jsonFromPojo + "]");

            } else {
                AddextingJsonData(_utility.GetPathNmae(FileName), mcq);
            }

            System.out.print("successfully created");
        } catch (Exception e) {
            System.out.println("error on saving data to json" + e.getMessage());
        }

    }

    @Override
    public void update(String ID, String FileName) {
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(_utility.GetPathNmae(FileName)));

            ArrayList< String> list = new ArrayList<>();
            JSONArray jsonArray = (JSONArray) object;
            int len = jsonArray.size();
            if (!jsonArray.isEmpty()) {
                for (int i = 0; i < len; i++) {
                    list.add(jsonArray.get(i).toString());
                }
            }

            for (int i = 0; i < list.size(); i++) {

                String stringToParse = list.get(i);
                JSONObject jsonObject = (JSONObject) parser.parse(stringToParse);

                boolean containsValue = jsonObject.containsValue(ID);

                if (containsValue) {

                    JSONObject xjson = (JSONObject) parser.parse(stringToParse);
                    xjson.put("difficulty", "hardxxxxxxxxx");

                    Gson gsonBuilder = new GsonBuilder().create();
                    String jsonFromPojo = gsonBuilder.toJson(xjson);

                    String AfterRemove = delete("", FileName).toString();

                    String Removedobj = _utility.RemoveFandLstring(AfterRemove);
                    System.out.print(Removedobj);

                    String k = "[" + jsonFromPojo + "," + Removedobj + "]";

                    SaveJsonToFile(_utility.GetPathNmae(FileName), k);

                    System.out.print("successfully updated");
                }

            }

        } catch (IOException | ParseException e) {
            System.out.print(e.getMessage());
        }
    }

    @Override
    public void read(String PathName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> delete(String ID, String FileName) {

        ArrayList< String> list = new ArrayList<>();
        try {

            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(_utility.GetPathNmae(FileName)));

            JSONArray jsonArray = (JSONArray) object;
            int len = jsonArray.size();
            if (!jsonArray.isEmpty()) {
                for (int i = 0; i < len; i++) {
                    list.add(jsonArray.get(i).toString());
                }
            }

            for (int i = 0; i < list.size(); i++) {

                String stringToParse = list.get(i);
                JSONObject jsonObject = (JSONObject) parser.parse(stringToParse);

                boolean containsValue = jsonObject.containsValue(ID);

                if (containsValue) {
                    System.out.println(i + ":" + ID);

                    list.remove(i);
                    SaveJsonToFile(_utility.GetPathNmae(FileName), list.toString());
                    System.out.print("successfully deleted");
                }

            }

        } catch (IOException | ParseException e) {
            System.out.print(e.getMessage());
        }

        return list;
    }

    private void SaveJsonToFile(String filePath, String j_object) {

        try {
            FileWriter file = new FileWriter(filePath);
            file.write(j_object);
            file.flush();
        } catch (IOException e) {
            System.out.println("error on saving data to json" + e.getMessage());
        }

    }

    private void AddextingJsonData(String filePath, MCQQuestion mcq) {

        try {

            //Data from file
            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(filePath));
            String Removedobj = _utility.RemoveFandLstring(object.toString());

            Gson gsonBuilder = new GsonBuilder().create();
            String jsonFromPojo = gsonBuilder.toJson(mcq);

            String k = "[" + Removedobj + "," + jsonFromPojo + "]";

            System.out.println(jsonFromPojo);

            SaveJsonToFile(filePath, k);

        } catch (IOException | ParseException e) {
            System.out.print(e.getMessage());
        }
    }

}
