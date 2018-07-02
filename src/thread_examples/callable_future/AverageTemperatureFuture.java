//package thread_examples.callable_future;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import javax.script.Invocable;
//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import javax.script.ScriptException;
//import jdk.nashorn.api.scripting.ScriptObjectMirror;
//
///**
// * Weather application connects to api.openweathermap.org and fetches the current
// * weather information for a number of places. It parses the JSON objects returned
// * by the services, extracts the temperature, displays and computes the average
// * over all places.
// *
// * The program submits the requests for all places concurrently to the web service
// * and processes the responses asynchronously. The temperatures are provided to the
// * main thread as Futures.
// */
//public class AverageTemperatureFuture {
//
//    /** Places for which to retrieve the weather information. */
//    private static final String[] PLACES = {
//            "Geneva,CH", "Lausanne,CH", "Bern,CH", "Biel,CH",
//            "Luzern,CH", "Aarau,CH", "Zurich,CH", "Schaffhausen,CH"
//    };
//
//    /** Prefix of the URL to fetch the weather data. */
//    private static final String URL_PREFIX = "http://api.openweathermap.org/data/2.5/weather?q=";
//
//    /** API Key to use for the web services (limited 60 requests/min). */
//    private static final String APP_ID = "bd5e378503939ddaee76f12ad7a97608";
//
//    /** Offset Kelvin to Celsius for temperature conversion. */
//    private static final double ABSOLUTE_ZERO = 273.15;
//
//    /** Reference to the JavaScript engine in Nashorn that runs the JSON parser. */
//    private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
//
//    /**
//     * Fetch the temperatures of the PLACES concurrently, and process them
//     * as Futures. Computes and prints the average of all readings.
//     */
//    public static void main(String[] args) {
//        ExecutorService executor = Executors.newCachedThreadPool();
//        ArrayList<Future<Double>> temps = new ArrayList<>();
//
//        for (String place : PLACES) {
//            final String thePlace = place;
//            Callable<Double> task = () -> getTemperature(thePlace);
//            temps.add(executor.submit(task));
//        }
//
//        double sum = 0.0;
//        int count = 0;
//        for (int i = 0; i < temps.size(); i++) {
//            try {
//                double temp = temps.get(i).get();
//                sum += temp;
//                count++;
//                System.out.format("%20s: %3.1f C\n", PLACES[i], temp);
//            } catch (ExecutionException e) {
//                System.err.format("%20s: %s\n", PLACES[i], e.getCause().getMessage());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        double avgTemp = sum / count;
//        System.out.format(" average temperature: %3.1f C", avgTemp);
//
//        executor.shutdown();
//        try {
//            executor.awaitTermination(10, TimeUnit.SECONDS);
//        } catch (InterruptedException ignored) {  }
//    }
//
//    /**
//     * Get the current temperature of the the city from the web service.
//     * @param city city too look up the current temperature.
//     * @return current temperature in Celsius
//     * @throws IOException if the request could not be submitted or the response not read back.
//     * @throws JSONParseException if the JSON response object could not be parsed or the
//     *                            temperature information not extracted.
//     */
//    private static double getTemperature(String city) throws IOException, JSONParseException {
//        String json = issueGetRequest(URL_PREFIX + city + "&APPID=" + APP_ID);
//        return extractTemperatureFromJSON(json);
//    }
//
//    /**
//     * Issue a GET request to the webservice and return the response as a string.
//     * @param urlString URL to call
//     * @return response of the call as a string object
//     * @throws IOException any error that happens when connecting to the web services or
//     *        submitting the request.
//     */
//    private static String issueGetRequest(String urlString) throws IOException {
//        URL url = new URL(urlString);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Accept", "application/json");
//
//        if (conn.getResponseCode() != 200) {
//            throw new IOException("Failed : HTTP error code : "
//                    + conn.getResponseCode());
//        }
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                (conn.getInputStream())));
//
//        StringBuilder buffer = new StringBuilder();
//        String line;
//        while ((line = br.readLine()) != null) {
//            buffer.append("\n").append(line);
//        }
//        conn.disconnect();
//
//        return buffer.toString();
//    }
//
//    /**
//     * Use the GSON parser to extract the temperature field from the weather response JSON object.
//     * @param json JSON object to parse
//     * @return temperature in Celsius
//     * @throws JSONParseException if the JSON object could not be parsed, either because it is
//     *  invalid JSON or if did not match the anticipated schema of the weather responses.
//     */
//    private static double extractTemperatureFromJSON(String json) throws JSONParseException {
//        try {
//            ScriptObjectMirror obj = (ScriptObjectMirror)((Invocable) engine).invokeFunction("parse", json);
//            ScriptObjectMirror main = (ScriptObjectMirror)obj.getMember("main");
//            return (Double)main.getMember("temp") - ABSOLUTE_ZERO;
//        } catch(ScriptException | NoSuchMethodException e ) {
//            throw new JSONParseException(e);
//        }
//    }
//
//    /* Instantiate JavaScript Engine and register JavaScript parse function. */
//    static {
//        final String javaScript = "function parse(json) { return JSON.parse(json); }";
//        try {
//            engine.eval(javaScript);
//        } catch (ScriptException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /** JSON Parse Exception is thrown by the JSON Parser. */
//    public static class JSONParseException extends Exception {
//        JSONParseException(Exception exception) {
//            super(exception);
//        }
//    }
//}
//
