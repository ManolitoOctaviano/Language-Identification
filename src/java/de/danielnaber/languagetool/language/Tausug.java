/* LanguageTool, a natural language style checker 
 * Copyright (C) 2011 Daniel Naber (http://www.danielnaber.de)
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

package de.danielnaber.languagetool.language;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import de.danielnaber.languagetool.Language;
import de.danielnaber.languagetool.rules.*;
import de.danielnaber.languagetool.tagging.Tagger;
import de.danielnaber.languagetool.tagging.tsg.TausugTagger;

/** 
 * @author Nathaniel Oco
 */
public class Tausug extends Language {

  private Tagger tagger;

  @Override
  public Locale getLocale() {
    return new Locale("tsg");
  }

  @Override
  public String getName() {
    return "Tausug";
  }

  @Override
  public String getShortName() {
    return "tsg";
  }

  @Override
  public String[] getCountryVariants() {
    return new String[] {"PH"};
  }
  
  @Override
  public Tagger getTagger() {
    if (tagger == null) {
      tagger = new TausugTagger();
    }
    return tagger;
  }

  @Override
  public Contributor[] getMaintainers() {
  	final Contributor contributor1 = new Contributor ("Nathaniel Oco");
  	contributor1.setUrl("http://www.dlsu.edu.ph/research/centers/adric/nlp/");
    return new Contributor[] { contributor1 };
  }

  @Override
  public List<Class<? extends Rule>> getRelevantRules() {
    return Arrays.asList(
            CommaWhitespaceRule.class,
            DoublePunctuationRule.class,
            GenericUnpairedBracketsRule.class,
            UppercaseSentenceStartRule.class,
            WhitespaceRule.class
    );
  }

}
