package com.app.Controller;

import com.app.Model.Sample;
import org.springframework.web.bind.annotation.*;

/**
 * A REST controller that allows for the creation of endpoints following RESTful conventions, 
 * such as the descriptive use of HTTP verbs (GET, POST, PUT, PATCH, DELETE).
 * The @RestController annotation combines @Controller and @ResponseBody,
 * automatically converting return values into JSON response bodies.
 */
@RestController
public class SampleController {

    /**
     * Endpoint for GET localhost:9000/sample/.
     * 
     * Mappings such as @GetMapping, @PostMapping, @PutMapping, @PatchMapping,
     * and @DeleteMapping are available thanks to the @RestController annotation.
     * The return type is Sample, a model contained in the 'Model' package.
     * Spring MVC will automatically serialize the returned object into a JSON response body.
     * 
     * Example response for a GET request to localhost:9000/sample/:
     * 
     * {
     *     "id": 1,
     *     "text": "sample text"
     * }
     */
    @GetMapping("/sample/")
    public Sample getSample(){
        return new Sample(1L, "sample text");
    }

    /**
     * Endpoint for GET localhost:9000/string/{text}.
     * 
     * For instance, an HTTP request to GET localhost:9000/string/abc will parse "abc" as a path variable.
     * Path variables are defined with curly braces in the endpoint signature, and Spring will map incoming
     * HTTP requests to a matching pattern. A path variable may be extracted using the @PathVariable annotation 
     * used in the parameter, together with the type of variable it should be.
     * 
     * Example: a request to GET localhost:9000/string/hello will respond with "hello".
     */
    @GetMapping("/string/{text}")
    public String getStringPathVariable(@PathVariable String text){
        return text;
    }

    /**
     * Endpoint for GET localhost:9000/long/{id}.
     *
     * Extracts a path variable from the request and responds with it.
     * A path variable is defined in the endpoint signature and extracted using 
     * the @PathVariable annotation, with the correct data type specified.
     *
     * Example: a request to GET localhost:9000/long/1 will respond with "1".
     */
    @GetMapping("/long/{id}")
    public long getPathVariable(@PathVariable long id){
        return id;
    }

    /**
     * Endpoint for POST localhost:9000/requestbody. Extracts the request body and responds with it.
     *
     * Similarly to how path variables are extracted with an annotation, request bodies may be extracted
     * in a similar way using the @RequestBody annotation. In that case, Spring will deserialize a JSON
     * request body into a Java Object using Jackson Databind.
     *
     * For example:
     *
     * @PostMapping("endpoint")
     * public Type postType(@RequestBody Type pojo){
     *     // the request body is deserialized here as 'pojo'.
     * }
     *
     * A PostMapping is used here, as GET requests don't use request bodies by convention.
     *
     * In this case, an HTTP request to POST localhost:9000/requestbody with the request body:
     *
     * {
     *     id: 1,
     *     text: "sample text"
     * }
     *
     * should respond with this JSON response body:
     * 
     * {
     *     id: 1,
     *     text: "sample text"
     * }
     */
    @PostMapping(value = "/requestbody")
    public Sample postSample(@RequestBody Sample sample){
        return sample;
    }
}