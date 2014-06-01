package self.vpalepu.friends;

public class Content {
    private final String text;
    private final int lineCount;

    public Content(String text, int lineCount) {
    this.text = text;
    this.lineCount = lineCount;
    }

    public String getText() {
    return this.text;
    }

    public int getLineCount() {
    return this.lineCount;
    }
    
    
}
