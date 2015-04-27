/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static es.ucm.abd.practica2.model.Orientation.HORIZONTAL;
import static es.ucm.abd.practica2.model.Orientation.VERTICAL;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import es.ucm.abd.practica2.dao.AbstractCrosswordDAO;
import es.ucm.abd.practica2.dao.CharConstraint;
import es.ucm.abd.practica2.model.Orientation;

/**
 * Clase base para la ejecución de tests de prueba.
 * 
 * No ejecutes esta clase mediante JUnit. Es una clase abstracta!
 *
 * @author Manuel Montenegro
 * @param <C>
 * @param <W>
 */
abstract public class CrosswordTestBase<C,W> {
    private static final String OTHER_CROSSWORD_TITLE = "Otro crossword de prueba";
    private static final String CROSSWORD_TITLE = "Lenguajes de programación";
    
    private static final String SEARCH_STRING_NOT_FOUND = "macarrones con chorizo";
    private static final String SEARCH_CROSSWORD_TITLE = "enguajes";
    private static final String SEARCH_OTHER_TITLE = "crossword";
    private static final String SEARCH_BOTH = "ro";
    
    private SessionFactory factory;
    private AbstractCrosswordFacade<C,W> facade;
    private AbstractCrosswordDAO<C,W> dao;
    
    private final static String[][] wordStrings = new String[][] {
        {"LUA", "Lenguaje interpretado multiparadigma"},
        {"VALA", "Lenguaje orientado a objetos compilable a C"},
        {"JAVA", "Lenguaje orientado a objetos multiplataforma"},
        {"PROLOG", "Lenguaje lógico"},
        {"PASCAL", "Lenguaje imperativo de los años 70"},
        {"SCALA", "Lenguaje funcional interoperable con Java"},
        {"HASKELL", "Lenguaje funcional puro"},
        {"ECMASCRIPT", "Lenguaje muy utilizado en la web"},
        {"ERLANG", "Lenguaje funcional concurrente"},
        {"PYTHON", "Lenguaje interpretado con tipado dinámico"},
        {"XQUERY", "Lenguaje de acceso a BD XML"},
        {"SQL", "Lenguaje de acceso a BD relacionales"}
    };
    
    private final static String[][] tags = new String[][] {
        {"lenguajes"},
        {"lenguajes", "orientado a objetos"},
        {"lenguajes", "orientado a objetos"},
        {"lenguajes", "logico"},
        {"lenguajes"},
        {"lenguajes", "funcional"},
        {"lenguajes", "funcional"},
        {"lenguajes"},
        {"lenguajes", "funcional"},
        {"lenguajes"},
        {"lenguajes", "bases de datos"},
        {"lenguajes", "bases de datos"}
    };
    
    
    
    private final static int[][] positions = new int[][] {
        {2,6}, {1,7}, {4,8}, {3,11}, {3,11}, {6,10}, {8,6},
        {10,8}, {11,7}, {5,6}, {6,1}, {5,2}
    };
    
    private final static Orientation[] orientations = new Orientation[] {
        HORIZONTAL, VERTICAL, HORIZONTAL, HORIZONTAL, VERTICAL, HORIZONTAL,
        HORIZONTAL, VERTICAL, HORIZONTAL, VERTICAL, HORIZONTAL, VERTICAL
    };
        

    private List<W> words;
    
    private C cw, cw2;
    private int cwId, cwId2;
    private Date date;
    
    public static SessionFactory buildSessionFactory() {
        Configuration config = new Configuration();
        config.configure();
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();
        ssrb.applySettings(config.getProperties());
        StandardServiceRegistry ssr = ssrb.build();
        return config.buildSessionFactory(ssr);
    }
    
    
    private void createCrosswords() {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(2015, GregorianCalendar.APRIL, 1);
        date = cal.getTime();
        
        cw = facade.newCrossword(CROSSWORD_TITLE, date);
        for (int i = 0; i < positions.length; i++) {
            facade.addWordToCrossword(cw, words.get(i), positions[i][0], positions[i][1], orientations[i]);
        }
        
        cw2 = facade.newCrossword(OTHER_CROSSWORD_TITLE, date);
    }

    private void createWords() {
        words = new ArrayList<>();
        for (int i = 0; i < wordStrings.length; i++) {
            W newWord = facade.newDefinition(wordStrings[i][0], wordStrings[i][1], tags[i]);
            words.add(newWord);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        facade = buildFacade();
        dao = facade.createDAO();
        createWords();
        createCrosswords();
    }
    
    @Test
    public void setUpSessionFactory() {
        factory = buildSessionFactory();
    }
    
    @Test
    public void setUpDB() {
    	setUpSessionFactory();
        dao.setSessionFactory(factory);   
        
        for (W word : words) {
           dao.insertWord(word);
        }
        cwId = dao.insertCrossword(cw);
        cwId2 = dao.insertCrossword(cw2);
    }
    
    @After
    public void tearDown() {
        if (factory != null) factory.close();
    }
    
    private <T> void compareModuloPermutation(String str, T[] original, T[] copy) {
        Arrays.sort(original);
        Arrays.sort(copy);
        
        assertArrayEquals(str, original, copy);
    }
    
    private <T extends Comparable<? super T>> void compareModuloPermutation(String str, List<T> otherWords, List<T> myWords) {
        Collections.sort(otherWords);
        Collections.sort(myWords);
        
        assertEquals(str, myWords, otherWords);
    }
    

    @Test
    public void checkWordModelSequences() {
        for (int i = 0; i < words.size(); i++) {
            W currentWord = words.get(i);
            assertEquals("Secuencia de una palabra", wordStrings[i][0], facade.getAnswerOfWord(currentWord));
        }
    }
    
    @Test
    public void checkWordModelDescriptions() {
        for (int i = 0; i < words.size(); i++) {
            W currentWord = words.get(i);
            assertEquals("Descripción de una palabra", wordStrings[i][1], facade.getHintOfWord(currentWord));
        }
    }

    @Test
    public void checkWordModelTags() {
        for (int i = 0; i < words.size(); i++) {
            W currentWord = words.get(i);
            compareModuloPermutation("Tags de una palabra", facade.getTagsOfWord(currentWord), tags[i]);
        }
    }
    
    @Test
    public void checkCrosswordTitle() {
        assertEquals("Título del crucigrama", CROSSWORD_TITLE, facade.getTitleOfCrossword(cw));
    }

    
    public void compareDates(String str, Date expected, Date actual) {
        Calendar cal1 = Calendar.getInstance(), cal2 = Calendar.getInstance();
        cal1.setTime(expected);
        cal2.setTime(actual);
        assertEquals(str, cal1.get(Calendar.YEAR), cal2.get(Calendar.YEAR));
        assertEquals(str, cal1.get(Calendar.MONTH), cal2.get(Calendar.MONTH));
        assertEquals(str, cal1.get(Calendar.DAY_OF_MONTH), cal2.get(Calendar.DAY_OF_MONTH));
    }
    
    @Test
    public void checkCrosswordDate() {
        compareDates("Fecha del crucigrama", date, facade.getDateOfCrossword(cw));
    }
    
    @Test 
    public void checkCrosswordWords() {
        List<String> otherWords = new LinkedList<>();
        List<String> myWords = new LinkedList<>();
        List<Object[]> info = facade.getWordsOfCrossword(cw);
        
        for (Object[] o : info) {
            assertTrue("El primer elemento de los arrays ha de ser una cadena", o[0] instanceof String);
            otherWords.add((String)o[0]);
        }
        for (String[] w: wordStrings) myWords.add(w[0]);
        
        compareModuloPermutation("Palabras de un crucigrama", myWords, otherWords);
    }
    
    @Test
    public void checkCrosswordTitleAfterInsertion() {
        setUpDB();
        this.cw = dao.findCrosswordById(cwId);
        checkCrosswordTitle();
    }
    
    @Test
    public void checkCrosswordDateAfterInsertion() {
        setUpDB();
        this.cw = dao.findCrosswordById(cwId);
        checkCrosswordDate();
    }
    
    @Test
    public void checkCrosswordWordsAfterInsertion() {
        setUpDB();
        this.cw = dao.findCrosswordById(cwId);
        checkCrosswordWords();
    }
    
    
    
    @Test
    public void checkCrosswordByNameNotFound() {
        setUpDB();
        List<Object[]> result = dao.getCrosswordData(SEARCH_STRING_NOT_FOUND);
        assertTrue("Búsqueda de datos por la cadena '" + SEARCH_STRING_NOT_FOUND + "' debe devolver la lista vacía",
                result.isEmpty());
    }
    
    @Test
    public void checkCrosswordByName() {
        setUpDB();
        List<Object[]> result = dao.getCrosswordData(SEARCH_CROSSWORD_TITLE);
        assertEquals("Búsqueda de datos por la cadena '" + SEARCH_CROSSWORD_TITLE + "' debe devolver 1 resultado",
                1, result.size());
        Object[] o = result.get(0);
        checkCrosswordData(o, cwId, CROSSWORD_TITLE, date, 12);
    }
    
    @Test
    public void checkCrosswordByNameOther() {
        setUpDB();
        List<Object[]> result = dao.getCrosswordData(SEARCH_OTHER_TITLE);
        assertEquals("Búsqueda de datos por la cadena '" + SEARCH_OTHER_TITLE + "' debe devolver 1 resultado",
                1, result.size());
        Object[] o = result.get(0);
        System.out.println(o[0] + " " + o[1] + " " + o[2] + " " + o[3]);
        checkCrosswordData(o, cwId2, OTHER_CROSSWORD_TITLE, date, 0);
    }
    
    @Test
    public void checkCrosswordBoth() {
        setUpDB();
        List<Object[]> result = dao.getCrosswordData(SEARCH_BOTH);
        assertEquals("Búsqueda de datos por la cadena '" + SEARCH_BOTH + "' debe devolver 2 resultados",
                2, result.size());
        assertTrue("El primer elemento de los arrays devueltos ha de ser un entero", 
                result.get(0)[0] instanceof Integer && result.get(1)[0] instanceof Integer);
        int principalIndex = 0, otherIndex = 0;
        if (result.get(0)[0].equals(cwId) && result.get(1)[0].equals(cwId2)) {
            principalIndex = 0; otherIndex = 1;
        } else if (result.get(0)[0].equals(cwId2) && result.get(1)[0].equals(cwId)) {
            principalIndex = 1; otherIndex = 0;
        } else {
            fail("La búsqueda por nombre '" + SEARCH_BOTH + "' debe devolver los ids de los dos crucigramas" );
        }
        checkCrosswordData(result.get(principalIndex), cwId, CROSSWORD_TITLE, date, 12);
        checkCrosswordData(result.get(otherIndex), cwId2, OTHER_CROSSWORD_TITLE, date, 0);
    }

    private void checkCrosswordData(Object[] o, int id, String title, Date date, int numWords) {
        assertTrue("El primer elemento del array ha de ser un entero", o[0] instanceof Integer);
        assertEquals("Id de getCrosswordData", id, o[0]);
        assertTrue("El segundo elemento del array ha de ser un String", o[1] instanceof String);
        assertEquals("Título de getCrosswordData", title, o[1]);
        assertTrue("El tercer elemento del array ha de ser una fecha", o[2] instanceof Date);
        compareDates("Fecha de getCrosswordData", date, (Date) o[2]);
        assertTrue("El cuarto elemento del array ha de ser un entero o long", o[3] instanceof Integer || o[3] instanceof Long);
        assertEquals("Nº de palabras de getCrosswordData", (long)numWords, (long)o[3]);
    }

    private void checkWordsByTag(String[] tags, String[] expectedNames) {
        List<W> words = dao.findWordsByTags(tags);
        List<String> wordNames = new LinkedList<>();
        for (W word : words) {
            wordNames.add(facade.getAnswerOfWord(word));
        }
        String[] actualNames = wordNames.toArray(new String[] {});
        compareModuloPermutation("Palabras con las etiquetas [" +
                StringUtils.join(tags, ", ") + "]"
                ,expectedNames, actualNames);
    }
    
    @Test
    public void checkFindByTagsFuncional() {
        setUpDB();
        checkWordsByTag(new String[]{"funcional"}, new String[]{"SCALA", "HASKELL", "ERLANG"});
    }

    @Test
    public void checkFindByTagsEmpty() {
        setUpDB();
        String[] all = new String[wordStrings.length];
        for (int i = 0; i < all.length; i++) all[i] = wordStrings[i][0];
        checkWordsByTag(new String[]{}, all);
    }
    
    @Test
    public void checkFindByTagsTwo() {
        setUpDB();
        checkWordsByTag(new String[]{"lenguajes", "logico"}, new String[] {"PROLOG"});
    }
    
    @Test
    public void checkFindByTagsNone() {
        setUpDB();
        checkWordsByTag(new String[]{"muddafucka"}, new String[] {});
    }
    
    private void checkMatching(CharConstraint[] constraints, String[] expected) {
        List<W> words = dao.getMatchingWords(constraints);
        List<String> wordNames = new LinkedList<>();
        for (W word : words) wordNames.add(facade.getAnswerOfWord(word));
        String[] actual = wordNames.toArray(new String[] {});
        String constraintStr = StringUtils.join(constraints, ", ");
        compareModuloPermutation("Find by " + constraintStr, expected, actual);
    }
    
    @Test
    public void checkMatchingOneChar() {
        setUpDB();
        CharConstraint[] constraints = new CharConstraint[] {
            new CharConstraint(2, 'A')
        };
        checkMatching(constraints, new String[]{"VALA", "JAVA", "PASCAL", "HASKELL"});
    }

    @Test
    public void checkMatchingTwoChars() {
        setUpDB();
        CharConstraint[] constraints = new CharConstraint[] {
            new CharConstraint(2, 'Q'),
            new CharConstraint(6, 'Y')
        };
        checkMatching(constraints, new String[]{"SQL", "XQUERY"});
    }
    
    @Test
    public void checkMatchingThreeChars() {
        setUpDB();
        CharConstraint[] constraints = new CharConstraint[] {
            new CharConstraint(2, 'A'),
            new CharConstraint(6, 'L'),
            new CharConstraint(8, 'L')
        };
        checkMatching(constraints, new String[]{"VALA", "JAVA", "PASCAL"});
    }

    @Test
    public void checkMatchingNone() {
        setUpDB();
        CharConstraint[] constraints = new CharConstraint[] {
            new CharConstraint(1, 'Z')
        };
        checkMatching(constraints, new String[]{});
    }
    
    @Test
    public void checkMatchingNoConstraints() {
        setUpDB();
        CharConstraint[] constraints = new CharConstraint[] {};
        String[] all = new String[wordStrings.length];
        for (int i = 0; i < all.length; i++) all[i] = wordStrings[i][0];
        checkMatching(constraints, all);
    }
    abstract protected AbstractCrosswordFacade<C, W> buildFacade();
}
