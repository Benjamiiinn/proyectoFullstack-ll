package com.levelup.proyectoFullstack_ll.config;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.levelup.proyectoFullstack_ll.model.Categoria;
import com.levelup.proyectoFullstack_ll.model.Especificacion;
import com.levelup.proyectoFullstack_ll.model.Plataforma;
import com.levelup.proyectoFullstack_ll.model.Producto;
import com.levelup.proyectoFullstack_ll.repository.CategoriaRepository;
import com.levelup.proyectoFullstack_ll.repository.EspecificacionRepository;
import com.levelup.proyectoFullstack_ll.repository.PlataformaRepository;
import com.levelup.proyectoFullstack_ll.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Configuration
public class DataLoader {

    @Bean
    @Transactional
    CommandLineRunner initData(
            CategoriaRepository categoriaRepository,
            PlataformaRepository plataformaRepository,
            ProductoRepository productoRepository,
            EspecificacionRepository especificacionRepository) {
        
        return args -> {
            // Solo cargamos datos si la base de datos está vacía
            if (productoRepository.count() > 0) {
                System.out.println("La base de datos ya tiene productos. Saltando carga inicial.");
                return;
            }

            System.out.println("Iniciando carga de datos desde products.js...");

            // CREAR CATEGORÍAS
            Categoria consolas = crearCategoria(categoriaRepository, "Consolas");
            Categoria juegos = crearCategoria(categoriaRepository, "Juegos");
            Categoria accesorios = crearCategoria(categoriaRepository, "Accesorios");

            // 2. CREAR PLATAFORMAS
            Plataforma ps5 = crearPlataforma(plataformaRepository, "PlayStation 5");
            Plataforma ps4 = crearPlataforma(plataformaRepository, "PlayStation 4");
            Plataforma xbox = crearPlataforma(plataformaRepository, "Xbox Series X");
            Plataforma switchOled = crearPlataforma(plataformaRepository, "Nintendo Switch");
            Plataforma pc = crearPlataforma(plataformaRepository, "PC");
            

            // 3. MIGRAR PRODUCTOS

            // Consolas
            crearProductoCompleto(productoRepository, especificacionRepository,
                "PlayStation 5",
                700000, // Precio sin puntos
                "La consola de nueva generación de Sony con gráficos 4K...",
                "/src/assets/images/PS5Digital.png",
                15,
                true, // Destacado
                consolas,
                ps5,
                // Aquí copiamos el objeto 'specs' de tu JS como un Mapa
                Map.of(
                    "CPU", "AMD Zen 2, 8 núcleos a 3.5 GHz",
                    "GPU", "AMD RDNA 2, 10.28 TFLOPS",
                    "Memoria", "16GB GDDR6",
                    "Almacenamiento", "825GB SSD",
                    "Resolución", "Hasta 4K a 120Hz",
                    "conectividad", "WiFi 6, Bluetooth 5.1, USB-C",
                    "peso", "4.5 kg",
                    "dimensiones", "390mm x 104mm x 260mm"
                )
            );

            crearProductoCompleto(productoRepository, especificacionRepository,
                "Xbox Series X",
                500000, // Precio sin puntos
                "La consola más potente de Xbox con compatibilidad total hacia atrás.",
                "/src/assets/images/XboxSeriesX.png",
                12,
                true, // Destacado
                consolas,
                xbox,
                // Aquí copiamos el objeto 'specs' de tu JS como un Mapa
                Map.of(
                    "pantalla", "OLED de 7 pulgadas",
                    "resolucion", "1280 x 720 (portátil), 1920 x 1080 (TV)",
                    "Memoria", "16GB GDDR6",
                    "Almacenamiento", "64GB interna",
                    "Resolución", "1280 x 720 (portátil), 1920 x 1080 (TV)",
                    "conectividad", "WiFi, Bluetooth 4.1",
                    "peso", "320g (consola), 422g (con Joy-Cons)",
                    "dimensiones", "242mm x 103mm x 13.9mm"
                )
            );

            crearProductoCompleto(productoRepository, especificacionRepository,
                "Nintendo Switch OLED",
                300000, // Precio sin puntos
                "La versión mejorada de Nintendo Switch con pantalla OLED de 7 pulgadas.",
                "/src/assets/images/XboxSeriesX.png",
                20,
                true, // Destacado
                consolas,
                switchOled,
                // Aquí copiamos el objeto 'specs' de tu JS como un Mapa
                Map.of(
                    "CPU", "AMD Zen 2, 8 núcleos a 3.8 GHz",
                    "GPU", "AMD RDNA 2, 12 TFLOPS",
                    "Memoria", "16GB GDDR6",
                    "Almacenamiento", "1TB SSD NVMe",
                    "Resolución", "Hasta 4K a 120Hz",
                    "conectividad", "WiFi 5, Bluetooth 5.1, HDMI 2.1",
                    "peso", "4.45 kg'",
                    "dimensiones", "301mm x 151mm x 151mm"
                )
            );

            crearProductoCompleto(productoRepository, especificacionRepository,
                "PlayStation 4 Pro",
                400000, // Precio sin puntos
                "La versión mejorada de PlayStation 4 con soporte 4K.",
                "/src/assets/images/PS4Pro.webp",
                8,
                false, // Destacado
                consolas,
                ps4,
                // Aquí copiamos el objeto 'specs' de tu JS como un Mapa
                Map.of(
                    "CPU", "AMD Jaguar, 8 núcleos a 2.1 GHz",
                    "GPU", "4.20 TFLOPS",
                    "Memoria", "8GB GDDR5",
                    "Almacenamiento", "1TB HDD",
                    "Resolución", "Hasta 4K",
                    "conectividad", "WiFi, Bluetooth 4.0",
                    "peso", "3.3 kg",
                    "dimensiones", "295mm x 55mm x 327mm"
                )
            );

            // Juegos PlayStation
            crearProductoCompleto(productoRepository, especificacionRepository,
                "God of War Ragnarök",
                70000,
                "Continúa la épica aventura de Kratos y Atreus...",
                "/src/assets/images/GoWRPS4.jpg",
                30,
                true,
                juegos,
                ps5, // Asignamos la plataforma aquí directo
                Map.of(
                    "Género", "Acción, Aventura",
                    "Jugadores", "1 jugador",
                    "Desarrollador", "Santa Monica Studio",
                    "Clasificación", "M (17+)"
                )
            );

            crearProductoCompleto(productoRepository, especificacionRepository,
                "Spider-Man 2",
                70000,
                "Balancéate por Nueva York con Peter Parker y Miles Morales.",
                "/src/assets/images/Spiderman2.webp",
                30,
                true,
                juegos,
                ps5, // Asignamos la plataforma aquí directo
                Map.of(
                    "Género", "Acción, Aventura",
                    "Jugadores", "1 jugador",
                    "Desarrollador", "Insomniac Games",
                    "Editor" ,"Sony Interactive Entertainment",
                    "añoDeLanzamiento", "2023",
                    "Clasificación", "T (13+)",
                    "idiomas", "Español, Inglés, y más"
                )
            );

            crearProductoCompleto(productoRepository, especificacionRepository,
                "The Last of Us Part II",
                70000,
                "La esperada secuela del aclamado juego de Naughty Dog.",
                "/src/assets/images/TLoU2PS4.webp",
                22,
                false,
                juegos,
                ps5, // Asignamos la plataforma aquí directo
                Map.of(
                    "Género", "Acción, Aventura",
                    "Jugadores", "1 jugador",
                    "Desarrollador", "Naughty Dog",
                    "Editor" ,"Sony Interactive Entertainment",
                    "añoDeLanzamiento", "2020",
                    "Clasificación", "M (17+)",
                    "idiomas", "Español, Inglés, y más"
                )
            );

            //Juegos Xbox
            crearProductoCompleto(productoRepository, especificacionRepository,
                "Halo Infinite",
                60000,
                "El regreso del Jefe Maestro en esta nueva aventura de Halo.",
                "/src/assets/images/HInfiniteXSX.webp",
                20,
                false,
                juegos,
                xbox, // Asignamos la plataforma aquí directo
                Map.of(
                    "Género", "FPS, Acción",
                    "Jugadores", "1 jugador (campaña), Multijugador",
                    "Desarrollador", "343 Industries",
                    "Editor" ,"Xbox Game Studios",
                    "añoDeLanzamiento", "2021",
                    "Clasificación", "T (13+)",
                    "idiomas", "Español, Inglés, y más"
                )
            );

            crearProductoCompleto(productoRepository, especificacionRepository,
                "Starfield",
                70000,
                "Explora el universo en este RPG espacial de Bethesda.",
                "/src/assets/images/Starfield.jpg",
                26,
                true,
                juegos,
                xbox, // Asignamos la plataforma aquí directo
                Map.of(
                    "Género", "RPG, Acción",
                    "Jugadores", "1 jugador",
                    "Desarrollador", "Bethesda Game Studios",
                    "Editor" ,"Bethesda Softworks",
                    "añoDeLanzamiento", "2023",
                    "Clasificación", "M (17+)",
                    "idiomas", "Español, Inglés, y más"
                )
            );

            //Juegos Nintendo
            crearProductoCompleto(productoRepository, especificacionRepository,
                "The Legend of Zelda: Tears of the Kingdom",
                70000,
                "La esperada secuela de Breath of the Wild.",
                "/src/assets/images/ToTK.jpg",
                35,
                true,
                juegos,
                switchOled, // Asignamos la plataforma aquí directo
                Map.of(
                    "Género", "Acción, Aventura",
                    "Jugadores", "1 jugador",
                    "Desarrollador", "Nintendo EPD",
                    "Editor" ,"Nintendo",
                    "añoDeLanzamiento", "2023",
                    "Clasificación", "E10+ (10+)",
                    "idiomas", "Español, Inglés, y más"
                )
            );

            crearProductoCompleto(productoRepository, especificacionRepository,
                "Super Mario Odyssey",
                60000,
                "Acompaña a Mario en una aventura por todo el mundo.",
                "/src/assets/images/SMOdyssey.webp",
                24,
                false,
                juegos,
                switchOled, // Asignamos la plataforma aquí directo
                Map.of(
                    "Género", "Plataformas, Aventura",
                    "Jugadores", "1-2 jugadores",
                    "Desarrollador", "Nintendo EPD",
                    "Editor" ,"Nintendo",
                    "añoDeLanzamiento", "2017",
                    "Clasificación", "E10+ (10+)",
                    "idiomas", "Español, Inglés, y más"
                )
            );

            //Accesorios
            crearProductoCompleto(productoRepository, especificacionRepository,
                "DualSense Wireless Controller",
                70000,
                "El innovador controlador de PS5 con retroalimentación háptica.",
                "/src/assets/images/dualsense.webp",
                40,
                false,
                accesorios,
                pc, // Asignamos la plataforma aquí directo
                Map.of(
                    "compatibilidad", "PS5, PC",
                    "memoria", "16GB GDDR6",
                    "conectividad", "Bluetooth, USB-C",
                    "batería", "Hasta 12 horas",
                    "peso", "280g",
                    "dimensiones", "160mm x 66mm x 106mm",
                    "colores", "Blanco, Negro, Rojo, Azul"
                )
            );

            crearProductoCompleto(productoRepository, especificacionRepository,
                "Nintendo Switch Pro Controller",
                70000,
                "Control profesional para Nintendo Switch.",
                "/src/assets/images/SwitchProController1.jfif",
                25,
                false,
                accesorios,
                switchOled, // Asignamos la plataforma aquí directo
                Map.of(
                    "compatibilidad", "Nintendo Switch",
                    "conectividad", "Bluetooth, USB-C",
                    "batería", "Hasta 40 horas",
                    "caracteristicas", "Giroscopio, HD Rumble, NFC",
                    "peso", "246g",
                    "dimensiones", "1152mm x 106mm x 60mm",
                    "colores", "Negro"
                )
            );
            System.out.println("Carga de datos completada con éxito.");
        };
    }

    private Categoria crearCategoria(CategoriaRepository repo, String nombre) {
        Categoria c = new Categoria();
        c.setNombre(nombre);
        return repo.save(c);
    }

    private Plataforma crearPlataforma(PlataformaRepository repo, String nombre) {
        Plataforma p = new Plataforma();
        p.setNombre(nombre);
        return repo.save(p);
    }

    private void crearProductoCompleto(
            ProductoRepository prodRepo,
            EspecificacionRepository specRepo,
            String nombre, Integer precio, String desc, String img, Integer stock, boolean destacado,
            Categoria cat, Plataforma plat,
            Map<String, String> especificaciones) {

        // 1. Guardar Producto
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setDescripcion(desc);
        p.setImagen(img);
        p.setStock(stock);
        p.setDestacado(destacado);
        p.setCategoria(cat);
        p.setPlataforma(plat);
        
        Producto productoGuardado = prodRepo.save(p);

        // 2. Guardar Especificaciones (Iteramos el mapa)
        especificaciones.forEach((clave, valor) -> {
            Especificacion spec = new Especificacion();
            spec.setNombre(clave);          // Ej: "CPU"
            spec.setValorEspecifico(valor); // Ej: "AMD Zen 2"
            spec.setProducto(productoGuardado);
            specRepo.save(spec);
        });
    }
}
