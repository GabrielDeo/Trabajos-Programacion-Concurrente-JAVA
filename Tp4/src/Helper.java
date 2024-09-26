import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Helper {
    public static StringBuilder contenido;


    public static String sacarContenido(String enlace){
        try {
            // URL de la página web que quieres consultar
            URL url = new URL(enlace);
            
            // Abrir la conexión
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            
            // Leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String inputLine;
            contenido = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                contenido.append(inputLine);
            }
            
            // Cerrar las conexiones
            in.close();
            conexion.disconnect();
            
            // Imprimir el contenido de la respuesta
        } catch (Exception e) {
            e.printStackTrace();
        }
        return(contenido.toString());
    }
    
    public static String sacarNota(String contenido) {
        int comienzoIndice1 = contenido.indexOf("<div amp-access=\"mostrarNota\">" );
        int comienzoIndice2 = contenido.indexOf("<p>"  , comienzoIndice1);
        int finalIndice = contenido.indexOf("</p>", comienzoIndice1);
        String nota = contenido.substring(comienzoIndice2+3, finalIndice);
        return nota;
    }



}
