package Binomial_Expansion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KataSolution {

    public static void main(String[] args) {
        System.out.println(expand("(-12t+43)^2"));
    }

    /**
     * Function  takes in an expression with a single, one character variable, and expands it.
     * The expression is in the form (ax+b)^n where a and b are integers which may be positive or negative,
     * x is any single character variable, and n is a natural number. If a = 1, no coefficient will be placed
     * in front of the variable. If a = -1, a "-" will be placed in front of the variable.
     *
     * @param expr - input expression
     * @return expanded expression
     */
    public static String expand(String expr) {
        ExpressionParser parser = new ExpressionParser().parse(expr);
        String varName = parser.getVarName();
        int a = parser.getA();
        int b = parser.getB();
        int n = parser.getN();

        List<String> exprCollector = new ArrayList<>();
        if (n == 0) {
            exprCollector.add("1");
        } else if (n == 1) {
            exprCollector.add(buildTerm(varName, true, BigInteger.valueOf(a), 1));
            exprCollector.add(buildTerm(varName, false, BigInteger.valueOf(b), 0));
        } else {
            BigInteger[] pascalRow = buildPascalRow(n);

            for (int i = 0; i < pascalRow.length; i++) {
                BigInteger coef = pascalRow[i];
                int pow = n - i;
                BigInteger ai = BigInteger.valueOf(a).pow(pow);
                BigInteger bi = BigInteger.valueOf(b).pow(i);
                coef = coef.multiply(ai).multiply(bi);

                String term = buildTerm(varName, exprCollector.size() == 0, coef, pow);
                if (!term.isEmpty()) {
                    exprCollector.add(term);
                }
            }
        }

        return String.join("", exprCollector);
    }

    static class ExpressionParser {
        private String expr;
        private String varName;
        private int a = 1;
        private int b = 0;
        private int n = 1;

        // (ax+b)^n
        public ExpressionParser parse(String expr) {
            this.expr = expr;
            if (expr == null) {
                throw new IllegalArgumentException("Expression cannot be null.");
            }

            Pattern p = Pattern.compile("\\((?<a>[+-]?\\d*)?(?<x>[a-zA-Z])(?<b>[+|-]\\d+)?\\)\\^(?<n>[0-9]+)");
            Matcher m = p.matcher(expr);
            if (m.find()) {
                String aExpr = m.group("a");
                if (aExpr != null) {
                    this.a = parseCoefficient(aExpr);
                }
                String xExpr = m.group("x");
                if (xExpr != null) {
                    this.varName = xExpr;
                }
                String bExpr = m.group("b");
                if (bExpr != null) {
                    this.b = Integer.parseInt(bExpr);
                }
                String nExpr = m.group("n");
                if (nExpr != null) {
                    this.n = Integer.parseInt(nExpr);
                }
            } else {
                throw new IllegalArgumentException("Cannot parse expression : " + expr);
            }
            return this;
        }

        private int parseCoefficient(String aExpr) {
            if ("-".equals(aExpr)) {
                return -1;
            }
            if ("".equals(aExpr) || "+".equals(aExpr)) {
                return 1;
            }
            return Integer.parseInt(aExpr);
        }

        public String getExpr() {
            return expr;
        }

        public String getVarName() {
            return varName;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getN() {
            return n;
        }

    }

    private static String buildTerm(String varName, boolean isFirst, BigInteger coef, int pow) {
        if (BigInteger.ZERO.equals(coef)) {
            return "";
        }
        String term = "";
        if (!isFirst && coef.signum() == 1) {
            term += "+";
        } else if (coef.signum() == -1) {
            term += "-";
        }
        if (!BigInteger.ONE.equals(coef.abs()) || pow == 0) {
            term += coef.abs();
        }
        if (pow == 0) {
            return term;
        }
        if (pow == 1) {
            return term + varName;
        }
        return term + varName + "^" + pow;
    }

    static BigInteger[] buildPascalRow(int rowIndex) {
        if (rowIndex < 1) {
            throw new IndexOutOfBoundsException("Pascal triangle row index less than 1 : " + rowIndex);
        }
        int iterations = (rowIndex / 2) + 1;
        int size = rowIndex == 1 ? 1 : rowIndex + 1;

        BigInteger[] row = new BigInteger[size];
        int dividend = rowIndex;
        int divisor = 1;
        row[0] = row[size - 1] = BigInteger.valueOf(1);

        for (int i = 1; i < iterations; i++) {
            BigInteger val = row[i - 1].multiply(BigInteger.valueOf(dividend)).divide(BigInteger.valueOf(divisor));
            row[i] = row[size - i - 1] = val;
            dividend--;
            divisor++;
        }
        return row;
    }

}

