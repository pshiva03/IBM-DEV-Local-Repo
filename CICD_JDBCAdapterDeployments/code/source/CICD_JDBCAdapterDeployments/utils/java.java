package CICD_JDBCAdapterDeployments.utils;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.FileReader;
import java.util.Properties;
import java.util.Set;
// --- <<IS-END-IMPORTS>> ---

public final class java

{
	// ---( internal utility methods )---

	final static java _instance = new java();

	static java _newInstance() { return new java(); }

	static java _cast(Object o) { return (java)o; }

	// ---( server methods )---




	public static final void getCurrentEnvKey (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getCurrentEnvKey)>> ---
		// @sigtype java 3.5
		// [i] field:0:required propertyFile
		// [i] field:0:required hostPort
		// [o] field:0:required currentEnvironment
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		//get the property file
		String	propertyFile = IDataUtil.getString( pipelineCursor, "propertyFile" );
		//get the host port string to be matched
		String	hostPort = IDataUtil.getString( pipelineCursor, "hostPort" );
		pipelineCursor.destroy();
		
		//initialize strings
		String allHostPort="";
		String currentEnvironment="";
		
		try {
			//read the file
			FileReader reader = new FileReader(propertyFile);
			//get the properties as key-value pairs
			Properties prop=new Properties();
			prop.load(reader);
			//get all the keys in this properties file
			Set<Object> keys = prop.keySet();
			//loop over all the keys
			for(Object k:keys){
				//get the current key
				String key = (String)k;
				//get the values of this key
				allHostPort=prop.getProperty(key).toString();
				//split the values based on comma as delimiter
				String[] currentHostPort=allHostPort.split(",");
				//loop over the different values obtained
				for(String currHostPort:currentHostPort){
					//check if host port pair found
					if(currHostPort.equalsIgnoreCase(hostPort))
					{
						//set the current key as the environment type
						currentEnvironment=key;
					}
				}
			}
		
		} catch (Exception e) {
			currentEnvironment="";
		}
		
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		
		// map the output
		IDataUtil.put( pipelineCursor_1, "currentEnvironment", currentEnvironment );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}
}

