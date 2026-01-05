# The Spring MVC
The Spring MVC (Model-View-Controller) is a core component of the Spring ecosystem, used for building RESTFUL API's
in Java - seperating concerns into model (data), view (presentation such as JSON), and controller (logic). 

A key Spring feature is the DispatcherServerlet. It handles all incoming requests and dispatches them to the appropriate
controller.

Spring comes packaged with numerous Annotations that simplify API configuration effectively. E.g. `@RestContoller`,
`@GetMapping`, `@Controller`, `@Service`

Spring also handles content negotiation naturally, determing the best response format (HTML, JSON, XML) based on client
`Accept` headers.

