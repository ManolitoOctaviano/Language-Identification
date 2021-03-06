package de.danielnaber.languagetool.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.tika.language.*;

import javax.swing.JOptionPane;
import de.danielnaber.languagetool.JLanguageTool;

public class LanguageIdentifierTools {

  public static final String[] ADDITIONAL_LANGUAGES = {"be", "ca", "eo", "ro", "sk", "sl", "uk", "ast", "tl", "bik", "ceb", "hil", "ilo", "pag", "pam", "war", "agt", "kak", "agn", "xnn", "blw", "krj", 
  "bkd", "mdh", "bpr", "mmn", "smk", "mta", "lbk", "msk", "mrw", "cbk", "msb", "gad", "hnn",
  "prf", "sml", "ifk", "xsb", "tbk", "itv", "tsg", "ivv", "tiy", "kyb"};
  
  
  public static void addLtProfiles() {
    for (String language : ADDITIONAL_LANGUAGES) {
      addProfile(language);
    }
  }

  private static void addProfile(String language) {
    final String PROFILE_SUFFIX = ".ngp";
    final String PROFILE_ENCODING = "UTF-8";

    try {
      LanguageProfile profile = new LanguageProfile();
      final String languageFilePath = JLanguageTool.getDataBroker().getResourceDir() +
              "/" + language + "/" + language + PROFILE_SUFFIX;


	InputStream stream = JLanguageTool.class.getResourceAsStream(languageFilePath);
		
      try {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(stream, PROFILE_ENCODING));
        String line = reader.readLine();
		
        while (line != null) {
          if (line.length() > 0 && !line.startsWith("#")) {
            int space = line.indexOf(' ');
            profile.add(
                    line.substring(0, space),
                    Long.parseLong(line.substring(space + 1)));
          }
          line = reader.readLine();
        }
      } finally {  
        stream.close();
      }

      LanguageIdentifier.addProfile(language, profile);
    } catch (Exception e) {
      throw new RuntimeException("Failed trying to load language profile for language \"" + language + "\".", e);
    }
  }

}
