package caloriecounter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FoodTest {
    private static final int DEFAULT_TIMEOUT = 2000;

    private Food f;

    @Before
    public void setFood() {
        f = new Food(200);
    }

    @Test(timeout = DEFAULT_TIMEOUT )
    public void getCalories() throws Exception {
        assertEquals("getter retrieves calories",200,f.getCalories());
    }

    @Test(timeout = DEFAULT_TIMEOUT)
    public void setCalories() throws Exception {
        f.setCalories(800);
        assertEquals("setter alters calories",800,f.getCalories());
    }
}
