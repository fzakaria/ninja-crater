package filters;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExceptionFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(ExceptionFilter.class);


    @Override
    public Result filter(FilterChain chain, Context context) {

        try {
            return chain.next(context);
        }catch () {

        }

    }

}