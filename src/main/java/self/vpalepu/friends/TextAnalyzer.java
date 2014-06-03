package self.vpalepu.friends;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.common.base.Joiner;

import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class TextAnalyzer {
  
  public static void main(String[] args) {
    System.out.println(
        new TextAnalyzer("read some text written by\n Savitri in Kanpur.")
        .analyze());
  }
  
  private final String text;
  
  private TextAnalyzer(String text) {
    this.text = text;
  }
  
  /**
   * analyze some text in the text variable
   * creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing
   * create an empty Annotation just with the given text
   * run all Annotators on this text
   * these are all the sentences in this document
   * a CoreMap is essentially a Map that uses class objects as keys and has 
   * values with custom types
   *
   * traversing the words in the current sentence
   * a CoreLabel is a CoreMap with additional token-specific methods
   * 
   * this is the text of the token
   * this is the POS tag of the token
   * this is the NER label of the token
   * this is the parse tree of the current sentence
   * this is the Stanford dependency graph of the current sentence
   * @param text
   */
  public ArrayList<TextAnalysisResult> analyze() {
    Properties props = new Properties();
    props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

    Annotation document = new Annotation(this.text);

    pipeline.annotate(document);

    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
    
    ArrayList<AnnotatedWord> words = new ArrayList<>();
    
    ArrayList<TextAnalysisResult> results = new ArrayList<>();
    
    for(CoreMap sentence: sentences) {
      for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
        String word = token.get(TextAnnotation.class);
        String pos = token.get(PartOfSpeechAnnotation.class);
        String ne = token.get(NamedEntityTagAnnotation.class);  
        words.add(AnnotatedWord.create(word, pos, ne));
      }

      Tree tree = sentence.get(TreeAnnotation.class);

      SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
      results.add(TextAnalysisResult.create(words, tree, dependencies));
    }
    return results;
  }
  
  public ArrayList<String> summarize() {
    // TODO complete this method.
    return null;
  }
}
