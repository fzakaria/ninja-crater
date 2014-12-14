package filters;

import static ninja.Result.APPLICATON_JSON;
import ninja.Filter;
import ninja.Context;
import ninja.FilterChain;
import ninja.Result;

public class JsonContentTypeFilter implements Filter {

    @Override
    public Result filter(FilterChain chain, Context context) {
        Result result = chain.next(context);
        return result.supportedContentTypes(APPLICATON_JSON)
                     .fallbackContentType(APPLICATON_JSON);
    }
}