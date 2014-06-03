package self.vpalepu.friends;

import java.util.ArrayList;

public class Content {
  private final ArrayList<String> text;

  public static Content init() {
    Content content = new Content();
    return content;
  }

    private Content() { 
      this.text = new ArrayList<String>();
    }

  public ArrayList<String> getText() {
    return this.text;
  }

  public int getLineCount() {
    return this.text.size();
  }

  public void append(String line) {
    if(line == null || line.trim().isEmpty()) return;
    this.text.add(line.trim());
  }

}
