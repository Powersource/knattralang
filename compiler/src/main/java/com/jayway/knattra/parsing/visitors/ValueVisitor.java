package com.jayway.knattra.parsing.visitors;

import com.jayway.knattra.antlr.KnattraBaseVisitor;
import com.jayway.knattra.antlr.KnattraParser;
import com.jayway.knattra.domain.Expression;
import com.jayway.knattra.domain.NumericValue;
import com.jayway.knattra.domain.StringValue;
import org.apache.commons.lang3.StringUtils;

public class ValueVisitor extends KnattraBaseVisitor<Expression> {
    @Override
    public Expression visitValue(KnattraParser.ValueContext ctx) {
        String text = ctx.getText();
        if (StringUtils.isNumeric(text))
            return new NumericValue(text);
        else return new StringValue(text);
    }
}
