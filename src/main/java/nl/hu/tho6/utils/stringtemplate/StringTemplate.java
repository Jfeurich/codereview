package nl.hu.tho6.utils.stringtemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nathan on 21/01/2015.
 */
public class StringTemplate {
    private String content;
    private HashMap<String,String> attributes;

    public StringTemplate(String content){
        this.content = content;
        attributes = new HashMap<String, String>();
        fillList();
    }

    public HashMap<String,String> getAttributes(){
        return attributes;
    }

    public boolean setAttribute(String attribute, String value){
        boolean result = checkAttribute(attribute);
        if(result){
            attributes.put(attribute,value);
        }
        return result;
    }

    private void fillList(){
        ArrayList<String> tags = new ArrayList<String>();
        Pattern p = Pattern.compile("\\[([^\\]]+)]");
        Matcher m = p.matcher(content);

        while (m.find()) {
            tags.add(m.group(1));
        }
        for(String s : tags){
            attributes.put(s,"empty");
        }
    }

    public boolean checkAttribute(String attribute){
        boolean result = false;
        for(String s : attributes.keySet()){
            if(s.equals(attribute)){
                result = true;
            }
        }
        return result;
    }

    public boolean checkAllAttributes(){
        boolean result = true;
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            if(entry.getValue().equals("empty")){
                result = false;
            }
        }
        return result;
    }

    public String render(){
        if(checkAllAttributes()){
            String result = "";
            content = content.replaceAll("\\]", "\\[");
            String[] rs = content.split("\\[");
            for (int i = 0; i < rs.length; i++) {
                if (i % 2 == 0) {
                    result += rs[i];
                } else {
                    result += attributes.get(rs[i]);
                }
            }
            return result;
        } else {
           return "Not all attributes have a value yet";
        }
    }
}