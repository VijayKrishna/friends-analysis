package self.vpalepu.friends;

import static com.google.common.base.Preconditions.*;

import com.google.common.base.Joiner;

public class AnnotatedWord {
  private final String word;
  private final String partOfSpeech;
  private final String namedEntityTag;
  
  public static AnnotatedWord create(String word, String pos, String net) {
    return new AnnotatedWord(checkNotNull(word), 
                             checkNotNull(pos), 
                             checkNotNull(net));
  }
  
    private AnnotatedWord(String word, String pos, String net) {
      this.word = word;
      this.partOfSpeech = pos;
      this.namedEntityTag = net;
    }
  
  public String word() {
    return word;
  }
  
  public String partOfSpeech() {
    return partOfSpeech;
  }
  
  public String namedEntityTag() {
    return namedEntityTag;
  }
  
  @Override
  public String toString() {
    return Joiner.on(" ").join(word, partOfSpeech, namedEntityTag);
  }
  
  @Override
  public boolean equals(Object x) {
    if(x == null)
      return false;
    if(!(x instanceof AnnotatedWord))
      return false;
    AnnotatedWord word = (AnnotatedWord) x;
    if(!this.word.equals(word.word)) {
      return false;
    }
    if(!this.partOfSpeech.equals(word.partOfSpeech)) {
      return false;
    }
    if(!this.namedEntityTag.equals(word.namedEntityTag)) {
      return false;
    }
    return true;
  }
  
  @Override
  public int hashCode() {
    int code = 17;
    code += 31 * word.hashCode();
    code += 31 * partOfSpeech.hashCode();
    code += 31 * namedEntityTag.hashCode();
    return code;
  }
}
