package com.nibado.example.antlr4.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

/**
 * Generates the datastore script.sql
 */
public class Generator {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String[] WORDS = {"Abibliophobia", "Absquatulate", "Allegator", "Anencephalous", "Argle-bargle", "Batrachomyomachy", "Billingsgate", "Bloviate", "Blunderbuss", "Borborygm", "Boustrophedon", "Bowyang", "Brouhaha", "Bumbershoot", "Callipygian", "Canoodle", "Cantankerous", "Catercornered", "Cockalorum", "Cockamamie", "Codswallop", "Collop", "Collywobbles", "Comeuppance", "Crapulence", "Crudivore", "Discombobulate", "Donnybrook", "Doozy", "Dudgeon", "Ecdysiast", "Eructation", "Fard", "Fartlek", "Fatuous", "Filibuster", "Firkin", "Flibbertigibbet", "Flummox", "Folderol", "Formication", "Fuddy-duddy", "Furbelow", "Furphy", "Gaberlunzie", "Gardyloo", "Gastromancy", "Gazump", "Gobbledygook", "Gobemouche", "Godwottery", "Gongoozle", "Gonzo", "Goombah", "Hemidemisemiquaver", "Hobbledehoy", "Hocus-pocus", "Hoosegow", "Hootenanny", "Jackanapes", "Kerfuffle", "Klutz", "La-di-da", "Lagopodous", "Lickety-split", "Lickspittle", "Logorrhea", "Lollygag", "Malarkey", "Maverick", "Mollycoddle", "Mugwump", "Mumpsimus", "Namby-pamby", "Nincompoop", "Oocephalus", "Ornery", "Pandiculation", "Panjandrum", "Pettifogger", "Pratfall", "Quean", "Rambunctious", "Ranivorous", "Rigmarole", "Shenanigan", "Sialoquent", "Skedaddle", "Skullduggery", "Slangwhanger", "Smellfungus", "Snickersnee", "Snollygoster", "Snool", "Tatterdemalion", "Troglodyte", "Turdiform", "Unremacadamized", "Vomitory", "Wabbit", "Widdershins", "Yahoo"};
    private static final String[] NAMES = {"Alice", "Bob", "Carol", "Edward", "Felicity", "Gerard"};

    public static void generateSqlScript() throws Exception {
        PrintWriter outs = new PrintWriter(new FileWriter("src/main/resources/script.sql"));
        outs.println("CREATE TABLE project (id INTEGER, name VARCHAR(255), teamlead VARCHAR(255), budget INTEGER, startdate DATE, enddate DATE, PRIMARY KEY(id));");
        outs.println("CREATE INDEX project_teamlead ON project(teamlead);");
        outs.println("CREATE INDEX project_startdate ON project(startdate);");
        outs.println("CREATE INDEX project_enddate ON project(enddate);");

        Random random = new Random(42);
        LocalDate now = LocalDate.now();
        for (int i = 0; i < 100; i++) {
            String name = WORDS[random.nextInt(WORDS.length)] + " " + WORDS[random.nextInt(WORDS.length)];
            String teamlead = NAMES[random.nextInt(NAMES.length)];
            int budget = 50000 * (1 + random.nextInt(20));
            LocalDate start = now.plusDays(random.nextInt(180));
            LocalDate end = start.plusDays(random.nextInt(180));

            String sql = String.format(Locale.ROOT, "INSERT INTO project (id, name, teamlead, budget, startdate, enddate) VALUES (%02d%03d, '%s', '%s', %s, '%s', '%s');", random.nextInt(100), i + 1, name, teamlead, budget, FORMATTER.format(start), FORMATTER.format(end));
            outs.println(sql);
        }
        outs.close();
    }

    public static void main(String... argv) throws Exception {
        generateSqlScript();
    }

}
