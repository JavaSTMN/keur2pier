package com.stmn.keur2pier.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONUtils {

    public static JSONObject readJSON(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        return (JSONObject) obj;
    }

    public static void writeJSON(JSONObject jsonObject, String path) throws IOException {
        FileWriter file = new FileWriter(path);
        file.write(jsonObject.toJSONString());
        file.flush();
    }

}
