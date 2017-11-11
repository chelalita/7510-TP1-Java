package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class KnowledgeBase {
	Parser parser;
	public KnowledgeBase (String db){
		parser = new Parser(db);

	}

	public boolean answer(String query)  {
		if (! queryValido( query )) return false;
		query = query.replaceAll("\\s+","");
		if (parser.getFacts().contains(query) ) return true;
		return (verificarRule(query));

	}

	private boolean verificarRule(String query) {
		String[] elem =  query.substring(0,query.length() -1 ).split("\\(");
		String key = elem [0];
		String[] parametros = elem[1].split("\\,");
		ArrayList<Rule> r = parser.getRules();
		for (int i =0 ; i< r.size(); i++){
			if (r.get(i).compararKey(key) == 0){
				String defReemplazada = r.get(i).reemplazarParams(parametros);
				return (checkFacts(defReemplazada));
			}

		}
		return false;
	}

	private boolean checkFacts(String defReemplazada) {
		String [] rules = defReemplazada.split("\\)\\,");
		for (int i=0 ; i < rules.length ; i++){
			String rule = rules[i];
			if (  i + 1 != rules.length) {
				rule = rules[i] + ")";
			}
			if (! parser.getFacts().contains(rule)) return false;
		}
		return true;
	}

	private boolean queryValido (String s ){
		String regex = "^[a-zA-Z]+\\([a-zA-Z]+(,[a-zA-Z]+)*\\)$";
		return (s.matches(regex));

	}

}
