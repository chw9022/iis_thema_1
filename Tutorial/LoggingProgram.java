import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class LoggingProgram {

    private static Logger logger = Logger.getRootLogger();
    
    public static void main(String[] args)
    {
        
        try {
            SimpleLayout layout = new SimpleLayout();
            ConsoleAppender consoleAppender = new ConsoleAppender( layout );
            logger.addAppender( consoleAppender );
            FileAppender fileAppender = new FileAppender( layout, "logs/MeineLogDatei.log", false );
            logger.addAppender( fileAppender );
            // ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
            logger.setLevel( Level.WARN );
          } catch( Exception ex ) {
            System.out.println( ex );
          }
          logger.debug( "Meine Debug-Meldung" );
          logger.info(  "Meine Info-Meldung"  );
          logger.warn(  "Meine Warn-Meldung"  );
          logger.error( "Meine Error-Meldung" );
          logger.fatal( "Meine Fatal-Meldung" );
        
    }

}
