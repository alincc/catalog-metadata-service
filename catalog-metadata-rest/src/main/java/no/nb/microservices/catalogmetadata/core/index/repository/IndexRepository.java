package no.nb.microservices.catalogmetadata.core.index.repository;

import no.nb.microservices.catalogsearchindex.NBSearchType;
import no.nb.microservices.catalogsearchindex.SearchResource;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("catalog-search-index-service")
public interface IndexRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/catalog/v1/search")
    SearchResource search(@RequestParam("q") String q,
                          @RequestParam("page") int pageNumber,
                          @RequestParam("size") int pageSize,
                          @RequestParam("searchType") NBSearchType searchType);

}
