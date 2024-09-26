import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class TareaNoticias implements Runnable {

    private String[] link;
    private int id;
    public TareaNoticias(String[] enlaces, int id) {
        this.link = enlaces;
        this.id = id;
    }


    public void run() {
        try {
            String enlace = link[id];
            System.out.println("El hilo " + id + " obteniendo noticia del enlace: " + enlace);
            String html = realizarHttpRequest(enlace);
            String cuerpoNoticia = extraerCuerpoNoticia(html);
            System.out.println("El hilo " + id + " encontro la noticia: ");
            System.out.println(cuerpoNoticia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //lit esto no entendi nada pero hace lo que pide :D
    private String realizarHttpRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return content.toString();
    }

    private String extraerCuerpoNoticia(String html) {
        String inicioDiv = "<div amp-access=\"mostrarNota\">";
        String finDiv = "</div>";

        int inicioIndex = html.indexOf(inicioDiv);
        if (inicioIndex != -1) {
            int finIndex = html.indexOf(finDiv, inicioIndex);
            if (finIndex != -1) {
                return extraerYUnir(html.substring(inicioIndex + inicioDiv.length(), indiceFinal(html, inicioIndex)));
                //return html.substring(inicioIndex + inicioDiv.length(), indiceFinal(html, inicioIndex));
            }
        }
        return null;
    }

    public static String extraerYUnir(String input) {
        StringBuilder resultado = new StringBuilder();
        String inicioTag = "<p>";
        String finTag = "</p>";
        int inicioIndex = 0;
        int finIndex = 0;

        while ((inicioIndex = input.indexOf(inicioTag, finIndex)) != -1) {
            inicioIndex += inicioTag.length();
            finIndex = input.indexOf(finTag, inicioIndex);
            if (finIndex != -1) {
                resultado.append(input, inicioIndex, finIndex).append('\n');
            }
        }

        return resultado.toString().trim();  // Elimina espacios en blanco al inicio y al final
    }

    public int indiceFinal(String cuerpo, int indiceInicial){
        int contador = 0;
        int indiceFinal = indiceInicial;
        String a = "<div";
        String b = "</div";
        do{
            if (cuerpo.indexOf(a, indiceFinal) != -1) {
                contador++;
                indiceFinal= cuerpo.indexOf(a, indiceFinal)+4;
            }
            if (cuerpo.indexOf(b, indiceFinal) != -1) {
                contador--;
                indiceFinal= cuerpo.indexOf(b, indiceFinal)+5;
            }
            else{
                break;
            }
        }while(contador == 0);
        return indiceFinal;
    }


}


public class Noti {
    public static void main(String[] args) {
        List<Thread> hilos = new ArrayList<>();
        Instant tiempoInicial = Instant.now();

        String[] linked = {
                "https://eltribunodejujuy.com/policiales/2024-9-24-20-6-0-fue-agredido-de-un-botellazo-en-la-cabeza",
                "https://eltribunodejujuy.com/policiales/2024-9-25-12-34-0-volco-la-camioneta-de-la-comision-municipal-de-tres-cruces",
                "https://eltribunodejujuy.com/politica/2024-9-16-19-20-0-hoy-comienza-los-operativos-de-limpieza-en-rio-blanco",
                "https://eltribunodejujuy.com/policiales/2024-9-24-22-31-0-fue-condenado-por-un-intento-de-asesinato",
                "https://eltribunodejujuy.com/policiales/2024-9-24-22-31-0-defendio-a-una-mujer-d-un-robo-y-lo-apunalaron",
                "https://eltribunodejujuy.com/policiales/2024-9-24-20-6-0-pareja-fue-asaltada-y-herida-con-un-elemento-cortante",
                "https://eltribunodejujuy.com/policiales/2024-9-24-19-36-0-piden-que-un-condenado-sea-trasladado-a-otra-carcel",
                "https://eltribunodejujuy.com/policiales/2024-9-24-9-39-0-condenado-a-6-anos-de-prision-por-intento-de-homicidio",
                "https://eltribunodejujuy.com/policiales/2024-9-24-0-55-0-transportistas-salvan-sus-vidas-tras-un-vuelco",
                "https://eltribunodejujuy.com/policiales/2024-9-24-0-54-0-perdio-el-control-de-su-auto-volco-y-fue-hospitalizado",
        };

        for (int i = 0; i < 10; i++) {
            TareaNoticias tarea = new TareaNoticias(linked, i);
            Thread hilo = new Thread(tarea);
            hilos.add(hilo);
            hilo.start();
        }
        
        for (Thread h : hilos) {
            try {
                h.join();
            } catch (Exception e) {
                System.err.println("error en join()");
            }
        }

        Instant tiempoFinal = Instant.now();

        // Calcula la duración
        Duration duracion = Duration.between(tiempoInicial, tiempoFinal);
        System.out.println("El tiempo de ejecución fue: " + duracion.toMillis() + " ms");
    }
}

