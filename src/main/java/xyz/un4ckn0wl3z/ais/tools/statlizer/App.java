package xyz.un4ckn0wl3z.ais.tools.statlizer;


import net.andreinc.ansiscape.AnsiClass;
import net.andreinc.ansiscape.AnsiScape;
import net.andreinc.ansiscape.AnsiScapeContext;
import net.andreinc.ansiscape.AnsiSequence;

import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class App {

    /*
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    */

    public static void main(String[] args) throws IOException {

        AnsiScapeContext context = new AnsiScapeContext();
        AnsiClass textRed = AnsiClass.withName("textRed").add(AnsiSequence.RED);
        AnsiClass textGreen = AnsiClass.withName("textGreen").add(AnsiSequence.GREEN);
        AnsiClass textYellow = AnsiClass.withName("textYellow").add(AnsiSequence.YELLOW);
        AnsiClass textBanner = AnsiClass.withName("textBanner").add(AnsiSequence.CYAN);
        AnsiClass Logsz = AnsiClass.withName("Logsz").add(AnsiSequence.BLUE);
        AnsiClass statLizer = AnsiClass.withName("statLizer").add(AnsiSequence.UNDERLINE, AnsiSequence.OVERSTRIKE,AnsiSequence.BOLD);
        context.add(textRed).add(textGreen).add(textYellow).add(textBanner).add(statLizer).add(Logsz);

        AnsiScape ansiScape = new AnsiScape(context);

        String formatBanner = ansiScape.format("{textBanner ========================================================}\n");
        String formatLizer = ansiScape.format("{statLizer                         STATLIZER                       }\n");
        String format1 = ansiScape.format("{textGreen Usage: java -jar <jar-file-name>.jar <logPath>}\n");
        String format2 = ansiScape.format("{textGreen <logPath> can be absolute path or URL}\n");
        String format3 = ansiScape.format("{textYellow Tip: Run this command in log storage server for get URL: python -m SimpleHTTPServer <port>}\n");
        String format4 = ansiScape.format("{textRed Author: A.N.U.W.A.T}\n");

        // Open the file
        //FileInputStream fstream = new FileInputStream("D://DSSBVDA001G_API-TRANSFORM_201806051645.stat");
        if(args.length == 0){
            // String className = this.getClass().getSimpleName();
//            System.out.println("Usage: java -jar <jar-file-name>.jar <logPath>");
//            System.out.println("<logPath> can be absolute path or URL");
//            System.out.println("Tip: Run this command in log storage server for get URL: python -m SimpleHTTPServer <port>");
            System.out.println(formatBanner+formatLizer+formatBanner+format1+format2+format3+format4);
            return;
        }
        BufferedReader br = null;

        try {
            if (args[0].startsWith("http")) {
                URL oracle = new URL(args[0]);
                br = new BufferedReader(new InputStreamReader(oracle.openStream()));
            } else {
                FileInputStream fstream = new FileInputStream(args[0]);
                br = new BufferedReader(new InputStreamReader(fstream));
            }

        }catch (FileNotFoundException ee){
            System.out.println(ansiScape.format("{textRed 404 FUCKING FILE NOT FOUND...}"));
            return;
        }catch (ConnectException ea){
            //System.out.println("CAN'T CONNECT TO: "+args[0]);
            System.out.println(ansiScape.format("{textRed CAN'T CONNECT TO: "+args[0]+"}"));
            return;
        }catch (UnknownHostException eu){
            System.out.println(ansiScape.format("{textRed UNKNOWN HOST...}"));
            return;
        }catch (Exception eq){
            System.out.println(ansiScape.format("{textRed SOMETHING.. WRONG...}"));
            //System.out.println("SOMETHING.. WRONG");
            return;
        }

        String strLine;
        System.out.println(formatBanner+formatLizer+formatBanner);

        try {

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console

                String lastone = strLine.split("\\|")[(strLine.split("\\|")).length - 1];

                if (("Start".equals(lastone)) || ("End".equals(lastone)) || ("".equals(lastone))) {
                    continue;
                }

                if (Integer.parseInt(lastone) > 0) {
                    //System.out.println(strLine);
                    System.out.println(ansiScape.format("{Logsz "+strLine+"}"));
                }

            }

            //Close the input stream
            br.close();

        } catch (Exception e){
            System.out.println(ansiScape.format("{textRed SOMETHING.. WRONG...}"));
            //System.out.println("SOMETHING.. WRONG");
            return;
        }
    }

}
