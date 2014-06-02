package self.vpalepu.friends;

import java.util.ArrayList;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;

import static com.google.common.base.Preconditions.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.Tree;

public class TextAnalysisResult {
  private ArrayList<AnnotatedWord> words;
  private Tree parseTree;
  private SemanticGraph dependencyGraph;
  
  public static TextAnalysisResult create(ArrayList<AnnotatedWord> words,
                                          Tree parseTree,
                                          SemanticGraph graph) {
    return new TextAnalysisResult(words, parseTree, graph);
  }
  
  private TextAnalysisResult(ArrayList<AnnotatedWord> words,
                             Tree parseTree,
                             SemanticGraph graph) {
    this.words = checkNotNull(words);
    this.parseTree = checkNotNull(parseTree);
    this.dependencyGraph = checkNotNull(graph);
  }
  
  public Optional<AnnotatedWord> firstOccurance(String word) {
    for(AnnotatedWord w : this.words) {
      if(word.equals(w.word()))
        return Optional.of(w);
    }
    return Optional.absent();
  }
  
  public Optional<AnnotatedWord> lastOccurance(String word) {
    for(int i = this.words.size() - 1; i >= 0; i += 1) {
      AnnotatedWord w = this.words.get(i);
      if(word.equals(w.word()))
        return Optional.of(w);
    }
    return Optional.absent();
  }
  
  public ArrayList<AnnotatedWord> allOccurances(String word) {
    ArrayList<AnnotatedWord> allWords = new ArrayList<>();
    for(AnnotatedWord w : this.words) {
      if(word.equals(w.word()))
        allWords.add(w);
    }
    return allWords;
  }
  
  public Tree parseTree() {
    return parseTree;
  }
  
  public SemanticGraph dependencies() {
    return dependencyGraph;
  }

  @Override
  public String toString() {
    return Joiner.on('\n').join(
        Joiner.on("\n").join(words),
        parseTree.toString(),
        dependencyGraph.toDotFormat());
  }
}
