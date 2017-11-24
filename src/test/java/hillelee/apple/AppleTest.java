package hillelee.apple;

import org.junit.Test;

public class AppleTest {
    @Test
    public void getColor() throws Exception {
        new Apple("RED", 100).getColor();
    }

}