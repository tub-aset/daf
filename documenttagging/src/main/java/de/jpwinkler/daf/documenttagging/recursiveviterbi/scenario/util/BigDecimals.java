package de.jpwinkler.daf.documenttagging.recursiveviterbi.scenario.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimals {

    public static final MathContext CONTEXT = new MathContext(20, RoundingMode.HALF_UP);

    public static final BigDecimal ZERO = new BigDecimal(0, CONTEXT);

    public static final BigDecimal ONE = new BigDecimal(1, CONTEXT);
}
