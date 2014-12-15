package filters;

import static com.google.common.base.Throwables.*;
import static ninja.Results.*;
import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;
import ninja.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.NoResultException;

/***
 * This filter should be placed first for all Controllers to catch any common exceptions or even
 * unknown ones and transform them into InternalServerErrorException
 */
public class ExceptionFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(ExceptionFilter.class);

    @Override
    public Result filter(FilterChain chain, Context context) {
        try {
            return chain.next(context);
        }catch (NoResultException e) {
            return notFound().render(new NotFoundException(e));
        }catch (Throwable t) {
            logger.warn("Uncaught exception in filter", t);
            Throwable rootCause = getRootCause(t);
            return internalServerError().render(rootCause);
        }

    }

}