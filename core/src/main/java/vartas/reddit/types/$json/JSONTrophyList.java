package vartas.reddit.types.$json;

import org.json.JSONArray;
import org.json.JSONObject;
import vartas.reddit.types.Thing;
import vartas.reddit.types.Trophy;
import vartas.reddit.types.TrophyList;

public class JSONTrophyList extends JSONTrophyListTOP{
    public static final String KEY = "trophies";
    @Override
    protected void $fromData(JSONObject source, TrophyList target){
        JSONArray values = source.getJSONArray(KEY);
        for(int i = 0 ; i < values.length() ; ++i)
            target.addData(Thing.from(values.getJSONObject(i)).toTrophy());
    }

    @Override
    protected void $toData(TrophyList source, JSONObject target){
        JSONArray values = new JSONArray();
        for(Trophy data : source.getData())
            values.put(Thing.from(JSONTrophy.toJson(data, new JSONObject()), Thing.Kind.Award));
        target.put(KEY, values);
    }
}
