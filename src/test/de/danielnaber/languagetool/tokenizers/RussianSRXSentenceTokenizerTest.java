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

package de.danielnaber.languagetool.tokenizers;

import junit.framework.TestCase;
import de.danielnaber.languagetool.TestTools;

/*
 * Russian SRX Sentence Tokenizer Test
 * $Id: RussianSRXSentenceTokenizerTest.java,v 1.1 2010-02-07 14:22:38 yakovru Exp $
 */


public class RussianSRXSentenceTokenizerTest extends TestCase {

  // accept \n as paragraph:
  private SentenceTokenizer stokenizer = new SRXSentenceTokenizer("ru");
  // accept only \n\n as paragraph:
  private SentenceTokenizer stokenizer2 = new SRXSentenceTokenizer("ru");
  
  
  public final void setUp() {
    stokenizer.setSingleLineBreaksMarksParagraph(true);  
    stokenizer2.setSingleLineBreaksMarksParagraph(false);  
  }

  public final void testTokenize() {
    // NOTE: sentences here need to end with a space character so they
    // have correct whitespace when appended:
    testSplit(new String[] { "Dies ist ein Satz." });
    testSplit(new String[] { "Dies ist ein Satz. ", "Noch einer." });
    testSplit(new String[] { "Ein Satz! ", "Noch einer." });
    testSplit(new String[] { "Ein Satz... ", "Noch einer." });
    testSplit(new String[] { "Unter http://www.test.de gibt es eine Website." });
    testSplit(new String[] { "Das Schreiben ist auf den 3.10. datiert." });
    testSplit(new String[] { "Das Schreiben ist auf den 31.1. datiert." });
    testSplit(new String[] { "Das Schreiben ist auf den 3.10.2000 datiert." });

    testSplit(new String[] { "Heute ist der 13.12.2004." });
    testSplit(new String[] { "Es geht am 24.09. los." });
    testSplit(new String[] { "Das in Punkt 3.9.1 genannte Verhalten." });

    testSplit(new String[] { "Das ist,, also ob es bla." });
    testSplit(new String[] { "Das ist es.. ", "So geht es weiter." });

    testSplit(new String[] { "Das hier ist ein(!) Satz." });
    testSplit(new String[] { "Das hier ist ein(!!) Satz." });
    testSplit(new String[] { "Das hier ist ein(?) Satz." });
    testSplit(new String[] { "Das hier ist ein(???) Satz." });
    testSplit(new String[] { "Das hier ist ein(???) Satz." });

    // TODO: derzeit unterscheiden wir nicht, ob nach dem Doppelpunkt ein
    // ganzer Satz kommt oder nicht:
    testSplit(new String[] { "Das war es: gar nichts." });
    testSplit(new String[] { "Das war es: Dies ist ein neuer Satz." });

    // incomplete sentences, need to work for on-thy-fly checking of texts:
    testSplit(new String[] { "Here's a" });
    testSplit(new String[] { "Here's a sentence. ", "And here's one that's not comp" });

    // Tests taken from LanguageTool's SentenceSplitterTest.py:
    testSplit(new String[] { "This is a sentence. " });
    testSplit(new String[] { "This is a sentence. ", "And this is another one." });
    testSplit(new String[] { "This is a sentence.", "Isn't it?", "Yes, it is." });
    testSplit(new String[] { "Don't split strings like U.S.A. either." });
    testSplit(new String[] { "Don't split strings like U. S. A. either." });
    testSplit(new String[] { "Don't split... ", "Well you know. ", "Here comes more text." });
    testSplit(new String[] { "Don't split... well you know. ", "Here comes more text." });
    testSplit(new String[] { "The \".\" should not be a delimiter in quotes." });
    testSplit(new String[] { "\"Here he comes!\" she said." });
    testSplit(new String[] { "\"Here he comes!\", she said." });
    testSplit(new String[] { "\"Here he comes.\" ", "But this is another sentence." });
    testSplit(new String[] { "\"Here he comes!\". ", "That's what he said." });
    testSplit(new String[] { "The sentence ends here. ", "(Another sentence.)" });
    // known to fail:
    // testSplit(new String[]{"He won't. ", "Really."});
    testSplit(new String[] { "He won't go. ", "Really." });
    testSplit(new String[] { "He won't say no.", "Not really." });
    testSplit(new String[] { "He won't say No.", "Not really." });
    testSplit(new String[] { "This is it: a test." });
    // one/two returns = paragraph = new sentence:
    TestTools.testSplit(new String[] { "He won't\n\n", "Really." }, stokenizer2);
    TestTools.testSplit(new String[] { "He won't\n", "Really." }, stokenizer);
    TestTools.testSplit(new String[] { "He won't\n\n", "Really." }, stokenizer2);
    TestTools.testSplit(new String[] { "He won't\nReally." }, stokenizer2);
    // Missing space after sentence end:
    testSplit(new String[] { "James is from the Ireland!", "He lives in Spain now." });
    // From the Russian abbreviation list:
    testSplit(new String[] { "Отток капитала из России составил 7 млрд. долларов, сообщил министр финансов Алексей Кудрин." });    
    testSplit(new String[] { "Журнал издаётся с 1967 г., пользуется большой популярностью в мире." });
    testSplit(new String[] { "С 2007 г. периодичность выхода газеты – 120 раз в год." });
    testSplit(new String[] { "Редакция журнала находится в здании по адресу: г. Москва, 110000, улица Мира, д. 1." });
    testSplit(new String[] { "Все эти вопросы заставляют нас искать ответы в нашей истории 60-80-х гг. прошлого столетия." });
    testSplit(new String[] { "Более 300 тыс. документов и справочников." });
    testSplit(new String[] { "Скидки до 50000 руб. на автомобили." });                
    testSplit(new String[] { "Изготовление визиток любыми тиражами (от 20 шт. до 10 тысяч) в минимальные сроки (от 20 минут)." });  
  }

  public final void testSplit(final String[] sentences) {
    TestTools.testSplit(sentences, stokenizer);
  }

}