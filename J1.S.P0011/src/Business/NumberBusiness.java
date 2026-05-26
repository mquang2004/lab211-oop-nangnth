package Business;

import java.math.BigInteger;
import Model.Number;

public class NumberBusiness {

    private static final String DIGITS = "0123456789ABCDEF";

    public String convert(Number number, int outputBase) {
        if (outputBase != 2 && outputBase != 10 && outputBase != 16) {
            throw new IllegalArgumentException("Output base must be 2, 10, or 16.");
        }
        BigInteger decimalValue = toDecimal(number);
        return fromDecimal(decimalValue, outputBase);
    }

    private BigInteger toDecimal(Number number) {
        BigInteger result = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(number.getBase());

        for (char character : number.getValue().toUpperCase().toCharArray()) {
            int digit = DIGITS.indexOf(character);
            result = result.multiply(base).add(BigInteger.valueOf(digit));
        }
        return result;
    }

    private String fromDecimal(BigInteger decimalValue, int outputBase) {
        if (decimalValue.equals(BigInteger.ZERO)) {
            return "0";
        }

        BigInteger base = BigInteger.valueOf(outputBase);
        StringBuilder result = new StringBuilder();

        while (decimalValue.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divideAndRemainder = decimalValue.divideAndRemainder(base);
            result.append(DIGITS.charAt(divideAndRemainder[1].intValue()));
            decimalValue = divideAndRemainder[0];
        }

        return result.reverse().toString();
    }
}
