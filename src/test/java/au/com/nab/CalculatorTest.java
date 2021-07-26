package au.com.nab;

import au.com.nab.postfix.domain.Calculator;
import au.com.nab.postfix.exception.CalculatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

public class CalculatorTest {

    private Calculator calcApp;


    @BeforeEach
    public void setCalcApp() {
        calcApp = Calculator.getInstance();
        calcApp.clear(); // clear before each run - calc is singleton
    }

    @Test
    @DisplayName("addition calculation")
    public void testAdd() {
        calcApp.push(Optional.of(BigDecimal.valueOf(3.5)));
        calcApp.push(Optional.of(BigDecimal.valueOf(1.5)));
        assertEquals(2, calcApp.size());
        calcApp.add();
        assertEquals(1, calcApp.size());
    }

    @Test
    @DisplayName("multiplication calculation")
    public void testMult() {
        calcApp.push(Optional.of(BigDecimal.valueOf(3.5)));
        calcApp.push(Optional.of(BigDecimal.valueOf(1.5)));
        assertEquals(2, calcApp.size());
        calcApp.multiply();
        assertEquals(1, calcApp.size());
        assertEquals(BigDecimal.valueOf(5.25), calcApp.top());
    }

    @Test
    @DisplayName("pop")
    public void testPop() {
        calcApp.push(Optional.of(BigDecimal.valueOf(3.5)));
        assertEquals(BigDecimal.valueOf(3.5), calcApp.top());
    }

    @Test
    @DisplayName("negation calc ")
    public void testNegation() {
        calcApp.push(Optional.of(BigDecimal.valueOf(3.5)));
        calcApp.negate();
        assertEquals(BigDecimal.valueOf(-3.5), calcApp.top());
    }

    @Test
    @DisplayName("inverse calc ")
    public void testInverse() {
        calcApp.push(Optional.of(BigDecimal.valueOf(3.5)));
        calcApp.inverse();
        assertEquals(BigDecimal.valueOf(0.29), calcApp.top());
    }

    @Test
    @DisplayName("undo last addition operation")
    public void testUndoAddition() {
        calcApp.push(Optional.of(BigDecimal.valueOf(1.5)));
        calcApp.push(Optional.of(BigDecimal.valueOf(-3.5)));
        calcApp.add();
        assertEquals(1, calcApp.size());
        calcApp.undo();
        assertEquals(2, calcApp.size());
        assertEquals(BigDecimal.valueOf(-3.5), calcApp.top());
    }

    @Test
    @DisplayName("undo last pop operation")
    public void testUndoPop() {
        calcApp.push(Optional.of(BigDecimal.valueOf(-3.5)));
        calcApp.pop();
        calcApp.undo();
        assertEquals(1, calcApp.size());
    }

    @Test
    @DisplayName("exception expected")
    public void testPopEmtyStack() {
        Assertions.assertThrows(CalculatorException.class, () -> calcApp.pop());
    }

    @Test
    @DisplayName("a series of operations")
    public void testABunchofOperations() {

        calcApp.push(Optional.of(BigDecimal.valueOf(1.5)));
        calcApp.push(Optional.of(BigDecimal.valueOf(-3.5)));
        calcApp.push(Optional.of(BigDecimal.valueOf(-1.0)));
        calcApp.multiply();
        calcApp.add();
        calcApp.inverse();
        assertEquals(1, calcApp.size());
        assertEquals(BigDecimal.valueOf(0.20).setScale(2), calcApp.top());

        calcApp.push(Optional.of(BigDecimal.valueOf(1.5)));
        calcApp.push(Optional.of(BigDecimal.valueOf(-3.5)));
        calcApp.push(Optional.of(BigDecimal.valueOf(-8.0)));
        assertEquals(4, calcApp.size());

        calcApp.multiply();
        assertEquals(3, calcApp.size());
        assertEquals(BigDecimal.valueOf(28.00).setScale(2), calcApp.top().setScale(2));

        calcApp.undo();
        assertEquals(4, calcApp.size());
        assertEquals(BigDecimal.valueOf(-8.00).setScale(2), calcApp.top().setScale(2));

        calcApp.multiply();
        calcApp.multiply();

        assertEquals(2, calcApp.size());
        calcApp.undo();
        assertEquals(3, calcApp.size());
        assertEquals(BigDecimal.valueOf(28.00).setScale(2), calcApp.top().setScale(2));

        calcApp.clear();
        assertEquals(0, calcApp.size());

        calcApp.undo();
        assertEquals(3, calcApp.size());

        calcApp.negate();
        assertEquals(3, calcApp.size());
        assertEquals(BigDecimal.valueOf(-28.00).setScale(2), calcApp.top().setScale(2));

    }
}
