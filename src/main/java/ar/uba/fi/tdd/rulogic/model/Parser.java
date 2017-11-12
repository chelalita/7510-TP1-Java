package ar.uba.fi.tdd.rulogic.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.exit;

public class Parser {
    ArrayList <String> facts ;
    ArrayList <Rule> rules;
    List<String> lineas;

    public Parser( String DB) {
        try (Stream<String> baseLineas = Files.lines(
                Paths.get(ClassLoader.getSystemResource(DB).toURI()))) {
            this.facts = new ArrayList<String>();
            this.rules = new ArrayList<Rule>();
            this.lineas = baseLineas.collect(Collectors.toList());
            if (!this.acomodarDB()) {
                System.out.println("La base no esta correctamente confeccionada");
                exit(0);
            }
        }
        catch (NullPointerException | IOException | URISyntaxException e) {
            System.out.println("hubo un problema con el archivo de data base");
        }
    }

    public ArrayList<String> getFacts(){
        return facts;
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    private boolean acomodarDB() {
        for (int i = 0; i < this.lineas.size(); i++) {
            String l = this.lineas.get(i).trim();
            if (!l.contains(".") | !l.contains("(") | !l.contains(")")) {
                return false;
            }
            this.identificarTipo (l.replaceAll("\\s+","").replaceAll("\\.",""));
        }
        return true;
    }


    private void identificarTipo(String s) {

        if (esRule(s)) {
            guardarRule(s);
        }
        else
            this.facts.add(s);
        }

    private void guardarRule(String s) {
        Rule r = new Rule(s);
        rules.add(r);
    }

    private boolean esRule(String s){
        return( s.contains(":-"));

    }
}
