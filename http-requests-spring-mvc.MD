# **Handling HTTP Requests in Spring MVC**

In **Spring MVC**, we handle different HTTP methods (`GET`, `POST`, `PUT`, `DELETE`, `PATCH`, etc.) using `@RequestMapping` or shorthand annotations (`@GetMapping`, `@PostMapping`, etc.).

---

## **1️⃣ Using `@RequestMapping` (General Approach)**  

### **What is `@RequestMapping` and When to Use It?**
In Spring MVC, the `@RequestMapping` annotation is used to map HTTP requests to handler methods in controllers. It allows developers to define which method should handle a particular URL pattern and HTTP method (such as `GET`, `POST`, `PUT`, `DELETE`, etc.). You can apply `@RequestMapping` at both the **class level** and **method level**.

- **At the class level**, `@RequestMapping` specifies a common base URL for all handler methods in the controller. This is useful when grouping related endpoints under a common base path.

    ```java
    @Controller
    @RequestMapping("/api")
    public class MyController {
        // Handler methods...
    }
    ```

- **At the method level**, `@RequestMapping` is used to map specific HTTP methods (like `GET`, `POST`) and URL patterns to handler methods.

    ```java
    @Controller
    public class MyController {
        
        @RequestMapping("/hello", method = RequestMethod.GET)
        public String hello() {
            // Handler logic...
        }

        @RequestMapping("/save", method = RequestMethod.POST)
        public String saveData() {
            // Handler logic...
        }
    }
    ```

---

## **2️⃣ Using Shorthand Annotations (`@GetMapping`, `@PostMapping`, etc.)**

### **What Are Shorthand Annotations?**
Spring also provides **shorthand annotations** for more common HTTP methods to simplify the code. These include:

- `@GetMapping` for `GET` requests.
- `@PostMapping` for `POST` requests.
- `@PutMapping` for `PUT` requests.
- `@PatchMapping` for `PATCH` requests.
- `@DeleteMapping` for `DELETE` requests.

These shorthand annotations make your code cleaner and more readable.

```java
@Controller
@RequestMapping("/api")
public class ExampleController {

    @GetMapping("/test")
    @ResponseBody
    public String getMethod() {
        return "GET request received";
    }

    @PostMapping("/test")
    @ResponseBody
    public String postMethod() {
        return "POST request received";
    }

    @PutMapping("/test")
    @ResponseBody
    public String putMethod() {
        return "PUT request received";
    }

    @PatchMapping("/test")
    @ResponseBody
    public String patchMethod() {
        return "PATCH request received";
    }

    @DeleteMapping("/test")
    @ResponseBody
    public String deleteMethod() {
        return "DELETE request received";
    }
}
```

---

### **Key Differences Between `@RequestMapping` and Shorthand Annotations**
| HTTP Method | `@RequestMapping` | Shorthand Annotation | Purpose |
|-------------|-------------------|----------------------|---------|
| **GET**     | `@RequestMapping(value = "/test", method = RequestMethod.GET)` | `@GetMapping("/test")` | Retrieve data |
| **POST**    | `@RequestMapping(value = "/test", method = RequestMethod.POST)` | `@PostMapping("/test")` | Create a new resource |
| **PUT**     | `@RequestMapping(value = "/test", method = RequestMethod.PUT)` | `@PutMapping("/test")` | Replace entire resource |
| **PATCH**   | `@RequestMapping(value = "/test", method = RequestMethod.PATCH)` | `@PatchMapping("/test")` | Modify part of a resource |
| **DELETE**  | `@RequestMapping(value = "/test", method = RequestMethod.DELETE)` | `@DeleteMapping("/test")` | Remove a resource |

### **When to Use `@RequestMapping` vs Shorthand?**
- Use **shorthand annotations** like `@GetMapping`, `@PostMapping`, etc., for **simplicity and readability** when you are working with a single HTTP method.
- Use `@RequestMapping` when you need to handle multiple HTTP methods within the same method or when you need more complex configurations.

---

## **3️⃣ Using `@RequestMapping` with Parameters**

### **Request Parameters**
The `params` attribute in `@RequestMapping` is used to specify request parameters that must be present in the request for the handler method to be invoked. This is useful for more refined control over which requests should trigger a specific method.

```java
@RequestMapping(value = "/users", params = "id")
public String getUserById(@RequestParam("id") Long id) {
    // Handle request...
}
```
In the example above, the method will only be triggered when the request contains the parameter `id`. If the parameter is missing, the request will be ignored.

---

## **4️⃣ `@ResponseBody` Annotation**
The `@ResponseBody` annotation in Spring MVC indicates that the return value of a controller method should be serialized directly into the HTTP response body. This is useful when you're not returning a view (such as a JSP or HTML page) but instead returning data like JSON, XML, or plain text.

```java
@Controller
public class ExampleController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello, World!";
    }
}
```
### **Why Use `@ResponseBody`?**
- Use `@ResponseBody` when you want to return raw data (JSON, XML, etc.) as the response, rather than rendering a view.
- It's commonly used with **RESTful APIs** to send data back to the client.

---

## **5️⃣ PUT vs PATCH (Key Differences)**

| **Method** | **Purpose** | **Example Usage** |
|------------|-------------|-------------------|
| **PUT**    | **Full update** (Replaces the entire resource) | `PUT /users/1` with full JSON body |
| **PATCH**  | **Partial update** (Modifies only specific fields) | `PATCH /users/1` with only changed fields |

### **🔹 Example: Using PUT (Full Update)**
```java
@PutMapping("/user/{id}")
@ResponseBody
public String updateUser(@PathVariable Long id, @RequestBody User user) {
    return "Updated user " + id + " with name: " + user.getName();
}
```
**Request:**
```json
PUT http://localhost:8080/api/user/1
Content-Type: application/json
{
    "name": "John",
    "email": "john@example.com"
}
```
**Effect:**  
- **Replaces the entire user object** (even if only one field changes).

---

### **🔹 Example: Using PATCH (Partial Update)**
```java
@PatchMapping("/user/{id}")
@ResponseBody
public String patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
    return "Patched user " + id + " with updates: " + updates;
}
```
**Request:**
```json
PATCH http://localhost:8080/api/user/1
Content-Type: application/json
{
    "email": "new-email@example.com"
}
```
**Effect:**  
- **Only updates the `email` field**, leaving other fields unchanged.

---

## **Real World Application**

In real-world applications, URLs like `/login`, `/signup`, `/user`, `/admin`, and `/addProduct` are commonly mapped to controller methods using `@RequestMapping` or shorthand annotations. The `@ResponseBody` annotation ensures that the return value from these methods is serialized directly into the HTTP response body, providing the appropriate response type (like JSON or XML).

For example:
- `@RequestMapping("/login", method = RequestMethod.POST)` could handle a **login request**.
- `@RequestMapping("/user", method = RequestMethod.GET)` could retrieve **user details**.
- `@RequestMapping("/addProduct", method = RequestMethod.POST)` could create a **new product**.

### **Summary of HTTP Request Mappings and When to Use Each**
| HTTP Method | `@RequestMapping` | Shorthand Annotation | Purpose |
|-------------|-------------------|----------------------|---------|
| **GET**     | `@RequestMapping(value = "/test", method = RequestMethod.GET)` | `@GetMapping("/test")` | Retrieve data |
| **POST**    | `@RequestMapping(value = "/test", method = RequestMethod.POST)` | `@PostMapping("/test")` | Create new resource |
| **PUT**     | `@RequestMapping(value = "/test", method = RequestMethod.PUT)` | `@PutMapping("/test")` | Replace entire resource |
| **PATCH**   | `@RequestMapping(value = "/test", method = RequestMethod.PATCH)` | `@PatchMapping("/test")` | Modify part of a resource |
| **DELETE**  | `@RequestMapping(value = "/test", method = RequestMethod.DELETE)` | `@DeleteMapping("/test")` | Remove a resource |

---

### **Which One Should You Use?**
✅ Use **shorthand annotations** (`@GetMapping`, `@PostMapping`, etc.) for **readability and simplicity**.  
✅ Use `@RequestMapping` when you **handle multiple HTTP methods** in a single method or need to configure more complex conditions.