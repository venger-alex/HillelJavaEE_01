package hillelee.reflection;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProblemSolverTest {
    @Test
    public void solvePuzzle() throws Exception {
        String res = new ProblemSolver().solve(new Puzzle());

        Assert.assertThat(res, Matchers.is("Correct answer"));
    }

    @Test
    public void solveDecryptor() throws Exception {
        String res = new ProblemSolver().solve(new MessageDecryptor());

        Assert.assertThat(res, Matchers.is("Correct answer"));
    }

}