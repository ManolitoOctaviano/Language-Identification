/* LanguageTool, a natural language style checker 
 * Copyright (C) 2005 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package de.danielnaber.languagetool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import de.danielnaber.languagetool.language.*;
import de.danielnaber.languagetool.rules.Rule;
import de.danielnaber.languagetool.rules.patterns.Unifier;
import de.danielnaber.languagetool.synthesis.Synthesizer;
import de.danielnaber.languagetool.tagging.Tagger;
import de.danielnaber.languagetool.tagging.disambiguation.Disambiguator;
import de.danielnaber.languagetool.tagging.disambiguation.xx.DemoDisambiguator;
import de.danielnaber.languagetool.tagging.xx.DemoTagger;
import de.danielnaber.languagetool.tokenizers.SentenceTokenizer;
import de.danielnaber.languagetool.tokenizers.Tokenizer;
import de.danielnaber.languagetool.tokenizers.WordTokenizer;
import de.danielnaber.languagetool.tools.StringTools;

/**
 * Base class for any supported language (English, German, etc).
 * 
 * @author Daniel Naber
 */
public abstract class Language {

  // NOTE: keep in sync with array below!
  public static final Language BRETON = new Breton();
  //public final static Language CZECH = new Czech();
  public static final Language CHINESE = new Chinese();
  public static final Language DANISH = new Danish();
  public static final Language DUTCH = new Dutch();
  public static final Language ENGLISH = new English();
  public static final Language ESPERANTO = new Esperanto();
  public static final Language FRENCH = new French();
  public static final Language GERMAN = new German();
  public static final Language ITALIAN = new Italian();
  public static final Language KHMER = new Khmer();
  public static final Language LITHUANIAN = new Lithuanian();
  public static final Language POLISH = new Polish();
  public static final Language SLOVAK = new Slovak();
  public static final Language SLOVENIAN = new Slovenian();
  public static final Language SPANISH = new Spanish();
  public static final Language SWEDISH = new Swedish();
  public static final Language UKRAINIAN = new Ukrainian();
  public static final Language RUSSIAN = new Russian();
  public static final Language ROMANIAN = new Romanian();
  public static final Language ICELANDIC = new Icelandic();
  //public static final Language GALICIAN = new Galician();
  public static final Language CATALAN = new Catalan();
  public static final Language MALAYALAM = new Malayalam();
  public static final Language BELARUSIAN = new Belarusian();
  public static final Language ASTURIAN = new Asturian();

  public static final Language BIKOL = new Bikol();
  public static final Language CEBUANO = new Cebuano();
  public static final Language HILIGAYNON = new Hiligaynon();
  public static final Language ILOCANO = new Ilocano();
  public static final Language KAPAMPANGAN = new Kapampangan();
  public static final Language PANGASINENSE = new Pangasinense();
  public static final Language TAGALOG = new Tagalog();
  public static final Language WARAY = new Waray();
  
  public static final Language DEMO = new Demo();
  
  // Manolito
  public static final Language AGTA = new Agta();
  public static final Language KALLAHAN = new Kallahan();
  public static final Language AGUTAYNEN = new Agutaynen();
  public static final Language KANKANAY = new Kankanay();
  public static final Language BALANGAO = new Balangao();
  public static final Language KINARAY_A = new Kinaray_a();
  public static final Language BINUKID = new Binukid();
  public static final Language MAGUINDANAO = new Maguindanao();
  public static final Language BLAAN = new Blaan();
  public static final Language MAMANWA = new Mamanwa();
  public static final Language BOLINAO = new Bolinao();
  public static final Language MANOBO = new Manobo();
  public static final Language BONTOK = new Bontok();
  public static final Language MANSAKA = new Mansaka();
  public static final Language MARANAO = new Maranao();
  public static final Language CHAVACANO = new Chavacano();
  public static final Language MASBATENYO = new Masbatenyo();
  public static final Language GADDANG = new Gaddang();

  public static final Language HANUNOO = new Hanunoo();
  public static final Language PARANAN = new Paranan();
  public static final Language SAMAL = new Samal();
  public static final Language IFUGAO = new Ifugao();
  public static final Language SAMBAL = new Sambal();
  public static final Language ISNEG = new Isneg();
  public static final Language TAGBANWA = new Tagbanwa();
  public static final Language ITAWIT = new Itawit();
  public static final Language TAUSUG = new Tausug();
  public static final Language IVATAN = new Ivatan();
  public static final Language TIRURAY = new Tiruray();
  public static final Language KALINGA = new Kalinga();

  private static List<Language> externalLanguages = new ArrayList<Language>();
  
  
  // Manolito
  /**
   * All languages supported by LanguageTool.
   
  public static Language[] LANGUAGES = {
    ASTURIAN, BELARUSIAN, CATALAN, DANISH, GERMAN, ENGLISH, SPANISH, FRENCH, ITALIAN, DUTCH, POLISH, RUSSIAN, SWEDISH, TAGALOG,
    BIKOL, CEBUANO, HILIGAYNON, ILOCANO, KAPAMPANGAN, PANGASINENSE, WARAY,
    DEMO,
	AGTA, KALLAHAN, AGUTAYNEN, KANKANAY, BALANGAO, KINARAYA, BINUKID, MAGUINDANAO, BLAAN, MAMANWA,
	BOLINAO, MANOBO, BONTOK, MANSAKA, MARANAO, CHAVACANO, MASBATENYO, GADDANG
  };*/

  public static Language[] LANGUAGES = {
    TAGALOG, DEMO, ENGLISH,
    BIKOL, CEBUANO, HILIGAYNON, ILOCANO, KAPAMPANGAN, PANGASINENSE, WARAY,
	AGTA, KALLAHAN, AGUTAYNEN, KANKANAY, BALANGAO, KINARAY_A, BINUKID, MAGUINDANAO, BLAAN, MAMANWA,
	BOLINAO, MANOBO, BONTOK, MANSAKA, MARANAO, CHAVACANO, MASBATENYO, GADDANG, HANUNOO, PARANAN,
	SAMAL, IFUGAO, SAMBAL, ISNEG, TAGBANWA, ITAWIT, TAUSUG, IVATAN, TIRURAY, KALINGA
  };
  
  
  /**
   * All languages supported by LanguageTool, but without the demo language.
   */
  public static final Language[] REAL_LANGUAGES = new Language[LANGUAGES.length-1];
  static {
    int i = 0;
    for (final Language lang : LANGUAGES) {
      if (lang != DEMO) {
        REAL_LANGUAGES[i] = lang;
        i++;
      }
    }
  }

  private static final Language[] BUILTIN_LANGUAGES = LANGUAGES;

  private static final Disambiguator DEMO_DISAMBIGUATOR = new DemoDisambiguator();
  private static final Tagger DEMO_TAGGER = new DemoTagger();
  private static final SentenceTokenizer SENTENCE_TOKENIZER = new SentenceTokenizer();
  private static final WordTokenizer WORD_TOKENIZER = new WordTokenizer();
  private static final Unifier MATCH_UNIFIER = new Unifier();

  // -------------------------------------------------------------------------

  /**
   * Get this language's two character code, e.g. <code>en</code> for English.
   * @return String - language code
   */
  public abstract String getShortName();

  /**
   * Get this language's name in English, e.g. <code>English</code> or <code>German</code>.
   * @return String - language name
   */
  public abstract String getName();
  
  /**
   * Get this language's variants, e.g. <code>US</code> (as in <code>en_US</code>) or
   * <code>PL</code> (as in <code>pl_PL</code>).
   * @return String[] - array of country variants for the language.
   */
  public abstract String[] getCountryVariants();
  
  /**
   * Get this language's Java locale.
   */
  public abstract Locale getLocale();

  /**
   * Get the name(s) of the maintainer(s) for this language or <code>null</code>.
   */
  public abstract Contributor[] getMaintainers();

  /**
   * Get the rules classes that should run for texts in this language.
   * @since 1.4
   */
  public abstract List<Class<? extends Rule>> getRelevantRules();

  // -------------------------------------------------------------------------

  /**
   * Get the location of the rule file.
   */
  public String getRuleFileName() {
    return JLanguageTool.getDataBroker().getRulesDir() + "/" + getShortName() + "/" + JLanguageTool.PATTERN_FILE;
  }

  /**
   * Get this language's part-of-speech disambiguator implementation.
   */
  public Disambiguator getDisambiguator() {
    return DEMO_DISAMBIGUATOR;
  }

  /**
   * Get this language's part-of-speech tagger implementation.
   */
  public Tagger getTagger() {
    return DEMO_TAGGER;
  }

  /**
   * Get this language's sentence tokenizer implementation.
   */
  public SentenceTokenizer getSentenceTokenizer() {
    return SENTENCE_TOKENIZER;
  }

  /**
   * Get this language's word tokenizer implementation.
   */
  public Tokenizer getWordTokenizer() {
    return WORD_TOKENIZER;
  }

  /**
   * Get this language's part-of-speech synthesizer implementation or <code>null</code>.
   */
  public Synthesizer getSynthesizer() {
    return null;
  }

  /**
   * Get this language's feature unifier.
   * @return Feature unifier for analyzed tokens.
   */
  public Unifier getUnifier() {
    return MATCH_UNIFIER;
  }
  
  /**
   * Get this language's feature unifier used for disambiguation.
   * Note: it might be different from the normal rule unifier.
   * @return Feature unifier for analyzed tokens.
   */
  public Unifier getDisambiguationUnifier() {
    return MATCH_UNIFIER;
  }
  
  /**
   * Get the name of the language translated to the current locale,
   * if available. Otherwise, get the untranslated name.
   */
  public final String getTranslatedName(final ResourceBundle messages) {
    try {
      return messages.getString(getShortName());
    } catch (final MissingResourceException e) {
      return getName();
    }
  }

  /**
   * Start symbols used by {@link de.danielnaber.languagetool.rules.GenericUnpairedBracketsRule}.
   * Note that the array must be of equal length as {@link #getUnpairedRuleEndSymbols()} and the sequence of
   * starting symbols must match exactly the sequence of ending symbols.
   */
  public String[] getUnpairedRuleStartSymbols() {
    return new String[]{ "[", "(", "{", "\"", "'" };
  }

  /**
   * End symbols used by {@link de.danielnaber.languagetool.rules.GenericUnpairedBracketsRule}.
   * @see #getUnpairedRuleStartSymbols()
   */
  public String[] getUnpairedRuleEndSymbols() {
    return new String[]{ "]", ")", "}", "\"", "'" };
  }
  
  // -------------------------------------------------------------------------
  
  /**
   * Re-inits the built-in languages and adds the specified ones.
   */
  public static void reInit(final List<Language> languages) {
    LANGUAGES = new Language[BUILTIN_LANGUAGES.length + languages.size()];
    int i = BUILTIN_LANGUAGES.length;
    System.arraycopy(BUILTIN_LANGUAGES, 0,
        LANGUAGES, 0, BUILTIN_LANGUAGES.length);
    for (final Language lang : languages) {
      LANGUAGES[i++] = lang;
    }
    externalLanguages = languages;
  }

  /**
   * Return languages that are not built-in but have been added manually.
   */
  public static List<Language> getExternalLanguages() {
    return externalLanguages;
  }
  
  /**
   * Get the Language object for the given short language name.
   * 
   * @param shortLanguageCode e.g. <code>en</code> or <code>de</code>
   * @return a Language object or <code>null</code>
   */
  public static Language getLanguageForShortName(final String shortLanguageCode) {
    StringTools.assureSet(shortLanguageCode, "shortLanguageCode");
    if (shortLanguageCode.length() != "xx".length() && shortLanguageCode.length() != "xxx".length()) {
      throw new IllegalArgumentException("'" + shortLanguageCode + "' isn't a two- or three-character code");
    }
    for (Language element : Language.LANGUAGES) {
      if (shortLanguageCode.equals(element.getShortName())) {
        return element;
      }
    }
    return null;
  }

  /**
   * Get the Language object for the given language name.
   * 
   * @param languageName e.g. <code>English</code> or <code>German</code> (case is significant)
   * @return a Language object or <code>null</code>
   */
  public static Language getLanguageForName(final String languageName) {
    for (Language element : Language.LANGUAGES) {
      if (languageName.equals(element.getName())) {
        return element;
      }
    }
    return null;
  }  
  
  @Override
  public final String toString() {
    return getName();
  }
  
  /**
   * Get sorted info about all maintainers.
   * @since 0.9.9
   * @param messages
   *        {{@link ResourceBundle} language bundle to translate
   *        the info
   * @return
   *        A sorted list of maintainers.
   */
  public static String getAllMaintainers(final ResourceBundle messages) {
    final StringBuilder maintainersInfo = new StringBuilder();
    final List<String> toSort = new ArrayList<String>();
    for (final Language lang : Language.LANGUAGES) {
      if (lang != Language.DEMO) {
        if (lang.getMaintainers() != null) {
          final List<String> names = new ArrayList<String>();
          for (Contributor contributor : lang.getMaintainers()) {
            names.add(contributor.getName());
          }
          toSort.add(messages.getString(lang.getShortName()) +
              ": " + StringTools.listToString(names, ", "));
        }
      }            
    }    
    Collections.sort(toSort);
    for (final String lElem : toSort) {    
      maintainersInfo.append(lElem);    
      maintainersInfo.append('\n');
    }
    return maintainersInfo.toString();
  }

  public boolean isExternal() {
    return false;
  }

}
