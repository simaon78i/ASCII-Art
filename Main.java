import ascii_art.Shell;
import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new Shell().run();
//        char[] chars = new char[127 - 32];
//        for (int i = 32; i < 127; i++) {
//            chars[i - 32] = (char) i;
//        }
//
//       char[][] output = new ascii_art.AsciiArtAlgorithm(
//               new Image(".asset/cat.jpeg"),
//               256,
//               new image_char_matching.SubImgCharMatcher(chars)
//       ).run();
//        new HtmlAsciiOutput("cat.html", "New Courier").out(output);
    }
}
