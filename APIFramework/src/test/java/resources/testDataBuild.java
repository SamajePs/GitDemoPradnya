package resources;

import Pojo.Location;
import Pojo.addMap;

import java.util.ArrayList;
import java.util.List;

public class testDataBuild {

    public addMap addPlacePayload(String name,String language,String address)
    {
        addMap a=new addMap();
        a.setAccuracy(50);
        a.setName(name);
        a.setPhone_number("(+91)983 893 3937");
        a.setAddress(address);
        a.setWebsite("http://google.com");
        a.setLanguage(language);
        Location l=new Location();
        l.setLng(33.427362);
        l.setLat(-38.383494);
        a.setLocation(l);
        List<String> t=new ArrayList<>();
        t.add("shoe park");
        t.add("shop");
        a.setTypes(t);
        return a;
    }

    public String deletePlacePayload(String placeid)
    {
        String s="{\n" +
                "\n" +
                "    \"place_id\":\""+placeid+"\"\n" +
                "}";
        return s;
    }

    public String updatePlacePayload(String placeid,String address)
    {
        String s="{\n" +
                "\"place_id\":\""+placeid+"\",\n" +
                "\"address\":\""+address+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
        return s;
    }
}

