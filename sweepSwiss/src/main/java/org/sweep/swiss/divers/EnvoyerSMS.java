package org.sweep.swiss.divers;

public class EnvoyerSMS {

	/*public static String sendSMS(String email, String apikey, String subtype, String senderlabel, String message, String recipient) throws IOException{
	       
	       String result="";
	       String urlParameters = "email=" + email+"&apikey="+apikey+"&message[type]=SMS&message[subtype]="+subtype+"&message[senderlabel]="+senderlabel+"&message[content]="+message+"&message[recipients]="+recipient;
	               
	        URL url = new URL("https://www.smsenvoi.com/httpapi/sendsms/");
	        URLConnection conn = url.openConnection();

	        conn.setDoOutput(true);

	        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

	        writer.write(urlParameters);
	        writer.flush();

	        String line;
	        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

	        while ((line = reader.readLine()) != null) {
	            System.out.println(line);
	            result=result.concat(line).concat("n");
	        }
	        writer.close();
	        
	        reader.close();    
	        
	        
	     return result;
	        
	        
	        
	    }*/
}
