package ps;

import java.lang.Object;
import java.lang.Throwable;
import java.lang.Exception; 
import java.io.FileReader; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator; 
import java.util.Map; 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*! \brief This class lets the code to read information from the configuration file (json)
 *
 * This class reads the json, store the content and interfaces to retrieve the informations.
 */

public class JsonHandler {

    private String filename; /*!< Name of the json file. */
    private JSONObject jo; /*!< JSON object containing all the parsed information. */
    
    /*! \brief Constructor of the class.
     *
     * Constructor of the class.
     *
     * @param name Name of the cofiguration json file to read.
     */
    
    public JsonHandler (String name) {
	filename = name;
	Object obj;
	try (FileReader reader = new FileReader(filename)){
	    obj = new JSONParser().parse(new FileReader(filename));
	    jo = (JSONObject) obj;
	} catch (FileNotFoundException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
	} catch (ParseException e) {
	    e.printStackTrace();
	}
    }

    public String getMode (){
	return (String)jo.get ("MODE");
    }

}
