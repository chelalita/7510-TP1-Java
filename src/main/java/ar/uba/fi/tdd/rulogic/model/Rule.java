package ar.uba.fi.tdd.rulogic.model;

public class Rule {
    String key;
    String[] parameters;
    String def;

    public Rule(String s) {
        String[] elem = s.split(":-");
        this.def = elem[1];
        String[] sep = elem[0].split("\\(");
        this.key = sep[0];
        this.parameters = sep[1].substring(0,sep[1].length()-1).split("\\,");
    }


    public int compararKey(String key) {
        return (this.key.compareTo(key));
    }


    public String reemplazarParams(String[] parametros) {
        String s = def;
        for(int i =0 ; i < parametros.length ; i++){
            s= s.replaceAll(parameters[i],parametros[i]);
        }
        return s;
    }
}
