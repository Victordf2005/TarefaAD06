package Clases;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.model.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.BsonDocument;


/**
 *
 * @author Víctor Díaz
 */

public class BaseDatos {
    
    private  ConfigBBDD accesoBBDD;
    private  DB db;
    
    private static final String C_USUARIO = "usuario";  // Nome da colección "usuario"
    private static final String C_MENSAXE = "mensaxe";  // Nome da colección "mensaxe

    private MongoClient mongoClient;
    
    public BaseDatos() {
        cargarConfiguracion();
        abrir();
    }
    
    // Método para abrir a conexión coa base de datos
    private  void abrir() {
        
        // Cadea de conexión á base de datos local, sen autenticarse
        //MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://" + accesoBBDD.getAddress() + ":" + accesoBBDD.getPort()));
    
        // Cadea de conexión á base de datos con autenticación
        mongoClient = new MongoClient(new MongoClientURI("mongodb://" + accesoBBDD.getUsername() +":" + accesoBBDD.getPassword() + "@"
                                                                                  + accesoBBDD.getAddress() + ":" + accesoBBDD.getPort() + "/" + accesoBBDD.getDbname() + "?retryWrites=false"));

        db = mongoClient.getDB(accesoBBDD.getDbname());
    }
    
    // Método para pechar a conexioón coa base de datos
    public void pechar() {
        mongoClient.close();
    }
        
    
    // Método que devolve se xa existe un rexistro co usuario que se lle pasa
    public  boolean existeUsuario(String usuario) {

        boolean retorno;
        
        DBCollection colUsuario = db.getCollection(C_USUARIO);
        DBObject consulta = new BasicDBObject("username", usuario);
        DBCursor cursor = colUsuario.find(consulta);

        retorno = (cursor.count() >0);
        cursor.close();
        
        return retorno;
        
    }
    
    // Método que devolve se hai un rexistro con ese usuario e contrasinal
    public  boolean existeUsuario(String usuario, String contrasinal) {
        
        boolean retorno;
        
        DBCollection colUsuario = db.getCollection(C_USUARIO);
        
        Bson filtro = Filters.and(Filters.eq("username",usuario), Filters.eq("password",contrasinal));
        DBObject consulta = new BasicDBObject(filtro.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        DBCursor cursor = colUsuario.find(consulta);
        
        retorno = (cursor.count() > 0);
        cursor.close();
        
        return retorno;
        
    }
    
    // Método para gravar os datos dun novo usuario rexistrado
    public  void gravarUsuario(Usuario usuario) {
        
        DBCollection colUsuario = db.getCollection(C_USUARIO);
        
        DBObject usu = new BasicDBObject()
                .append("nome", usuario.getNome())
                .append("username", usuario.getUsuario())
                .append("password", usuario.getContrasinal())
                .append("follows", usuario.getSegueA());
        
        colUsuario.insert(usu);
       
    }
    
    // Método que devolve un obxecto Usuario cos datos rexistrados na colección usuario
    public  Usuario getUsuario(String usuario) {
        
        DBCollection colUsuario = db.getCollection(C_USUARIO);
        
        Bson filtro = Filters.eq("username",usuario);
        DBObject consulta = new BasicDBObject(filtro.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        DBCursor cursor = colUsuario.find(consulta);
        
        Usuario usu = null;
        
        while (cursor.hasNext()) {
            DBObject usuAux = cursor.next();
            String sigo = usuAux.get("follows").toString().replace("[", "").replace("]","");
            
            ArrayList<String> sigo3 = new ArrayList<String>(Arrays.asList(sigo.split(", ")));
                                    
            usu = new Usuario(usuAux.get("nome").toString(), usuAux.get("username").toString(),usuAux.get("password").toString(), sigo3);
            
        }
        
        cursor.close();
        return usu;
        
    }
    
    // Método que grava unha nova mensaxe
    public  void enviarMensaxe(String texto, String nomeUsuario, String userUsuario, List hashtags) {
        
        Date data = new Date();
        
        DBCollection colMensaxe = db.getCollection(C_MENSAXE);
        
        DBObject mensaxe = new BasicDBObject()
                .append("text", texto)
                .append("user", new BasicDBObject()
                                .append("nome", nomeUsuario)
                                .append("username", userUsuario))
                .append("date", Instant.now().toString())
                .append("hashtags", hashtags);
        
        colMensaxe.insert(mensaxe);
        
    }
    
    // Método que devolve todas as mensaxes rexistradas
    public ArrayList<MensaxeLista> getMensaxes(int salto, int limite) {
        
        ArrayList<MensaxeLista> mensaxes = new ArrayList<>();
        
        DBCollection colMensaxe = db.getCollection(C_MENSAXE);
        
        DBCollectionFindOptions opcions = new DBCollectionFindOptions();
        Bson projectionAux = Projections.include(Arrays.asList("user.nome", "user.username", "text", "date"));
        DBObject projection = new BasicDBObject(projectionAux.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        opcions.projection(projection);
        
        // lemos un rexistro máis para que saiba se chegou ao final ou hai máis rexistros.
        opcions.limit(limite + 1);          
        opcions.skip(salto);
        
        // establecemos orde de clasificción
        Bson sortAux = Sorts.descending("date");
        DBObject sort = new BasicDBObject(sortAux.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        opcions.sort(sort);
        
        DBCursor cursor = colMensaxe.find(new BasicDBObject(),opcions);
        
        while (cursor.hasNext()) {
            DBObject docAux = cursor.next();
            DBObject docUser = (BasicDBObject) docAux.get("user");
            
            MensaxeLista mensaxe = new MensaxeLista(
                    docUser.get("nome").toString(),
                    docUser.get("username").toString(),
                    docAux.get("text").toString(),
                    docAux.get("date").toString());
            
            mensaxes.add(mensaxe);
        }
        
        cursor.close();
        return mensaxes;
    
    }
    
    // Método que devolve todas as mensaxes rexistradas de usuarios que sigo
    public ArrayList<MensaxeLista> getMensaxesUsuarios(int salto, int limite, ArrayList<String> segueA) {
        
        ArrayList<MensaxeLista> mensaxes = new ArrayList<>();
        
        DBCollection colMensaxe = db.getCollection(C_MENSAXE);
        
        Bson filtro = Filters.in("user.username", segueA);
        DBObject consulta = new BasicDBObject(filtro.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        
        DBCollectionFindOptions opcions = new DBCollectionFindOptions();
        Bson projectionAux = Projections.include(Arrays.asList("user.nome", "user.username", "text", "date"));        
        DBObject projection = new BasicDBObject(projectionAux.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        opcions.projection(projection);
        
        // lemos un rexistro máis para que saiba se chegou ao final ou hai máis rexistros.
        opcions.limit(limite + 1);  
        opcions.skip(salto);
        
        // establecemos orde de clasificción
        Bson sortAux = Sorts.descending("date");
        DBObject sort = new BasicDBObject(sortAux.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        opcions.sort(sort);
        
        DBCursor cursor = colMensaxe.find(consulta, opcions);
        
        
        while (cursor.hasNext()) {
            DBObject docAux = cursor.next();
            DBObject docUser = (BasicDBObject) docAux.get("user");
            
            MensaxeLista mensaxe = new MensaxeLista(
                    docUser.get("nome").toString(),
                    docUser.get("username").toString(),
                    docAux.get("text").toString(),
                    docAux.get("date").toString());
            
            mensaxes.add(mensaxe);
        }
        
        cursor.close();
        return mensaxes;
    
    }
    
    // Método que devolve todas as mensaxes que conteñan un determinado hashtag
    public ArrayList<MensaxeLista> getMensaxesHashtag(int salto, int limite, String hashtag) {
        
        ArrayList<MensaxeLista> mensaxes = new ArrayList<>();
        
        DBCollection colMensaxe = db.getCollection(C_MENSAXE);
        
        DBObject consulta = new BasicDBObject("hashtags", hashtag);
        
        DBCollectionFindOptions opcions = new DBCollectionFindOptions();
        Bson projectionAux = Projections.include(Arrays.asList("user.nome", "user.username", "text", "date"));        
        DBObject projection = new BasicDBObject(projectionAux.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        opcions.projection(projection);
        
        // lemos un rexistro máis para que saiba se chegou ao final ou hai máis rexistros.
        opcions.limit(limite + 1);  
        opcions.skip(salto);
        
        // establecemos orde de clasificción
        Bson sortAux = Sorts.descending("date");
        DBObject sort = new BasicDBObject(sortAux.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        opcions.sort(sort);
        
        DBCursor cursor = colMensaxe.find(consulta, opcions);
        
        while (cursor.hasNext()) {
            DBObject docAux = cursor.next();
            DBObject docUser = (BasicDBObject) docAux.get("user");
            
            MensaxeLista mensaxe = new MensaxeLista(
                    docUser.get("nome").toString(),
                    docUser.get("username").toString(),
                    docAux.get("text").toString(),
                    docAux.get("date").toString());
            
            mensaxes.add(mensaxe);
        }
        
        cursor.close();
        return mensaxes;
    
    }
    
    // Método que devolve a lista de usuarios rexistrados cuiu username
    // coincide en parte ou todo co filtro que se lle pasa
    // se o filtro está baleiro, amosa todos.
    public ArrayList<Usuario> getUsuarios(int salto, int limite, String filtro2) {
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        DBCollection colUsuario = db.getCollection(C_USUARIO);
        
        // se o filtro vén baleiro, creamos un regex que seleccione todos
        if (filtro2.equals("")) { filtro2 = ".*";}
        
        Bson filtro = Filters.regex("username", filtro2);
        DBObject consulta = new BasicDBObject(filtro.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        
        DBCollectionFindOptions opcions = new DBCollectionFindOptions();
        Bson projectionAux = Projections.include(Arrays.asList("nome", "username", "password", "follows"));        
        DBObject projection = new BasicDBObject(projectionAux.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        opcions.projection(projection);
        
        // lemos un rexistro máis para que saiba se chegou ao final ou hai máis rexistros.
        opcions.limit(limite + 1);
        opcions.skip(salto);
        DBCursor cursor = colUsuario.find(consulta, opcions);
        
        while (cursor.hasNext()) {
            DBObject docAux = cursor.next();
            usuarios.add(getUsuario(docAux.get("username").toString()));
        }
        
        cursor.close();
        return usuarios;
    
    }
    
    // Método que actualiza a listaxe de usuarios/as aos/ás que segue
    public void actualizarUsuariosSegueA(String usu, ArrayList<String> seguir) {
        
        DBCollection colUsuario = db.getCollection(C_USUARIO);
        
        Bson filtro = Filters.eq("username",usu);
        DBObject consulta = new BasicDBObject(filtro.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        Bson actualizAux = Updates.set("follows", seguir);        
        DBObject actualiz = new BasicDBObject(actualizAux.toBsonDocument(BsonDocument.class, MongoClient.getDefaultCodecRegistry()));
        colUsuario.update(consulta, actualiz);
        DBCursor cursor = colUsuario.find(new BasicDBObject());
        cursor.close();
        
    }
    
    // Método para cargar a configuraci´n de acceso á base de datos
    private  void cargarConfiguracion() {            

        // Cargamos os datos de configuración de acceso á BBDD
        File arquivoConf = new File("config.json");

        // Comprobamos se existe o arquivo
        if (arquivoConf.exists()) {

            try {
                // Creamos fluxo de datos
                FileReader fluxoDatos = new FileReader(arquivoConf);
                BufferedReader entrada = new BufferedReader(fluxoDatos);

                StringBuilder jsonBuilder = new StringBuilder();
                String linea;

                // Lemos o arquivo liña a liña
                while ((linea = entrada.readLine()) != null) {
                    jsonBuilder.append(linea).append("\n");
                }

                // Pechamos o arquivo
                entrada.close();

                // Construimos a cadea json
                String json = jsonBuilder.toString();
                Gson gson = new Gson();

                // Creamos o obxecto de configuración de acceso á base de datos
                // cos datos lidos.
                accesoBBDD = gson.fromJson(json, ConfigBBDD.class);

            }
            catch (IOException erro) {
                System.out.println("Erro cargando a configuración de conexión á base de datos.");
            }
        }
    }
    
    
}
