package sample;

import sample.CSVtoJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class WatsonAPI {



    public static void watson() throws IOException {

        // NOTE: you must manually construct wml_credentials hash map below
        // using information retrieved from your IBM Cloud Watson Machine Learning Service instance.

        Map<String, String> wml_credentials = new HashMap<String, String>()
        {{
            put("url", ""); // add your region's URL
            put("username", ""); // add your username
            put("password", ""); // add your password
        }};

        String wml_auth_header = "Basic " +
                Base64.getEncoder().encodeToString((wml_credentials.get("username") + ":" +
                        wml_credentials.get("password")).getBytes(StandardCharsets.UTF_8));
        String wml_url = wml_credentials.get("url") + "/v3/identity/token";
        HttpURLConnection tokenConnection = null;
        HttpURLConnection scoringConnection = null;
        BufferedReader tokenBuffer = null;
        BufferedReader scoringBuffer = null;
        try {
            // Getting WML token
            URL tokenUrl = new URL(wml_url);
            tokenConnection = (HttpURLConnection) tokenUrl.openConnection();
            tokenConnection.setDoInput(true);
            tokenConnection.setDoOutput(true);
            tokenConnection.setRequestMethod("GET");
            tokenConnection.setRequestProperty("Authorization", wml_auth_header);
            tokenBuffer = new BufferedReader(new InputStreamReader(tokenConnection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = tokenBuffer.readLine()) != null) {
                jsonString.append(line);
            }

            // Scoring request
            URL scoringUrl = new URL("https://us-south.ml.cloud.ibm.com/v3/wml_instances/8d31a5d0-5195-4fde-a79a-7025996c22c7/deployments/68b4900f-5754-47df-b589-d656e26261c9/online");

            String wml_token = "Bearer " +
                    jsonString.toString()
                            .replace("\"","")
                            .replace("}", "")
                            .split(":")[1];
            scoringConnection = (HttpURLConnection) scoringUrl.openConnection();
            scoringConnection.setDoInput(true);
            scoringConnection.setDoOutput(true);
            scoringConnection.setRequestMethod("POST");
            scoringConnection.setRequestProperty("Accept", "application/json");
            scoringConnection.setRequestProperty("Authorization", wml_token);
            scoringConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStreamWriter writer = new OutputStreamWriter(scoringConnection.getOutputStream(), "UTF-8");

            // NOTE: manually define and pass the array(s) of values to be scored in the next line
            //String payload = "{\"fields\": [\"Make\", \"Model\", \"Miles\", \"Year\", \"Days Rented \", \"Number of Repairs\", \"Avg Engine Coolant Temperature(°F)\", \"Avg Engine RPM\", \"Avg Intake Air Temperature(°F)\", \"Avg Engine Load(%)\", \"Avg Throttle Position(Manifold)(%)\"], \"values\": [\"Make\",\"Model\",\"Miles\",\"Year\",\"Days Rented \",\"Number of Repairs\",\"Avg Engine Coolant Temperature(∞F)\",\"Avg Engine RPM\",\"Avg Intake Air Temperature(∞F)\",\"Avg Engine Load(%)\",\"Avg Throttle Position(Manifold)(%)\"],\"values\":[[\"Toyota\",\"Camry\",34000,2015,125,2,45,4000,75,21,21]]}";
            //String payload = "{\"fields\": [\"Make\", \"Model\", \"Miles\", \"Year\", \"Days Rented \", \"Number of Repairs\", \"Avg Engine Coolant Temperature(°F)\", \"Avg Engine RPM\", \"Avg Intake Air Temperature(°F)\", \"Avg Engine Load(%)\", \"Avg Throttle Position(Manifold)(%)\"], \"values\": [\"Make\",\"Model\",\"Miles\",\"Year\",\"Days Rented \",\"Number of Repairs\",\"Avg Engine Coolant Temperature(∞F)\",\"Avg Engine RPM\",\"Avg Intake Air Temperature(∞F)\",\"Avg Engine Load(%)\",\"Avg Throttle Position(Manifold)(%)\"],\"values\":" + CSVtoJSON.readJSon() + "}";
            String payload = "{\"fields\": [\"Make\", \"Model\", \"Miles\", \"Year\", \"Days Rented \", \"Number of Repairs\", \"Avg Engine Coolant Temperature(°F)\", \"Avg Engine RPM\", \"Avg Intake Air Temperature(°F)\", \"Avg Engine Load(%)\", \"Avg Throttle Position(Manifold)(%)\"], \"values\": [\"Make\",\"Model\",\"Miles\",\"Year\",\"Days Rented \",\"Number of Repairs\",\"Avg Engine Coolant Temperature(∞F)\",\"Avg Engine RPM\",\"Avg Intake Air Temperature(∞F)\",\"Avg Engine Load(%)\",\"Avg Throttle Position(Manifold)(%)\"],\"values\":" + inputScreenController.getInput()+ "}";
            writer.write(payload);
            writer.close();

            scoringBuffer = new BufferedReader(new InputStreamReader(scoringConnection.getInputStream()));
            StringBuffer jsonStringScoring = new StringBuffer();
            String lineScoring;
            while ((lineScoring = scoringBuffer.readLine()) != null) {
                jsonStringScoring.append(lineScoring + "\n");
            }

            StringTokenizer output = new StringTokenizer(jsonStringScoring.toString(), ",{}[]\" ");

            int i = 0;
            while (output.hasMoreTokens()) {
               if (i == 54) {
                   TableWindowController.setNoPercent(output.nextToken());
                   i++;
                   continue;
                } else if (i == 55) {
                   TableWindowController.setYesPercent(output.nextToken());
                   i++;
                   continue;
               } else if (i == 57) {
                   TableWindowController.setYesOrNo(output.nextToken());
                   i++;
                   continue;
               }
               output.nextToken();
               i++;
            }

        } catch (IOException e) {
            System.out.println("The URL is not valid.");
            System.out.println(e.getMessage());
        }
        finally {
            if (tokenConnection != null) {
                tokenConnection.disconnect();
            }
            if (tokenBuffer != null) {
                tokenBuffer.close();
            }
            if (scoringConnection != null) {
                scoringConnection.disconnect();
            }
            if (scoringBuffer != null) {
                scoringBuffer.close();
            }
        }
    }
}