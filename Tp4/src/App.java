
public class App {
    static public String [] arrayDeStrings = {
        "https://eltribunodejujuy.com/policiales/2024-9-23-1-0-0-conducia-alcoholizado-y-protagonizo-un-siniestro",
        "https://eltribunodejujuy.com/policiales/2024-9-23-1-0-0-motociclista-murio-luego-de-perder-el-control-y-derrapar",
        "https://eltribunodejujuy.com/policiales/2024-9-23-1-0-0-fue-atacado-a-machetazos-tras-una-discusion-en-la-via-publica",
        "https://eltribunodejujuy.com/policiales/2024-9-23-1-0-0-robaron-un-automovil-y-15-millones",
        "https://eltribunodejujuy.com/policiales/2024-7-22-1-0-0-joven-fue-herido-con-un-arma-blanca-en-un-asalto",
        "https://eltribunodejujuy.com/policiales/2024-9-22-17-55-0-un-hombre-de-33-anos-perdio-la-vida-tras-derrapar-con-su-motocicleta-en-perico",
        "https://eltribunodejujuy.com/policiales/2024-9-22-14-16-0-siniestro-vial-fatal-en-perico-joven-motociclista-pierde-la-vida",
        "https://eltribunodejujuy.com/policiales/2024-9-22-1-0-0-un-nino-de-dos-anos-fue-aplastado-por-una-camioneta",
        "https://eltribunodejujuy.com/policiales/2024-9-22-1-0-0-cae-organizacion-narco-que-operaba-en-el-norte-del-pais",
        "https://eltribunodejujuy.com/policiales/2024-9-22-1-0-0-mechera-detenida-luego-de-robar-a-un-joven-en-la-calle"
    };
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println(arrayDeStrings[0]);
        
        System.out.println(Helper.sacarNota(Helper.sacarContenido(arrayDeStrings[1])));




    }


}
