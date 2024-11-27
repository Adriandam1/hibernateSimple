import config.HibernateConfig;
import model.Gatos;
import org.hibernate.Session;
import services.GatosServices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        printDateTime();
        PostgresVersion();


        List<Gatos> gatosAactualizar= new ArrayList<>();
        GatosServices gatosServices = new GatosServices();

        // Añadiendo gatiños manualmente
        gatosServices.crearGato("Paturras", "Siames", 2, "Beige", new BigDecimal("4.5"), new Date(), true, "Carlos");
        gatosServices.crearGato("Lua", "Persa", 3, "Negra", new BigDecimal("5.2"), new Date(), true, "Maria");
        gatosServices.crearGato("Coco", "Calico", 1, "Tricolor", new BigDecimal("6.0"), new Date(), false, "Manuel");
        gatosServices.crearGato("Pepe", "Abisinio", 4, "Verde", new BigDecimal("4.8"), new Date(), true, "Luis");
        gatosServices.crearGato("Greta", "Europea", 1, "Branco", new BigDecimal("5.5"), new Date(), true, "Manuel");

        System.out.println("Listado de todos os gatos:");
        List<Gatos> gatosList = gatosServices.listarGatos();
        for (Gatos gato : gatosList) {
            System.out.println(gato);
        }

        gatosAactualizar.add(gatosServices.lerGatoPorNomeEDono("Paturras", "Carlos"));
        gatosAactualizar.add(gatosServices.lerGatoPorNomeEDono("Lua", "Maria"));

        for (Gatos gato:gatosAactualizar){
            System.out.println("Ancianificamos a "+gato.getNome());
            gato.setIdade(12);
            gatosServices.actualizarGato(gato);
            System.out.println("Gato Actualizado");
        }

        //EXEMPLOS CON ID que non se deben facer
        /*
        gatosServices.actualizarGato(1L, "Paturras", "Siames", 3, "Beixe", new BigDecimal("4.7"), new Date(), true, "Carlos");
        gatosServices.actualizarGato(2L, "Lua", "Persa", 4, "Branca", new BigDecimal("5.3"), new Date(), true, "Maria");

        gatosServices.lerGato(1L);
        gatosServices.lerGato(2L);
        */


        System.out.println("\nListado de gatos actualizados (Carlos e Maria):");
        Gatos gatoCarlos = gatosServices.lerGatoPorNomeEDono("Paturras", "Carlos");
        Gatos gatoMaria = gatosServices.lerGatoPorNomeEDono("Lua", "Maria");

        if (gatoCarlos != null) {
            System.out.println(gatoCarlos);
        }
        if (gatoMaria != null) {
            System.out.println(gatoMaria);
        }

        // Eliminando gatos polo seu nombre e o seu dono
        gatosServices.eliminarGatoPorNomeEDono("Paturras", "Carlos");
        gatosServices.eliminarGatoPorNomeEDono("Lua", "Maria");

        // Eliminando todos os gatiños co bucle for
        System.out.println("Eliminamos todo-los gatos");
        gatosList = gatosServices.listarGatos();
        for (Gatos gato : gatosList) {
            gatosServices.eliminarGato(gato.getId());
        }

    }
    public  static  void PostgresVersion()
    {
        try(Session session = HibernateConfig.getSessionFactory().openSession())
        {
            Object obj = session.createNativeQuery("SELECT VERSION()").getSingleResult();
            System.out.println(obj);
        }
    }

    public  static  void printDateTime()
    {
        try(Session session = HibernateConfig.getSessionFactory().openSession())
        {
            Object obj = session.createNativeQuery("SELECT NOW()").getSingleResult();
            System.out.println(obj);
        }
    }
}